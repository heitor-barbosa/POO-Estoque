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

public class TelaVisualizarTabela extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	private ArrayList<Material> materiais;

	/**
	 * Launch the application.
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
	 * Create the dialog.
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
		EstoqueController controller = new EstoqueController();
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
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
