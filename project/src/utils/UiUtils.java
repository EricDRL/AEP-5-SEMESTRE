package utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UiUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM HH:mm");

    public static String formatarData(LocalDateTime data) {
        if (data == null) return "N/A";
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
                    bar.append("█");
                } else {
                    bar.append(" ");
                }
            }
            bar.append("] ").append(percent).append("%");
            System.out.print("\rCARREGANDO " + bar.toString());

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
