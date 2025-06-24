package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe responsável por exibir a tela principal do sistema.
 * Apresenta uma saudação ao usuário e oferece botões com acesso às principais funcionalidades:
 * visualização de estoque, movimentação de materiais e histórico de movimentações.
 */
public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	/** Painel principal que contém todos os elementos da interface. */
	private JPanel contentPane;

	/**
	 * Método principal para inicializar a aplicação de forma independente.
	 * 
	 * @param args Argumentos da linha de comando (não utilizados).
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal("Usuário Teste");
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Construtor da tela principal do sistema.
	 * 
	 * @param nome Nome do usuário logado, exibido na interface.
	 */
	public TelaPrincipal(String nome) {
		setBackground(new Color(255, 0, 0));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(219, 232, 231));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/** Painel superior com dados do usuário logado. */
		JPanel painelUsuario = new JPanel();
		painelUsuario.setBackground(new Color(45, 55, 72));
		painelUsuario.setBounds(46, 42, 700, 120);
		painelUsuario.setLayout(null);
		painelUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(painelUsuario);
		
		/** Label com saudação "Bem Vindo" */
		JLabel lblBemVindo = new JLabel("Bem Vindo,");
		lblBemVindo.setForeground(Color.WHITE);
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblBemVindo.setBounds(10, 35, 171, 45);
		painelUsuario.add(lblBemVindo);
		
		/** Label com o nome do usuário. */
		JLabel lblNome = new JLabel(nome);
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNome.setBounds(178, 35, 390, 45);
		painelUsuario.add(lblNome);
		
		/** Label com o cargo fixo "Vendedor". */
		JLabel lblCargo = new JLabel("Vendedor");
		lblCargo.setForeground(new Color(160, 174, 192));
		lblCargo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCargo.setBounds(519, 72, 171, 37);
		painelUsuario.add(lblCargo);
		
		/** Botão para abrir a tela de visualização do estoque. */
		JButton btnVisualizar = new JButton("Visualizar Estoque");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaVisualizarTabela telaVisualizarTabela = new TelaVisualizarTabela();
				telaVisualizarTabela.setLocationRelativeTo(null);
				telaVisualizarTabela.setVisible(true);
			}
		});
		btnVisualizar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnVisualizar.setBounds(29, 298, 236, 132);
		btnVisualizar.setBackground(Color.WHITE);
		btnVisualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVisualizar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnVisualizar.setIcon(new ImageIcon("icon/visualizar.png"));
		contentPane.add(btnVisualizar);
		
		/** Botão para abrir a tela de movimentação de estoque. */
		JButton btnMovimentar = new JButton("Movimentar Estoque");
		btnMovimentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMovimentarTabela telaMovimentarTabela = new TelaMovimentarTabela();
				telaMovimentarTabela.setLocationRelativeTo(null);
				telaMovimentarTabela.setVisible(true);
			}
		});
		btnMovimentar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnMovimentar.setBounds(275, 298, 236, 132);
		btnMovimentar.setBackground(Color.WHITE);
		btnMovimentar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMovimentar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMovimentar.setIcon(new ImageIcon("icon/movimentarr.png"));
		contentPane.add(btnMovimentar);
		
		/** Botão para abrir o histórico de movimentações. */
		JButton btnHistorico = new JButton("<html>Histórico de<br>Movimentações</html>");
		btnHistorico.setToolTipText("");
		btnHistorico.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnHistorico.setBounds(521, 298, 236, 132);
		btnHistorico.setBackground(Color.WHITE);
		btnHistorico.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHistorico.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnHistorico.setIcon(new ImageIcon("icon/historicoo.png"));
		contentPane.add(btnHistorico);

		btnHistorico.addActionListener(e -> {
		    TelaHistorico tela = new TelaHistorico();
		    tela.setLocationRelativeTo(null);
		    tela.setVisible(true);
		});
	}
}
