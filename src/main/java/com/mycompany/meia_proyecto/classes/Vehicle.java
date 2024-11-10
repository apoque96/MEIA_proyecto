package com.mycompany.meia_proyecto.classes;

public class Vehicle extends Maintenance {
    private final String vin;
    public String plate;
    public String model;
    public String type;
    public String description;

    public Vehicle(String vin, String model, String type, String description, String plate) {
        this.vin = vin;
        this.model = model;
        this.type = type;
        this.description = description;
        this.plate = plate;
    }

    @Override
    public String getPk() {
        return this.vin;
    }

    @Override
    public String getName() {
        return "Vehiculo";
    }

    @Override
    public String toString() {
        return vin + "," + plate + "," + model + "," + type + "," + description;
    }
}
