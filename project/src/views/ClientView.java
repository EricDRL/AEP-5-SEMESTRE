package views;

import entity.Solicitacao;
import entity.Usuario;
import enums.Categoria;
import enums.Prioridade;
import services.SolicitacaoService;
import services.UsuarioService;
import utils.ScannerUtil;
import utils.UiUtils;

public class ClientView {
    private final UsuarioService usuarioService;
    private final SolicitacaoService solicitacaoService;
    private boolean anonimo = false;

    public ClientView(UsuarioService usuarioService, SolicitacaoService solicitacaoService) {
        this.usuarioService = usuarioService;
        this.solicitacaoService = solicitacaoService;
    }

    public void selecionarTipoUsuario() {
        int opcao = 0;

        do {
            System.out.println("=== NOVA SOLICITAÇÃO ===");
            System.out.println("\nDeseja se identificar?\n");
            System.out.println("1 - Sim");
            System.out.println("2 - Não (Anônimo)");
            System.out.println("0 - Voltar");
            System.out.print("\nEscolha uma opção: ");
            opcao = ScannerUtil.lerInt();

            switch (opcao) {
                case 1:
                    this.anonimo = false;
                    novaSolicitacao();
                    break;
                case 2:
                    this.anonimo = true;
                    novaSolicitacao();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção desconhecida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    public void novaSolicitacao() {
        Usuario usuario = null;

        if (!anonimo) {
            System.out.println("\nNome:");
            String nome = ScannerUtil.lerLinha();
            System.out.println("\nCPF:");
            String cpf = ScannerUtil.lerLinha();

            UiUtils.simulaLoading();

            Usuario existente = usuarioService.buscarPorCpf(cpf);
            if (existente != null) {
                usuario = existente;
                System.out.println("Usuário já cadastrado. Bem-vindo de volta, " + usuario.getNome() + "!");
                UiUtils.pausar();
            } else {
                try {
                    usuario = usuarioService.criarUsuario(nome, cpf);
                    System.out.println("Usuário identificado: " + usuario.getNome());
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro ao identificar usuário: " + e.getMessage());
                    UiUtils.pausar();
                    return;
                }
            }
        }

        System.out.println("\nCategoria:");
        exibirCategorias();
        int escolha = ScannerUtil.lerInt();

        if (escolha < 1 || escolha > Categoria.values().length) {
            System.out.println("Opção inválida.");
            UiUtils.pausar();
            return;
        }
        Categoria categoria = Categoria.values()[escolha - 1];

        System.out.println("\nDescrição (mínimo 10 caracteres):");
        String descricao = ScannerUtil.lerLinha();

        System.out.println("\nLocalização (bairro ou referência):");
        String localizacao = ScannerUtil.lerLinha();

        System.out.println("\nPrioridade:");
        exibirPrioridades();
        int escolhaPrioridade = ScannerUtil.lerInt();

        if (escolhaPrioridade < 1 || escolhaPrioridade > Prioridade.values().length) {
            System.out.println("Opção inválida.");
            UiUtils.pausar();
            return;
        }
        Prioridade prioridade = Prioridade.values()[escolhaPrioridade - 1];

        UiUtils.simulaLoading();

        try {
            Solicitacao solicitacao;
            if (anonimo) {
                solicitacao = solicitacaoService.criarSolicitacao(
                        categoria,
                        descricao,
                        localizacao,
                        prioridade);
            } else {
                solicitacao = solicitacaoService.criarSolicitacao(
                        categoria,
                        descricao,
                        localizacao,
                        prioridade,
                        usuario);
            }

            System.out.println("\n[SOLICITAÇÃO REGISTRADA COM SUCESSO]");
            System.out.println("\nProtocolo gerado:  " + solicitacao.getProtocolo());
            System.out.println("Data: " + UiUtils.formatarData(solicitacao.getDataCriacao()));
            System.out.println("Categoria: " + solicitacao.getCategoria().getDescricao());
            System.out.println("Prioridade: " + solicitacao.getPrioridade().getDescricao());
            System.out.println("Status atual: " + solicitacao.getStatus().getDescricao());

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao criar solicitação: " + e.getMessage());
        } finally {
            UiUtils.pausar();
        }
    }

    public void acompanharSolicitacao() {
        System.out.println("=== ACOMPANHAR SOLICITAÇÃO ===");
        System.out.println("\nDigite o protocolo:");
        System.out.print("> ");
        String protocolo = ScannerUtil.lerLinha();

        UiUtils.simulaLoading();

        solicitacaoService.buscarEExibirSolicitacao(protocolo);

        UiUtils.pausar();
    }

    private void exibirCategorias() {
        Categoria[] categorias = Categoria.values();
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + " - " + categorias[i].getDescricao());
        }
        System.out.print("\nEscolha: ");
    }

    private void exibirPrioridades() {
        Prioridade[] prioridades = Prioridade.values();
        for (int i = 0; i < prioridades.length; i++) {
            System.out.println((i + 1) + " - " + prioridades[i].getDescricao());
        }
        System.out.print("\nEscolha: ");
    }
}
