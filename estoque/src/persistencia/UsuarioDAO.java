package persistencia;

import model.Usuario;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por acessar os dados dos usuários armazenados em arquivo.
 * Realiza a leitura e o mapeamento dos dados do arquivo "Usuarios.txt".
 */
public class UsuarioDAO {

    /**
     * Lê o arquivo de usuários e retorna uma lista de objetos {@link Usuario}.
     * <p>
     * O arquivo deve estar localizado na pasta "dados" e conter usuários
     * separados por ponto e vírgula no seguinte formato:
     * <br><code>nome;login;senha;nivelAcesso</code>
     *
     * @return Lista de usuários encontrados no arquivo. Retorna uma lista vazia se o arquivo não for encontrado.
     * @throws IOException se ocorrer um erro ao ler o arquivo.
     */
    public List<Usuario> listarUsuarios() throws IOException {
        // Monta o caminho do arquivo "dados/Usuarios.txt"
        Path path = Paths.get("dados", "Usuarios.txt");

        // Verifica se o arquivo existe
        if (!Files.exists(path)) {
            System.err.println("Arquivo não encontrado: " + path.toAbsolutePath());
            return new ArrayList<>();
        }

        // Lê todas as linhas do arquivo
        List<String> linhas = Files.readAllLines(path);
        List<Usuario> usuarios = new ArrayList<>();

        // Processa cada linha convertendo-a em um objeto Usuario
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length >= 4) {
                String nome = partes[0].trim();
                String login = partes[1].trim();
                String senha = partes[2].trim();
                String nivelAcesso = partes[3].trim();

                usuarios.add(new Usuario(nome, login, senha, nivelAcesso));
            }
        }

        return usuarios;
    }
}
