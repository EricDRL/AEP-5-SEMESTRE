package repositories;

import entity.Usuario;
import java.util.List;

public interface UsuarioRepository {
    void adicionar(Usuario usuario);

    void remover(Usuario usuario);

    List<Usuario> buscarPorNome(String nome);

    Usuario buscarPorCpf(String cpf);

    void atualizar(Usuario usuario);
}
