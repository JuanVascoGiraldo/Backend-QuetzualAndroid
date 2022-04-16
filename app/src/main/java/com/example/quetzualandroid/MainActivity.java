package com.example.quetzualandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quetzualandroid.Interfaces.API;
import com.example.quetzualandroid.Models.Cifrado;
import com.example.quetzualandroid.Models.Validar;
import com.example.quetzualandroid.Models.usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText correo, contra;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        correo = findViewById(R.id.correo);
        contra = findViewById(R.id.contra);
        boton = findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    iniciar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void iniciar() throws Exception {
        if (comemail(correo.getText().toString()) && compass(contra.getText().toString())){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://apiquetzual.herokuapp.com/quetzual/Doctor/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API api = retrofit.create(API.class);
            usuario usu = new usuario();
            usuario user = new usuario();
            final boolean[] seguir = {false};
            Cifrado cifrado = new Cifrado();
            usu.setContra_usu(cifrado.encrypt(contra.getText().toString()));
            usu.setEmail_usu(cifrado.encrypt(correo.getText().toString()));
            System.out.println(usu.getEmail_usu() + "   " + usu.getContra_usu());
            Call<usuario> call = api.iniciarsesion(usu);
            String[] sts = new String[2];
            call.enqueue(new Callback<usuario>() {
                @Override
                public void onResponse(Call<usuario> call, Response<usuario> response) {
                    if (response.isSuccessful()) {
                        usuario usuer = response.body();
                        sts[0] = usuer.getStatus();
                        if (sts[0].equals("encontrado")) {
                            user.setId_usu(usuer.getId_usu());
                            user.setEmail_usu(usuer.getEmail_usu());
                            user.setNom_usu(usuer.getNom_usu());
                            user.setId_gen(usuer.getId_gen());
                            user.setFecha_nac(usuer.getFecha_nac());
                            user.setToken(usuer.getToken());
                            ir(user);
                        } else {
                            no();
                        }
                    }
                }

                @Override
                public void onFailure(Call<usuario> call, Throwable t) {

                }
            });
        }
    }



    public void ir(usuario user){
        Intent i = new Intent(this, InicioDoctor.class);
        String nombre = "";
        String correo = "";

        try{
            nombre = Cifrado.decrypt(user.getNom_usu());
            correo = Cifrado.decrypt(user.getEmail_usu() );
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        i.putExtra("id",  user.getId_usu());
        i.putExtra("correo", correo);
        i.putExtra("fecha", user.getFecha_nac());
        i.putExtra("nombre", nombre);
        String genero = "";
        if(user.getId_gen() == 1){
            genero = "Prefiero no Decirlo";
        }else if(user.getId_gen()== 2){
            genero = "femenino";
        }else{
            genero = "masculino";
        }
        i.putExtra("genero", genero);
        i.putExtra("token", user.getToken());

        startActivity(i);

    }

    private boolean comemail(String tcorreo){
        boolean seguir = Validar.Validarcorreo(tcorreo);
        if(!seguir){
            Toast.makeText(this, "Correo no Valido",Toast.LENGTH_SHORT).show();
        }
        return seguir;
    }

    private boolean compass(String tpass){
        boolean seguir = Validar.Validarcontra(tpass);
        if(!seguir){
            Toast.makeText(this, "Contraseña no Valida",Toast.LENGTH_SHORT).show();
        }
        return seguir;
    }


    public void no(){
        Toast.makeText(this, "Doctor no encontrado",Toast.LENGTH_SHORT).show();
    }
}