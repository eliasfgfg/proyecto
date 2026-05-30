/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Excepciones.LongitudInvalida;

/**
 *
 * @author User Gamer
 */

// ESTA CLASE REPRESENTA UN ESLABON DEL BRAZO ROBOTICO
public class Eslabon {
    private double longitud;
    private double angulo; 
    private double anguloMin;
    private double anguloMax; // cuanto podria girar cada eslabon podriamos decidir nosotros

    public Eslabon(double longitud, double angulo) throws LongitudInvalida {
        if(longitud <=0){
            throw new LongitudInvalida();
        } else{
        this.longitud = longitud;
        this.angulo = angulo;
        anguloMin = -Math.PI;
        anguloMax = Math.PI;
        }
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) throws LongitudInvalida {
        if(longitud <=0){
            throw new LongitudInvalida();
        } 
        
        this.longitud = longitud;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        if(angulo < anguloMin){
            this.angulo = anguloMin;
        } else if(angulo > anguloMax){
            this.angulo = anguloMax;
        } else{
            this.angulo = angulo;
        }
    }

    public double getAnguloMin() {
        return anguloMin;
    }

    public void setAnguloMin(double anguloMin) {
        this.anguloMin = anguloMin;
    }

    public double getAnguloMax() {
        return anguloMax;
    }

    public void setAnguloMax(double anguloMax) {
        this.anguloMax = anguloMax;
    }
}
