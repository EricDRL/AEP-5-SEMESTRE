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
            System.out.println(UiUtils.CYAN + "=== NOVA SOLICITAÇÃO ===" + UiUtils.RESET);
            System.out.println("\nDeseja se identificar?\n");
            System.out.println("1 - Sim");
            System.out.println("2 - Não (Anônimo)");
            System.out.println("0 - Voltar");
            System.out.print("\n" + UiUtils.YELLOW + "Escolha uma opção: " + UiUtils.RESET);
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
                    System.out.println(UiUtils.RED + "Opção desconhecida. Tente novamente." + UiUtils.RESET);
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
                System.out.println(UiUtils.GREEN + "Usuário já cadastrado. Bem-vindo de volta, " + usuario.getNome()
                        + "!" + UiUtils.RESET);
                UiUtils.pausar();
            } else {
                try {
                    usuario = usuarioService.criarUsuario(nome, cpf);
                    System.out.println(UiUtils.GREEN + "Usuário identificado: " + usuario.getNome() + UiUtils.RESET);
                } catch (IllegalArgumentException e) {
                    System.out.println(UiUtils.RED + "Erro ao identificar usuário: " + e.getMessage() + UiUtils.RESET);
                    UiUtils.pausar();
                    return;
                }
            }
        }

        System.out.println("\nCategoria:");
        exibirCategorias();
        int escolha = ScannerUtil.lerInt();

        if (escolha < 1 || escolha > Categoria.values().length) {
            System.out.println(UiUtils.RED + "Opção inválida." + UiUtils.RESET);
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
            System.out.println(UiUtils.RED + "Opção inválida." + UiUtils.RESET);
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

            System.out.println(UiUtils.GREEN + "\n[SOLICITAÇÃO REGISTRADA COM SUCESSO]" + UiUtils.RESET);
            System.out.println("\nProtocolo gerado:  " + UiUtils.YELLOW + solicitacao.getProtocolo() + UiUtils.RESET);
            System.out.println("Data: " + UiUtils.formatarData(solicitacao.getDataCriacao()));
            System.out.println("Categoria: " + solicitacao.getCategoria().getDescricao());
            System.out.println("Prioridade: " + solicitacao.getPrioridade().getDescricao());
            System.out
                    .println("Status atual: " + UiUtils.CYAN + solicitacao.getStatus().getDescricao() + UiUtils.RESET);

        } catch (IllegalArgumentException e) {
            System.out.println(UiUtils.RED + "\nErro ao criar solicitação: " + e.getMessage() + UiUtils.RESET);
        } finally {
            UiUtils.pausar();
        }
    }

    public void acompanharSolicitacao() {
        System.out.println(UiUtils.CYAN + "=== ACOMPANHAR SOLICITAÇÃO ===" + UiUtils.RESET);
        System.out.println("\nDigite o protocolo:");
        String protocolo = ScannerUtil.lerLinha();

        UiUtils.simulaLoading();

        Solicitacao solicitacao = solicitacaoService.buscarPorProtocolo(protocolo);

        if (solicitacao == null) {
            System.out
                    .println(UiUtils.RED + "\nSolicitação não encontrada para o protocolo informado." + UiUtils.RESET);
        } else {
            exibirDetalhesSolicitacao(solicitacao);
        }

        UiUtils.pausar();
    }

    private void exibirDetalhesSolicitacao(Solicitacao solicitacao) {
        String linha = "========================================";
        String divisor = "----------------------------------------";

        System.out.println("\n" + UiUtils.CYAN + linha);
        System.out.println(UiUtils.BOLD + "         DETALHES DA SOLICITAÇÃO" + UiUtils.RESET + UiUtils.CYAN);
        System.out.println(linha + UiUtils.RESET);

        System.out.println();
        System.out.printf("%-12s: %s%n", "Protocolo", UiUtils.YELLOW + solicitacao.getProtocolo() + UiUtils.RESET);
        System.out.printf("%-12s: %s%n", "Criado em", UiUtils.formatarData(solicitacao.getDataCriacao()));
        System.out.printf("%-12s: %s%n", "Categoria", solicitacao.getCategoria().getDescricao());
        System.out.printf("%-12s: %s%n", "Local", solicitacao.getLocalizacao());
        System.out.printf("%-12s: %s%n", "Tipo", (solicitacao.isAnonima() ? "Anônimo" : "Identificado"));
        System.out.printf("%-12s: %s%n", "Prioridade", solicitacao.getPrioridade().getDescricao());
        System.out.printf("%-12s: %s%s%s%n", "Status", UiUtils.CYAN + UiUtils.BOLD,
                solicitacao.getStatus().getDescricao(), UiUtils.RESET);

        System.out.println();
        System.out.println(divisor);
        System.out.println(UiUtils.BOLD + "DESCRIÇÃO" + UiUtils.RESET);
        System.out.println();
        System.out.println(solicitacao.getDescricao());
        System.out.println();
        System.out.println(divisor);

        System.out.println();
        System.out.println(UiUtils.CYAN + linha + UiUtils.RESET);
    }

    private void exibirCategorias() {
        Categoria[] categorias = Categoria.values();
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + " - " + categorias[i].getDescricao());
        }
        System.out.print("\n" + UiUtils.YELLOW + "Escolha: " + UiUtils.RESET);
    }

    private void exibirPrioridades() {
        Prioridade[] prioridades = Prioridade.values();
        for (int i = 0; i < prioridades.length; i++) {
            System.out.println((i + 1) + " - " + prioridades[i].getDescricao());
        }
        System.out.print("\n" + UiUtils.YELLOW + "Escolha: " + UiUtils.RESET);
    }
}
