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

    private void validarDescricao(String descricao) {
        if (descricao == null || descricao.trim().length() < 10) {
            throw new IllegalArgumentException("A descrição deve ter pelo menos 10 caracteres.");
        }
    }
}
