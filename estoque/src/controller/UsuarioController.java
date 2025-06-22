package controller;

import model.Usuario;
import persistencia.UsuarioDAO;

import java.util.List;

public class UsuarioController {

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

