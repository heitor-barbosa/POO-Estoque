package view;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textoUsuario;
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

		JLabel lblNewLabel = new JLabel("Bem Vindo!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(59, 0, 215, 45);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Usuário:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(69, 65, 115, 25);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Senha:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(69, 125, 166, 25);
		panel.add(lblNewLabel_2);

		textoUsuario = new JTextField();
		textoUsuario.setBounds(68, 89, 197, 25);
		panel.add(textoUsuario);
		textoUsuario.setColumns(10);

		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = textoUsuario.getText();
				String senha = new String(passwordField.getPassword());

				String nomeUsuario = obterNomeDoUsuario(usuario, senha);
				if (nomeUsuario != null) {
					dispose();
					TelaPrincipal telaPrincipal = new TelaPrincipal(nomeUsuario);
					telaPrincipal.setVisible(true);
					telaPrincipal.setLocationRelativeTo(null);
				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Usuário ou senha incorretos", "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton.setBounds(109, 193, 115, 36);
		panel.add(btnNewButton);

		passwordField = new JPasswordField();
		passwordField.setBounds(69, 148, 196, 25);
		panel.add(passwordField);
	}

	// Método para validar usuário e senha lendo do arquivo
	private String obterNomeDoUsuario(String usuario, String senha) {
		try {
			Path path = Paths.get("src/view/Usuarios.txt");
			List<String> linhas = Files.readAllLines(path);

			for (String linha : linhas) {
				String[] partes = linha.split(";");
				if (partes.length >= 3) {
					String usuarioArquivo = partes[0].trim();
					String senhaArquivo = partes[1].trim();
					String nomeCompleto = partes[2].trim();

					if (usuario.equals(usuarioArquivo) && senha.equals(senhaArquivo)) {
						return nomeCompleto;
					}
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo de usuários: " + e.getMessage());
		}
		return null;
	}

}
