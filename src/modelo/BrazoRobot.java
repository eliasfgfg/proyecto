/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User Gamer
 */
public class BrazoRobot {
    
    private List<Eslabon> eslabones;
    private Punto objetivo;

    public BrazoRobot() {
        eslabones = new ArrayList<>();
        objetivo = new Punto(0,0);
    }
    
    public void agregarEslabon(Eslabon e){
    eslabones.add(e);
    }
    
    public void quitarEslabon(int indice){
        if(indice<0 || indice >= eslabones.size()){
            throw new IndexOutOfBoundsException("Indice no valido");
        }
        else{
            eslabones.remove(indice);
        }
    }

    public List<Eslabon> getEslabones() {
        return eslabones;
    }


    public Punto getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Punto objetivo) {
        this.objetivo = objetivo;
    }
    
    public double AlcanceMaximo(){
        double suma = 0;
        for (Eslabon eslabone : eslabones) {
            suma+= eslabone.getLongitud();
        }
        return suma;
    }
    
    
    
}
