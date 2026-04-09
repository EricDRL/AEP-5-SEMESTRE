package views;

import utils.ScannerUtil;
import utils.UiUtils;

public class AuthView {
    private static final String ADMIN_EMAIL = "admin@admin.com";
    private static final String ADMIN_PASSWORD = "1234";

    private final AdminView adminView;

    public AuthView(AdminView adminView) {
        this.adminView = adminView;
    }

    public void login() {
        System.out.println(UiUtils.CYAN + "\n=== LOGIN - ÁREA DO GESTOR ===" + UiUtils.RESET);

        System.out.print("\nEmail: ");
        String email = ScannerUtil.lerLinha();

        System.out.print("Senha: ");
        String senha = ScannerUtil.lerLinha();

        UiUtils.simulaLoading();

        if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(senha)) {
            System.out.println(UiUtils.GREEN + "Login realizado com sucesso! Bem-vindo." + UiUtils.RESET);
            adminView.exibirMenu();
        } else {
            System.out.println(UiUtils.RED + "Usuário ou senha inválidos! Tente novamente." + UiUtils.RESET);
            UiUtils.pausar();
        }
    }
}
