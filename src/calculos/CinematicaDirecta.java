/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculos;

import modelo.BrazoRobot;
import modelo.Eslabon;
import modelo.Punto;

/**
 *
 * @author User Gamer
 */
public class CinematicaDirecta {
    
 
    public static Punto calcularPunto(BrazoRobot brazo){
        double x = 0;
        double y = 0;
        double sumaAngulos = 0;
        for (Eslabon eslabone : brazo.getEslabones()) {
            sumaAngulos += eslabone.getAngulo();
            x += eslabone.getLongitud()*Math.cos(sumaAngulos);
            y += eslabone.getLongitud()*Math.sin(sumaAngulos);
        }
        
        return new Punto(x,y);
            
        }
        
    }
    

