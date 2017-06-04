package com.teis.david.chasky;

/**
 * Created by DAVID on 30/05/2017.
 */

public class Natillera {

    private int ahorro;
    private int pagos;
    private int cantAhorrada;
    private String name;
    private long id;
    private int color;
    private int userId;

    public Natillera(int ahorro, int pagos, int cantAhorrada, String name, long id, int color, int userId){
        this.ahorro = ahorro;
        this.pagos = pagos;
        this.cantAhorrada = cantAhorrada;
        this.name = name;
        this.id = id;
        this.color = color;
        this.userId = userId;
    }


    public int getAhorro() {
        return ahorro;
    }

    public void setAhorro(int ahorro) {
        this.ahorro = ahorro;
    }

    public int getPagos() {
        return pagos;
    }

    public void setPagos(int pagos) {
        this.pagos = pagos;
    }

    public int getCantAhorrada() {
        return cantAhorrada;
    }

    public void setCantAhorrada(int cantAhorrada) {
        this.cantAhorrada = cantAhorrada;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getColorResource() {
        return color;
    }

    public void setColorResource(int color_resource) {
        this.color = color_resource;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
