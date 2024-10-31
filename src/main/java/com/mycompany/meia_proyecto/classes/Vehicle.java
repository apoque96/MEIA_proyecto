package com.mycompany.meia_proyecto.classes;

public class Vehicle extends Maintenance {
    private final String plate;
    private String model;
    private String type;
    private String description;

    public Vehicle(String model, String type, String description, String plate) {
        this.model = model;
        this.type = type;
        this.description = description;
        this.plate = plate;
    }

    @Override
    public String getPk() {
        return this.plate;
    }

    @Override
    public String getPKNameType() {
        return "Placa";
    }

    @Override
    public String getName() {
        return "Vehiculo";
    }

    @Override
    public String toString() {
        return plate + "," + model + "," + type + "," + description;
    }
}
