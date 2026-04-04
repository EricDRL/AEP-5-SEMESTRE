package entity;

import java.time.LocalDateTime;

public class Solicitacao {
    private Long idSolicitacao;
    private Categoria categoria;
    private String descricao;
    private String localizacao;
    private int prioridade;
    private boolean anonimo;
    private String status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private String comentarioUltimaAtualizacao;

    public Solicitacao(Long idSolicitacao, Categoria categoria, String descricao, String localizacao, int prioridade, boolean anonimo, String status, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String comentarioUltimaAtualizacao) {
        this.idSolicitacao = idSolicitacao;
        this.categoria = categoria;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.prioridade = prioridade;
        this.anonimo = anonimo;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.comentarioUltimaAtualizacao = comentarioUltimaAtualizacao;
    }

    public Long getIdSolicitacao() {
        return this.idSolicitacao;
    }

    public void setIdSolicitacao(Long var1) {
        this.idSolicitacao = var1;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria var1) {
        this.categoria = var1;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getLocalizacao() {
        return this.localizacao;
    }

    public int getPrioridade() {
        return this.prioridade;
    }

    public boolean isAnonimo() {
        return this.anonimo;
    }

    public String getStatus() {
        return this.status;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    public String getComentarioUltimaAtualizacao() {
        return this.comentarioUltimaAtualizacao;
    }

    public void atualizarStatus(String var1, String var2) {
        if (var2 != null && !var2.isEmpty()) {
            this.status = var1;
            this.comentarioUltimaAtualizacao = var2;
            this.dataAtualizacao = LocalDateTime.now();
        } else {
            throw new IllegalArgumentException("Comentário é obrigatório para atualizar o status.");
        }
    }

    public String toString() {
        return "Solicitacao{id=" + this.idSolicitacao + ", categoria='" + this.categoria + "', descricao='" + this.descricao + "', localizacao='" + this.localizacao + "', prioridade=" + this.prioridade + ", anonimo=" + this.anonimo + ", status='" + this.status + "'}";
    }
}