package views;

import utils.ScannerUtil;
import utils.UiUtils;

public class MenuView {
    private final ClientView clientView;
    private final AuthView authView;

    public MenuView(ClientView clientView, AuthView authView) {
        this.clientView = clientView;
        this.authView = authView;
    }

    public void iniciar() {
        int opcao = 0;

        do {

            System.out.println(UiUtils.CYAN + "\n========================================");
            System.out.println("     OBSERVAÇÃO - SISTEMA DE SERVIÇOS   ");
            System.out.println("========================================" + UiUtils.RESET);

            System.out.println("\nMENU PRINCIPAL");
            System.out.println("----------------------------------------");
            System.out.println(UiUtils.GREEN + " 1 - Nova solicitação" + UiUtils.RESET);
            System.out.println(UiUtils.GREEN + " 2 - Acompanhar solicitação" + UiUtils.RESET);
            System.out.println(UiUtils.GREEN + " 3 - Área do gestor" + UiUtils.RESET);
            System.out.println(" 0 - Sair");
            System.out.println("----------------------------------------");

            System.out.print(UiUtils.YELLOW + "Escolha uma opção: " + UiUtils.RESET);
            opcao = ScannerUtil.lerInt();

            switch (opcao) {
                case 1:
                    clientView.selecionarTipoUsuario();
                    break;
                case 2:
                    clientView.acompanharSolicitacao();
                    break;
                case 3:
                    authView.login();
                    break;
                case 0:
                    System.out.println("\nEncerrando a aplicação...");
                    break;
                default:
                    System.out.println(UiUtils.RED + "Opção desconhecida. Tente novamente." + UiUtils.RESET);
            }

        } while (opcao != 0);
    }
}