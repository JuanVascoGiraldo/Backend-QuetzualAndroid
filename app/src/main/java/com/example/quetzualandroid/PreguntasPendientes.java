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

import com.example.quetzualandroid.Interfaces.API;
import com.example.quetzualandroid.Models.mpregunta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PreguntasPendientes extends AppCompatActivity {

    String nombre, correo, fecha, genero, token;
    int id;
    TextView fecha_pre, des_pre;
    Button seguir, atras, respre, rechapre;
    List<mpregunta> pen = new ArrayList<mpregunta>();
    int it = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_pendientes);
        id = getIntent().getIntExtra("id", 2);
        nombre = getIntent().getStringExtra("nombre");
        correo = getIntent().getStringExtra("correo");
        fecha = getIntent().getStringExtra("fecha");
        genero = getIntent().getStringExtra("genero");
        token = getIntent().getStringExtra("token");
        fecha_pre = findViewById(R.id.textrank);
        des_pre = findViewById(R.id.des_pre);
        seguir = findViewById(R.id.seguir);
        atras = findViewById(R.id.atras);
        buscarpen();
        respre = findViewById(R.id.responderpre);
        rechapre = findViewById(R.id.volversin);


        seguir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                seguir();
            }
        });
        atras.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                atras();
            }
        });
        respre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responder();
            }
        });
        rechapre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rechazar();
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
            i.putExtra("correo", correo);
            i.putExtra("fecha", fecha);
            i.putExtra("nombre", nombre);
            i.putExtra("genero", genero);
            i.putExtra("token", token);
            startActivity(i);
            Toast.makeText(this, "Preguntas Pendientes",Toast.LENGTH_SHORT).show();
        }
        else if (idd == R.id.item2){
            Intent i = new Intent(this, Ranking.class);
            i.putExtra("id",  id);
            i.putExtra("correo", correo);
            i.putExtra("fecha", fecha);
            i.putExtra("nombre", nombre);
            i.putExtra("genero", genero);
            i.putExtra("token", token);
            startActivity(i);
            Toast.makeText(this, "Ranking",Toast.LENGTH_SHORT).show();
        }
        else if (idd == R.id.item3){
            Intent i = new Intent(this, Cuenta.class);
            i.putExtra("id",  id);
            i.putExtra("correo", correo);
            i.putExtra("fecha", fecha);
            i.putExtra("nombre", nombre);
            i.putExtra("genero", genero);
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

    public void buscarpen(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apiquetzual.herokuapp.com/quetzual/Doctor/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<List<mpregunta>> call = api.pendientes(token);
        call.enqueue(new Callback<List<mpregunta>>() {
            @Override
            public void onResponse(Call<List<mpregunta>> call, Response<List<mpregunta>> response) {
             if(response.isSuccessful()){
                 pen = response.body();
                 if(pen.size()>0) {
                     mpregunta pre = pen.get(it);
                     fecha_pre.setText(pre.getFecha_pre());
                     des_pre.setText(pre.getDes_pre());
                 }else{
                     sin();
                 }
             }
            }



            @Override
            public void onFailure(Call<List<mpregunta>> call, Throwable t) {

            }
        });
    }
    private void sin() {
        Intent i = new Intent(this, SinPregutasPendientes.class);
        i.putExtra("id",  id);
        i.putExtra("correo", correo);
        i.putExtra("fecha", fecha);
        i.putExtra("nombre", nombre);
        i.putExtra("genero", genero);
        i.putExtra("token", token);
        i.putExtra("accion", 1);
        startActivity(i);
        Toast.makeText(this, "Sin Preguntas",Toast.LENGTH_SHORT).show();
    }

    public void seguir(){
        int size = pen.size()-1;
        if(it != size){
            it++;
            mpregunta pre = pen.get(it);
            fecha_pre.setText(pre.getFecha_pre());
            des_pre.setText(pre.getDes_pre());
        }
    }

    public void atras(){
        if(it != 0){
            it--;
            mpregunta pre = pen.get(it);
            fecha_pre.setText(pre.getFecha_pre());
            des_pre.setText(pre.getDes_pre());
        }
    }

    public  void rechazar(){
        mpregunta pre = pen.get(it);
        Intent i = new Intent(this, rechazar.class);
        i.putExtra("id",  id);
        i.putExtra("correo", correo);
        i.putExtra("fecha", fecha);
        i.putExtra("nombre", nombre);
        i.putExtra("genero", genero);
        i.putExtra("token", token);
        i.putExtra("idpre", pre.getId_pre());
        i.putExtra("despre", pre.getDes_pre());
        i.putExtra("fechapre", pre.getFecha_pre());
        startActivity(i);
        Toast.makeText(this, "Rechazar Pregunta",Toast.LENGTH_SHORT).show();
    }

    public void responder(){
        mpregunta pre = pen.get(it);
        Intent i = new Intent(this, responder.class);
        i.putExtra("id",  id);
        i.putExtra("correo", correo);
        i.putExtra("fecha", fecha);
        i.putExtra("nombre", nombre);
        i.putExtra("genero", genero);
        i.putExtra("token", token);
        i.putExtra("idpre", pre.getId_pre());
        i.putExtra("despre", pre.getDes_pre());
        i.putExtra("fechapre", pre.getFecha_pre());
        startActivity(i);
        Toast.makeText(this, "Responder Pregunta",Toast.LENGTH_SHORT).show();
    }
}