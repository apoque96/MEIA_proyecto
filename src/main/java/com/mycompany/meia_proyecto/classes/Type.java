package com.mycompany.meia_proyecto.classes;

public class Type extends Maintenance {
    private final String type;
    private String year;

    public Type(String type, String year) {
        this.type = type;
        this.year = year;
    }

    @Override
    public String getPk() {
        return this.type;
    }

    @Override
    public String getPKNameType() {
        return "Tipo";
    }

    @Override
    public String getName() {
        return "Tipo";
    }

    @Override
    public String toString() {
        return type + "," + year;
    }
}
