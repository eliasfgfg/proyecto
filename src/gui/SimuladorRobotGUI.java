
package gui;
import Excepciones.LongitudInvalida;
import calculos.CinematicaDirecta;
import calculos.NewtonRaphsonMultidimensional;
import modelo.BrazoRobot;
import modelo.Eslabon;
import modelo.Punto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author User Gamer
 */
public class SimuladorRobotGUI extends JFrame {
    private BrazoRobot robot;
    private Punto puntoObjetivo;
    private Timer temporizador;
    private JTextField txtL1, txtL2; 
    private JTextField txtX, txtY;   
    private JButton btnCalcular;
    private PanelDibujo panelDibujo; // clase privada algo nuevo que tuve que aprender XD

    public SimuladorRobotGUI() {
        try {
            robot = new BrazoRobot();
            robot.agregarEslabon(new Eslabon(140, Math.toRadians(45))); 
            robot.agregarEslabon(new Eslabon(110, Math.toRadians(30)));  // ponemos ya un brazo por defecto
        } catch (LongitudInvalida e) {
        }
        puntoObjetivo = CinematicaDirecta.calcularPunto(robot); // calculamos el punto del efector dado
        setTitle("Simulador Brazo Robot");
        setSize(850, 680); // tamaño de nuestra ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout()); 

        JPanel panelControles = new JPanel();
        panelControles.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // creamos un panel arriba para cargar los datos
        
        panelControles.add(new JLabel("Largo Eslabon 1:"));
        txtL1 = new JTextField("140", 4); // aca es donde cargaremos los datos 
        panelControles.add(txtL1);

        panelControles.add(new JLabel("Largo Eslabon 2:"));
        txtL2 = new JTextField("110", 4);
        panelControles.add(txtL2);

        panelControles.add(new JLabel(" | "));

        panelControles.add(new JLabel("X Objetivo:"));
        txtX = new JTextField("120", 4);
        panelControles.add(txtX);

        panelControles.add(new JLabel("Y Objetivo:"));
        txtY = new JTextField("120", 4);
        panelControles.add(txtY);

        btnCalcular = new JButton("Configurar y simular");
        panelControles.add(btnCalcular); // nuestro boton para hcaer que funcione todo

        add(panelControles, BorderLayout.NORTH);

        panelDibujo = new PanelDibujo();
        add(panelDibujo, BorderLayout.CENTER);
        temporizador = new Timer(100, new ActionListener() { // cada 100 milisegundos se realizara esta accion un vez tocado el boton
            @Override
            public void actionPerformed(ActionEvent e) {  // llama una vez al newton raphson y redibujara el panel!
                boolean llego = NewtonRaphsonMultidimensional.iterar(robot, puntoObjetivo);
                panelDibujo.repaint();
            }
        });
        btnCalcular.addActionListener(new ActionListener() { // empezamos a configuar que hara el boton 
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double nuevaLongitud1 = Double.parseDouble(txtL1.getText()); // pasamos a double nuestro texto 
                    double nuevaLongitud2 = Double.parseDouble(txtL2.getText());
                    robot.getEslabones().get(0).setLongitud(nuevaLongitud1);
                    robot.getEslabones().get(1).setLongitud(nuevaLongitud2);
                    double xDestino = Double.parseDouble(txtX.getText());
                    double yDestino = Double.parseDouble(txtY.getText());
                    double alcanceMaximo = nuevaLongitud1 + nuevaLongitud2;
                    double distanciaAlObjetivo = Math.sqrt(xDestino * xDestino + yDestino * yDestino);
                    if (distanciaAlObjetivo > alcanceMaximo) {
                        panelDibujo.repaint(); 
                        JOptionPane.showMessageDialog(null, 
                            "ERROR: El punto (" + xDestino + ", " + yDestino + ") no es alcanzable.\n");
                        return; 
                    }
                    puntoObjetivo = new Punto(xDestino, yDestino);
                    temporizador.start(); // aca empieza la simulacion
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "favor, ingresar un nro valido");
                } catch (LongitudInvalida ex) {
                    JOptionPane.showMessageDialog(null,"Longitud Inválida");
                }
            }
        });
    }

    private class PanelDibujo extends JPanel {
        public PanelDibujo() { // el  "lienzo"
            setBackground(Color.WHITE); 
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            int origenX = getWidth() / 2;       
            int origenY = getHeight() / 2 + 50; 
            g2.setColor(new Color(235, 238, 242)); 
            g2.setStroke(new BasicStroke(1f));
            for (int i = origenX; i < getWidth(); i += 40) g2.drawLine(i, 0, i, getHeight());
            for (int i = origenX; i > 0; i -= 40) g2.drawLine(i, 0, i, getHeight());
            for (int j = origenY; j < getHeight(); j += 40) g2.drawLine(0, j, getWidth(), j);
            for (int j = origenY; j > 0; j -= 40) g2.drawLine(0, j, getWidth(), j);

            g2.setColor(Color.LIGHT_GRAY);
            g2.setStroke(new BasicStroke(2f));
            g2.drawLine(0, origenY, getWidth(), origenY); 
            g2.drawLine(origenX, 0, origenX, getHeight()); 
            g2.setColor(Color.RED);
            int visualX = origenX + (int) puntoObjetivo.getX();
            int visualY = origenY - (int) puntoObjetivo.getY(); 
            g2.fillOval(visualX - 5, visualY - 5, 10, 10);
            g2.drawString("OBJETIVO (" + (int)puntoObjetivo.getX() + "," + (int)puntoObjetivo.getY() + ")", visualX + 8, visualY - 5);
            int xActual = origenX;
            int yActual = origenY;
            double anguloAcumulado = 0;
            for (int i = 0; i < robot.getEslabones().size(); i++) {
                Eslabon eslabon = robot.getEslabones().get(i);
                anguloAcumulado += eslabon.getAngulo();
                int xSiguiente = xActual + (int) (eslabon.getLongitud() * Math.cos(anguloAcumulado));
                int ySiguiente = yActual - (int) (eslabon.getLongitud() * Math.sin(anguloAcumulado));
                g2.setColor(Color.BLUE);
                g2.setStroke(new BasicStroke(8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawLine(xActual, yActual, xSiguiente, ySiguiente);
                g2.setColor(Color.DARK_GRAY);
                g2.fillOval(xActual - 5, yActual - 5, 10, 10);
                xActual = xSiguiente;
                yActual = ySiguiente;
            }
            g2.setColor(Color.BLACK);
            g2.fillOval(xActual - 6, yActual - 6, 12, 12);
        }
    }
}