package com.example.quetzualandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quetzualandroid.Interfaces.API;
import com.example.quetzualandroid.Models.Validar;
import com.example.quetzualandroid.Models.mrespuesta;

import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class responder extends AppCompatActivity {

    String nombre, correo, fecha, genero,  despre, fechapre, fechact, token;
    int id, idpre;
    TextView despres, fechactual;
    EditText respuesta;
    Button responder, cancelar;
    RadioButton cat1, cat2, cat3, cat4, cat5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder);
        id = getIntent().getIntExtra("id", 2);
        nombre = getIntent().getStringExtra("nombre");
        correo = getIntent().getStringExtra("correo");
        fecha = getIntent().getStringExtra("fecha");
        genero = getIntent().getStringExtra("genero");
        idpre = getIntent().getIntExtra("idpre", 2);
        despre = getIntent().getStringExtra("despre");
        fechapre =getIntent().getStringExtra("fechapre");
        token = getIntent().getStringExtra("token");
        Calendar fe = java.util.Calendar.getInstance();
        fechact=fe.get(java.util.Calendar.DATE) + "/"
                + (fe.get(java.util.Calendar.MONTH)+1) + "/"
                + fe.get(java.util.Calendar.YEAR);
        despres = findViewById(R.id.despreres);
        fechactual = findViewById(R.id.fechaactual);
        respuesta = findViewById(R.id.respuesta);
        responder = findViewById(R.id.respreg);
        cancelar = findViewById(R.id.cance);
        fechactual.setText(fechact);
        despres.setText(despre);
        cat1 = findViewById(R.id.cat1);
        cat2 = findViewById(R.id.cat2);
        cat3 = findViewById(R.id.cat3);
        cat4 = findViewById(R.id.cat4);
        cat5 = findViewById(R.id.cat5);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        responder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responder();
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

    public void cancel(){
        Intent i = new Intent(this, PreguntasPendientes.class);
        i.putExtra("id",  id);
        i.putExtra("correo", correo);
        i.putExtra("fecha", fecha);
        i.putExtra("nombre", nombre);
        i.putExtra("token", token);
        i.putExtra("genero", genero);
        startActivity(i);
        Toast.makeText(this, "Preguntas Pendientes",Toast.LENGTH_SHORT).show();
    }

    public void responder() {
        boolean seguir = true;
        mrespuesta res = new mrespuesta();
        res.setId_pre(idpre);
        res.setFecha_res(fechact);
        res.setDes_res(respuesta.getText().toString());
        res.setId_usures(id);
        if (cat1.isChecked()) {
            res.setId_cat(1);
        } else if (cat2.isChecked()) {
            res.setId_cat(2);
        } else if (cat3.isChecked()) {
            res.setId_cat(3);
        } else if (cat4.isChecked()) {
            res.setId_cat(4);
        } else if (cat5.isChecked()) {
            res.setId_cat(5);
        } else {
            seguir = false;
        }

        if (!seguir) {
            Toast.makeText(this, "Elije una Clasificación", Toast.LENGTH_SHORT).show();
        }

        if(seguir && conres(res.getDes_res())){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://apiquetzual.herokuapp.com/quetzual/Doctor/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API api = retrofit.create(API.class);
            Call<ResponseBody> call = api.responder(res, token);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        cancel();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
    }

    private boolean conres(String respuesta){
        boolean seguir = Validar.Validarpregunta(respuesta);
        if(!seguir){
            Toast.makeText(this, "Ingresa Caracteres Validos en la respuesta",Toast.LENGTH_SHORT).show();
        }
        return seguir;
    }

}