package persistencia;

import model.Usuario;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public List<Usuario> listarUsuarios() throws IOException {
        Path path = Paths.get("src/persistencia/Usuarios.txt"); // Ou outro caminho se preferir
        List<String> linhas = Files.readAllLines(path);
        List<Usuario> usuarios = new ArrayList<>();

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
