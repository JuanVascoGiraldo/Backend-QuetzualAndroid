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
import com.example.quetzualandroid.Models.publi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class respondidas extends AppCompatActivity {

    String nombre, correo, fecha, genero, token;
    int id, it = 0;
    TextView despre, cat, desres;
    Button seguir, atras, volver;
    List<publi> recha = new ArrayList<publi>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respondidas);
        id = getIntent().getIntExtra("id", 2);
        nombre = getIntent().getStringExtra("nombre");
        correo = getIntent().getStringExtra("correo");
        fecha = getIntent().getStringExtra("fecha");
        genero = getIntent().getStringExtra("genero");
        token = getIntent().getStringExtra("token");

        despre = findViewById(R.id.despre);
        desres = findViewById(R.id.desres);
        cat = findViewById(R.id.catres);

        resp();
        seguir = findViewById(R.id.seguirr);
        atras = findViewById(R.id.atrasr);
        volver = findViewById(R.id.volverr);

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

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver();
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

    public void resp(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apiquetzual.herokuapp.com/quetzual/Doctor/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<List<publi>> call = api.respondidas(token);
        call.enqueue(new Callback<List<publi>>() {
            @Override
            public void onResponse(Call<List<publi>> call, Response<List<publi>> response) {
                if(response.isSuccessful()){
                    recha = response.body();
                    System.out.println(recha.size());
                    if(recha.size() > 0){
                        publi pu = recha.get(it);
                        despre.setText(pu.getDes_pre());
                        desres.setText(pu.getDes_res());
                        String categoria = "";
                        if(pu.getId_cat() == 1){
                            categoria = "ETS";
                        } else if(pu.getId_cat() == 2){
                            categoria = "Embarazo";
                        } else if(pu.getId_cat() == 3){
                            categoria = "Salud sexual femenina";
                        }else if(pu.getId_cat() == 4){
                            categoria = "Salud sexual masculina";
                        }else if(pu.getId_cat() == 5){
                            categoria = "Anticonceptivos";
                        }
                        cat.setText(categoria);
                    }else{
                        sin();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<publi>> call, Throwable t) {

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
        i.putExtra("accion", 2);
        startActivity(i);
        Toast.makeText(this, "Sin Preguntas",Toast.LENGTH_SHORT).show();
    }

    public void seguir(){
        int size = recha.size()-1;
        if(it != size){
            it++;
            publi pu = recha.get(it);
            despre.setText(pu.getDes_pre());
            desres.setText(pu.getDes_res());
            String categoria = "";
            if(pu.getId_cat() == 1){
                categoria = "ETS";
            } else if(pu.getId_cat() == 2){
                categoria = "Embarazo";
            } else if(pu.getId_cat() == 3){
                categoria = "Salud sexual femenina";
            }else if(pu.getId_cat() == 4){
                categoria = "Salud sexual masculina";
            }else if(pu.getId_cat() == 5){
                categoria = "Anticonceptivos";
            }
            cat.setText(categoria);
        }
    }

    public void atras() {
        if (it != 0) {
            it--;
            publi pu = recha.get(it);
            despre.setText(pu.getDes_pre());
            desres.setText(pu.getDes_res());
            String categoria = "";
            if(pu.getId_cat() == 1){
                categoria = "ETS";
            } else if(pu.getId_cat() == 2){
                categoria = "Embarazo";
            } else if(pu.getId_cat() == 3){
                categoria = "Salud sexual femenina";
            }else if(pu.getId_cat() == 4){
                categoria = "Salud sexual masculina";
            }else if(pu.getId_cat() == 5){
                categoria = "Anticonceptivos";
            }
            cat.setText(categoria);
        }
    }

    public  void volver(){
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