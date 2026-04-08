package entity;

import java.time.LocalDateTime;
import java.util.UUID;
import enums.Categoria;
import enums.Prioridade;
import enums.StatusSolicitacao;

public abstract class Solicitacao {
    private String protocolo;
    private Categoria categoria;
    private String descricao;
    private String localizacao;
    private Prioridade prioridade;
    private StatusSolicitacao status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    protected Solicitacao(Categoria categoria, String descricao, String localizacao, Prioridade prioridade) {
        this.protocolo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.categoria = categoria;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.prioridade = prioridade;
        this.status = StatusSolicitacao.ABERTO;
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    public String getProtocolo() {
        return this.protocolo;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getLocalizacao() {
        return this.localizacao;
    }

    public Prioridade getPrioridade() {
        return this.prioridade;
    }

    public StatusSolicitacao getStatus() {
        return this.status;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    public void atualizarStatus(StatusSolicitacao novoStatus) {
        this.status = novoStatus;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public abstract boolean isAnonima();

    @Override
    public abstract String toString();
}