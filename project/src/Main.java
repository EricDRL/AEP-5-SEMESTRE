import util.ScannerUtil;
import views.AdminView;
import views.ClientView;

public class Main {
    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            exibirMenuPrincipal();
            int opcao = ScannerUtil.lerInt();

            switch (opcao) {
                case 1:
                    ClientView.exibir();
                    break;
                case 2:
                    AdminView.exibir();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opcao invalida. Digite 1, 2 ou 0.");
            }
        }

        ScannerUtil.fecharScanner();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== ObservAcao - Menu Principal ===");
        System.out.println("1) Painel do usuario");
        System.out.println("2) Painel admin");
        System.out.println("0) Sair");
        System.out.print("Escolha uma opcao: ");
    }
}
