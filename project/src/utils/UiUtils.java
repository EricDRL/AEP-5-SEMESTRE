package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UiUtils {
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    public static String formatarData(LocalDateTime data) {
        if (data == null)
            return "N/A";
        return "[" + data.format(FORMATTER) + "]";
    }

    public static void simulaLoading() {
        int width = 30;
        System.out.println(); // Nova linha inicial
        for (int i = 0; i <= width; i++) {
            StringBuilder bar = new StringBuilder("[");
            int percent = (i * 100) / width;

            for (int j = 0; j < width; j++) {
                if (j < i) {
                    bar.append(GREEN).append("█").append(RESET);
                } else {
                    bar.append(" ");
                }
            }
            bar.append("] ").append(percent).append("%");
            System.out.print("\r" + CYAN + "CARREGANDO " + RESET + bar.toString());

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("\n");
    }

    public static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        ScannerUtil.lerLinha();
    }
}
