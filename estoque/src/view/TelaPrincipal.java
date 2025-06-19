package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel painelUsuario = new JPanel();
		painelUsuario.setBounds(46, 42, 700, 120);
		contentPane.add(painelUsuario);
		painelUsuario.setLayout(null);
		
		JLabel lblBemVindo = new JLabel("Bem Vindo,");
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblBemVindo.setBounds(10, 35, 171, 45);
		painelUsuario.add(lblBemVindo);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNome.setBounds(178, 35, 171, 45);
		painelUsuario.add(lblNome);
		
		JLabel lblCargo = new JLabel("Cargo ");
		lblCargo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCargo.setBounds(529, 75, 171, 45);
		painelUsuario.add(lblCargo);
		
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
		contentPane.add(btnVisualizar);
		
		JButton btnMovimentar = new JButton("Movimentar Estoque");
		btnMovimentar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnMovimentar.setBounds(275, 298, 236, 132);
		contentPane.add(btnMovimentar);
		
		JButton btnHistorico = new JButton("<html>Historico de<br>Movimentações<html>");
		btnHistorico.setToolTipText("");
		btnHistorico.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnHistorico.setBounds(521, 298, 236, 132);
		contentPane.add(btnHistorico);
	}
	
}
