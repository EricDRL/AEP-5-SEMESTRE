import infra.SolicitacaoMemoryRepository;
import infra.UsuarioMemoryRepository;
import repositories.SolicitacaoRepository;
import repositories.UsuarioRepository;
import services.SolicitacaoService;
import services.UsuarioService;
import utils.ScannerUtil;
import views.AdminView;
import views.AuthView;
import views.ClientView;
import views.MenuView;

public class Main {
    public static void main(String[] args) {
        // 1. Instanciar Repositórios (Infra)
        UsuarioRepository usuarioRepository = new UsuarioMemoryRepository();
        SolicitacaoRepository solicitacaoRepository = new SolicitacaoMemoryRepository();

        // 2. Instanciar Serviços (Lógica de Negócio) - Injeção de Repositórios
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        SolicitacaoService solicitacaoService = new SolicitacaoService(solicitacaoRepository);

        // 3. Instanciar Views (CLI) - Injeção de Serviços
        ClientView clientView = new ClientView(usuarioService, solicitacaoService);
        AdminView adminView = new AdminView(solicitacaoService);
        AuthView authView = new AuthView(adminView);
        MenuView menuView = new MenuView(clientView, authView);

        // 4. Iniciar Aplicação
        menuView.iniciar();

        // 5. Finalizar recursos
        ScannerUtil.fecharScanner();
    }
}
