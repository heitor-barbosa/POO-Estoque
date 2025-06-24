package view;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.UsuarioController;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textoLogin;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
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

        JLabel lblTitulo = new JLabel("Bem-vindo!");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 32));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(59, 0, 215, 45);
        panel.add(lblTitulo);

        JLabel lblUsuario = new JLabel("Login:");
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsuario.setBounds(69, 65, 115, 25);
        panel.add(lblUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSenha.setBounds(69, 125, 166, 25);
        panel.add(lblSenha);

        textoLogin = new JTextField();
        textoLogin.setBounds(68, 89, 197, 25);
        panel.add(textoLogin);
        textoLogin.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(69, 148, 196, 25);
        panel.add(passwordField);

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
                    dispose();
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

