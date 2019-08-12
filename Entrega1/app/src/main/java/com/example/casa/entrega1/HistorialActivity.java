package com.example.casa.entrega1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import android.view.View;
import android.widget.TextView;

public class HistorialActivity extends AppCompatActivity {

    String resultadosString;
    JSONArray resultados;
    String partida;
    int ganador;
    TextView historial,victoria,derrota;
    String historialCompleto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        historial = (TextView) findViewById(R.id.textoPartidas);
        victoria = (TextView) findViewById(R.id.h_resultadoVictoria);
        derrota = (TextView) findViewById(R.id.h_resultadoDerrota);

        victoria.setVisibility(View.GONE);
        derrota.setVisibility(View.GONE);

        Bundle datos = getIntent().getExtras(); //Recuperación de los datos mandados por el intent del MainActivity.
        if (datos != null){
            partida = datos.getString("Partida");
            ganador = datos.getInt("BarraRes");
        }

        SharedPreferences preference = getSharedPreferences("historial", MODE_PRIVATE); //Creación de la BD.
        resultadosString = preference.getString("resultados","[]");

        try {
            resultados = new JSONArray(resultadosString);
            resultados.put(partida);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <= resultados.length(); i++){ //Análisis del array.
            try{
                String partida = resultados.getString(i);
                if (resultados.length() > 10){
                    resultados.remove(0);
                }
                historialCompleto = historialCompleto + partida+ "\n" ;
            } catch(JSONException e){
                e.printStackTrace();
            }
        }

        SharedPreferences.Editor edit = preference.edit(); //Edición del array para que muestre los 10 primeros resultados.
        resultadosString = resultados.toString();
        if (resultados.length() > 10){
            resultados.remove(0);
        }
        edit.putString("resultados",resultadosString);
        edit.apply();

        historial.setText(historialCompleto);
        resultado(ganador);
    }

    public void resultado (int e){ //Seteo de la barra de victoria/derrota según corresponda.
        if (e == 1){
            victoria.setVisibility(View.VISIBLE);
        }
        if (e == 2){
            derrota.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) { //onSave y onRestore para el combio de vista.
        outState.putInt("ganador", ganador);
        outState.putString("hisCompleto",historialCompleto);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        ganador = savedInstanceState.getInt("ganador");
        resultado(ganador);
        historialCompleto = savedInstanceState.getString("hisCompleto");
        historial.setText(historialCompleto);
        super.onRestoreInstanceState(savedInstanceState);
    }
}