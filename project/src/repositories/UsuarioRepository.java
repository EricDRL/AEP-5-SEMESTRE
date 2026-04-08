package repositories;

import entity.Usuario;
import java.util.List;

public interface UsuarioRepository {
    void adicionar(Usuario usuario);
    void remover(Usuario usuario);
    List<Usuario> buscarPorNome(String nome);
    void atualizar(Usuario usuario);
}
