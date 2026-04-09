package views;

import entity.Solicitacao;
import enums.Categoria;
import enums.Prioridade;
import enums.StatusSolicitacao;
import java.util.List;
import services.SolicitacaoService;
import utils.ScannerUtil;
import utils.UiUtils;

public class AdminView {
    private final SolicitacaoService solicitacaoService;

    public AdminView(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    public void exibirMenu() {
        int opcao = 0;

        do {
            System.out.println(UiUtils.PURPLE + "\n==============================================");
            System.out.println("          BEM-VINDO À ÁREA DO GESTOR         ");
            System.out.println("==============================================" + UiUtils.RESET);

            System.out.println("\nMENU ADMINISTRATIVO");
            System.out.println("----------------------------------------------");
            System.out.println(" 1 - Atualizar status de uma solicitação");
            System.out.println(" 2 - Exibir todas as solicitações");
            System.out.println(" 3 - Exibir solicitações por prioridade");
            System.out.println(" 4 - Exibir solicitações por categoria");
            System.out.println(" 5 - Exibir solicitações por status");
            System.out.println(" 0 - Voltar");
            System.out.println("----------------------------------------------");

            System.out.print(UiUtils.YELLOW + "Escolha uma opção: " + UiUtils.RESET);
            opcao = ScannerUtil.lerInt();

            switch (opcao) {
                case 1:
                    atualizarStatus();
                    break;
                case 2:
                    listarSolicitacoes(solicitacaoService.listarTodas(), "TODAS AS SOLICITAÇÕES");
                    break;
                case 3:
                    filtrarPorPrioridade();
                    break;
                case 4:
                    filtrarPorCategoria();
                    break;
                case 5:
                    filtrarPorStatus();
                    break;
                case 0:
                    break;
                default:
                    System.out.println(UiUtils.RED + "Opção inválida." + UiUtils.RESET);
            }

        } while (opcao != 0);
    }

    private void atualizarStatus() {
        System.out.print("\nDigite o protocolo da solicitação: ");
        String protocolo = ScannerUtil.lerLinha();

        UiUtils.simulaLoading();

        Solicitacao solicitacao = solicitacaoService.buscarPorProtocolo(protocolo);
        if (solicitacao == null) {
            System.out.println(UiUtils.RED + "Solicitação não encontrada." + UiUtils.RESET);
            UiUtils.pausar();
            return;
        }

        System.out.println(UiUtils.GREEN + "Solicitação encontrada!" + UiUtils.RESET);
        System.out.println("\nStatus atual: " + UiUtils.CYAN + solicitacao.getStatus().getDescricao() + UiUtils.RESET);
        System.out.println("\nEscolha o novo status:");
        StatusSolicitacao[] statuses = StatusSolicitacao.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println((i + 1) + " - " + statuses[i].getDescricao());
        }

        System.out.print(UiUtils.YELLOW + "Opção: " + UiUtils.RESET);
        int escolha = ScannerUtil.lerInt();

        if (escolha < 1 || escolha > statuses.length) {
            System.out.println(UiUtils.RED + "Opção inválida." + UiUtils.RESET);
            return;
        }

        StatusSolicitacao novoStatus = statuses[escolha - 1];
        UiUtils.simulaLoading();

        try {
            solicitacaoService.atualizarStatus(protocolo, novoStatus);
            System.out.println(UiUtils.GREEN + "Status atualizado com sucesso para: " + novoStatus.getDescricao()
                    + UiUtils.RESET);
        } catch (Exception e) {
            System.out.println(UiUtils.RED + "Erro ao atualizar status: " + e.getMessage() + UiUtils.RESET);
        }
        UiUtils.pausar();
    }

    private void filtrarPorPrioridade() {
        System.out.println("\nEscolha a prioridade:");
        Prioridade[] prioridades = Prioridade.values();
        for (int i = 0; i < prioridades.length; i++) {
            System.out.println((i + 1) + " - " + prioridades[i].getDescricao());
        }

        System.out.print(UiUtils.YELLOW + "Opção: " + UiUtils.RESET);
        int escolha = ScannerUtil.lerInt();

        if (escolha < 1 || escolha > prioridades.length) {
            System.out.println(UiUtils.RED + "Opção inválida." + UiUtils.RESET);
            return;
        }

        Prioridade p = prioridades[escolha - 1];
        listarSolicitacoes(solicitacaoService.listarPorPrioridade(p),
                "SOLICITAÇÕES POR PRIORIDADE: " + p.getDescricao());
    }

    private void filtrarPorCategoria() {
        System.out.println("\nEscolha a categoria:");
        Categoria[] categorias = Categoria.values();
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + " - " + categorias[i].getDescricao());
        }

        System.out.print(UiUtils.YELLOW + "Opção: " + UiUtils.RESET);
        int escolha = ScannerUtil.lerInt();

        if (escolha < 1 || escolha > categorias.length) {
            System.out.println(UiUtils.RED + "Opção inválida." + UiUtils.RESET);
            return;
        }

        Categoria c = categorias[escolha - 1];
        listarSolicitacoes(solicitacaoService.listarPorCategoria(c), "SOLICITAÇÕES POR CATEGORIA: " + c.getDescricao());
    }

    private void filtrarPorStatus() {
        System.out.println("\nEscolha o status:");
        StatusSolicitacao[] statuses = StatusSolicitacao.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println((i + 1) + " - " + statuses[i].getDescricao());
        }

        System.out.print(UiUtils.YELLOW + "Opção: " + UiUtils.RESET);
        int escolha = ScannerUtil.lerInt();

        if (escolha < 1 || escolha > statuses.length) {
            System.out.println(UiUtils.RED + "Opção inválida." + UiUtils.RESET);
            return;
        }

        StatusSolicitacao s = statuses[escolha - 1];
        listarSolicitacoes(solicitacaoService.listarPorStatus(s), "SOLICITAÇÕES POR STATUS: " + s.getDescricao());
    }

    private void listarSolicitacoes(List<Solicitacao> lista, String titulo) {
        System.out.println(UiUtils.CYAN + "\n=== " + titulo + " ===" + UiUtils.RESET);

        if (lista.isEmpty()) {
            System.out.println("\nNenhuma solicitação encontrada.");
        } else {
            System.out.printf("\n%-15s | %-15s | %-12s | %-12s%n", "PROTOCOLO", "CATEGORIA", "PRIORIDADE", "STATUS");
            System.out.println("---------------------------------------------------------------");
            for (Solicitacao s : lista) {
                System.out.printf("%-15s | %-15s | %-12s | %-12s%n",
                        s.getProtocolo(),
                        s.getCategoria().getDescricao(),
                        s.getPrioridade().getDescricao(),
                        s.getStatus().getDescricao());
            }
        }
        UiUtils.pausar();
    }
}
