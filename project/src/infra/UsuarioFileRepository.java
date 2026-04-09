// package infra;

// import entity.Usuario;
// import repositories.UsuarioRepository;

// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// public class UsuarioFileRepository implements UsuarioRepository {

// private static final String FILE_PATH = "usuarios.csv";

// // Salva um novo usuário no final do arquivo CSV
// @Override
// public void adicionar(Usuario usuario) {
// try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH,
// true))) {
// String linhaCsv = usuario.getNome() + "," + usuario.getCpf();
// writer.write(linhaCsv);
// writer.newLine();
// } catch (IOException e) {
// e.printStackTrace();
// }
// }

// // Lê o arquivo CSV e retorna todos os usuários cadastrados
// public List<Usuario> listar() {
// List<Usuario> usuarios = new ArrayList<>();

// try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
// String linha;
// while ((linha = reader.readLine()) != null) {
// String[] campos = linha.split(",");
// String nome = campos[0];
// String cpf = campos[1];
// usuarios.add(new Usuario(nome, cpf));
// }
// } catch (IOException e) {
// e.printStackTrace();
// }

// return usuarios;
// }

// @Override
// public void remover(Usuario usuario) {
// throw new UnsupportedOperationException("remover() não implementado no
// UsuarioFileRepository");
// }

// @Override
// public List<Usuario> buscarPorNome(String nome) {
// throw new UnsupportedOperationException("buscarPorNome() não implementado no
// UsuarioFileRepository");
// }

// @Override
// public void atualizar(Usuario usuario) {
// throw new UnsupportedOperationException("atualizar() não implementado no
// UsuarioFileRepository");
// }

// @Override
// public Usuario buscarPorCpf(String cpf) {
// return null;
// }
// }
