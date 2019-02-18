package com.example.lxmtr_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static android.content.ContentValues.TAG;
import static com.example.lxmtr_2.DeviceListActivity.EXTRA_DEVICE_ADDRESS;

public class SideWalkActivity extends AppCompatActivity {

    Button btnSensor, btnGPS, btnSend;
    TextView sensorView, tvValueLatitud, tvValueLongitude;

    Handler bluetoothIn;

    final int handlerState = 0;             //used to identify handler message
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder recDataString = new StringBuilder();
    private ConnectedThread mConnectedThread;

    // SPP UUID service - this should work for most devices
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // String for MAC address
    private static String address = null;


    //String informacion
    String clase_de_iluminacion, tramo, direccion_l1, direccion_l2, barrio, referencia_luxometro, condicion_atmosferica, interdistancia, ancho;
    //String L1
    String orientacion_l1, fuente_l1, apoyo_l1, longitud_l1, avance_calzada_l1, distancia_l1_borde,
            altura_montaje_l1, angulo_inclinacion_l1, tension_nominal_l1, tension_medida_l1, polucion_l1;
    //String L2
    String orientacion_l2, fuente_l2, apoyo_l2, longitud_l2, avance_calzada_l2, distancia_l2_borde,
            altura_montaje_l2, angulo_inclinacion_l2, tension_nominal_l2, tension_medida_l2, polucion_l2;

    //Coordenadas
    String latitud, longitud;

    //Nueve puntos
    String nueve_uno, nueve_dos, nueve_tres, nueve_cuatro,nueve_cinco,nueve_seis,nueve_siete,nueve_ocho,nueve_nueve;

    //Resumen de nueve puntos
    String ILUMINANCIA_PROMEDIO ,MINIMO_CALZADA, MAXIMO_CALZADA, UNIFORMIDAD_GENERAL_CALZADA,
            VALOR_MIN_MAX_CALZADA, VALOR_PROMEDIO_MAX_CALZADA, Estado, CUMPLIMIENTO;

    //Ubicacion diez puntos puntos
    TextView s1,s2, s3, s4, s5, s6,s7, s8,s9, s10;

    //Valores de los diez puntos
    TextView Viewlx1, Viewlx2, Viewlx3, Viewlx4, Viewlx5, Viewlx6, Viewlx7, Viewlx8, Viewlx9, Viewlx10;

    //Botones diez puntos
    Button btn_lux1, btn_lux2,btn_lux3,btn_lux4,btn_lux5,btn_lux6,btn_lux7,btn_lux8,btn_lux9, btn_lux10;

    float p1,p2,p3,p4,p5,p6,p7,p8,p9,p10;
    float prom_ady, uni_ady, min_ady, max_ady, min_max_ady, prom_max_ady;


    int btn;

    //Para siguiente actividad
    String lx1_enviar, lx2_enviar,lx3_enviar,lx4_enviar,lx5_enviar,lx6_enviar,lx7_enviar,lx8_enviar,lx9_enviar, lx10_enviar;

