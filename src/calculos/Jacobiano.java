/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculos;

import modelo.BrazoRobot;
import modelo.Eslabon;


/**
 *
 * @author User Gamer
 */
public class Jacobiano {
     public static double[][] calculo(BrazoRobot robot){

        Eslabon e1=robot.getEslabones().get(0);
        Eslabon e2=robot.getEslabones().get(1);

        double L1=e1.getLongitud();
        double L2=e2.getLongitud();

        double t1=e1.getAngulo();
        double t2=e2.getAngulo();

        double[][]J=new double[2][2];

        J[0][0] = -L1*Math.sin(t1) - L2*Math.sin(t1+t2);
        J[0][1] = -L2*Math.sin(t1+t2);

        J[1][0] =  L1*Math.cos(t1) + L2*Math.cos(t1+t2);
        J[1][1] =  L2*Math.cos(t1+t2);

        return J;
    }
    
}
