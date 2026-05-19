/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author User Gamer
 */

// ESTA CLASE REPRESENTA UN ESLABON DEL BRAZO ROBOTICO
public class Eslabon {
    private double longitud;
    private double angulo; // para definir completamente a un eslabon necesitamos su longitud y el angulo que forma con el eje x

    public Eslabon(double longitud, double angulo) {
        this.longitud = longitud;
        this.angulo = angulo;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }
    
}
