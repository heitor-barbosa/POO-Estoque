package persistencia;

import model.Usuario;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    public List<Usuario> listarUsuarios() throws IOException {
        // Monta o caminho de forma relativa à raiz do projeto
        Path path = Paths.get("dados", "Usuarios.txt");

        // Verifica se o arquivo existe
        if (!Files.exists(path)) {
            System.err.println("Arquivo não encontrado: " + path.toAbsolutePath());
            return new ArrayList<>();
        }

        // Lê todas as linhas
        List<String> linhas = Files.readAllLines(path);
        List<Usuario> usuarios = new ArrayList<>();

        // Processa cada linha
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
