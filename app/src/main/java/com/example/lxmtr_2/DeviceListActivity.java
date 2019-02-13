package com.example.lxmtr_2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class DeviceListActivity extends AppCompatActivity {

    // Debugging for LOGCAT
    private static final String TAG = "DeviceListActivity";
    private static final boolean D = true;
    private static final String hc = "hc0";
    public static final String EXTRA_KEY_1 = "EXTRA_KEY_1";


    // declare button for launching website and textview for connection status
    Button tlbutton;
    TextView textView1;

    // EXTRA string to send on to mainactivity
    public static String EXTRA_DEVICE_ADDRESS = "device_address";

    // Member fields
    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);

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





    }

    @Override
    public void onResume()
    {
        super.onResume();
        //***************
        checkBTState();

        textView1 = findViewById(R.id.connecting);
        textView1.setTextSize(40);
        textView1.setText(" ");

        // Initialize array adapter for paired devices
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);

        // Find and set up the ListView for paired devices
        ListView pairedListView = (ListView) findViewById(R.id.paired_devices);
        pairedListView.setAdapter(mPairedDevicesArrayAdapter);
        pairedListView.setOnItemClickListener(mDeviceClickListener);

        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        // Get a set of currently paired devices and append to 'pairedDevices'
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

        // Add previosuly paired devices to the array
        if (pairedDevices.size() > 0) {
            findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);//make title viewable
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        } else {
            String noDevices = getResources().getText(R.string.none_paired).toString();
            mPairedDevicesArrayAdapter.add(noDevices);
        }
    }

    // Set up on-click listener for the list (nicked this - unsure)
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3)
        {



            // Get the device MAC address, which is the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String name= info.substring(0,3);
            String address = info.substring(info.length() - 17);
            Log.e("aqui", "aqui");

            if(name.equals(hc)){
                textView1.setText("Contectando...");
                // Make an intent to start next activity while taking an extra which is the MAC address.
                Intent intent = new Intent(DeviceListActivity.this, NinePointsActivity.class);
                intent.putExtra(EXTRA_DEVICE_ADDRESS, address);
                intent.putExtra(EXTRA_KEY_1, info);

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
                intent.putExtra("longitd_l1",longitud_l1);
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
                intent.putExtra("longitd_l2",longitud_l2);
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





                startActivity(intent);
            }
            else {
                Toast.makeText(getBaseContext(), "Seleccione el sensor (HC)", Toast.LENGTH_SHORT).show();
            }


        }
    };

    private void checkBTState() {
        // Check device has Bluetooth and that it is turned on
        mBtAdapter=BluetoothAdapter.getDefaultAdapter(); // CHECK THIS OUT THAT IT WORKS!!!
        if(mBtAdapter==null) {
            Toast.makeText(getBaseContext(), "No soporta Bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            if (mBtAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth encendido...");
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);

            }
        }
    }

}
