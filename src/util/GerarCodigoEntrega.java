package util;
import java.util.Random;

public class GerarCodigoEntrega {
    // Método para gerar uma letra aleatória (A-Z)
    private static char gerarLetraAleatoria(Random random) {
        // Gera um número entre 0 e 25 (correspondente às letras A-Z)
        int numeroAleatorio = random.nextInt(26);
        // Converte o número para uma letra maiúscula
        return (char) ('A' + numeroAleatorio);
    }

    // Método para gerar um número aleatório (0-9)
    private static char gerarNumeroAleatorio(Random random) {
        // Gera um número entre 0 e 9
        int numeroAleatorio = random.nextInt(10);
        // Converte o número para um caractere (0-9)
        return (char) ('0' + numeroAleatorio);
    }

    // Método para gerar a String aleatória
    public static String gerarCodigo() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Gera os dois primeiros caracteres (letras)
        for (int i = 0; i < 2; i++) {
            sb.append(gerarLetraAleatoria(random));
        }

        // Gera os quatro caracteres do meio (letras ou números)
        for (int i = 0; i < 4; i++) {
            // Decide aleatoriamente se será uma letra ou número
            if (random.nextBoolean()) {
                sb.append(gerarLetraAleatoria(random));
            } else {
                sb.append(gerarNumeroAleatorio(random));
            }
        }

        // Gera os dois últimos caracteres (números)
        for (int i = 0; i < 2; i++) {
            sb.append(gerarNumeroAleatorio(random));
        }

        return sb.toString();
    }
}
