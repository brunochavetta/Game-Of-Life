package Service.random;

import Entity.Board;

import java.util.concurrent.TimeUnit;

import static Service.GameLogic.calculateNextGeneration;

/**
 * CheckRandom --- Clase que representa el juego de la vida para jugar de forma aleatoria
 *
 * @author Bruno Chavetta
 */
public class GameOfLifeRandom {
    static CheckRandom check = new CheckRandom();
    static Board boardClass;

    public GameOfLifeRandom() {
    }

    /**
     * EXTRA AL JUEGO - OTRA ALTERNATIVA
     * Métodos para jugar de forma aleatoria modificando los datos ingresados por la consola
     */

    /**
     * Método con la lógica para jugar de forma aleatoria
     *
     * @param args Argumentos pasados por la consola
     */
    public static void playRandom(String[] args) {

        // se crea la variable para contar las generaciones y se inicializa en 1
        int generation = 1;
        // Manejo de errores
        try {
            check.defineArguments(args); // Se cambian los valores de args por los aleatorios

            int w = Integer.parseInt(args[3].substring(2));
            int h = Integer.parseInt(args[2].substring(2));
            int g = Integer.parseInt(args[0].substring(2));
            int s = Integer.parseInt(args[1].substring(2));

            boardClass = new Board(w, h); //Se inicializa el tablero con el método initializeBoard
            boardClass.fillBoardRandom(); // Se llama al método fillBoardRandom
            args[4] = check.definePopulation(boardClass.getBoard()); // guarda en el argumento 4 la población devuelta por el método definePopulation()
            String p = args[4]; // guarda en la variable p el argumento 4

            // Imprime los valores obtenidos
            System.out.println("width = [" + w + "] \n" +
                    "height = [" + h + "] \n" +
                    "generations = [" + g + "] \n" +
                    "speed = [" + s + "] \n" +
                    "population = ['" + p + "']");
            do {
                System.out.println("Generación " + generation);
                System.out.println("==============");
                boardClass.printBoard(); // Se imprime el tablero
                calculateNextGeneration(boardClass); // Se calcula la pŕoxima generación.
                generation += 1; // se suma 1 en la variable generation en cada iteración
                TimeUnit.MILLISECONDS.sleep(s); // El tiempo de espera en un intervalo de milisegundos entre una interación y la otra
            } while (generation <= g); // Se ejecuta hasta que generaciones sea menor o igual a g

        } catch (NumberFormatException e) { // Excepción si el número no se pudo convertir a entero
            e.printStackTrace();
            System.out.println("Could not convert some number to integer");
            // No se pudo convertir algún número a entero
        } catch (InterruptedException e) { // Excepción si hubo alguna interrupción en el intervalo de milisegundos
            e.printStackTrace();
            System.out.println("Interruption during timeout");
            // Interrupción durante el tiempo de espera
        }
    }
}

