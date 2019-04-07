package br.com.padawan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LeitorDeResposta {

    private static final Scanner leitor = new Scanner(System.in);

    public static String next() {
        return leitor.next();
    }

    public static String nextLine() {;
        return leitor.nextLine();
    }

    public static int nextInt() {
        return nextInt("Por favor, digite apenas digitos de valor inteiro.");
    }

    public static int nextInt(String mensagemDigitoInvalido) {
        try {
           return leitor.nextInt();
        } catch (InputMismatchException e) {
            if (!leitor.hasNextInt()) {
                System.out.println(mensagemDigitoInvalido);
                System.out.println();
                leitor.next();
                return  nextInt(mensagemDigitoInvalido);
            }
        }

        return 0;
    }

}
