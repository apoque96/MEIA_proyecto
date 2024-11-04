package com.mycompany.meia_proyecto.classes;

public class Vehicle extends Maintenance {
    private final String plate;
    public String model;
    public String type;
    public String description;

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
    public String getName() {
        return "Vehiculo";
    }

    @Override
    public String toString() {
        return plate + "," + model + "," + type + "," + description;
    }
}
