package questao5;

// Classe para converter cores hexadecimais em códigos ANSI para terminal
public class ANSIConverter {
    // Define o código ANSI para resetar a cor ao padrão do terminal
    public static final String RESET = "\u001B[0m";

        /**
        * Gera o código ANSI de True Color (24-bit) a partir de um valor hexadecimal.
        * @param hexColor A string hexadecimal da cor (ex: "#FF5733" ou "FF5733").
        * @return O código ANSI para definir a cor da fonte.
        */
    public static String getANSIColor(String hexColor) {
        // Remove o '#' se estiver presente na string da cor
        if (hexColor.startsWith("#")) {
            hexColor = hexColor.substring(1); // Remove o primeiro caractere (#)
        }

        // Bloco try-catch para tratar possíveis erros de conversão
        try {
            // Converte os dois primeiros caracteres hexadecimais para valor decimal (componente Vermelho)
            int r = Integer.parseInt(hexColor.substring(0, 2), 16);
            // Converte os dois caracteres do meio para valor decimal (componente Verde)
            int g = Integer.parseInt(hexColor.substring(2, 4), 16);
            // Converte os dois últimos caracteres para valor decimal (componente Azul)
            int b = Integer.parseInt(hexColor.substring(4, 6), 16);

            // Retorna o código ANSI de 24-bit (True Color) para a cor da fonte
            // Formato: \u001B[38;2;R;G;Bm onde R, G, B são valores de 0-255
            return String.format("\u001B[38;2;%d;%d;%dm", r, g, b);
        } catch (NumberFormatException e) {
            // Retorna o código de reset em caso de formato hexadecimal inválido
            return RESET;
        }
    }
}