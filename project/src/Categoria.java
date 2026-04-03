import java.util.List;
import java.util.Objects;

public final class Categoria {

    public static final String ILUMINACAO = "iluminacao";
    public static final String BURACO = "buraco";
    public static final String LIMPEZA = "limpeza";
    public static final String SAUDE = "saude";
    public static final String SEGURANCA_ESCOLAR = "seguranca escolar";

    private static final List<String> CATEGORIAS_PADRAO = List.of(
            ILUMINACAO,
            BURACO,
            LIMPEZA,
            SAUDE,
            SEGURANCA_ESCOLAR
    );

    private final String nome;

    public Categoria(String nome) {
        this.nome = normalizarNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public boolean ehPadrao() {
        return CATEGORIAS_PADRAO.contains(nome);
    }

    public static List<String> listarCategoriasPadrao() {
        return CATEGORIAS_PADRAO;
    }

    private String normalizarNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("A categoria deve ser informada.");
        }

        String nomeNormalizado = nome.trim().toLowerCase();
        if (nomeNormalizado.isEmpty()) {
            throw new IllegalArgumentException("A categoria nao pode ser vazia.");
        }

        return nomeNormalizado;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Categoria categoria)) {
            return false;
        }
        return Objects.equals(nome, categoria.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
