package views;

import utils.ScannerUtil;

public class MenuView {
    private final ClientView clientView;

    public MenuView(ClientView clientView) {
        this.clientView = clientView;
    }

    public void iniciar() {
        int opcao = 0;

        do {
            System.out.println("\n=== OBSERVAÇÃO - SISTEMA DE SOLICITAÇÕES ===");
            System.out.println("\n1 - Nova solicitação");
            System.out.println("2 - Acompanhar solicitação");
            System.out.println("3 - Login como gestor");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha uma opção: ");
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