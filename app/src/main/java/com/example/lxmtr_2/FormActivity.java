package com.example.lxmtr_2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity  {

    Spinner clase_iluminacion, condicion_atmosferica;

    //Spinner L1
    Spinner orientacion_l1, fuente_l1, apoyo_l1, longitud_l1, tension_nominal_l1, tension_medida_l1, polucion_l1;
    //Spinner l2
    Spinner orientacion_l2, fuente_l2, apoyo_l2, longitud_l2, tension_nominal_l2, tension_medida_l2, polucion_l2;

    EditText tramo,direccion_L1, direccion_L2, barrio, luxometro, interdistancia, ancho;

    //edit text L1
    EditText potencia_bombilla_l1, avance_calzada_l1, distancia_l1_borde, altura_montaje_l1, angulo_inclinacion_l1;
    //edit text l2
    EditText potencia_bombilla_l2, avance_calzada_l2, distancia_l2_borde, altura_montaje_l2, angulo_inclinacion_l2;

    ArrayAdapter <String> adapter_ci, adapter_ca;

    //adapter L1
    ArrayAdapter<String> adapter_ol1, adapter_fl1, adapter_al1, adapter_ll1, adapter_tl1, adapter_tml1, adapter_pl1;
    //adapter l2
    ArrayAdapter<String> adapter_ol2, adapter_fl2, adapter_al2, adapter_ll2, adapter_tl2, adapter_tml2, adapter_pl2;

    Button btn;

    List<String> clases_iluminacion = new ArrayList<>();
    List<String> condiciones_atmosfericas = new ArrayList<>();

    //listas L1
    List<String> morientacion_l1 = new ArrayList<>();
    List<String> mfuente_l1 = new ArrayList<>();
    List<String> mapoyo_l1 = new ArrayList<>();
    List<String> mlongitud_l1 = new ArrayList<>();
    List<String> mtension_l1 = new ArrayList<>();
    List<String> mtensionmedida_l1 = new ArrayList<>();
    List<String> mpolucion_l1 = new ArrayList<>();

    //Listas L2
    List<String> morientacion_l2 = new ArrayList<>();
    List<String> mfuente_l2 = new ArrayList<>();
    List<String> mapoyo_l2 = new ArrayList<>();
    List<String> mlongitud_l2 = new ArrayList<>();
    List<String> mtension_l2 = new ArrayList<>();
    List<String> mtensionmedida_l2 = new ArrayList<>();
    List<String> mpolucion_l2 = new ArrayList<>();

    //String para siguiente activity (intent)

    String clase_elegida, tramo_enviar, direccion_L1_enviar, direccion_L2_enviar, barrio_enviar, luxometro_enviar, condicion_atmosferica_enviar;

    String orientacion_l1_enviar, fuente_l1_enviar, tipo_apoyo_l1_enviar, longitud_l1_enviar, avance_calzada_l1_enviar,
            distancia_l1_borde_enviar, altura_montaje_l1_enviar, angulo_l1_enviar, tension_nominal_l1_enviar,
            tension_medida_l1_enviar, polucion_l1_enviar;

    String orientacion_l2_enviar, fuente_l2_enviar, tipo_apoyo_l2_enviar, longitud_l2_enviar, avance_calzada_l2_enviar,
            distancia_l2_borde_enviar, altura_montaje_l2_enviar, angulo_l2_enviar, tension_nominal_l2_enviar,
            tension_medida_l2_enviar, polucion_l2_enviar;

    String interdistancia_enviar, ancho_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        btn=findViewById(R.id.continuar_location_activity);

        //clase de iluminacion
        clase_iluminacion= findViewById(R.id.clase_iluminacion_vehicular);
        clases_iluminacion.add("M1");
        clases_iluminacion.add("M2");
        clases_iluminacion.add("M3");
        clases_iluminacion.add("M4");
        clases_iluminacion.add("M5");
        adapter_ci = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,clases_iluminacion);
        clase_iluminacion.setAdapter(adapter_ci);

        clase_iluminacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item_clase_iluminacion = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //direccion del tramo
        tramo=findViewById(R.id.tramo);
        //direccion L1
        direccion_L1=findViewById(R.id.direccion_l1);
        //direccion L2
        direccion_L2=findViewById(R.id.direccion_l2);
        //barrio
        barrio=findViewById(R.id.barrio);
        //luxometro
        luxometro=findViewById(R.id.luxometro);
        //condiciones atmosfericas
        condicion_atmosferica= findViewById(R.id.condicion_atmosferica);
        condiciones_atmosfericas.add("NUBLADO");
        condiciones_atmosfericas.add("SEMINUBLADO");
        condiciones_atmosfericas.add("DESPEJADO");
        condiciones_atmosfericas.add("CON LUNA");
        condiciones_atmosfericas.add("SIN LUNA");
        adapter_ca = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,condiciones_atmosfericas);
        condicion_atmosferica.setAdapter(adapter_ca);
        condicion_atmosferica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //orientacion L1
        orientacion_l1= findViewById(R.id.orientacion_l1);
        morientacion_l1.add("HORIZONTAL");
        morientacion_l1.add("VERTICAL");
        adapter_ol1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,morientacion_l1);
        orientacion_l1.setAdapter(adapter_ol1);
        orientacion_l1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //potencia L1
        potencia_bombilla_l1=findViewById(R.id.potencia_bombilla_l1);
        //tipo fuente bombilla L1
        fuente_l1= findViewById(R.id.tipo_bombilla_l1);
        mfuente_l1.add("SODIO");
        mfuente_l1.add("LED");
        adapter_fl1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mfuente_l1);
        fuente_l1.setAdapter(adapter_fl1);
        fuente_l1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //tipo de apoyo L1
        apoyo_l1= findViewById(R.id.tipo_apoyo_l1);
        mapoyo_l1.add("CONCRETO");
        mapoyo_l1.add("MADERA");
        mapoyo_l1.add("METALICO");
        adapter_al1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mapoyo_l1);
        apoyo_l1.setAdapter(adapter_al1);
        apoyo_l1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //longitud L1
        longitud_l1= findViewById(R.id.longitud_poste_l1);
        mlongitud_l1.add("8");
        mlongitud_l1.add("9");
        mlongitud_l1.add("10");
        mlongitud_l1.add("11");
        mlongitud_l1.add("12");
        mlongitud_l1.add("14");
        adapter_ll1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mlongitud_l1);
        longitud_l1.setAdapter(adapter_ll1);
        longitud_l1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //avance de la luminaria L1 sobre la calzada
        avance_calzada_l1=findViewById(R.id.avance_sobre_calzada_l1);
        //distancia del poste L1 al borde de la calzada
        distancia_l1_borde=findViewById(R.id.distancia_l1_borde);
        //altua montaje luminaria L1
        altura_montaje_l1=findViewById(R.id.altura_montaje_l1);
        //angulo inclinacion L1
        angulo_inclinacion_l1=findViewById(R.id.angulo_l1);
        //tension nominal L1
        tension_nominal_l1= findViewById(R.id.tension_nominal_l1);
        mtension_l1.add("208");
        mtension_l1.add("220");
        mtension_l1.add("240");
        adapter_tl1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mtension_l1);
        tension_nominal_l1.setAdapter(adapter_tl1);
        tension_nominal_l1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //tension medida en la red L1
        tension_medida_l1= findViewById(R.id.tension_red_l1);
        mtensionmedida_l1.add("208");
        mtensionmedida_l1.add("220");
        mtensionmedida_l1.add("240");
        adapter_tml1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mtensionmedida_l1);
        tension_medida_l1.setAdapter(adapter_tml1);
        tension_medida_l1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //polucion L1
        polucion_l1= findViewById(R.id.polucion_l1);
        mpolucion_l1.add("LIMPIO");
        mpolucion_l1.add("SUCIO");
        mpolucion_l1.add("SEMI_LIMPIO");
        adapter_pl1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mpolucion_l1);
        polucion_l1.setAdapter(adapter_pl1);
        polucion_l1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //orientacion l2
        orientacion_l2= findViewById(R.id.orientacion_l2);
        morientacion_l2.add("HORIZONTAL");
        morientacion_l2.add("VERTICAL");
        adapter_ol2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,morientacion_l2);
        orientacion_l2.setAdapter(adapter_ol2);
        orientacion_l2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //potencia l2
        potencia_bombilla_l2=findViewById(R.id.potencia_bombilla_l2);
        //tipo fuente bombilla l2
        fuente_l2= findViewById(R.id.tipo_bombilla_l2);
        mfuente_l2.add("SODIO");
        mfuente_l2.add("LED");
        adapter_fl2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mfuente_l2);
        fuente_l2.setAdapter(adapter_fl2);
        fuente_l2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //tipo de apoyo l2
        apoyo_l2= findViewById(R.id.tipo_apoyo_l2);
        mapoyo_l2.add("CONCRETO");
        mapoyo_l2.add("MADERA");
        mapoyo_l2.add("METALICO");
        adapter_al2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mapoyo_l2);
        apoyo_l2.setAdapter(adapter_al2);
        apoyo_l2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //longitud l2
        longitud_l2= findViewById(R.id.longitud_poste_l2);
        mlongitud_l2.add("8");
        mlongitud_l2.add("9");
        mlongitud_l2.add("10");
        mlongitud_l2.add("11");
        mlongitud_l2.add("12");
        mlongitud_l2.add("14");
        adapter_ll2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mlongitud_l2);
        longitud_l2.setAdapter(adapter_ll2);
        longitud_l2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //avance de la luminaria l2 sobre la calzada
        avance_calzada_l2=findViewById(R.id.avance_sobre_calzada_l2);
        //distancia del poste l2 al borde de la calzada
        distancia_l2_borde=findViewById(R.id.distancia_l2_borde);
        //altua montaje luminaria l2
        altura_montaje_l2=findViewById(R.id.altura_montaje_l2);
        //angulo inclinacion l2
        angulo_inclinacion_l2=findViewById(R.id.angulo_l2);
        //tension nominal l2
        tension_nominal_l2= findViewById(R.id.tension_nominal_l2);
        mtension_l2.add("208");
        mtension_l2.add("220");
        mtension_l2.add("240");
        adapter_tl2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mtension_l2);
        tension_nominal_l2.setAdapter(adapter_tl2);
        tension_nominal_l2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //tension medida en la red l2
        tension_medida_l2= findViewById(R.id.tension_red_l2);
        mtensionmedida_l2.add("208");
        mtensionmedida_l2.add("220");
        mtensionmedida_l2.add("240");
        adapter_tml2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mtensionmedida_l2);
        tension_medida_l2.setAdapter(adapter_tml2);
        tension_medida_l2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //polucion l2
        polucion_l2= findViewById(R.id.polucion_l2);
        mpolucion_l2.add("LIMPIO");
        mpolucion_l2.add("SUCIO");
        mpolucion_l2.add("SEMI_LIMPIO");
        adapter_pl2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mpolucion_l2);
        polucion_l2.setAdapter(adapter_pl2);
        polucion_l2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Interdistancia
        interdistancia=findViewById(R.id.interdistancia);
        //ancho de la calzada
        ancho=findViewById(R.id.ancho);

    }

    public void next_activity_location(View view) {


        clase_elegida=clase_iluminacion.getSelectedItem().toString();
        tramo_enviar=tramo.getText().toString();
        direccion_L1_enviar=direccion_L1.getText().toString();
        direccion_L2_enviar=direccion_L2.getText().toString();
        barrio_enviar=barrio.getText().toString();
        luxometro_enviar=luxometro.getText().toString();
        condicion_atmosferica_enviar=condicion_atmosferica.getSelectedItem().toString();

        orientacion_l1_enviar=orientacion_l1.getSelectedItem().toString();

        fuente_l1_enviar=fuente_l1.getSelectedItem().toString();
        tipo_apoyo_l1_enviar=apoyo_l1.getSelectedItem().toString();
        longitud_l1_enviar=longitud_l1.getSelectedItem().toString();
        avance_calzada_l1_enviar=avance_calzada_l1.getText().toString();
        distancia_l1_borde_enviar=distancia_l1_borde.getText().toString();
        altura_montaje_l1_enviar=altura_montaje_l1.getText().toString();
        angulo_l1_enviar=angulo_inclinacion_l1.getText().toString();
        tension_nominal_l1_enviar=tension_nominal_l1.getSelectedItem().toString();
        tension_medida_l1_enviar=tension_medida_l1.getSelectedItem().toString();
        polucion_l1_enviar=polucion_l1.getSelectedItem().toString();

        orientacion_l2_enviar=orientacion_l2.getSelectedItem().toString();
        fuente_l2_enviar=fuente_l2.getSelectedItem().toString();
        tipo_apoyo_l2_enviar=apoyo_l2.getSelectedItem().toString();
        longitud_l2_enviar=longitud_l2.getSelectedItem().toString();
        avance_calzada_l2_enviar=avance_calzada_l2.getText().toString();
        distancia_l2_borde_enviar=distancia_l2_borde.getText().toString();
        altura_montaje_l2_enviar=altura_montaje_l2.getText().toString();
        angulo_l2_enviar=angulo_inclinacion_l2.getText().toString();
        tension_nominal_l2_enviar=tension_nominal_l2.getSelectedItem().toString();
        tension_medida_l2_enviar=tension_medida_l2.getSelectedItem().toString();
        polucion_l2_enviar=polucion_l2.getSelectedItem().toString();

        interdistancia_enviar=interdistancia.getText().toString();
        ancho_enviar=ancho.getText().toString();

        Intent intent= new Intent(FormActivity.this,MapsActivity.class);


        intent.putExtra("clase_de_iluminacion",clase_elegida);
        intent.putExtra("tramo",tramo_enviar);
        intent.putExtra("direccion_l1",direccion_L1_enviar);
        intent.putExtra("direccion_l2",direccion_L2_enviar);
        intent.putExtra("barrio",barrio_enviar);
        intent.putExtra("referencia_luxometro",luxometro_enviar);
        intent.putExtra("condicion_atmosferica",condicion_atmosferica_enviar);

        intent.putExtra("orientacion_l1", orientacion_l1_enviar);
        intent.putExtra("fuente_l1",fuente_l1_enviar);
        intent.putExtra("apoyo_l1",tipo_apoyo_l1_enviar);
        intent.putExtra("longitd_l1",longitud_l1_enviar);
        intent.putExtra("avance_calzada_l1",avance_calzada_l1_enviar);
        intent.putExtra("distancia_l1_borde",distancia_l1_borde_enviar);
        intent.putExtra("altura_montaje_l1", altura_montaje_l1_enviar);
        intent.putExtra("angulo_inclinacion_l1", angulo_l1_enviar);
        intent.putExtra("tension_nominal_l1", tension_nominal_l1_enviar);
        intent.putExtra("tension_medida_l1",tension_medida_l1_enviar);
        intent.putExtra("polucion_l1", polucion_l1_enviar);

        intent.putExtra("orientacion_l2", orientacion_l2_enviar);
        intent.putExtra("fuente_l2",fuente_l2_enviar);
        intent.putExtra("apoyo_l2",tipo_apoyo_l2_enviar);
        intent.putExtra("longitd_l2",longitud_l2_enviar);
        intent.putExtra("avance_calzada_l2",avance_calzada_l2_enviar);
        intent.putExtra("distancia_l2_borde",distancia_l2_borde_enviar);
        intent.putExtra("altura_montaje_l2", altura_montaje_l2_enviar);
        intent.putExtra("angulo_inclinacion_l2", angulo_l2_enviar);
        intent.putExtra("tension_nominal_l2", tension_nominal_l2_enviar);
        intent.putExtra("tension_medida_l2",tension_medida_l2_enviar);
        intent.putExtra("polucion_l2", polucion_l2_enviar);

        intent.putExtra("interdistancia", interdistancia_enviar);
        intent.putExtra("ancho_calzada", ancho_enviar);
        startActivity(intent);





    }
}