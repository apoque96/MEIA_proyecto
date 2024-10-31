package com.mycompany.meia_proyecto.classes;

public class Line extends Maintenance {
    private final String model;
    private String description;
    private String year;

    public Line(String model, String description, String year) {
        this.model = model;
        this.description = description;
        this.year = year;
    }

    @Override
    public String getPk() {
        return this.model + "_" + this.description;
    }

    @Override
    public String getPKNameType() {
        return "Modelo";
    }

    @Override
    public String getName() {
        return "Linea";
    }

    @Override
    public String toString() {
        return model + "," + description + "," + year;
    }
}
