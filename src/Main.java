import Service.normal.GameOfLife;
import Service.random.GameOfLifeRandom;

import javax.swing.*;

// Argumentos ingresados por consola:
//w=10
//h=10
//g=15
//s=1000
//p=0000010110#0000100100#1100000100#0000000100#0000010100#0010000101#0000000010#0100000000#1000001000#0001001001
//p = #####0011100000#0100010000##1000001000#0100000100
//p = #0011100000#0100010000#1000001000#0100000100#####

/**
 * Clase principal que inicia la ejecución del programa.
 * Main --- Inicia la ejecución del programa.
 *
 * @author Bruno Chavetta
 */
public class Main {
    public static void main(String[] args) {
        // Crear el marco principal
        // Se crea un nuevo objeto JFrame que será el "botón" para detener la ejecución
        JFrame frame = new JFrame("Select the X to finish the execution");

        // Se establece la operación de cierre de la ventana principal al hacer clic en el botón de cierre de la ventana.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Se establece el tamaño de la ventana principal con una anchura de 400 píxeles y una altura de 40 píxeles.
        frame.setSize(400, 40);

        // Mostrar el marco
        // Se hace visible la ventana principal, lo que la muestra en la pantalla.
        frame.setVisible(true);

        // Imprimir en la consola
        GameOfLife gol = new GameOfLife();
        GameOfLifeRandom golRandom = new GameOfLifeRandom();
        gol.start(args);
        golRandom.playRandom(args);


    }
}