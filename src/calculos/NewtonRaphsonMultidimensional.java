
package calculos;

import modelo.BrazoRobot;
import modelo.Punto;

/**
 *
 * @author User Gamer
 */
public class NewtonRaphsonMultidimensional {

    private static final double TOLERANCIA = 0.001;
    private static final double MAX_PASO = 0.1;

    public static boolean iterar(BrazoRobot robot,Punto objetivo){

        double[] F = sistemaError(robot, objetivo);

        double error = norma(F);

        if(error < TOLERANCIA){
            return true;
        }

        double[][] J = Jacobiano.calculo(robot);

        double[] menosF = {-F[0],-F[1]};

        double[] delta = resolverSistema(J, menosF);
        if ( delta == null){
            return true;
        }

        limitarPaso(delta);

        actualizarAngulo(robot, delta);

        return false;
    }

    private static double[] sistemaError( BrazoRobot robot,Punto objetivo){
        
        Punto actual = CinematicaDirecta.calcularPunto(robot);

        double errorX = actual.getX() - objetivo.getX();

        double errorY = actual.getY() - objetivo.getY();

        return new double[]{errorX,errorY};
    }

    private static double norma(double[] v){

        return Math.sqrt(v[0]*v[0]+ v[1]*v[1]);
    }

    private static double[] resolverSistema(double[][] J,double[] b){
        
        double a = J[0][0];
        double bb = J[0][1];
        double c = J[1][0];
        double d = J[1][1];
        double det = a*d - bb*c;

        if(Math.abs(det) < 1e-8){
            return null;
        }
        double[] x = new double[2];
        x[0] =(d*b[0] - bb*b[1])/det;
        x[1] =(-c*b[0] + a*b[1])/det;
        return x;
    }

    private static void limitarPaso(double[] delta){
        delta[0] =Math.max(-MAX_PASO,Math.min(MAX_PASO,delta[0]));
        delta[1] =Math.max(-MAX_PASO,Math.min(MAX_PASO,delta[1]));
    }

    private static void actualizarAngulo(
        BrazoRobot robot,double[] delta)
    {
        robot.getEslabones().get(0).setAngulo(robot.getEslabones().get(0).getAngulo()+ delta[0]);
        robot.getEslabones().get(1).setAngulo(robot.getEslabones().get(1).getAngulo()+ delta[1]);
    }
}