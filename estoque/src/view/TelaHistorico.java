package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import controller.EstoqueController;
import model.ModeloTabelaHistorico;
import model.MovimentacaoEstoque;

/**
 * Tela que exibe o histórico de movimentações do estoque em uma tabela.
 * Permite visualizar registros de entrada e saída de materiais.
 */
public class TelaHistorico extends JDialog {

	private static final long serialVersionUID = 1L;

	/** Tabela que exibe os registros do histórico. */
	private JTable table;

	/**
	 * Construtor da tela de histórico.
	 * Inicializa os componentes gráficos e exibe os dados de movimentações.
	 */
	public TelaHistorico() {
		setTitle("Histórico de Movimentações");
		setModal(true); // Bloqueia interação com outras janelas enquanto esta estiver aberta
		setBounds(100, 100, 800, 500);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());

		// Recupera o histórico de movimentações do controller
		EstoqueController controller = EstoqueController.getInstance();
		ArrayList<MovimentacaoEstoque> historico = controller.getHistorico();

		// Cria a tabela com o modelo específico para histórico
		table = new JTable(new ModeloTabelaHistorico(historico));
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		// Painel inferior com botão de fechar
		JPanel panel = new JPanel();
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(e -> dispose());
		panel.add(btnFechar);
		getContentPane().add(panel, BorderLayout.SOUTH);
	}
}
