package com.mycompany.meia_proyecto.classes;

public class RecordInfo {
    String pk;
    long index;
    int length;
    String indexToNext;

    RecordInfo(String pk, long index, int length) {
        this.pk = pk;
        this.index = index;
        this.length = length+2;
        this.indexToNext = "     ";
    }
}
