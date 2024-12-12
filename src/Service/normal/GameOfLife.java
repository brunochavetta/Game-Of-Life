package Service.normal;

import Entity.Board;

import java.util.concurrent.TimeUnit;

import static Service.GameLogic.calculateNextGeneration;

/**
 * GameOfLife --- Clase que representa el juego de la vida.
 *
 * @author Bruno Chavetta
 */
public class GameOfLife {

    static Board boardClass;
    static Check check = new Check();

    /**
     * Constructor de la clase GameOfLife.
     */
    public GameOfLife() {
    }

    /**
     * Método para iniciar el juego.
     *
     * @param args los argumentos para iniciar el juego
     */
    public void start(String[] args) {
        String w, h, g, s, p; // Se crean las variables en las que se guardarán los parámetros
        if (args.length > 0) {
            try { // Manejo de errores
                String[] arguments = check.checkDimension(args); // Comprueba y obtiene los argumentos válidos
                // Guarda en las variables creadas anteriormente las posiciones del arreglo arguments
                w = arguments[0];
                h = arguments[1];
                g = arguments[2];
                s = arguments[3];
                p = arguments[4];

                // Imprime los valores obtenidos
                System.out.println("width = [" + w + "] \n" +
                        "height = [" + h + "] \n" +
                        "generations = [" + g + "] \n" +
                        "speed = [" + s + "] \n" +
                        "population = ['" + p + "']");

                play(w, h, g, s, p); // Inicia el juego
            } catch (Exception e) { // Captura y maneja excepciones
                if (args.length < 5) { // Si la dimensión de args es menor a 5
                    System.out.println("Debe ingresar los 5 valores por consola");
                    // Debe ingresar 5 valores por consola
                } else {
                    System.out.println("Algunos de los valores ingresados no son correctos");
                    // Algunos de los valores ingresados no son correctos
                }
            }
        } else { // Si el array args está vacío
            System.out.println("No se ha ingresado ningún argumento. Debe ingresar los valores por consola");
            // No se ha ingresado ningún argumento. Debe ingresar los valores por consola
        }
    }

    /**
     * Método que contiene la lógica del juego.
     *
     * @param w ancho
     * @param h alto
     * @param g generaciones
     * @param s velocidad
     * @param p población
     */
    public void play(String w, String h, String g, String s, String p) {
        // Se crea la variable para contar las generaciones y se inicializa en 1
        int generation = 1;
        // Manejo de errores
        try {
            boardClass = new Board(Integer.parseInt(w), Integer.parseInt(h)); // Se instancia e inicializa el tablero llamando al método initializeBoard()
            boardClass.fillBoard(p); // Se llena el tablero según la población
            do {
                System.out.println("Generación " + generation);
                System.out.println("==============");
                boardClass.printBoard(); // Se imprime el tablero
                // Se calcula la próxima generación.
                boardClass.setBoard(calculateNextGeneration(boardClass));
                generation += 1; // Se suma 1 en la generación en cada iteración
                TimeUnit.MILLISECONDS.sleep(Integer.parseInt(s)); // El tiempo de espera en un intervalo de milisegundos entre una iteración y la otra
            } while (generation <= Integer.parseInt(g)); // Se seguirá ejecutando hasta que generación sea igual a g
        } catch (NumberFormatException e) { // Excepción si el número no se pudo convertir a entero
            e.printStackTrace();
            System.out.println("No se pudo convertir algún número a entero");
            // No se pudo convertir algún número a entero
        } catch (InterruptedException e) { // Excepción si hubo alguna interrupción en el intervalo de milisegundos
            e.printStackTrace();
            System.out.println("Interrupción durante el tiempo de espera");
            // Interrupción durante el tiempo de espera
        }
    }

}
