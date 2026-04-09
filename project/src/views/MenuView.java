package views;

import utils.ScannerUtil;

public class MenuView {
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    private final ClientView clientView;

    public MenuView(ClientView clientView) {
        this.clientView = clientView;
    }

    public void iniciar() {
        int opcao = 0;

        do {

            System.out.println(CYAN + "\n========================================");
            System.out.println("     OBSERVAÇÃO - SISTEMA DE SERVIÇOS   ");
            System.out.println("========================================" + RESET);

            System.out.println("\nMENU PRINCIPAL");
            System.out.println("----------------------------------------");
            System.out.println(GREEN + " 1 - Nova solicitação" + RESET);
            System.out.println(GREEN + " 2 - Acompanhar solicitação" + RESET);
            System.out.println(GREEN + " 3 - Área do gestor" + RESET);
            System.out.println(" 0 - Sair");
            System.out.println("----------------------------------------");

            System.out.print("Escolha uma opção: ");
            opcao = ScannerUtil.lerInt();

            switch (opcao) {
                case 1:
                    clientView.selecionarTipoUsuario();
                    break;
                case 2:
                    clientView.acompanharSolicitacao();
                    break;
                case 3:
                    System.out.println("\nFuncionalidade em desenvolvimento...");
                    break;
                case 0:
                    System.out.println("\nEncerrando a aplicação...");
                    break;
                default:
                    System.out.println("Opção desconhecida. Tente novamente.");
            }

        } while (opcao != 0);
    }
}