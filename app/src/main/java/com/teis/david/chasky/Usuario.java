package com.teis.david.chasky;

/**
 * Created by jgavi on 31/05/2017.
 */

public class Usuario {
    private String token;
    private int id;
    private String nombre;
    private String correo;
    private static Usuario usuario;

    private Usuario(String token, int id, String nombre, String correo){
        this.token = token;
        this.id = id;
        this. nombre = nombre;
        this.correo = correo;
    }

    public static Usuario solicitarUsuario(){
        return Usuario.usuario;
    }

    public static void crearUsuario(String token, int id, String nombre, String correo){
        Usuario.usuario = new Usuario(token, id, nombre, correo);
    }

    public int getId(){
        return this.id;
    }
}
