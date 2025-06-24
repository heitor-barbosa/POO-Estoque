package view;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.UsuarioController;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe responsável por exibir a tela de login do sistema.
 * Permite ao usuário inserir seu login e senha, e realizar a autenticação.
 */
public class Login extends JFrame {

    private static final long serialVersionUID = 1L;

    /** Painel principal da tela */
    private JPanel contentPane;

    /** Campo de texto para o login do usuário */
    private JTextField textoLogin;

    /** Campo de senha para a autenticação */
    private JPasswordField passwordField;

    /**
     * Método principal que inicia a aplicação.
     * Cria e exibe a janela de login.
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setLocationRelativeTo(null); // Centraliza a tela
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Construtor que inicializa a interface da tela de login.
     * Configura os componentes e trata o evento de autenticação.
     */
    public Login() {
        setTitle("Tela de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(50, 11, 330, 240);
        contentPane.add(panel);
        panel.setLayout(null);

        // Título da tela
        JLabel lblTitulo = new JLabel("Bem-vindo!");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 32));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(59, 0, 215, 45);
        panel.add(lblTitulo);

        // Rótulo do campo de login
        JLabel lblUsuario = new JLabel("Login:");
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsuario.setBounds(69, 65, 115, 25);
        panel.add(lblUsuario);

        // Rótulo do campo de senha
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSenha.setBounds(69, 125, 166, 25);
        panel.add(lblSenha);

        // Campo para digitar o login
        textoLogin = new JTextField();
        textoLogin.setBounds(68, 89, 197, 25);
        panel.add(textoLogin);
        textoLogin.setColumns(10);

        // Campo para digitar a senha
        passwordField = new JPasswordField();
        passwordField.setBounds(69, 148, 196, 25);
        panel.add(passwordField);

        // Botão de entrada que realiza a autenticação
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login = textoLogin.getText();
                String senha = new String(passwordField.getPassword());

                UsuarioController loginController = new UsuarioController();
                String nomeUsuario = null;

                try {
                    nomeUsuario = loginController.autenticar(login, senha);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(btnEntrar, "Erro ao autenticar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (nomeUsuario != null) {
                    JOptionPane.showMessageDialog(null, "Bem-vindo, " + nomeUsuario + "!");
                    dispose(); // Fecha a tela de login
                    TelaPrincipal telaPrincipal = new TelaPrincipal(nomeUsuario);
                    telaPrincipal.setVisible(true);
                    telaPrincipal.setLocationRelativeTo(null);
                } else {
                    JOptionPane.showMessageDialog(btnEntrar, "Login ou senha incorretos", "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEntrar.setBounds(109, 193, 115, 36);
        panel.add(btnEntrar);
    }
}

