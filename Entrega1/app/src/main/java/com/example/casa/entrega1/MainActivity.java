package com.example.casa.entrega1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Variables de manejo en onCreate, onSaved y onRestored
    ImageButton ib_piedra, ib_papel, ib_tijera, ib_reinicio;
    ImageView i_player, i_com, i_res;
    TextView r_player, r_com, contador, victoria, derrota;
    Button b_historial;
    String mostrar;

    int res_p, res_com, cantPartidas = 0;
    int num_p, guardarEP, guardarECOM, guardarER, estado_boton_h, estado_victoria, estado_derrota, estado_boton_piedra, estado_boton_papel, estado_boton_tijera, ganador; //Variables para el onRestore

    private int[] imgE = {R.drawable.piedra, R.drawable.papel, R.drawable.tijera};// imgE[0]: piedra, imgE[1]:papel, imgE[2]:tijera
    private int[] imgR = {R.drawable.gana, R.drawable.empate, R.drawable.pierde};// imgR[0]:gana imgR[1]:empate imgR[2]:pierde
    private int[] estadoBH = {View.VISIBLE, View.GONE, View.INVISIBLE}; //Array de estados de visibilidad.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ib_piedra = (ImageButton) findViewById(R.id.ib_piedra);
        ib_papel = (ImageButton) findViewById(R.id.ib_papel);
        ib_tijera = (ImageButton) findViewById(R.id.ib_tijera);
        ib_reinicio = (ImageButton) findViewById(R.id.ib_reinicio);

        r_player = (TextView) findViewById(R.id.r_player);
        r_com = (TextView) findViewById(R.id.r_com);
        contador = (TextView) findViewById(R.id.cero);

        i_player = (ImageView) findViewById(R.id.i_player);
        i_com = (ImageView) findViewById(R.id.i_com);
        i_res = (ImageView) findViewById(R.id.i_res);

        b_historial = (Button) findViewById(R.id.b_historial);
        b_historial.setVisibility(View.INVISIBLE);
        estado_boton_h = b_historial.getVisibility();

        victoria = (TextView) findViewById(R.id.resultadoVictoria); //Falta hacer onRestore y onSaved.
        victoria.setVisibility(View.GONE);
        estado_victoria = victoria.getVisibility();

        derrota = (TextView) findViewById(R.id.resultadoDerrota); //Falta hacer onRestore y onSaved.
        derrota.setVisibility(View.GONE);
        estado_derrota = derrota.getVisibility();

        ib_piedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Seteo del listener del boton piedra.
                if (res_p < 2 && res_com < 2)
                    i_player.setImageResource(imgE[0]);
                guardarEP = 0;
                num_p = 0;
                turno(num_p); //Función que procesa el resultado del juego.

                if (res_p == 2){ //Vanish and show de los botones
                    b_historial.setVisibility(View.VISIBLE);
                    estado_boton_h = b_historial.getVisibility();
                    victoria.setVisibility(View.VISIBLE);
                    estado_victoria = victoria.getVisibility();
                    ib_piedra.setVisibility(View.GONE);
                    estado_boton_piedra = ib_piedra.getVisibility();
                    ib_papel.setVisibility(View.GONE);
                    estado_boton_papel = ib_papel.getVisibility();
                    ib_tijera.setVisibility(View.GONE);
                    estado_boton_tijera = ib_tijera.getVisibility();
                }

                if (res_com == 2){ //Vanish and show de los botones
                    b_historial.setVisibility(View.VISIBLE);
                    estado_boton_h = b_historial.getVisibility();
                    derrota.setVisibility(View.VISIBLE);
                    estado_derrota = derrota.getVisibility();
                    ib_piedra.setVisibility(View.GONE);
                    estado_boton_piedra = ib_piedra.getVisibility();
                    ib_papel.setVisibility(View.GONE);
                    estado_boton_papel = ib_papel.getVisibility();
                    ib_tijera.setVisibility(View.GONE);
                    estado_boton_tijera = ib_tijera.getVisibility();
                }
                r_player.setText(Integer.toString(res_p)); //Muestra el contador de los jugadores
                r_com.setText(Integer.toString(res_com));
            }
        });

        ib_papel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Seteo del listener del boton papel.
                if (res_p < 2 && res_com < 2)
                    i_player.setImageResource(imgE[1]);
                guardarEP = 1;
                num_p = 1;
                turno(num_p); //Función que procesa el resultado del juego.

                if (res_p == 2){ //Vanish and show de los botones
                    b_historial.setVisibility(View.VISIBLE);
                    estado_boton_h = b_historial.getVisibility();
                    victoria.setVisibility(View.VISIBLE);
                    estado_victoria = victoria.getVisibility();
                    ib_piedra.setVisibility(View.GONE);
                    estado_boton_piedra = ib_piedra.getVisibility();
                    ib_papel.setVisibility(View.GONE);
                    estado_boton_papel = ib_papel.getVisibility();
                    ib_tijera.setVisibility(View.GONE);
                    estado_boton_tijera = ib_tijera.getVisibility();
                }

                if (res_com == 2){ //Vanish and show de los botones
                    b_historial.setVisibility(View.VISIBLE);
                    estado_boton_h = b_historial.getVisibility();
                    derrota.setVisibility(View.VISIBLE);
                    estado_derrota = derrota.getVisibility();
                    ib_piedra.setVisibility(View.GONE);
                    estado_boton_piedra = ib_piedra.getVisibility();
                    ib_papel.setVisibility(View.GONE);
                    estado_boton_papel = ib_papel.getVisibility();
                    ib_tijera.setVisibility(View.GONE);
                    estado_boton_tijera = ib_tijera.getVisibility();
                }
                r_player.setText(Integer.toString(res_p));
                r_com.setText(Integer.toString(res_com));
            }
        });

        ib_tijera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Seteo del listener del boton tijera.
                if (res_p < 2 && res_com < 2)
                    i_player.setImageResource(imgE[2]);
                guardarEP = 2;
                num_p = 2;
                turno(num_p); //Función que procesa el resultado del juego.

                if (res_p == 2){ //Vanish and show de los botones
                    b_historial.setVisibility(View.VISIBLE);
                    estado_boton_h = b_historial.getVisibility();
                    victoria.setVisibility(View.VISIBLE);
                    estado_victoria = victoria.getVisibility();
                    ib_piedra.setVisibility(View.GONE);
                    estado_boton_piedra = ib_piedra.getVisibility();
                    ib_papel.setVisibility(View.GONE);
                    estado_boton_papel = ib_papel.getVisibility();
                    ib_tijera.setVisibility(View.GONE);
                    estado_boton_tijera = ib_tijera.getVisibility();
                }

                if (res_com == 2){ //Vanish and show de los botones
                    b_historial.setVisibility(View.VISIBLE);
                    estado_boton_h = b_historial.getVisibility();
                    derrota.setVisibility(View.VISIBLE);
                    estado_derrota = derrota.getVisibility();
                    ib_piedra.setVisibility(View.GONE);
                    estado_boton_piedra = ib_piedra.getVisibility();
                    ib_papel.setVisibility(View.GONE);
                    estado_boton_papel = ib_papel.getVisibility();
                    ib_tijera.setVisibility(View.GONE);
                    estado_boton_tijera = ib_tijera.getVisibility();
                }
                r_player.setText(Integer.toString(res_p));
                r_com.setText(Integer.toString(res_com));
            }
        });

        ib_reinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Reinicio del juego
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

    }

    public void turno (int eleccion_p) { //Función que procesa el resultado del juego, invocada en los botones.
        Random r = new Random();
        int eleccion_c = r.nextInt(2); //La com elige un numero aleatorio y luego se concatena un string a una variable...
                                        //segun el resultado del random y se compara mas abajo.
        i_com.setImageResource(imgE[eleccion_c]);
        guardarECOM = eleccion_c;
                                        // Todas las comparaciones se hacen desde el POV del jugador
        if(res_p == 2||res_com == 2){
            b_historial.setVisibility(View.VISIBLE);
            estado_boton_h = b_historial.getVisibility();
            ib_piedra.setVisibility(View.GONE);
            estado_boton_piedra = ib_piedra.getVisibility();
            ib_papel.setVisibility(View.GONE);
            estado_boton_papel = ib_papel.getVisibility();
            ib_tijera.setVisibility(View.GONE);
            estado_boton_tijera = ib_tijera.getVisibility();
        }
        else {
            if (eleccion_c == eleccion_p) { //misma eleccion
                i_res.setImageResource(imgR[1]);
                guardarER = 1;
            } else if (eleccion_p == 0 && eleccion_c == 2) { //piedra vs tijera
                res_p++;
                i_res.setImageResource(imgR[0]);
                guardarER = 0;
            } else if (eleccion_p == 0 && eleccion_c == 1) { //piedra vs papel
                res_com++;
                i_res.setImageResource(imgR[2]);
                guardarER = 2;
            } else if (eleccion_p == 1 && eleccion_c == 2) { //papel vs tijera
                res_com++;
                i_res.setImageResource(imgR[2]);
                guardarER = 2;
            } else if (eleccion_p == 2 && eleccion_c == 0) { //tijera vs piedra
                res_com++;
                i_res.setImageResource(imgR[2]);
                guardarER = 2;
            } else if (eleccion_p == 2 && eleccion_c == 1) { //tijera vs papel
                res_p++;
                i_res.setImageResource(imgR[0]);
                guardarER = 0;
            } else if (eleccion_p == 1 && eleccion_c == 0) { //papel vs piedra
                res_p++;
                i_res.setImageResource(imgR[0]);
                guardarER = 0;
            }
            cantPartidas++;
            contador.setText(Integer.toString(cantPartidas));
        }
    }

    public void alHistorial(View v){ //Envio de datos a la activity del historial.
        Intent i = new Intent(this,HistorialActivity.class);
        if (res_p == 2){
            mostrar = "Ganaste en la partida "+cantPartidas;
            ganador = 1;
        }
        if (res_com == 2){
            mostrar = "Perdiste en la partida "+cantPartidas;
            ganador = 2;
        }
        i.putExtra("Partida", mostrar);
        i.putExtra("BarraRes", ganador);
        startActivity(i);
    }

    public int estadoBotonH(int e){ //Arreglo para el manejo del onSaved y onRestored del botón Ver Historial y las barras vic/der.
        int i,j = 0;
        for (i=0; i<estadoBH.length; i++){
            if (e == estadoBH[i]){
                j = i;
            }
        }
        return j;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("resultadoP", res_p);
        outState.putInt("resultadoCOM", res_com);
        outState.putInt("imagenP", guardarEP);
        outState.putInt("imagenCOM", guardarECOM);
        outState.putInt("imagenR", guardarER);
        outState.putInt("contador", cantPartidas);
        outState.putInt("estado_boton_h", estado_boton_h);
        outState.putInt("estado_victoria", estado_victoria);
        outState.putInt("estado_derrota", estado_derrota);
        outState.putInt("estado_boton_piedra", estado_boton_piedra);
        outState.putInt("estado_boton_papel", estado_boton_papel);
        outState.putInt("estado_boton_tijera", estado_boton_tijera);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        res_p = savedInstanceState.getInt("resultadoP");
        r_player.setText(String.valueOf(res_p));
        res_com = savedInstanceState.getInt("resultadoCOM");
        r_com.setText(String.valueOf(res_com));
        guardarEP = savedInstanceState.getInt("imagenP");
        i_player.setImageResource(imgE[guardarEP]);
        guardarECOM = savedInstanceState.getInt("imagenCOM");
        i_com.setImageResource(imgE[guardarECOM]);
        guardarER = savedInstanceState.getInt("imagenP");
        i_res.setImageResource(imgR[guardarER]);
        cantPartidas = savedInstanceState.getInt("contador");
        contador.setText(String.valueOf(cantPartidas));
        estado_boton_h = savedInstanceState.getInt("estado_boton_h");
        b_historial.setVisibility(estadoBH[estadoBotonH(estado_boton_h)]);
        estado_victoria = savedInstanceState.getInt("estado_victoria");
        victoria.setVisibility(estadoBH[estadoBotonH(estado_victoria)]);
        estado_derrota = savedInstanceState.getInt("estado_derrota");
        derrota.setVisibility(estadoBH[estadoBotonH(estado_derrota)]);
        estado_boton_piedra = savedInstanceState.getInt("estado_boton_piedra");
        ib_piedra.setVisibility(estadoBH[estadoBotonH(estado_boton_piedra)]);
        estado_boton_papel = savedInstanceState.getInt("estado_boton_papel");
        ib_papel.setVisibility(estadoBH[estadoBotonH(estado_boton_papel)]);
        estado_boton_tijera = savedInstanceState.getInt("estado_boton_tijera");
        ib_tijera.setVisibility(estadoBH[estadoBotonH(estado_boton_tijera)]);
        super.onRestoreInstanceState(savedInstanceState);
    }
}