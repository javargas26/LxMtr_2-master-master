package com.example.lxmtr_2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String id, nombre, apellido, responsable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle=getIntent().getExtras();
        nombre= (String) bundle.get("nombre");
        apellido= (String) bundle.get("apellido");
        id= (String) bundle.get("id");
        responsable= id;

    }

    public void inicioTomaDatos(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, FormActivity.class);
        intent.putExtra("responsable", responsable);
        startActivity(intent);
    }

    public void CerrarCesion(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}