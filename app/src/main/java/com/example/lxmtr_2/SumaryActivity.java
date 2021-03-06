package com.example.lxmtr_2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SumaryActivity extends AppCompatActivity {

    String url= "https://streetlightdata.000webhostapp.com/write_data.php";

    //Responsable
    String responsable;

    //String informacion
    String clase_de_iluminacion, tramo, direccion_l1, direccion_l2, barrio, referencia_luxometro, condicion_atmosferica,
            interdistancia, ancho, separador, numero_separadores;
    //String L1
    String orientacion_l1,potencia_l1, fuente_l1, apoyo_l1, longitud_l1, avance_calzada_l1, distancia_l1_borde,
            altura_montaje_l1, angulo_inclinacion_l1, tension_nominal_l1, tension_medida_l1, polucion_l1;
    //String L2
    String orientacion_l2,potencia_l2, fuente_l2, apoyo_l2, longitud_l2, avance_calzada_l2, distancia_l2_borde,
            altura_montaje_l2, angulo_inclinacion_l2, tension_nominal_l2, tension_medida_l2, polucion_l2;

    //Coordenadas
    String latitud, longitud;

    //Nueve puntos
    String nueve_uno, nueve_dos, nueve_tres, nueve_cuatro,nueve_cinco,nueve_seis,nueve_siete,nueve_ocho,nueve_nueve;

    //Resumen de nueve puntos
    String ILUMINANCIA_PROMEDIO_CALZADA ,VALOR_MINIMO_CALZADA, VALOR_MAXIMO_CALZADA, UNIFORMIDAD_GENERAL_CALZADA,
            VALOR_MIN_MAX_CALZADA, VALOR_PROMEDIO_MAX_CALZADA, Estado, CUMPLIMIENTO;

    //Diez adyacentes
    String ady_uno, ady_dos, ady_tres, ady_cuatro,ady_cinco,ady_seis,ady_siete,ady_ocho,ady_nueve, ady_diez;

    //Resumen anden adyacente
    String ILUMINANCIA_PROMEDIO_ANDEN_ADYACENTE,VALOR_MINIMO_ANDEN_ADYACENTE,
            VALOR_MAXIMO_ANDEN_ADYACENTE, UNIFORMIDAD_GENERAL_ANDEN_ADYACENTE,
            VALOR_MIN_MAX_ANDEN_ADYACENTE,VALOR_PROMEDIO_MAX_ANDEN_ADYACENTE;

    //Diez opacentes
    String op_uno, op_dos, op_tres, op_cuatro,op_cinco,op_seis,op_siete,op_ocho,op_nueve, op_diez;

    //Resumen anden opuesto
    String ILUMINANCIA_PROMEDIO_ANDEN_OPUESTO,VALOR_MINIMO_ANDEN_OPUESTO,
            VALOR_MAXIMO_ANDEN_OPUESTO, UNIFORMIDAD_GENERAL_ANDEN_OPUESTO,
            VALOR_MIN_MAX_ANDEN_OPUESTO,VALOR_PROMEDIO_MAX_ANDEN_OPUESTO;

    //String Sumary
    String ilum_prom, ilum_min, ilum_max, unif, cumplimiento, obs;

    //String Holders
    String RESPONSABLE_Holder, date_Holder, CLASE_DE_ILIMUNACION_VEHICULAR_Holder,
            direccion_Holder, BARRIO_Holder, LUXOMETRO_Referencia_Holder, CONDICIONES_ATMOSFERICAS_DE_LA_NOCHE_Holder,
            DIRECCION_L1_Holder, DIRECCION_L2_Holder, LUMINARIA_L1_Holder, POTENCIA_BOMBILLA_L1_Holder, FUENTE_BOMBILLA_L1_Holder,
            TIPO_DE_APOYO_L1_Holder, LONGITUD_DEL_POSTE_L1_Holder, AVANCE_DE_LA_LUMINARIA_SOBRE_LA_CALZADA_L1_Holder,
            DISTANCIA_DEL_POSTE_AL_BORDE_DE_LA_CALZADA_EN_METROS_L1_Holder, ALTURA_DEL_MONTAJE_DE_LA_LUMINARIA_EN_METROS_L1_Holder,
            ANGULO_DE_INCLINACION_DE_LA_LUMINARIA_L1_Holder, TENSION_NOMINAL_DE_LUMINARIA_L1_Holder, TENSION_MEDIDA_EN_LA_RED_L1_Holder,
            ESTADO_DE_LA_LUMINARIA_POR_POLUSION_L1_Holder, LUMINARIA_L2_Holder, POTENCIA_BOMBILLA_L2_Holder, FUENTE_BOMBILLA_L2_Holder,
            TIPO_DE_APOYO_L2_Holder, LONGITUD_DEL_POSTE_L2_Holder, AVANCE_DE_LA_LUMINARIA_SOBRE_LA_CALZADA_L2_Holder,
            DISTANCIA_DEL_POSTE_AL_BORDE_DE_LA_CALZADA_EN_METROS_L2_Holder, ALTURA_DEL_MONTAJE_DE_LA_LUMINARIA_EN_METROS_L2_Holder,
            ANGULO_DE_INCLINACION_DE_LA_LUMINARIA_L2_Holder, TENSION_NOMINAL_DE_LUMINARIA_L2_Holder, TENSION_MEDIDA_EN_LA_RED_L2__Holder,
            ESTADO_DE_LA_LUMINARIA_POR_POLUSION_L2_Holder, INTERDISTANCIA_ENTRE_LUMINARIAS_CONSECUTIVAS_Holder, ANCHO_DE_LA_CALZADA_EN_METROS_Holder,
            SEPARADOR_Holder, NUMERO_DE_SEPARADORES_Holder, DISPOSICION_DE_LAS_LUMINARIAS_Holder,
            P_1_Holder, P_2_Holder, P_3_Holder, P_4_Holder, P_5_Holder, P_6_Holder, P_7_Holder, P_8_Holder, P_9_Holder,
            ADY_1_Holder, ADY_2_Holder, ADY_3_Holder, ADY_4_Holder, ADY_5_Holder, ADY_6_Holder, ADY_7_Holder, ADY_8_Holder, ADY_9_Holder, ADY_10_Holder,
            OP_1_Holder, OP_2_Holder, OP_3_Holder, OP_4_Holder, OP_5_Holder, OP_6_Holder, OP_7_Holder, OP_8_Holder, OP_9_Holder, OP_10_Holder;

    //los_que_faltan_Holder
    String ILUMINANCIA_PROMEDIO_CALZADA_Holder, ILUMINANCIA_PROMEDIO_ANDEN_ADYACENTE_Holder, ILUMINANCIA_PROMEDIO_ANDEN_OPUESTO_Holder,
            UNIFORMIDAD_GENERAL_CALZADA_Holder, UNIFORMIDAD_GENERAL_ANDEN_ADYACENTE_Holder, UNIFORMIDAD_GENERAL_ANDEN_OPUESTO_Holder,
            VALOR_MINIMO_CALZADA_Holder, VALOR_MINIMO_ANDEN_ADYACENTE_Holder, VALOR_MINIMO_ANDEN_OPUESTO_Holder,
            VALOR_MAXIMO_CALZADA_Holder, VALOR_MAXIMO_ANDEN_ADYACENTE_Holder, VALOR_MAXIMO_ANDEN_OPUESTO_Holder,
            VALOR_MIN_MAX_CALZADA_Holder, VALOR_MIN_MAX_ANDEN_ADYACENTE_Holder, VALOR_MIN_MAX_ANDEN_OPUESTO_Holder,
            VALOR_PROMEDIO_MAX_CALZADA_Holder, VALOR_PROMEDIO_MAX_ANDEN_ADYACENTE_Holder, VALOR_PROMEDIO_MAX_ANDEN_OPUESTO_Holder,
            OBSERVACIONES_Holder, CUMPLIMIENTO_Holder, POSIBLE_SOLUCION_Holder, value, latitud_Holder, longitud_Holder, Estado_Holder, value_Holder;





    // Creating Volley RequestQueue.
    RequestQueue requestQueue;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    //TextView
    TextView tv_ilumProm, tv_ilumMin, tv_ilumMax, tv_observaciones, tv_uni;

    //Button
    Button btnSend;

    //Checkbox

    CheckBox chSI, chNO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumary);

        //valores desde la actividad anterior
        Bundle bundle=getIntent().getExtras();

        responsable= (String) bundle.get("responsable");

        clase_de_iluminacion= (String) bundle.get("clase_de_iluminacion");
        tramo=(String) bundle.get("tramo");
        direccion_l1=(String) bundle.get("direccion_l1");
        direccion_l2=(String) bundle.get("direccion_l2");
        barrio=(String) bundle.get("barrio");
        referencia_luxometro=(String) bundle.get("referencia_luxometro");
        condicion_atmosferica=(String) bundle.get("condicion_atmosferica");

        orientacion_l1=(String) bundle.get("orientacion_l1");
        potencia_l1=(String) bundle.get("potencia_l1");

        fuente_l1=(String) bundle.get("fuente_l1");
        apoyo_l1=(String) bundle.get("apoyo_l1");
        longitud_l1=(String) bundle.get("longitud_l1");
        avance_calzada_l1=(String) bundle.get("avance_calzada_l1");
        distancia_l1_borde=(String) bundle.get("distancia_l1_borde");
        altura_montaje_l1=(String) bundle.get("altura_montaje_l1");
        angulo_inclinacion_l1=(String) bundle.get("angulo_inclinacion_l1");
        tension_nominal_l1=(String) bundle.get("tension_nominal_l1");
        tension_medida_l1=(String) bundle.get("tension_medida_l1");
        polucion_l1=(String) bundle.get("polucion_l1");

        orientacion_l2=(String) bundle.get("orientacion_l2");
        potencia_l2=(String) bundle.get("potencia_l2");

        fuente_l2=(String) bundle.get("fuente_l2");
        apoyo_l2=(String) bundle.get("apoyo_l2");
        longitud_l2=(String) bundle.get("longitud_l2");
        avance_calzada_l2=(String) bundle.get("avance_calzada_l2");
        distancia_l2_borde=(String) bundle.get("distancia_l2_borde");
        altura_montaje_l2=(String) bundle.get("altura_montaje_l2");
        angulo_inclinacion_l2=(String) bundle.get("angulo_inclinacion_l2");
        tension_nominal_l2=(String) bundle.get("tension_nominal_l2");
        tension_medida_l2=(String) bundle.get("tension_medida_l2");
        polucion_l2=(String) bundle.get("polucion_l2");

        interdistancia=(String) bundle.get("interdistancia");
        ancho=(String) bundle.get("ancho_calzada");

        latitud=(String) bundle.get("latitud");
        longitud=(String) bundle.get("longitud");

        separador=(String) bundle.get("separador");
        numero_separadores=(String) bundle.get("numero_separadores");

        nueve_uno=(String)bundle.get("nueve_uno");
        nueve_dos=(String)bundle.get("nueve_dos");
        nueve_tres=(String)bundle.get("nueve_tres");
        nueve_cuatro=(String)bundle.get("nueve_cuatro");
        nueve_cinco=(String)bundle.get("nueve_cinco");
        nueve_seis=(String)bundle.get("nueve_seis");
        nueve_siete=(String)bundle.get("nueve_siete");
        nueve_ocho=(String)bundle.get("nueve_ocho");
        nueve_nueve=(String)bundle.get("nueve_nueve");

        ady_uno=(String)bundle.get("ady_uno");
        ady_dos=(String)bundle.get("ady_dos");
        ady_tres=(String)bundle.get("ady_tres");
        ady_cuatro=(String)bundle.get("ady_cuatro");
        ady_cinco=(String)bundle.get("ady_cinco");
        ady_seis=(String)bundle.get("ady_seis");
        ady_siete=(String)bundle.get("ady_siete");
        ady_ocho=(String)bundle.get("ady_ocho");
        ady_nueve=(String)bundle.get("ady_nueve");
        ady_diez=(String)bundle.get("ady_diez");

        op_uno=(String)bundle.get("op_uno");
        op_dos=(String)bundle.get("op_dos");
        op_tres=(String)bundle.get("op_tres");
        op_cuatro=(String)bundle.get("op_cuatro");
        op_cinco=(String)bundle.get("op_cinco");
        op_seis=(String)bundle.get("op_seis");
        op_siete=(String)bundle.get("op_siete");
        op_ocho=(String)bundle.get("op_ocho");
        op_nueve=(String)bundle.get("op_nueve");
        op_diez=(String)bundle.get("op_diez");

        ILUMINANCIA_PROMEDIO_CALZADA =(String)bundle.get("ILUMINANCIA_PROMEDIO");
        VALOR_MINIMO_CALZADA=(String)bundle.get("MINIMO_CALZADA");
        VALOR_MAXIMO_CALZADA  =(String)bundle.get("MAXIMO_CALZADA");
        UNIFORMIDAD_GENERAL_CALZADA=(String)bundle.get("UNIFORMIDAD_GENERAL_CALZADA");
        VALOR_MIN_MAX_CALZADA=(String)bundle.get("VALOR_MIN_MAX_CALZADA");
        VALOR_PROMEDIO_MAX_CALZADA=(String)bundle.get("VALOR_PROMEDIO_MAX_CALZADA");
        Estado=(String)bundle.get("Estado");
        CUMPLIMIENTO=(String)bundle.get("CUMPLIMIENTO");

        ILUMINANCIA_PROMEDIO_ANDEN_ADYACENTE =(String)bundle.get("ILUMINANCIA_PROMEDIO");
        VALOR_MINIMO_ANDEN_ADYACENTE=(String)bundle.get("MINIMO_ANDEN_ADYACENTE");
        VALOR_MAXIMO_ANDEN_ADYACENTE  =(String)bundle.get("MAXIMO_ANDEN_ADYACENTE");
        UNIFORMIDAD_GENERAL_ANDEN_ADYACENTE=(String)bundle.get("UNIFORMIDAD_GENERAL_ANDEN_ADYACENTE");
        VALOR_MIN_MAX_ANDEN_ADYACENTE=(String)bundle.get("VALOR_MIN_MAX_ANDEN_ADYACENTE");
        VALOR_PROMEDIO_MAX_ANDEN_ADYACENTE=(String)bundle.get("VALOR_PROMEDIO_MAX_ANDEN_ADYACENTE");

        ILUMINANCIA_PROMEDIO_ANDEN_OPUESTO =(String)bundle.get("ILUMINANCIA_PROMEDIO");
        VALOR_MINIMO_ANDEN_OPUESTO=(String)bundle.get("MINIMO_ANDEN_OPUESTO");
        VALOR_MAXIMO_ANDEN_OPUESTO  =(String)bundle.get("MAXIMO_ANDEN_OPUESTO");
        UNIFORMIDAD_GENERAL_ANDEN_OPUESTO=(String)bundle.get("UNIFORMIDAD_GENERAL_ANDEN_OPUESTO");
        VALOR_MIN_MAX_ANDEN_OPUESTO=(String)bundle.get("VALOR_MIN_MAX_ANDEN_OPUESTO");
        VALOR_PROMEDIO_MAX_ANDEN_OPUESTO=(String)bundle.get("VALOR_PROMEDIO_MAX_ANDEN_OPUESTO");

         //test
        /*clase_de_iluminacion= "clase_de_iluminacion";
        tramo="tramo";
        direccion_l1="direccion_l1";
        direccion_l2="direccion_l2";
        barrio="barrio";
        referencia_luxometro="referencia_luxometro";
        condicion_atmosferica="condicion_atmosferica";
        orientacion_l1="orientacion_l1";
        fuente_l1="fuente_l1";
        apoyo_l1="apoyo_l1";
        longitud_l1="longitud_l1";
        avance_calzada_l1="avance_calzada_l1";
        distancia_l1_borde="distancia_l1_borde";
        altura_montaje_l1="altura_montaje_l1";
        angulo_inclinacion_l1="angulo_inclinacion_l1";
        tension_nominal_l1="tension_nominal_l1";
        tension_medida_l1="tension_medida_l1";
        polucion_l1="polucion_l1";
        orientacion_l2="orientacion_l2";
        fuente_l2="fuente_l2";
        apoyo_l2="apoyo_l2";
        longitud_l2="longitud_l2";
        avance_calzada_l2="avance_calzada_l2";
        distancia_l2_borde="distancia_l2_borde";
        altura_montaje_l2="altura_montaje_l2";
        angulo_inclinacion_l2="angulo_inclinacion_l2";
        tension_nominal_l2="tension_nominal_l2";
        tension_medida_l2="tension_medida_l2";
        polucion_l2="polucion_l2";
        interdistancia="interdistancia";
        ancho="ancho_calzada";
        latitud="latitud";
        longitud="longitud";

        nueve_uno="nueve_uno";
        nueve_dos="nueve_dos";
        nueve_tres="nueve_tres";
        nueve_cuatro="nueve_cuatro";
        nueve_cinco="nueve_cinco";
        nueve_seis="nueve_seis";
        nueve_siete="nueve_siete";
        nueve_ocho="nueve_ocho";
        nueve_nueve="nueve_nueve";

        ady_uno="ady_uno";
        ady_dos="ady_dos";
        ady_tres="ady_tres";
        ady_cuatro="ady_cuatro";
        ady_cinco="ady_cinco";
        ady_seis="ady_seis";
        ady_siete="ady_siete";
        ady_ocho="ady_ocho";
        ady_nueve="ady_nueve";
        ady_diez="ady_diez";

        op_uno="op_uno";
        op_dos="op_dos";
        op_tres="op_tres";
        op_cuatro="op_cuatro";
        op_cinco="op_cinco";
        op_seis="op_seis";
        op_siete="op_siete";
        op_ocho="op_ocho";
        op_nueve="op_nueve";
        op_diez="op_diez";
        */

        tv_ilumProm=findViewById(R.id.tv_ilumProm);
        tv_ilumMin=findViewById(R.id.tv_ilumMin);
        tv_ilumMax=findViewById(R.id.tv_ilumMax);
        tv_observaciones=findViewById(R.id.tv_observaciones);
        tv_uni=findViewById(R.id.tv_uni);

        chSI = findViewById(R.id.chSI);
        chNO = findViewById(R.id.chNO);

        tv_ilumProm.setText(ILUMINANCIA_PROMEDIO_CALZADA);
        tv_ilumMin.setText(VALOR_MINIMO_CALZADA);
        tv_ilumMax.setText(VALOR_MAXIMO_CALZADA);
        tv_uni.setText(UNIFORMIDAD_GENERAL_CALZADA);

        btnSend= findViewById(R.id.btnSend);

        requestQueue = Volley.newRequestQueue(SumaryActivity.this);

        progressDialog = new ProgressDialog(SumaryActivity.this);

        if (CUMPLIMIENTO=="0")
        {
            chNO.setChecked(true);
            chSI.setChecked(false);
            CUMPLIMIENTO_Holder="El tramo NO cumple  con los niveles de iluminancia estipulados en el RETILAP";
        }
        else
        {
            chSI.setChecked(true);
            chNO.setChecked(false);
            CUMPLIMIENTO_Holder="El tramo SI cumple  con los niveles de iluminancia estipulados en el RETILAP";
        }


        //Send
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Showing progress dialog at user registration time.
                progressDialog.setMessage("Por favor espere, la información se esta guardando");
                progressDialog.show();

                // Calling method to get value from EditText.
                GetValueFromEditText();

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                // Hiding the progress dialog after all task complete.
                                Toast.makeText(SumaryActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                                String response=ServerResponse;
                                Log.e("onResponse: ",response );
                                if (response.equals("Guardado con exito"))
                                {
                                    Intent intent = new Intent(SumaryActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing error message if something goes wrong.
                                Toast.makeText(SumaryActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {

                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();

                        // Adding All values to Params.
                        params.put("RESPONSABLE",responsable);
                        params.put("CLASE_DE_ILIMUNACION_VEHICULAR",clase_de_iluminacion);
                        params.put("direccion",tramo);
                        params.put("BARRIO",barrio);
                        params.put("LUXOMETRO_Referencia",referencia_luxometro);
                        params.put("CONDICIONES_ATMOSFERICAS_DE_LA_NOCHE",condicion_atmosferica);
                        params.put("DIRECCION_L1",direccion_l1);
                        params.put("DIRECCION_L2",direccion_l2);
                        params.put("LUMINARIA_L1",orientacion_l1);
                        params.put("POTENCIA_BOMBILLA_L1",potencia_l1);
                        params.put("FUENTE_BOMBILLA_L1",fuente_l1);
                        params.put("TIPO_DE_APOYO_L1",apoyo_l1);
                        params.put("LONGITUD_DEL_POSTE_L1","longitud_l1");
                        params.put("AVANCE_DE_LA_LUMINARIA_SOBRE_LA_CALZADA_L1",avance_calzada_l1);
                        params.put("DISTANCIA_DEL_POSTE_AL_BORDE_DE_LA_CALZADA_EN_METROS_L1",distancia_l1_borde);
                        params.put("ALTURA_DEL_MONTAJE_DE_LA_LUMINARIA_EN_METROS_L1",altura_montaje_l1);
                        params.put("ANGULO_DE_INCLINACION_DE_LA_LUMINARIA_L1",angulo_inclinacion_l1);
                        params.put("TENSION_NOMINAL_DE_LUMINARIA_L1",tension_nominal_l1);
                        params.put("TENSION_MEDIDA_EN_LA_RED_L1",tension_medida_l1);
                        params.put("ESTADO_DE_LA_LUMINARIA_POR_POLUSION_L1",polucion_l1);
                        params.put("LUMINARIA_L2",orientacion_l2);
                        params.put("POTENCIA_BOMBILLA_L2",potencia_l2);
                        params.put("FUENTE_BOMBILLA_L2",fuente_l2);
                        params.put("TIPO_DE_APOYO_L2",apoyo_l2);
                        params.put("LONGITUD_DEL_POSTE_L2","longitud_l2");
                        params.put("AVANCE_DE_LA_LUMINARIA_SOBRE_LA_CALZADA_L2",avance_calzada_l2);
                        params.put("DISTANCIA_DEL_POSTE_AL_BORDE_DE_LA_CALZADA_EN_METROS_L2",distancia_l2_borde);
                        params.put("ALTURA_DEL_MONTAJE_DE_LA_LUMINARIA_EN_METROS_L2",altura_montaje_l2);
                        params.put("ANGULO_DE_INCLINACION_DE_LA_LUMINARIA_L2",angulo_inclinacion_l2);
                        params.put("TENSION_NOMINAL_DE_LUMINARIA_L2",tension_nominal_l2);
                        params.put("TENSION_MEDIDA_EN_LA_RED_L2",tension_medida_l2);
                        params.put("ESTADO_DE_LA_LUMINARIA_POR_POLUSION_L2",polucion_l2);
                        params.put("INTERDISTANCIA_ENTRE_LUMINARIAS_CONSECUTIVAS",interdistancia);
                        params.put("ANCHO_DE_LA_CALZADA_EN_METROS",ancho);
                        params.put("SEPARADOR",separador);
                        params.put("NUMERO_DE_SEPARADORES",numero_separadores);
                        params.put("DISPOSICION_DE_LAS_LUMINARIAS","DISPOSICION_DE_LAS_LUMINARIAS_Holder");
                        params.put("PUNTO_1",nueve_uno);
                        params.put("PUNTO_2",nueve_dos);
                        params.put("PUNTO_3",nueve_tres);
                        params.put("PUNTO_4",nueve_cuatro);
                        params.put("PUNTO_5",nueve_cinco);
                        params.put("PUNTO_6",nueve_seis);
                        params.put("PUNTO_7",nueve_siete);
                        params.put("PUNTO_8",nueve_ocho);
                        params.put("PUNTO_9",nueve_nueve);
                        params.put("ANDEN_ADYACENTE_1",ady_uno);
                        params.put("ANDEN_ADYACENTE_2",ady_dos);
                        params.put("ANDEN_ADYACENTE_3",ady_tres);
                        params.put("ANDEN_ADYACENTE_4",ady_cuatro);
                        params.put("ANDEN_ADYACENTE_5",ady_cinco);
                        params.put("ANDEN_ADYACENTE_6",ady_seis);
                        params.put("ANDEN_ADYACENTE_7",ady_siete);
                        params.put("ANDEN_ADYACENTE_8",ady_ocho);
                        params.put("ANDEN_ADYACENTE_9",ady_nueve);
                        params.put("ANDEN_ADYACENTE_10",ady_diez);
                        params.put("ANDEN_OPUESTO_1",op_uno);
                        params.put("ANDEN_OPUESTO_2",op_dos);
                        params.put("ANDEN_OPUESTO_3",op_tres);
                        params.put("ANDEN_OPUESTO_4",op_cuatro);
                        params.put("ANDEN_OPUESTO_5",op_cinco);
                        params.put("ANDEN_OPUESTO_6",op_seis);
                        params.put("ANDEN_OPUESTO_7",op_siete);
                        params.put("ANDEN_OPUESTO_8",op_ocho);
                        params.put("ANDEN_OPUESTO_9",op_nueve);
                        params.put("ANDEN_OPUESTO_10",op_diez);
                        params.put("ILUMINANCIA_PROMEDIO_CALZADA",ILUMINANCIA_PROMEDIO_CALZADA);
                        params.put("ILUMINANCIA_PROMEDIO_ANDEN_ADYACENTE",ILUMINANCIA_PROMEDIO_ANDEN_ADYACENTE);
                        params.put("ILUMINANCIA_PROMEDIO_ANDEN_OPUESTO",ILUMINANCIA_PROMEDIO_ANDEN_OPUESTO);
                        params.put("UNIFORMIDAD_GENERAL_CALZADA",UNIFORMIDAD_GENERAL_CALZADA+"%");
                        params.put("UNIFORMIDAD_GENERAL_ANDEN_ADYACENTE",UNIFORMIDAD_GENERAL_ANDEN_ADYACENTE+"%");
                        params.put("UNIFORMIDAD_GENERAL_ANDEN_OPUESTO",UNIFORMIDAD_GENERAL_ANDEN_OPUESTO+"%");
                        params.put("VALOR_MINIMO_CALZADA",VALOR_MAXIMO_CALZADA);
                        params.put("VALOR_MINIMO_ANDEN_ADYACENTE",VALOR_MAXIMO_ANDEN_ADYACENTE);
                        params.put("VALOR_MINIMO_ANDEN_OPUESTO",VALOR_MAXIMO_ANDEN_OPUESTO);
                        params.put("VALOR_MAXIMO_CALZADA",VALOR_MAXIMO_CALZADA);
                        params.put("VALOR_MAXIMO_ANDEN_ADYACENTE",VALOR_MAXIMO_ANDEN_ADYACENTE);
                        params.put("VALOR_MAXIMO_ANDEN_OPUESTO",VALOR_MAXIMO_ANDEN_OPUESTO);
                        params.put("VALOR_MIN_MAX_CALZADA",VALOR_MIN_MAX_CALZADA);
                        params.put("VALOR_MIN_MAX_ANDEN_ADYACENTE",VALOR_MINIMO_ANDEN_ADYACENTE);
                        params.put("VALOR_MIN_MAX_ANDEN_OPUESTO",VALOR_MIN_MAX_ANDEN_OPUESTO);
                        params.put("VALOR_PROMEDIO_MAX_CALZADA",VALOR_PROMEDIO_MAX_CALZADA);
                        params.put("VALOR_PROMEDIO_MAX_ANDEN_ADYACENTE",VALOR_PROMEDIO_MAX_ANDEN_ADYACENTE);
                        params.put("VALOR_PROMEDIO_MAX_ANDEN_OPUESTO",VALOR_PROMEDIO_MAX_ANDEN_OPUESTO);
                        params.put("OBSERVACIONES",OBSERVACIONES_Holder);
                        params.put("CUMPLIMIENTO",CUMPLIMIENTO_Holder);
                        params.put("POSIBLE_SOLUCION","POSIBLE_SOLUCION");
                        params.put("value",ILUMINANCIA_PROMEDIO_CALZADA);
                        params.put("latitud",latitud);
                        params.put("longitud",longitud);
                        params.put("Estado",Estado);
                        return params;
                    }

                };

                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(SumaryActivity.this);

                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);

            }



        });
    }
    // Creating method to get value from EditText.
    public void GetValueFromEditText(){

        RESPONSABLE_Holder = "Name";
        CLASE_DE_ILIMUNACION_VEHICULAR_Holder = clase_de_iluminacion;
        direccion_Holder = tramo;
        BARRIO_Holder = barrio;
        LUXOMETRO_Referencia_Holder = referencia_luxometro;
        CONDICIONES_ATMOSFERICAS_DE_LA_NOCHE_Holder = condicion_atmosferica;
        DIRECCION_L1_Holder = direccion_l1;
        DIRECCION_L2_Holder = direccion_l2;
        LUMINARIA_L1_Holder = orientacion_l1;
        POTENCIA_BOMBILLA_L1_Holder = "potencia l1";
        FUENTE_BOMBILLA_L1_Holder = fuente_l1;
        TIPO_DE_APOYO_L1_Holder = apoyo_l1;
        LONGITUD_DEL_POSTE_L1_Holder = "8";
        AVANCE_DE_LA_LUMINARIA_SOBRE_LA_CALZADA_L1_Holder = avance_calzada_l1;
        DISTANCIA_DEL_POSTE_AL_BORDE_DE_LA_CALZADA_EN_METROS_L1_Holder = distancia_l1_borde;
        ALTURA_DEL_MONTAJE_DE_LA_LUMINARIA_EN_METROS_L1_Holder = altura_montaje_l1;
        ANGULO_DE_INCLINACION_DE_LA_LUMINARIA_L1_Holder = angulo_inclinacion_l1;
        TENSION_NOMINAL_DE_LUMINARIA_L1_Holder = tension_nominal_l1;
        TENSION_MEDIDA_EN_LA_RED_L1_Holder = tension_medida_l1;
        ESTADO_DE_LA_LUMINARIA_POR_POLUSION_L1_Holder = polucion_l1;
        LUMINARIA_L2_Holder = orientacion_l2;
        POTENCIA_BOMBILLA_L2_Holder = "potencia l2";
        FUENTE_BOMBILLA_L2_Holder = fuente_l2;
        TIPO_DE_APOYO_L2_Holder = apoyo_l2;
        LONGITUD_DEL_POSTE_L2_Holder = "8";
        AVANCE_DE_LA_LUMINARIA_SOBRE_LA_CALZADA_L2_Holder = avance_calzada_l2;
        DISTANCIA_DEL_POSTE_AL_BORDE_DE_LA_CALZADA_EN_METROS_L2_Holder = distancia_l2_borde;
        ALTURA_DEL_MONTAJE_DE_LA_LUMINARIA_EN_METROS_L2_Holder = altura_montaje_l2;
        ANGULO_DE_INCLINACION_DE_LA_LUMINARIA_L2_Holder = angulo_inclinacion_l2;
        TENSION_NOMINAL_DE_LUMINARIA_L2_Holder = tension_nominal_l2;
        TENSION_MEDIDA_EN_LA_RED_L2__Holder = tension_medida_l2;
        ESTADO_DE_LA_LUMINARIA_POR_POLUSION_L2_Holder = polucion_l2;
        INTERDISTANCIA_ENTRE_LUMINARIAS_CONSECUTIVAS_Holder = interdistancia;
        ANCHO_DE_LA_CALZADA_EN_METROS_Holder = ancho;
        SEPARADOR_Holder = "no";
        NUMERO_DE_SEPARADORES_Holder = "N/A";
        DISPOSICION_DE_LAS_LUMINARIAS_Holder = "N/A" ;

        P_1_Holder = nueve_uno;
        P_2_Holder = nueve_dos;
        P_3_Holder = nueve_tres;
        P_4_Holder = nueve_cuatro;
        P_5_Holder = nueve_cinco;
        P_6_Holder = nueve_seis;
        P_7_Holder = nueve_siete;
        P_8_Holder = nueve_ocho;
        P_9_Holder = nueve_nueve;

        ADY_1_Holder = ady_uno;
        ADY_2_Holder = ady_dos;
        ADY_3_Holder = ady_tres;
        ADY_4_Holder = ady_cuatro;
        ADY_5_Holder = ady_cinco;
        ADY_6_Holder = ady_seis;
        ADY_7_Holder = ady_siete;
        ADY_8_Holder = ady_ocho;
        ADY_9_Holder = ady_nueve;
        ADY_10_Holder = ady_diez;

        OP_1_Holder = op_uno;
        OP_2_Holder = op_dos;
        OP_3_Holder = op_tres;
        OP_4_Holder = op_cuatro;
        OP_5_Holder = op_cinco;
        OP_6_Holder = op_seis;
        OP_7_Holder = op_siete;
        OP_8_Holder = op_ocho;
        OP_9_Holder = op_nueve;
        OP_10_Holder = op_diez;

        ILUMINANCIA_PROMEDIO_CALZADA_Holder = ILUMINANCIA_PROMEDIO_CALZADA;
        ILUMINANCIA_PROMEDIO_ANDEN_ADYACENTE_Holder = ILUMINANCIA_PROMEDIO_ANDEN_ADYACENTE;
        ILUMINANCIA_PROMEDIO_ANDEN_OPUESTO_Holder = ILUMINANCIA_PROMEDIO_ANDEN_OPUESTO;
        UNIFORMIDAD_GENERAL_CALZADA_Holder = UNIFORMIDAD_GENERAL_CALZADA;
        UNIFORMIDAD_GENERAL_ANDEN_ADYACENTE_Holder = UNIFORMIDAD_GENERAL_ANDEN_ADYACENTE;
        UNIFORMIDAD_GENERAL_ANDEN_OPUESTO_Holder = UNIFORMIDAD_GENERAL_ANDEN_OPUESTO;
        VALOR_MINIMO_CALZADA_Holder = VALOR_MINIMO_CALZADA;
        VALOR_MINIMO_ANDEN_ADYACENTE_Holder = VALOR_MINIMO_ANDEN_ADYACENTE;
        VALOR_MINIMO_ANDEN_OPUESTO_Holder = VALOR_MINIMO_ANDEN_OPUESTO;
        VALOR_MAXIMO_CALZADA_Holder = VALOR_MAXIMO_CALZADA;
        VALOR_MAXIMO_ANDEN_ADYACENTE_Holder = VALOR_MAXIMO_ANDEN_ADYACENTE;
        VALOR_MAXIMO_ANDEN_OPUESTO_Holder = VALOR_MAXIMO_ANDEN_OPUESTO;
        VALOR_MIN_MAX_CALZADA_Holder = VALOR_MIN_MAX_CALZADA;
        VALOR_MIN_MAX_ANDEN_ADYACENTE_Holder = VALOR_MIN_MAX_ANDEN_ADYACENTE;
        VALOR_MIN_MAX_ANDEN_OPUESTO_Holder = VALOR_MIN_MAX_ANDEN_OPUESTO;
        VALOR_PROMEDIO_MAX_CALZADA_Holder = VALOR_PROMEDIO_MAX_CALZADA;
        VALOR_PROMEDIO_MAX_ANDEN_ADYACENTE_Holder = VALOR_PROMEDIO_MAX_ANDEN_ADYACENTE;
        VALOR_PROMEDIO_MAX_ANDEN_OPUESTO_Holder = VALOR_PROMEDIO_MAX_ANDEN_OPUESTO;
        OBSERVACIONES_Holder = tv_observaciones.getText().toString();
        if (OBSERVACIONES_Holder.equals(""))
        {
            OBSERVACIONES_Holder="N/A";
        }

        POSIBLE_SOLUCION_Holder= "falta";
        value_Holder = ILUMINANCIA_PROMEDIO_CALZADA;
        latitud_Holder = latitud;
        longitud_Holder = longitud;
        Estado_Holder = Estado;



    }
}
