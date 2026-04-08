package repositories;

import entity.Solicitacao;
import java.util.List;

public interface SolicitacaoRepository {
    void adicionar(Solicitacao solicitacao);
    void remover(Solicitacao solicitacao);
    List<Solicitacao> listarTodas();
    Solicitacao buscarPorProtocolo(String protocolo);
    void atualizar(Solicitacao solicitacao);
}
