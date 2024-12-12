package Service.normal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Board --- Clase que representa el tablero del juego de la vida.
 *
 * @author Bruno Chavetta
 */
public class Check {

    /**
     * Constructor de la clase Check.
     */
    public Check() {
    }

    /**
     * Método para verificar que se han cargado los argumentos por consola y si es así, se analiza si cumple con el tipo.
     *
     * @param args los argumentos pasados por consola
     * @return un arreglo con los argumentos válidos o mensajes de error
     */
    public static String[] checkDimension(String[] args) {

        // Definir el nuevo arreglo para guardar los argumentos válidos o mensajes de error
        String[] validArguments = new String[5];
        // Compila una expresión regular para validar los argumentos
        Pattern pattern = Pattern.compile("^([whgps])=([\\d#]+)$"); // Se informa que sólo puede contener letras como w, h, g, p, s seguido de un igual y despúes números o un numeral

        // Validar cada argumento
        for (int i = 0; i < 5; i++) {
            // Verificar si el argumento existe
            if (i < args.length) { // Condición para saber si la posición i está dentro de los argumentos ingresados por consola
                String argument = args[i]; // La variable argumento es igual al ingresado por consola
                Matcher matcher = pattern.matcher(argument); // Se averigua si cumple con las condiciones mencionadas arriba
                if (matcher.matches()) {
                    argument = matcher.group(2); // Obtiene el valor numérico
                    // Verificar el tipo de cada argumento y si cumple las condiciones
                    switch (i) {
                        case 0: // w
                            // Validar el valor numérico
                            if (isValidNumber(argument, new int[]{10, 20, 40, 80})) {
                                validArguments[i] = argument;
                            } else {
                                validArguments[i] = "Invalid";
                            }
                            break;
                        case 1: // h
                            // Validar el valor numérico
                            if (isValidNumber(argument, new int[]{10, 20, 40})) {
                                validArguments[i] = argument;
                            } else {
                                validArguments[i] = "Invalid";
                            }
                            break;
                        case 2: // g
                            // Validar el valor numérico para saber  si es mayor o igual a 0
                            if (Integer.parseInt(argument) >= 0) {
                                validArguments[i] = argument;
                            } else {
                                validArguments[i] = "Invalid";
                            }
                            break;
                        case 3: // s
                            // Validar el valor numérico para saber si es 250 o 1000
                            if (isValidNumber(argument, new int[]{250, 1000})) {
                                validArguments[i] = argument;
                            } else {
                                validArguments[i] = "Invalid";
                            }
                            break;
                        case 4: // p
                            // Validar si la cadena está compuesta por 0, 1 y #
                            if (argument.matches("[01#]+")) {
                                validArguments[i] = argument;
                            } else {
                                validArguments[i] = "Invalid";
                            }
                            break;
                        default:
                            validArguments[i] = "Invalid"; // Si hay más de 5 argumentos
                    }
                } else {
                    validArguments[i] = "Invalid"; // Si la sintaxis no coincide
                }
            } else {
                validArguments[i] = "No present"; // Si el argumento no existe
            }
        }
        return validArguments; // devuelve el array con los valores de los argumentos (invalid, no present o el argumento cargado por consola).
    }

    /**
     * Método que recibe un número y un array de tipo entero con los valores admitidos y devuelve true si cumple con el tipo y condiciones o false en caso contrario.
     *
     * @param number      el número a verificar
     * @param validValues los valores admitidos
     * @return true si el número es válido, false en caso contrario
     */
    public static boolean isValidNumber(String number, int[] validValues) {
        try {
            int num = Integer.parseInt(number); // convierte a entero el string pasado por parámetro
            if (validValues == null) {
                return true; // No hay restricciones sobre el valor
            }
            // Verifica si el número es válido
            for (int val : validValues) { //forEach para recorrer los valores del array
                if (num == val) { // condicion para saber si el numero el igual al valor del array
                    return true; // el número es válido
                }
            }
            return false; // El número no es válido
        } catch (NumberFormatException e) {
            return false; // No se puede convertir a un número entero
        }
    }
}
