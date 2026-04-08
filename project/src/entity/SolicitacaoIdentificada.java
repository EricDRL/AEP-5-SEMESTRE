package entity;

import enums.Categoria;
import enums.Prioridade;

public class SolicitacaoIdentificada extends Solicitacao {
    private Usuario usuario;

    public SolicitacaoIdentificada(Categoria categoria, String descricao, String localizacao, Prioridade prioridade,
            Usuario usuario) {
        super(categoria, descricao, localizacao, prioridade);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public boolean isAnonima() {
        return false;
    }

    @Override
    public String toString() {
        return "SolicitacaoIdentificada{" +
                "protocolo='" + getProtocolo() + '\'' +
                ", categoria=" + getCategoria() +
                ", status=" + getStatus() +
                ", usuario=" + usuario +
                '}';
    }
}
