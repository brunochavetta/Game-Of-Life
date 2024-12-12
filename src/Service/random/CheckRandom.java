package Service.random;

import java.util.Random;

/**
 * CheckRandom --- Clase para manejar los argumentos de forma aleatoria.
 *
 * @author Bruno Chavetta
 */
public class CheckRandom {

    /**
     * Constructor de la clase CheckRandom.
     */
    public CheckRandom() {
    }

    /**
     * Método para definir argumentos de forma aleatoria.
     *
     * @param args el arreglo de argumentos que se definirán de manera aleatoria
     */
    public static void defineArguments(String[] args) {
        Random random = new Random(); // Se instancia la clase Random
        // Arreglos con valores válidos para ancho, alto y milisegundos
        int[] width = {10, 20, 40, 80};
        int[] height = {10, 20, 40};
        int[] milliseconds = {250, 1000};

        for (int i = 0; i < args.length; i++) {
            if (i == 0) {
                int randomNumber = random.nextInt(500); // Número aleatorio hasta 500 para generaciones
                args[i] = "g=" + randomNumber;
            } else {
                int randomNumber = random.nextInt(i + 1); // Número aleatorio de i + 1 la posición de los arreglos
                switch (i) { // Según la posición de i se le asigna a args la posición aleatoria del arreglo correspondiente
                    case 1:
                        args[i] = "s=" + milliseconds[randomNumber];
                        break;
                    case 2:
                        args[i] = "h=" + height[randomNumber];
                        break;
                    case 3:
                        args[i] = "w=" + width[randomNumber];
                        break;
                }
            }
        }
    }

    /**
     * Método para definir la población del tablero generado aleatoriamente.
     *
     * @param board el tablero generado aleatoriamente
     * @return la cadena de población del tablero
     */
    public static String definePopulation(String[][] board) {
        // StringBuilder permite crear una cadena mutable, es decir, que puede cambiar o modificarse.
        StringBuilder population = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            StringBuilder auxPopulation = new StringBuilder();
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("X")) { // Si es una célula viva, escribe uno
                    auxPopulation.append("1");
                } else { // Si no es una célula viva, escribe 0
                    auxPopulation.append("0");
                }
            }
            if (isOnlyZero(auxPopulation.toString())) { // Se llama al método isOnlyZero
                population.append("#"); // Solo escribe "#"
            } else {
                if (i < (board.length - 1)) { // Condición para saber que no estamos en la última fila
                    population.append(auxPopulation).append("#"); // Escribe los 0 y 1 y un "#" al final
                } else {
                    population.append(auxPopulation); // Si es la última fila, escribe 1 y 0 sin el "#"
                }

            }

        }
        return population.toString(); // StringBuilder.toString() devuelve un String
    }

    /**
     * Método para verificar si la fila solo tiene células muertas.
     *
     * @param input la cadena de entrada a verificar
     * @return true si la fila solo contiene células muertas, false en caso contrario
     */
    public static boolean isOnlyZero(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '0') { // Si no es un "0", entonces hay una célula viva y devuelve false
                return false;
            }
        }
        return true; // Si no se encontraron células vivas, devuelve true
    }
}
