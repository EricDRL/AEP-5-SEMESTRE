package infra;

import entity.Usuario;
import repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMemoryRepository implements UsuarioRepository {

    private final List<Usuario> usuarios;

    public UsuarioMemoryRepository() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void adicionar(Usuario usuario) {
        if (usuario != null) {
            this.usuarios.add(usuario);
        }
    }

    @Override
    public void remover(Usuario usuario) {
        if (usuario != null) {
            this.usuarios.remove(usuario);
        }
    }

    @Override
    public List<Usuario> buscarPorNome(String nome) {
        return this.usuarios.stream()
                .filter(u -> u.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());
    }

    @Override
    public void atualizar(Usuario usuario) {
        for (int i = 0; i < this.usuarios.size(); i++) {
            if (this.usuarios.get(i).getCpf().equals(usuario.getCpf())) {
                this.usuarios.set(i, usuario);
                return;
            }
        }
    }
}
