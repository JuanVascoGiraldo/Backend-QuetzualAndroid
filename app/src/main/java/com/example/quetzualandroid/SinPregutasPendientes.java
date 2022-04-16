package com.example.quetzualandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SinPregutasPendientes extends AppCompatActivity {
    Button volver;
    TextView accion;
    String nombre, correo, fecha, genero, token;
    int id, accio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_pregutas_pendientes);

        volver = findViewById(R.id.volversin);
        accion = findViewById(R.id.accion);
        id = getIntent().getIntExtra("id", 2);
        nombre = getIntent().getStringExtra("nombre");
        correo = getIntent().getStringExtra("correo");
        fecha = getIntent().getStringExtra("fecha");
        genero = getIntent().getStringExtra("genero");
        token = getIntent().getStringExtra("token");
        accio = getIntent().getIntExtra("accion", 1);
        String lugar = "";
        if (accio == 1) {
            lugar = "Gestionar preguntas";
        }else if (accio == 2) {
            lugar = "Preguntas Respondidas";
        }else if (accio == 3) {
            lugar = "Preguntas Rechazadas";
        }
        accion.setText(lugar);
        volver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                volvers();
            }
        });

    }

    private void volvers(){
        if(accio==1){
            Intent i = new Intent(this, InicioDoctor.class);
            i.putExtra("id",  id);
            i.putExtra("correo", correo);
            i.putExtra("fecha", fecha);
            i.putExtra("nombre", nombre);
            i.putExtra("genero", genero);
            i.putExtra("token", token);
            startActivity(i);
            Toast.makeText(this, "Inicio",Toast.LENGTH_SHORT).show();
        }else{
            Intent i = new Intent(this, Cuenta.class);
            i.putExtra("id",  id);
            i.putExtra("correo", correo);
            i.putExtra("fecha", fecha);
            i.putExtra("nombre", nombre);
            i.putExtra("genero", genero);
            i.putExtra("token", token);
            startActivity(i);
            Toast.makeText(this, "Cuenta",Toast.LENGTH_SHORT).show();
        }

    }

}