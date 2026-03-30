package entity;

import java.time.LocalDateTime;

public class Solicitacao {

    private Long idSolicitacao;
    private String categoria;
    private String descricao;
    private String localizacao;
    private int prioridade;
    private boolean anonimo;

    private String status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    private String comentarioUltimaAtualizacao;

    public Solicitacao(Long idSolicitacao, String categoria, String descricao,
                       String localizacao, int prioridade, boolean anonimo) {
        this.idSolicitacao = idSolicitacao;
        this.categoria = categoria;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.prioridade = prioridade;
        this.anonimo = anonimo;
        this.status = "aberto";
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public boolean isAnonimo() {
        return anonimo;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public String getComentarioUltimaAtualizacao() {
        return comentarioUltimaAtualizacao;
    }

    public void atualizarStatus(String novoStatus, String comentario) {
        if (comentario == null || comentario.isEmpty()) {
            throw new IllegalArgumentException("Comentário é obrigatório para atualizar o status.");
        }

        this.status = novoStatus;
        this.comentarioUltimaAtualizacao = comentario;
        this.dataAtualizacao = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Solicitacao{" +
                "id=" + idSolicitacao +
                ", categoria='" + categoria + '\'' +
                ", descricao='" + descricao + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", prioridade=" + prioridade +
                ", anonimo=" + anonimo +
                ", status='" + status + '\'' +
                '}';
    }
}