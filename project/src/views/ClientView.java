package views;

import entity.Categoria;
import entity.Solicitacao;
import util.ScannerUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClientView {
    private static final List<Solicitacao> SOLICITACOES = new ArrayList<>();
    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static long proximoProtocolo = 1L;

    public static void exibir() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== Painel do Usuario ===");
            System.out.println("1) Realizar solicitacao");
            System.out.println("2) Buscar solicitacao");
            System.out.println("0) Voltar");
            System.out.print("Escolha uma opcao: ");

            int opcao = ScannerUtil.lerInt();

            if (opcao == 1) {
                realizarSolicitacao();
            } else if (opcao == 2) {
                buscarSolicitacao();
            } else if (opcao == 0) {
                executando = false;
            } else {
                System.out.println("Opcao invalida. Digite 1, 2 ou 0.");
            }
        }
    }

    public static List<Solicitacao> listarSolicitacoes(Integer prioridade, String bairro, String categoria) {
        List<Solicitacao> resultado = new ArrayList<>();

        for (Solicitacao solicitacao : SOLICITACOES) {
            boolean prioridadeValida = prioridade == null || solicitacao.getPrioridade() == prioridade;
            boolean bairroValido = bairro == null || bairro.isBlank()
                    || solicitacao.getLocalizacao().toLowerCase().contains(bairro.toLowerCase());
            boolean categoriaValida = categoria == null || categoria.isBlank()
                    || solicitacao.getCategoria().getNome().equalsIgnoreCase(categoria.trim());

            if (prioridadeValida && bairroValido && categoriaValida) {
                resultado.add(solicitacao);
            }
        }

        return resultado;
    }

    public static Solicitacao buscarPorProtocolo(long protocolo) {
        for (Solicitacao solicitacao : SOLICITACOES) {
            if (solicitacao.getIdSolicitacao() == protocolo) {
                return solicitacao;
            }
        }

        return null;
    }

    public static String obterProximoStatus(String statusAtual) {
        return switch (statusAtual.toUpperCase()) {
            case "ABERTO" -> "TRIAGEM";
            case "TRIAGEM" -> "EM_EXECUCAO";
            case "EM_EXECUCAO" -> "RESOLVIDO";
            case "RESOLVIDO" -> "ENCERRADO";
            default -> null;
        };
    }

    public static boolean atualizarStatus(long protocolo, String novoStatus, String comentario) {
        Solicitacao solicitacao = buscarPorProtocolo(protocolo);
        if (solicitacao == null) {
            return false;
        }

        solicitacao.atualizarStatus(novoStatus, comentario);
        return true;
    }

    private static void realizarSolicitacao() {
        System.out.println("\n=== Nova Solicitacao ===");

        Categoria categoria = lerCategoria();
        int prioridade = lerPrioridade();

        System.out.print("Informe o bairro/localizacao: ");
        String localizacao = lerTextoObrigatorio("A localizacao nao pode ficar vazia.");

        System.out.print("Descreva a solicitacao: ");
        String descricao = lerTextoObrigatorio("A descricao nao pode ficar vazia.");

        System.out.print("Solicitacao anonima? (S/N): ");
        boolean anonimo = ScannerUtil.lerLinha().equalsIgnoreCase("S");

        LocalDateTime agora = LocalDateTime.now();
        Solicitacao solicitacao = new Solicitacao(
                proximoProtocolo++,
                categoria,
                descricao,
                localizacao,
                prioridade,
                anonimo,
                "ABERTO",
                agora,
                agora,
                "Solicitacao criada"
        );

        SOLICITACOES.add(solicitacao);

        System.out.println("Solicitacao registrada com sucesso.");
        exibirSolicitacao(solicitacao);
        pausar();
    }

    private static void buscarSolicitacao() {
        System.out.print("\nInforme o protocolo da solicitacao: ");
        long protocolo = ScannerUtil.lerInt();

        Solicitacao solicitacao = buscarPorProtocolo(protocolo);
        if (solicitacao == null) {
            System.out.println("Solicitacao nao encontrada.");
        } else {
            exibirSolicitacao(solicitacao);
        }

        pausar();
    }

    private static Categoria lerCategoria() {
        List<String> categorias = Categoria.listarCategoriasPadrao();

        while (true) {
            System.out.println("Selecione a categoria:");
            for (int i = 0; i < categorias.size(); i++) {
                System.out.println((i + 1) + ") " + categorias.get(i));
            }
            System.out.print("Opcao: ");

            int opcao = ScannerUtil.lerInt();
            if (opcao >= 1 && opcao <= categorias.size()) {
                return new Categoria(categorias.get(opcao - 1));
            }

            System.out.println("Categoria invalida.");
        }
    }

    private static int lerPrioridade() {
        while (true) {
            System.out.println("Selecione a prioridade:");
            System.out.println("1) Baixa");
            System.out.println("2) Media");
            System.out.println("3) Alta");
            System.out.println("4) Critica");
            System.out.print("Opcao: ");

            int prioridade = ScannerUtil.lerInt();
            if (prioridade >= 1 && prioridade <= 4) {
                return prioridade;
            }

            System.out.println("Prioridade invalida.");
        }
    }

    private static String lerTextoObrigatorio(String mensagemErro) {
        while (true) {
            String valor = ScannerUtil.lerLinha();
            if (!valor.isBlank()) {
                return valor;
            }

            System.out.println(mensagemErro);
        }
    }

    private static void exibirSolicitacao(Solicitacao solicitacao) {
        System.out.println("\nProtocolo: " + solicitacao.getIdSolicitacao());
        System.out.println("Categoria: " + solicitacao.getCategoria().getNome());
        System.out.println("Prioridade: " + descreverPrioridade(solicitacao.getPrioridade()));
        System.out.println("Localizacao: " + solicitacao.getLocalizacao());
        System.out.println("Descricao: " + solicitacao.getDescricao());
        System.out.println("Anonimo: " + (solicitacao.isAnonimo() ? "Sim" : "Nao"));
        System.out.println("Status: " + solicitacao.getStatus());
        System.out.println("Criado em: " + FORMATADOR.format(solicitacao.getDataCriacao()));
        System.out.println("Atualizado em: " + FORMATADOR.format(solicitacao.getDataAtualizacao()));
        System.out.println("Comentario: " + solicitacao.getComentarioUltimaAtualizacao());
    }

    public static String descreverPrioridade(int prioridade) {
        return switch (prioridade) {
            case 1 -> "Baixa";
            case 2 -> "Media";
            case 3 -> "Alta";
            case 4 -> "Critica";
            default -> "Desconhecida";
        };
    }

    private static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        ScannerUtil.lerLinha();
    }
}
