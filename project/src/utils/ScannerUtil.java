package utils;

import java.util.Scanner;

public class ScannerUtil {
    private static Scanner SCANNER = new Scanner(System.in);

    public static String lerLinha() {
        System.out.print("> ");
        return SCANNER.nextLine().trim();
    }

    public static int lerInt() {
        while (true) {
            try {
                return Integer.parseInt(lerLinha());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Digite um número: ");
            }
        }
    }

    public static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(lerLinha().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Digite um número: ");
            }
        }
    }

    public static void fecharScanner() {
        SCANNER.close();
    }
}