package com.mycompany.meia_proyecto.classes;

public class RecordInfo {
    String pk;
    long index;
    int length;
    long indexToNext;

    RecordInfo(String pk, long index, int length) {
        this.pk = pk;
        this.index = index;
        this.length = length+2;
        this.indexToNext = -1;  // Default to -1 (end of list)
    }
}
