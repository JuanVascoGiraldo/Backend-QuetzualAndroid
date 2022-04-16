package com.example.quetzualandroid.Models;

public class usuario implements Comparable<usuario>{
    String nom_usu, fecha_nac, email_usu, contra_usu, status, token;
    int id_usu, id_gen, cant_punt;

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getEmail_usu() {
        return email_usu;
    }

    public void setEmail_usu(String email_usu) {
        this.email_usu = email_usu;
    }

    public String getContra_usu() {
        return contra_usu;
    }

    public void setContra_usu(String contra_usu) {
        this.contra_usu = contra_usu;
    }

    public int getId_usu() {
        return id_usu;
    }

    public void setId_usu(int id_usu) {
        this.id_usu = id_usu;
    }

    public int getId_gen() {
        return id_gen;
    }

    public void setId_gen(int id_gen) {
        this.id_gen = id_gen;
    }

    public int getCant_punt() {
        return cant_punt;
    }

    public void setCant_punt(int cant_punt) {
        this.cant_punt = cant_punt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int compareTo(usuario o) {
        if (cant_punt < o.cant_punt) {
            return -1;
        }
        if (cant_punt > o.cant_punt) {
            return 1;
        }
        return 0;
    }
}
