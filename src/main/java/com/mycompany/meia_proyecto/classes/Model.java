package com.mycompany.meia_proyecto.classes;

public class Model extends Maintenance{
    private final String model;
    public String year;
    public String founder;

    public Model(String model, String year, String founder) {
        this.model = model;
        this.year = year;
        this.founder = founder;
    }

    @Override
    public String getPk() {
        return this.model;
    }

    @Override
    public  String getName(){
        return "Modelo";
    }

    @Override
    public String toString(){
        return model + "," + year + "," + founder;
    }
}
