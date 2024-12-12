package Service;

import Entity.Board;

/**
 * GameLogic --- Clase que contiene la lógica del juego de la vida.
 *
 * @author Bruno Chavetta
 */
public class GameLogic {

    /**
     * Constructor de la clase GameLogic.
     */
    public GameLogic() {
    }

    /**
     * Método para calcular los vecinos vivos.
     *
     * @param board      el tablero actual
     * @param currentRow la fila actual
     * @param currentCol la columna actual
     * @return el número de vecinos vivos
     */
    public static int calculateNeighborsAlive(String[][] board, int currentRow, int currentCol) {
        int alive = 0; // Variable para contabilizar los vecinos vivos
        int[] cordinatesRow = {-1, 0, 1, 0, -1, 1, -1, 1}; // Posiciones de filas en las que sumar la posición de la célula
        int[] cordinatesCol = {0, -1, 0, 1, -1, 1, 1, -1}; // Posiciones de columnas en las que sumar la posición de la célula

        for (int i = 0; i < 8; i++) { // Bucle con 8 iteraciones, igual a las posiciones de las células
            int row = currentRow + cordinatesRow[i]; // Se suma la posición actual con las coordenadas de las filas
            int col = currentCol + cordinatesCol[i]; // Se suma la posición actual con las coordenadas de las columnas

            // Condición para saber si la fila y la columna se encuentran entre 0 y la longitud del tablero - 1
            if (row >= 0 && row < board.length && col >= 0 && col < board[i].length) {
                if (board[row][col].equals("X")) { // Si encuentra una "X" en la fila y columna asignada suma 1 a vivos
                    alive += 1;
                }
            }

        }

        return alive; // Devuelve los vecinos vivos
    }

    /**
     * Método para calcular la próxima generación.
     *
     * @param boardClass la instancia de la clase Board
     * @return el tablero con la próxima generación
     */
    public static String[][] calculateNextGeneration(Board boardClass) {
        String[][] board = boardClass.getBoard();
        String[][] newBoard = boardClass.initializeBoard(board.length, board[0].length); // Nuevo tablero para guardar la próxima generación

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int neighborsAlive = calculateNeighborsAlive(board, i, j); // Se llama al método que calcula los vecinos vivos
                if (board[i][j].equals("X")) { // Si es una célula viva
                    if (neighborsAlive < 2 || neighborsAlive > 3) { // Si es menor a 2 o mayor a 3 es una célula muerta
                        newBoard[i][j] = "O";
                    } else { // Sino, si es 2 o 3
                        newBoard[i][j] = "X"; // Célula viva
                    }
                } else { // Sino es una célula viva
                    if (neighborsAlive == 3) { // Si es igual a 3
                        newBoard[i][j] = "X"; // Célula viva
                    } else { // Si no es igual a 3
                        newBoard[i][j] = "O"; // Célula muerta
                    }
                }
            }
        }

        return newBoard; // Devuelve el tablero con la próxima generación
    }
}