    String    ILUMINANCIA_PROMEDIO_ANDEN_ADYACENTE_enviar,MINIMO_ANDEN_ADYACENTE_enviar,
                MAXIMO_ANDEN_ADYACENTE_enviar, UNIFORMIDAD_GENERAL_ANDEN_ADYACENTE_enviar,
                VALOR_MIN_MAX_ANDEN_ADYACENTE_enviar,VALOR_PROMEDIO_MAX_ANDEN_ADYACENTE_enviar;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_side_walk);

        //valores desde la actividad anterior
        Bundle bundle=getIntent().getExtras();
        clase_de_iluminacion= (String) bundle.get("clase_de_iluminacion");
        tramo=(String) bundle.get("tramo");
        direccion_l1=(String) bundle.get("direccion_l1");
        direccion_l2=(String) bundle.get("direccion_l2");
        barrio=(String) bundle.get("barrio");
        referencia_luxometro=(String) bundle.get("referencia_luxometro");
        condicion_atmosferica=(String) bundle.get("condicion_atmosferica");

        orientacion_l1=(String) bundle.get("orientacion_l1");
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

        nueve_uno=(String)bundle.get("nueve_uno");
        nueve_dos=(String)bundle.get("nueve_dos");
        nueve_tres=(String)bundle.get("nueve_tres");
        nueve_cuatro=(String)bundle.get("nueve_cuatro");
        nueve_cinco=(String)bundle.get("nueve_cinco");
        nueve_seis=(String)bundle.get("nueve_seis");
        nueve_siete=(String)bundle.get("nueve_siete");
        nueve_ocho=(String)bundle.get("nueve_ocho");
        nueve_nueve=(String)bundle.get("nueve_nueve");

        ILUMINANCIA_PROMEDIO =(String)bundle.get("ILUMINANCIA_PROMEDIO");
        MINIMO_CALZADA=(String)bundle.get("MINIMO_CALZADA");
        MAXIMO_CALZADA  =(String)bundle.get("MAXIMO_CALZADA");
        UNIFORMIDAD_GENERAL_CALZADA=(String)bundle.get("UNIFORMIDAD_GENERAL_CALZADA");
        VALOR_MIN_MAX_CALZADA=(String)bundle.get("VALOR_MIN_MAX_CALZADA");
        VALOR_PROMEDIO_MAX_CALZADA=(String)bundle.get("VALOR_PROMEDIO_MAX_CALZADA");
        Estado=(String)bundle.get("Estado");
        CUMPLIMIENTO=(String)bundle.get("CUMPLIMIENTO");

        //Ubicacion diez puntos
        s1=findViewById(R.id.s1);
        s2=findViewById(R.id.s2);
        s3=findViewById(R.id.s3);
        s4=findViewById(R.id.s4);
        s5=findViewById(R.id.s5);
        s6=findViewById(R.id.s6);
        s7=findViewById(R.id.s7);
        s8=findViewById(R.id.s8);
        s9=findViewById(R.id.s9);
        s10=findViewById(R.id.s10);

        //Valores diez puntos
        Viewlx1=findViewById(R.id.lx1);
        Viewlx2=findViewById(R.id.lx2);
        Viewlx3=findViewById(R.id.lx3);
        Viewlx4=findViewById(R.id.lx4);
        Viewlx5=findViewById(R.id.lx5);
        Viewlx6=findViewById(R.id.lx6);
        Viewlx7=findViewById(R.id.lx7);
        Viewlx8=findViewById(R.id.lx8);
        Viewlx9=findViewById(R.id.lx9);
        Viewlx10=findViewById(R.id.lx10);

        //Link the buttons and textViews to respective views
        btn_lux1 = findViewById(R.id.btn_lx1);
        btn_lux2= findViewById(R.id.btn_lx2);
        btn_lux3= findViewById(R.id.btn_lx3);
        btn_lux4= findViewById(R.id.btn_lx4);
        btn_lux5= findViewById(R.id.btn_lx5);
        btn_lux6= findViewById(R.id.btn_lx6);
        btn_lux7= findViewById(R.id.btn_lx7);
        btn_lux8= findViewById(R.id.btn_lx8);
        btn_lux9= findViewById(R.id.btn_lx9);
        btn_lux10= findViewById(R.id.btn_lx10);




        sensorView = (TextView) findViewById(R.id.lx1);

        float d = Float.parseFloat(interdistancia);
        float A = Float.parseFloat(ancho);

        float a2=(A)/2;
        float d1=d/10;
        float d2=d1*2;
        float d3=d1*3;
        float d4=d1*4;
        float d5=d1*5;
        float d6=d1*6;
        float d7=d1*7;
        float d8=d1*8;
        float d9=d1*9;
        float d10=d1*10;

        String xd1 = String.format("%.2f",d1);
        String xd2 = String.format("%.2f",d2);
        String xd3 = String.format("%.2f",d3);
        String xd4 = String.format("%.2f",d4);
        String xd5 = String.format("%.2f",d5);
        String xd6 = String.format("%.2f",d6);
        String xd7 = String.format("%.2f",d7);
        String xd8 = String.format("%.2f",d8);
        String xd9 = String.format("%.2f",d9);
        String xd10 = String.format("%.2f",d10);


        s1.setText(xd1 + " mts");
        s2.setText(xd2 + " mts");
        s3.setText(xd3 + " mts");
        s4.setText(xd4 + " mts");
        s5.setText(xd5 + " mts");
        s6.setText(xd6 + " mts");
        s7.setText(xd7 + " mts");
        s8.setText(xd8 + " mts");
        s9.setText(xd9 + " mts");
        s10.setText(xd10 + " mts");


        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                Log.e(TAG, "handleMessage: Llega informacion ");
                if (msg.what == handlerState) {                                     //if message is what we want
                    String readMessage = (String) msg.obj;
                    if (readMessage.equals("/n")) return;// msg.arg1 = bytes from connect thread
                    recDataString.append(readMessage);                                      //keep appending to string until ~
                    int endOfLineIndex = recDataString.indexOf("~");                    // determine the end-of-line
                    if (endOfLineIndex > 0) {                                           // make sure there data before ~
                        if (recDataString.charAt(0) == '#')                             //if it starts with # we know it is what we are looking for
                        {
                            String sensor0 = recDataString.substring(1, 6);             //get sensor value from string between indices 1-5
                            //sensorView.setText( sensor0 );    //update the textviews with sensor values

                            if (btn==1)
                            {
                                Viewlx1.setText( sensor0 );    //update the textviews with sensor values
                            }
                            if (btn==2)
                            {
                                Viewlx2.setText( sensor0 );    //update the textviews with sensor values
                            }
                            if (btn==3)
                            {
                                Viewlx3.setText( sensor0 );    //update the textviews with sensor values
                            }
                            if (btn==4)
                            {
                                Viewlx4.setText( sensor0 );    //update the textviews with sensor values
                            }
                            if (btn==5)
                            {
                                Viewlx5.setText( sensor0 );    //update the textviews with sensor values
                            }
                            if (btn==6)
                            {
                                Viewlx6.setText( sensor0 );    //update the textviews with sensor values
                            }
                            if (btn==7)
                            {
                                Viewlx7.setText( sensor0 );    //update the textviews with sensor values
                            }
                            if (btn==8)
                            {
                                Viewlx8.setText( sensor0 );    //update the textviews with sensor values
                            }
                            if (btn==9)
                            {
                                Viewlx9.setText( sensor0 );    //update the textviews with sensor values
                            }
                            if (btn==10)
                            {
                                Viewlx10.setText( sensor0 );    //update the textviews with sensor values
                            }

                        }
                        recDataString.delete(0, recDataString.length());                    //clear all string data
                    }
                }
            }
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
        checkBTState();

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }


        //Sensor
        btn_lux1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                btn=1;
                Viewlx1.setText(null);
                mConnectedThread.write("1");    // Envia accion
                Toast.makeText(getBaseContext(), "Pidiendo dato sensor", Toast.LENGTH_SHORT).show();
            }
        });
        btn_lux2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                btn=2;
                Viewlx2.setText(null);
                mConnectedThread.write("1");    // Envia accion
                Toast.makeText(getBaseContext(), "Pidiendo dato sensor", Toast.LENGTH_SHORT).show();
            }
        });
        btn_lux3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                btn=3;
                Viewlx3.setText(null);
                mConnectedThread.write("1");    // Envia accion
                Toast.makeText(getBaseContext(), "Pidiendo dato sensor", Toast.LENGTH_SHORT).show();
            }
        });
        btn_lux4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                btn=4;
                Viewlx4.setText(null);
                mConnectedThread.write("1");    // Envia accion
                Toast.makeText(getBaseContext(), "Pidiendo dato sensor", Toast.LENGTH_SHORT).show();
            }
        });
        btn_lux5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                btn=5;
                Viewlx5.setText(null);
                mConnectedThread.write("1");    // Envia accion
                Toast.makeText(getBaseContext(), "Pidiendo dato sensor", Toast.LENGTH_SHORT).show();
            }
        });
        btn_lux6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                btn=6;
                Viewlx6.setText(null);
                mConnectedThread.write("1");    // Envia accion
                Toast.makeText(getBaseContext(), "Pidiendo dato sensor", Toast.LENGTH_SHORT).show();
            }
        });
        btn_lux7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                btn=7;
                Viewlx7.setText(null);
                mConnectedThread.write("1");    // Envia accion
                Toast.makeText(getBaseContext(), "Pidiendo dato sensor", Toast.LENGTH_SHORT).show();
            }
        });
        btn_lux8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                btn=8;
                Viewlx8.setText(null);
                mConnectedThread.write("1");    // Envia accion
                Toast.makeText(getBaseContext(), "Pidiendo dato sensor", Toast.LENGTH_SHORT).show();
            }
        });
        btn_lux9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                btn=9;
                Viewlx9.setText(null);
                mConnectedThread.write("1");    // Envia accion
                Toast.makeText(getBaseContext(), "Pidiendo dato sensor", Toast.LENGTH_SHORT).show();
            }
        });
        btn_lux10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                btn=10;
                Viewlx10.setText(null);
                mConnectedThread.write("1");    // Envia accion
                Toast.makeText(getBaseContext(), "Pidiendo dato sensor", Toast.LENGTH_SHORT).show();
            }
        });

    }









    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connecetion with BT device using UUID
    }

    @Override
    public void onResume() {
        super.onResume();

        //Get MAC address from DeviceListActivity via intent
        Intent intent = getIntent();

        //Get the MAC address from the DeviceListActivty via EXTRA
        address = intent.getStringExtra(DeviceListActivity.EXTRA_DEVICE_ADDRESS);

        //create device and set the MAC address
        //Log.i("ramiro", "adress : " + address);
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
        }
        // Establish the Bluetooth socket connection.
        try
        {
            btSocket.connect();
        } catch (IOException e) {
            try
            {
                btSocket.close();
            } catch (IOException e2)
            {
                //insert code to deal with this
            }
        }
        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();

        //I send a character when resuming.beginning transmission to check device is connected
        //If it is not an exception will be thrown in the write method and finish() will be called
        mConnectedThread.write("x");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        try
        {
            //Don't leave Bluetooth sockets open when leaving activity
            btSocket.close();
        } catch (IOException e2) {
            //insert code to deal with this
        }
    }

    //Checks that the Android device Bluetooth is available and prompts to be turned on if off
    private void checkBTState() {

        if(btAdapter==null) {
            Toast.makeText(getBaseContext(), "El dispositivo no soporta bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    public void next_activity_op_walk(View view) {

        lx1_enviar=Viewlx1.getText().toString();
        lx2_enviar=Viewlx2.getText().toString();
        lx3_enviar=Viewlx3.getText().toString();
        lx4_enviar=Viewlx4.getText().toString();
        lx5_enviar=Viewlx5.getText().toString();
        lx6_enviar=Viewlx6.getText().toString();
        lx7_enviar=Viewlx7.getText().toString();
        lx8_enviar=Viewlx8.getText().toString();
        lx9_enviar=Viewlx9.getText().toString();
        lx10_enviar=Viewlx10.getText().toString();

        p1 = Float.parseFloat(lx1_enviar);
        p2 = Float.parseFloat(lx2_enviar);
        p3 = Float.parseFloat(lx3_enviar);
        p4 = Float.parseFloat(lx4_enviar);
        p5 = Float.parseFloat(lx5_enviar);
        p6 = Float.parseFloat(lx6_enviar);
        p7 = Float.parseFloat(lx7_enviar);
        p8 = Float.parseFloat(lx8_enviar);
        p9 = Float.parseFloat(lx9_enviar);
        p10 = Float.parseFloat(lx10_enviar);

        prom_ady=(p1+p2+p3+p4+p5+p6+p7+p8+p9+p10)/10;

        //p1, p2, p3, p4, p5, p6, p7, p8, p9

        List<Float> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p7);
        list.add(p8);
        list.add(p9);
        list.add(p10);
        Collections.sort(list);


        min_ady= list.get(0);
        max_ady= list.get(9);
        uni_ady= min_ady/prom_ady;
        min_max_ady= min_ady/max_ady;
        prom_max_ady= prom_ady/max_ady;



        ILUMINANCIA_PROMEDIO_ANDEN_ADYACENTE_enviar = String.format("%.2f",prom_ady);
        MINIMO_ANDEN_ADYACENTE_enviar= String.format("%.2f",min_ady);
        MAXIMO_ANDEN_ADYACENTE_enviar= String.format("%.2f",max_ady);
        UNIFORMIDAD_GENERAL_ANDEN_ADYACENTE_enviar= String.format("%.2f",uni_ady);
        VALOR_MIN_MAX_ANDEN_ADYACENTE_enviar=String.format("%.2f",min_max_ady);
        VALOR_PROMEDIO_MAX_ANDEN_ADYACENTE_enviar=String.format("%.2f",prom_max_ady);




        Intent intent= new Intent(SideWalkActivity.this,OpWalkActivity.class);
        intent.putExtra("clase_de_iluminacion",clase_de_iluminacion);
        intent.putExtra("tramo",tramo);
        intent.putExtra("direccion_l1",direccion_l1);
        intent.putExtra("direccion_l2",direccion_l2);
        intent.putExtra("barrio",barrio);
        intent.putExtra("referencia_luxometro",referencia_luxometro);
        intent.putExtra("condicion_atmosferica",condicion_atmosferica);

        intent.putExtra("orientacion_l1", orientacion_l1);
        intent.putExtra("fuente_l1",fuente_l1);
        intent.putExtra("apoyo_l1",apoyo_l1);
        intent.putExtra("longitud_l1",longitud_l1);
        intent.putExtra("avance_calzada_l1",avance_calzada_l1);
        intent.putExtra("distancia_l1_borde",distancia_l1_borde);
        intent.putExtra("altura_montaje_l1", altura_montaje_l1);
        intent.putExtra("angulo_inclinacion_l1", angulo_inclinacion_l1);
        intent.putExtra("tension_nominal_l1", tension_nominal_l1);
        intent.putExtra("tension_medida_l1",tension_medida_l1);
        intent.putExtra("polucion_l1", polucion_l1);

        intent.putExtra("orientacion_l2", orientacion_l2);
        intent.putExtra("fuente_l2",fuente_l2);
        intent.putExtra("apoyo_l2",apoyo_l2);
        intent.putExtra("longitud_l2",longitud_l2);
        intent.putExtra("avance_calzada_l2",avance_calzada_l2);
        intent.putExtra("distancia_l2_borde",distancia_l2_borde);
        intent.putExtra("altura_montaje_l2", altura_montaje_l2);
        intent.putExtra("angulo_inclinacion_l2", angulo_inclinacion_l2);
        intent.putExtra("tension_nominal_l2", tension_nominal_l2);
        intent.putExtra("tension_medida_l2",tension_medida_l2);
        intent.putExtra("polucion_l2", polucion_l2);

        intent.putExtra("interdistancia", interdistancia);
        intent.putExtra("ancho_calzada", ancho);

        intent.putExtra("latitud", latitud);
        intent.putExtra("longitud",longitud);

        intent.putExtra("nueve_uno", nueve_uno);
        intent.putExtra("nueve_dos", nueve_dos);
        intent.putExtra("nueve_tres", nueve_tres);
        intent.putExtra("nueve_cuatro", nueve_cuatro);
        intent.putExtra("nueve_cinco", nueve_cinco);
        intent.putExtra("nueve_seis", nueve_seis);
        intent.putExtra("nueve_siete", nueve_siete);
        intent.putExtra("nueve_ocho", nueve_ocho);
        intent.putExtra("nueve_nueve", nueve_nueve);

        intent.putExtra("ILUMINANCIA_PROMEDIO", ILUMINANCIA_PROMEDIO);
        intent.putExtra("MINIMO_CALZADA", MINIMO_CALZADA);
        intent.putExtra("MAXIMO_CALZADA", MAXIMO_CALZADA);
        intent.putExtra("UNIFORMIDAD_GENERAL_CALZADA", UNIFORMIDAD_GENERAL_CALZADA);
        intent.putExtra("VALOR_MIN_MAX_CALZADA", VALOR_MIN_MAX_CALZADA);
        intent.putExtra("VALOR_PROMEDIO_MAX_CALZADA", VALOR_PROMEDIO_MAX_CALZADA);

        intent.putExtra("Estado", Estado);
        intent.putExtra("CUMPLIMIENTO", CUMPLIMIENTO);

        intent.putExtra("ady_uno", lx1_enviar);
        intent.putExtra("ady_dos", lx2_enviar);
        intent.putExtra("ady_tres", lx3_enviar);
        intent.putExtra("ady_cuatro", lx4_enviar);
        intent.putExtra("ady_cinco", lx5_enviar);
        intent.putExtra("ady_seis", lx6_enviar);
        intent.putExtra("ady_siete", lx7_enviar);
        intent.putExtra("ady_ocho", lx8_enviar);
        intent.putExtra("ady_nueve", lx9_enviar);
        intent.putExtra("ady_diez", lx10_enviar);

        intent.putExtra("ILUMINANCIA_PROMEDIO_ANDEN_ADYACENTE", ILUMINANCIA_PROMEDIO_ANDEN_ADYACENTE_enviar);
        intent.putExtra("MINIMO_ANDEN_ADYACENTE", MINIMO_ANDEN_ADYACENTE_enviar);
        intent.putExtra("MAXIMO_ANDEN_ADYACENTE", MAXIMO_ANDEN_ADYACENTE_enviar);
        intent.putExtra("UNIFORMIDAD_GENERAL_ANDEN_ADYACENTE", UNIFORMIDAD_GENERAL_ANDEN_ADYACENTE_enviar);
        intent.putExtra("VALOR_MIN_MAX_ANDEN_ADYACENTE", VALOR_MIN_MAX_ANDEN_ADYACENTE_enviar);
        intent.putExtra("VALOR_PROMEDIO_MAX_ANDEN_ADYACENTE", VALOR_PROMEDIO_MAX_ANDEN_ADYACENTE_enviar);

        intent.putExtra(EXTRA_DEVICE_ADDRESS, address);



        startActivity(intent);



    }

    public void next_activity_summary(View view) {
    }

    //create new class for connect thread
    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        //creation of the connect thread
        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }


        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Keep looping to listen for received messages
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);         //read bytes from input buffer
                    String readMessage = new String(buffer, 0, bytes);
                    // Send the obtained bytes to the UI Activity via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
        public void write(String input) {
            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application
                Toast.makeText(getBaseContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
                finish();

            }
        }
    }
}