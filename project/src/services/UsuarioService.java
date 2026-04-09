package services;

import entity.Usuario;
import repositories.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(String nome, String cpf) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode estar vazio.");
        }

        if (cpf == null || cpf.replaceAll("\\D", "").length() != 11) {
            throw new IllegalArgumentException("O CPF deve conter exatamente 11 dígitos numéricos.");
        }

        Usuario novoUsuario = new Usuario(nome, cpf);
        usuarioRepository.adicionar(novoUsuario);
        return novoUsuario;
    }

    public Usuario buscarPorCpf(String cpf) {
        return usuarioRepository.buscarPorCpf(cpf);
    }
}
