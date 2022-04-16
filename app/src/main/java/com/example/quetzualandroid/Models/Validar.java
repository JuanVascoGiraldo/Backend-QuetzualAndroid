package com.example.quetzualandroid.Models;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {

    private static final String ExpCorreo = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    private static final String Expnombre = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð]+$";
    private static final String ExpTema = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ]+$";
    private static final String ExpFecha = "^(\\d{4})(\\/|-)(\\d{1,2})(\\/|-)(\\d{1,2})$";
    private static final String ExpContra = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$";
    private static final String ExpPregunta = "^[a-zA-Z0-9àáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑ \\? \\¿,\\.]+$";
    private static final String ExpFechapre = "^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$";
    private static final String ExpGenero = "^[123]$";
    private static final String ExpFiltro = "^[0-9]$";

    public static boolean Validarcorreo(String correo){
        Pattern pattern = Pattern.compile(ExpCorreo);
        Matcher matcher = pattern.matcher(correo);
        if(matcher.matches()){
            if(correo.length() > 40 || correo.length() < 10){
                System.out.println("Tamaño del coreeo no valido");
                return false;
            }else{
                return true;
            }
        }else{
            System.out.println("Correo no valido");
            return false;
        }
    }
    public static boolean Validarcontra(String contra){
        Pattern pattern = Pattern.compile(ExpContra);
        Matcher matcher = pattern.matcher(contra);
        return matcher.matches();
    }


    public static boolean Validarpregunta(String pregunta){
        Pattern pattern = Pattern.compile(ExpPregunta);
        Matcher matcher = pattern.matcher(pregunta);
        if(matcher.matches()){
            if(pregunta.length() > 200 || pregunta.length() < 11){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }




}
