package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.EstoqueController;
import model.Material;
import model.ModeloTabela;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaMovimentarTabela extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	private ArrayList<Material> materiais;
	private JTextField txtBusqueUmMaterial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaMovimentarTabela dialog = new TelaMovimentarTabela();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaMovimentarTabela() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 964, 448);
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
		
		JButton btnNewButton = new JButton("Adicionar Material");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Formulario para pegar informaçoes do produto 
				JTextField txtId = new JTextField();
				JTextField txtNome = new JTextField();
				JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Adesivo", "Solado", "Fivela", "Linha"});
				JComboBox<String> cbMarca = new JComboBox<>(new String[]{"MarcaX", "MarcaY", "MarcaZ"});
				JTextField txtQuantidade = new JTextField();

				JPanel panel = new JPanel(new GridLayout(0, 1));
				panel.add(new JLabel("ID:"));
				panel.add(txtId);
				panel.add(new JLabel("Nome:"));
				panel.add(txtNome);
				panel.add(new JLabel("Tipo:"));
				panel.add(cbTipo);
				panel.add(new JLabel("Marca:"));
				panel.add(cbMarca);
				panel.add(new JLabel("Quantidade:"));
				panel.add(txtQuantidade);

				int result = JOptionPane.showConfirmDialog(
				    null, panel, "Adicionar Material", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
				    try {
				        int id = Integer.parseInt(txtId.getText());
				        String nome = txtNome.getText();
				        String tipo = (String) cbTipo.getSelectedItem();
				        String marca = (String) cbMarca.getSelectedItem();
				        int quantidade = Integer.parseInt(txtQuantidade.getText());
				        
				        // Cria material com os dados fornecidos, e chama o controller
				        Material material = new Material(id, nome, tipo, marca, quantidade);
				        controller.botaoAdicionarMaterial(material);

				        JOptionPane.showMessageDialog(null, "Material adicionado com sucesso!");
				        
				        // Chama controller novamente para atualizar a tabela
				        ArrayList<Material> listaAtualizada = controller.getMateriais();
				        table.setModel(new ModeloTabela(listaAtualizada));
				        
				    } catch (NumberFormatException e1) {
				        JOptionPane.showMessageDialog(null, "ID e Quantidade devem ser números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
				    }
				}
				
			}
		});
		btnNewButton.setBounds(23, 23, 157, 23);
		contentPanel.add(btnNewButton);
		
		JButton btnRemoverProduto = new JButton("Remover Material");
		btnRemoverProduto.setBounds(225, 23, 157, 23);
		contentPanel.add(btnRemoverProduto);
		
		JButton btnEditarMaterial = new JButton("Editar Material");
		btnEditarMaterial.setBounds(428, 23, 157, 23);
		contentPanel.add(btnEditarMaterial);
		
		JPanel BarraPesquisa = new JPanel();
		BarraPesquisa.setBounds(624, 23, 350, 30);
		contentPanel.add(BarraPesquisa);
		BarraPesquisa.setLayout(null);
		
		txtBusqueUmMaterial = new JTextField();
		txtBusqueUmMaterial.setBounds(0, 5, 260, 20);
		BarraPesquisa.add(txtBusqueUmMaterial);
		txtBusqueUmMaterial.setText("Busque um material aqui");
		txtBusqueUmMaterial.setToolTipText("");
		txtBusqueUmMaterial.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setBounds(270, 4, 80, 23);
		BarraPesquisa.add(btnNewButton_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				JButton cancelButton = new JButton("Voltar");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(e -> {
				    dispose();
				});
			}
		}
	}
}
