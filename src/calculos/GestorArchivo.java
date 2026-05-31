/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculos;

/**
 *
 * @author User Gamer
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestorArchivo {

    private static final String NOMBRE_ARCHIVO = "robot.txt";

    public static void guardarConfiguracion(double l1, double l2, double x, double y) { // aca guardaremos
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            bw.write(l1 + "," + l2 + "," + x + "," + y);
            System.out.println("Configuracion guardada en " + NOMBRE_ARCHIVO);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static double[] cargarConfiguracion() {
        File archivo = new File(NOMBRE_ARCHIVO);
        if (!archivo.exists()) {
            return new double[]{140, 110, 120, 120}; // le damos uno por defecto!
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            if (linea != null) {
                String[] datos = linea.split(","); // aca lo separamos por coma
                double l1 = Double.parseDouble(datos[0]);
                double l2 = Double.parseDouble(datos[1]);
                double x = Double.parseDouble(datos[2]);
                double y = Double.parseDouble(datos[3]);
                return new double[]{l1, l2, x, y}; // lo guardamos como arreglo
            }
        } catch (Exception e) {
            System.err.println("Error al cargar el archivo, usando valores por defecto: " + e.getMessage());
        }
        return new double[]{140, 110, 120, 120};
    }
}