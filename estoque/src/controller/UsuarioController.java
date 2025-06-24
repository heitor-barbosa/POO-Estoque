package controller;

import model.Usuario;
import persistencia.UsuarioDAO;

import java.util.List;

public class UsuarioController {

    /**
     * Autentica um usuário verificando login e senha.
     * 
     * @param login login do usuário a ser autenticado
     * @param senha senha do usuário a ser autenticado
     * @return nome do usuário autenticado caso login e senha estejam corretos; 
     *         retorna null se não encontrar usuário válido
     * @throws RuntimeException se ocorrer erro ao acessar os dados dos usuários
     */
    public String autenticar(String login, String senha) {
        try {
            List<Usuario> usuarios = new UsuarioDAO().listarUsuarios();

            for (Usuario u : usuarios) {
                if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                    return u.getNome(); 
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao autenticar: " + e.getMessage(), e);
        }
        return null;
    }
}
