package infra;

import entity.Solicitacao;
import repositories.SolicitacaoRepository;

import java.util.ArrayList;
import java.util.List;

public class SolicitacaoMemoryRepository implements SolicitacaoRepository {

    private final List<Solicitacao> solicitacoes;

    public SolicitacaoMemoryRepository() {
        this.solicitacoes = new ArrayList<>();
    }

    @Override
    public void adicionar(Solicitacao solicitacao) {
        if (solicitacao != null) {
            this.solicitacoes.add(solicitacao);
        }
    }

    @Override
    public void remover(Solicitacao solicitacao) {
        if (solicitacao != null) {
            this.solicitacoes.remove(solicitacao);
        }
    }

    @Override
    public List<Solicitacao> listarTodas() {
        // Retorna uma cópia para proteger e encapsular a lista original
        return new ArrayList<>(this.solicitacoes);
    }

    @Override
    public Solicitacao buscarPorProtocolo(String protocolo) {
        return this.solicitacoes.stream()
                .filter(s -> s.getProtocolo().equalsIgnoreCase(protocolo))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void atualizar(Solicitacao solicitacao) {
        for (int i = 0; i < this.solicitacoes.size(); i++) {
            if (this.solicitacoes.get(i).getProtocolo().equals(solicitacao.getProtocolo())) {
                this.solicitacoes.set(i, solicitacao);
                return;
            }
        }
    }
}
