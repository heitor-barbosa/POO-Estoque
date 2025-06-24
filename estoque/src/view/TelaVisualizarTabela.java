package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import controller.EstoqueController;
import model.Material;
import model.ModeloTabela;

import javax.swing.JScrollPane;

/**
 * Classe responsável por exibir uma janela (JDialog) contendo uma tabela com os materiais cadastrados no sistema.
 * Utiliza o controlador de estoque para obter os dados e exibe em um JTable com um modelo customizado.
 */
public class TelaVisualizarTabela extends JDialog {

	private static final long serialVersionUID = 1L;

	/** Painel principal do conteúdo da janela. */
	private final JPanel contentPanel = new JPanel();

	/** Tabela onde os materiais serão exibidos. */
	private JTable table;
	
	/** Lista de materiais obtida do controlador. */
	private ArrayList<Material> materiais;

	/**
	 * Método principal para execução isolada da janela.
	 * @param args argumentos de linha de comando (não utilizados)
	 */
	public static void main(String[] args) {
		try {
			TelaVisualizarTabela dialog = new TelaVisualizarTabela();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Construtor da janela de visualização da tabela.
	 * Inicializa os componentes gráficos e popula a tabela com os dados dos materiais.
	 */
	public TelaVisualizarTabela() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 964, 506);
		contentPanel.add(scrollPane);
		
		// Chamar controller para buscar dados para povoar a tabela
		EstoqueController controller = EstoqueController.getInstance();
		materiais = controller.getMateriais();
		
		// Setando o modelo de tabela criado
		ModeloTabela modeloTabela = new ModeloTabela(materiais);
		
		{
			table = new JTable();
			scrollPane.setViewportView(table);
			table.setModel(modeloTabela);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			{
				JButton cancelButton = new JButton("Voltar");
				buttonPane.add(cancelButton);
				
				// Fecha a janela ao clicar no botão "Voltar"
				cancelButton.addActionListener(e -> {
				    dispose(); 
				});
			}
		}
	}
}
