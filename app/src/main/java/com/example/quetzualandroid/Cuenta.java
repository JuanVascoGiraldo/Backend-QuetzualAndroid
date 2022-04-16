package com.example.quetzualandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Cuenta extends AppCompatActivity {

    String nombres, correos, fechas, generos, token;
    int id;
    TextView nombret, correot, fechat, generot;
    Button res, rech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);


        id = getIntent().getIntExtra("id", 2);
        nombres = getIntent().getStringExtra("nombre");
        correos = getIntent().getStringExtra("correo");
        fechas = getIntent().getStringExtra("fecha");
        generos = getIntent().getStringExtra("genero");
        token = getIntent().getStringExtra("token");

        nombret = findViewById(R.id.accion);
        correot = findViewById(R.id.email);
        fechat = findViewById(R.id.fechanac);
        generot = findViewById(R.id.genero);

        nombret.setText(nombres);
        correot.setText(correos);
        fechat.setText(fechas);
        generot.setText(generos);

        res = findViewById(R.id.hisress);
        rech = findViewById(R.id.hisrech);

        System.out.println(id);

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               irresp();
            }
        });

        rech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irrec();
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int idd = item.getItemId();
        if (idd == R.id.item1){
            Intent i = new Intent(this, PreguntasPendientes.class);
            i.putExtra("id",  id);
            i.putExtra("correo", correos);
            i.putExtra("fecha", fechas);
            i.putExtra("nombre", nombres);
            i.putExtra("genero", generos);
            i.putExtra("token", token);
            startActivity(i);
            Toast.makeText(this, "Preguntas Pendientes",Toast.LENGTH_SHORT).show();
        }
        else if (idd == R.id.item2){
            Intent i = new Intent(this, Ranking.class);
            i.putExtra("id",  id);
            i.putExtra("correo", correos);
            i.putExtra("fecha", fechas);
            i.putExtra("nombre", nombres);
            i.putExtra("genero", generos);
            i.putExtra("token", token);
            startActivity(i);
            Toast.makeText(this, "Ranking",Toast.LENGTH_SHORT).show();
        }
        else if (idd == R.id.item3){
            Intent i = new Intent(this, Cuenta.class);
            i.putExtra("id",  id);
            i.putExtra("correo", correos);
            i.putExtra("fecha", fechas);
            i.putExtra("nombre", nombres);
            i.putExtra("genero", generos);
            i.putExtra("token", token);
            startActivity(i);
            Toast.makeText(this, "Cuenta",Toast.LENGTH_SHORT).show();
        }else if (idd == R.id.item4){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            Toast.makeText(this, "Inicio",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void irresp(){
        Intent i = new Intent(this, respondidas.class);
        i.putExtra("id",  id);
        i.putExtra("correo", correos);
        i.putExtra("fecha", fechas);
        i.putExtra("nombre", nombres);
        i.putExtra("genero", generos);
        i.putExtra("token", token);
        startActivity(i);
        Toast.makeText(this, "Preguntas Respondidas",Toast.LENGTH_SHORT).show();
    }

    public void irrec(){
        Intent i = new Intent(this, rechazadas.class);
        i.putExtra("id",  id);
        i.putExtra("correo", correos);
        i.putExtra("fecha", fechas);
        i.putExtra("nombre", nombres);
        i.putExtra("genero", generos);
        i.putExtra("token", token);
        startActivity(i);
        Toast.makeText(this, "Preguntas Rechazadas",Toast.LENGTH_SHORT).show();
    }
}