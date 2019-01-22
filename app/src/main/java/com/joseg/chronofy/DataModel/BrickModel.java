package com.joseg.chronofy.DataModel;

/**
 * Created by Jose on 28/08/2017.
 */

public class BrickModel {
    // TODO Hacer documentación
    // TODO Hará falta añadir una variable que indique si es un brick bucle o tiempo, y las variables de ambos
    // De momento solo tiene un String y un int de prueba

    private String name;
    private String description;
    private int type;

    public BrickModel(String name, String description, int type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getType() {
        return type;
    }
}
