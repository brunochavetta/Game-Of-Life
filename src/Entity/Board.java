package Entity;

import java.util.Random;

/**
 * Board --- Clase que representa el tablero del juego de la vida.
 *
 * @author Bruno Chavetta
 */
public class Board {
    private String[][] board;

    /**
     * Constructor de la clase Board.
     *
     * @param rowBoard número de filas del tablero
     * @param colBoard número de columnas del tablero
     */
    public Board(int rowBoard, int colBoard) {
        this.board = new String[rowBoard][colBoard];
        initializeBoard(rowBoard, colBoard);
    }

    /**
     * Método para obtener el tablero.
     *
     * @return el tablero
     */
    public String[][] getBoard() {
        return board;
    }

    /**
     * Método para establecer el tablero.
     *
     * @param board el tablero a establecer
     */
    public void setBoard(String[][] board) {
        this.board = board;
    }

    /**
     * Método para llenar el tablero con células muertas.
     *
     * @param rowBoard número de filas del tablero
     * @param colBoard número de columnas del tablero
     * @return el tablero lleno de células muertas
     */
    public String[][] initializeBoard(int rowBoard, int colBoard) { //recibe por parámetro el número de fila y el de columna
        this.board = new String[rowBoard][colBoard]; // se crea e instancia el arreglo con los parámetros

        for (int i = 0; i < board.length; i++) { // doble for para recorrer el arreglo bidireccional
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "O"; // "O" en todas las posiciones
            }
        }

        return board; // Devuelve el arreglo
    }

    /**
     * Método para llenar el tablero según la población pasada por parámetro.
     *
     * @param p la población del tablero
     */
    public void fillBoard(String p) {
        String[] rows = p.split("#"); // se crea un arreglo unidireccional con la población, separando cuando aparece "#"
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (rows[i] != "") {
                    char columns = rows[i].charAt(j); // Variable de tipo char para recorrer los valores de cada fila
                    int col = Integer.parseInt(String.valueOf(columns)); // transforma a entero la variable char
                    if (col == 1) { // si se encuentra un 1, entonces se escribe "X" para especificar una célula viva
                        this.board[i][j] = "X";
                    }
                }
            }
        }
    }

    /**
     * Método para llenar el tablero de forma aleatoria.
     */
    public void fillBoardRandom() {
        Random random = new Random(); // Se instancia la clase Random
        int count = 0; // se crea e inicializa el contador en 0

        while (count <= 35) { // se escriben 35 células vivas de forma aleatoria
            int row = random.nextInt(board.length); // se inicializa la fila con el valor random hasta la dimensión del tablero
            int col = random.nextInt(board[0].length); // se inicializa la columna con el valor random hasta la dimensión del tablero
            if (board[row][col].equals("O")) { // si es una célula muerta, se escribe como célula viva
                board[row][col] = "X";
                count++;
            }
        }
    }

    /**
     * Método para imprimir el tablero.
     */
    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("X")) { //Si es X entonces lo imprime en verde
                    System.out.print("\033[32m" + board[i][j] + "\033[0m" + " ");
                } else { // Sino, es O, lo imprime en rojo
                    System.out.print("\033[31m" + board[i][j] + "\033[0m" + " ");
                }

            }
            System.out.println(); // salto de línea al finalizar cada fila
        }
    }
}
