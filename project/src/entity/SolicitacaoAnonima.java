package entity;

import enums.Categoria;
import enums.Prioridade;

public class SolicitacaoAnonima extends Solicitacao {

    public SolicitacaoAnonima(Categoria categoria, String descricao, String localizacao, Prioridade prioridade) {
        super(categoria, descricao, localizacao, prioridade);
    }

    @Override
    public boolean isAnonima() {
        return true;
    }

    @Override
    public String toString() {
        return "SolicitacaoAnonima{" +
                "protocolo='" + getProtocolo() + '\'' +
                ", categoria=" + getCategoria() +
                ", status=" + getStatus() +
                ", anonima=true" +
                '}';
    }
}
