package com.mycompany.meia_proyecto.classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class FileManagerTest {

    @Test
    void updateIndexesFile() throws IOException {
        String path = "marcas_vehiculos.txt";
        String indexesPath = path.substring(0, path.length() - 4) + "_index.txt";

        FileManager.updateIndexesFile(path);

        List<String> lines = Files.readAllLines(Paths.get(indexesPath));

        Assertions.assertEquals(23, Integer.valueOf(lines.get(0).trim()));
        Assertions.assertEquals("Honda,0,29,-1", lines.get(1).trim());
        Assertions.assertEquals("GMC,29,24,6", lines.get(2).trim());

        FileManager.updateIndexesFile("Registro_Vehiculos.txt");
        FileManager.updateIndexesFile("types.txt");
        FileManager.updateIndexesFile("lineas.txt");
    }

    @Test
    void getDataByPK() throws IOException {
        String path = "marcas_vehiculos.txt";

        Assertions.assertEquals("Honda,0,29,-1",
                FileManager.getDataByPK("Honda", path));
        Assertions.assertEquals("GMC,29,24,6",
                FileManager.getDataByPK("GMC", path));
    }
}