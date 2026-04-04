package views;

import entity.Categoria;
import entity.Solicitacao;
import util.ScannerUtil;

import java.util.List;

public class AdminView {
    public static void exibir() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== Painel Admin ===");
            System.out.println("1) Listar solicitacoes");
            System.out.println("2) Atualizar status de uma solicitacao");
            System.out.println("0) Voltar");
            System.out.print("Escolha uma opcao: ");

            int opcao = ScannerUtil.lerInt();

            switch (opcao) {
                case 1:
                    listarSolicitacoes();
                    break;
                case 2:
                    atualizarStatusSolicitacao();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opcao invalida. Digite 1, 2 ou 0.");
            }
        }
    }

    private static void listarSolicitacoes() {
        System.out.println("\n=== Filtros de Listagem ===");

        Integer prioridade = lerFiltroPrioridade();

        System.out.print("Filtrar por bairro/localizacao (ENTER para todos): ");
        String bairro = ScannerUtil.lerLinha();

        String categoria = lerFiltroCategoria();

        List<Solicitacao> solicitacoes = ClientView.listarSolicitacoes(prioridade, bairro, categoria);

        if (solicitacoes.isEmpty()) {
            System.out.println("Nenhuma solicitacao encontrada com os filtros informados.");
            pausar();
            return;
        }

        System.out.println("\n=== Solicitacoes Encontradas ===");
        for (Solicitacao solicitacao : solicitacoes) {
            System.out.println("Protocolo: " + solicitacao.getIdSolicitacao()
                    + " | Categoria: " + solicitacao.getCategoria().getNome()
                    + " | Prioridade: " + ClientView.descreverPrioridade(solicitacao.getPrioridade())
                    + " | Bairro: " + solicitacao.getLocalizacao()
                    + " | Status: " + solicitacao.getStatus());
        }

        pausar();
    }

    private static void atualizarStatusSolicitacao() {
        System.out.print("\nInforme o protocolo da solicitacao: ");
        long protocolo = ScannerUtil.lerInt();

        Solicitacao solicitacao = ClientView.buscarPorProtocolo(protocolo);
        if (solicitacao == null) {
            System.out.println("Solicitacao nao encontrada.");
            pausar();
            return;
        }

        String proximoStatus = ClientView.obterProximoStatus(solicitacao.getStatus());
        if (proximoStatus == null) {
            System.out.println("Essa solicitacao ja esta encerrada e nao possui novo status valido.");
            pausar();
            return;
        }

        System.out.println("Status atual: " + solicitacao.getStatus());
        System.out.println("Proximo status valido: " + proximoStatus);
        System.out.print("Informe o comentario da atualizacao: ");
        String comentario = lerComentarioObrigatorio();

        boolean atualizado = ClientView.atualizarStatus(protocolo, proximoStatus, comentario);
        if (atualizado) {
            System.out.println("Status atualizado com sucesso para " + proximoStatus + ".");
        } else {
            System.out.println("Nao foi possivel atualizar o status.");
        }

        pausar();
    }

    private static Integer lerFiltroPrioridade() {
        while (true) {
            System.out.println("Filtrar por prioridade:");
            System.out.println("0) Todas");
            System.out.println("1) Baixa");
            System.out.println("2) Media");
            System.out.println("3) Alta");
            System.out.println("4) Critica");
            System.out.print("Opcao: ");

            int opcao = ScannerUtil.lerInt();
            if (opcao == 0) {
                return null;
            }
            if (opcao >= 1 && opcao <= 4) {
                return opcao;
            }

            System.out.println("Prioridade invalida.");
        }
    }

    private static String lerFiltroCategoria() {
        List<String> categorias = Categoria.listarCategoriasPadrao();

        while (true) {
            System.out.println("Filtrar por categoria:");
            System.out.println("0) Todas");
            for (int i = 0; i < categorias.size(); i++) {
                System.out.println((i + 1) + ") " + categorias.get(i));
            }
            System.out.print("Opcao: ");

            int opcao = ScannerUtil.lerInt();
            if (opcao == 0) {
                return null;
            }
            if (opcao >= 1 && opcao <= categorias.size()) {
                return categorias.get(opcao - 1);
            }

            System.out.println("Categoria invalida.");
        }
    }

    private static String lerComentarioObrigatorio() {
        while (true) {
            String comentario = ScannerUtil.lerLinha();
            if (!comentario.isBlank()) {
                return comentario;
            }

            System.out.print("Comentario obrigatorio. Digite novamente: ");
        }
    }

    private static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        ScannerUtil.lerLinha();
    }
}
