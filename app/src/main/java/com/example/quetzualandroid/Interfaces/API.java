package com.example.quetzualandroid.Interfaces;

import com.example.quetzualandroid.Models.mes;
import com.example.quetzualandroid.Models.mpregunta;
import com.example.quetzualandroid.Models.mrespuesta;
import com.example.quetzualandroid.Models.publi;
import com.example.quetzualandroid.Models.status;
import com.example.quetzualandroid.Models.usuario;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    @POST("Iniciar/Sesion/Validar")
    Call<usuario> iniciarsesion(@Body usuario usu);

    @POST("Ranking/Mensual")
    Call<List<usuario>> ranking(@Body mes mess, @Header("token") String token);

    @GET("Pendientes")
    Call<List<mpregunta>> pendientes(@Header("token") String token);//@Header("token") String token);

    @POST("Pregunta/Rechazar")
    Call<ResponseBody> rechazar(@Body mrespuesta res, @Header("token") String token);

    @POST("Pregunta/Responder")
    Call<ResponseBody> responder(@Body mrespuesta res, @Header("token") String token);

    @GET("Historico/Doctor/Respondidas")
    Call<List<publi>> respondidas(@Header("token") String token);

    @GET("Historico/Doctor/Rechazadas")
    Call<List<publi>> rechazadas(@Header("token") String token);

    @POST("Ranking/Historico")
    Call<List<usuario>> rankinghistorico(@Header("token") String token);

}
