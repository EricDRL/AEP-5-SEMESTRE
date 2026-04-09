package services;

import entity.Solicitacao;
import entity.SolicitacaoAnonima;
import entity.SolicitacaoIdentificada;
import entity.Usuario;
import enums.Categoria;
import enums.Prioridade;
import repositories.SolicitacaoRepository;

public class SolicitacaoService {
    private final SolicitacaoRepository solicitacaoRepository;

    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
    }

    public Solicitacao criarSolicitacao(Categoria categoria, String descricao, String localizacao,
            Prioridade prioridade, Usuario usuario) {
        validarDescricao(descricao);
        Solicitacao solicitacao = new SolicitacaoIdentificada(
                categoria,
                descricao,
                localizacao,
                prioridade,
                usuario);
        solicitacaoRepository.adicionar(solicitacao);
        return solicitacao;
    }

    public Solicitacao criarSolicitacao(Categoria categoria, String descricao, String localizacao,
            Prioridade prioridade) {
        validarDescricao(descricao);
        Solicitacao solicitacao = new SolicitacaoAnonima(
                categoria,
                descricao,
                localizacao,
                prioridade);
        solicitacaoRepository.adicionar(solicitacao);
        return solicitacao;
    }

    public Solicitacao buscarPorProtocolo(String protocolo) {
        return solicitacaoRepository.buscarPorProtocolo(protocolo);
    }

    public void atualizarStatus(String protocolo, enums.StatusSolicitacao novoStatus) {
        Solicitacao solicitacao = buscarPorProtocolo(protocolo);
        if (solicitacao == null) {
            throw new IllegalArgumentException("Solicitação não encontrada para o protocolo: " + protocolo);
        }
        solicitacao.atualizarStatus(novoStatus);
        solicitacaoRepository.atualizar(solicitacao);
    }

    public java.util.List<Solicitacao> listarTodas() {
        return solicitacaoRepository.listarTodas();
    }

    public java.util.List<Solicitacao> listarPorPrioridade(Prioridade prioridade) {
        return solicitacaoRepository.listarTodas().stream()
                .filter(s -> s.getPrioridade() == prioridade)
                .toList();
    }

    public java.util.List<Solicitacao> listarPorCategoria(Categoria categoria) {
        return solicitacaoRepository.listarTodas().stream()
                .filter(s -> s.getCategoria() == categoria)
                .toList();
    }

    public java.util.List<Solicitacao> listarPorStatus(enums.StatusSolicitacao status) {
        return solicitacaoRepository.listarTodas().stream()
                .filter(s -> s.getStatus() == status)
                .toList();
    }

    private void validarDescricao(String descricao) {
        if (descricao == null || descricao.trim().length() < 10) {
            throw new IllegalArgumentException("A descrição deve ter pelo menos 10 caracteres.");
        }
    }
}
