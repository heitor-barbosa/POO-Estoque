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

/**
 * Tela responsável por permitir a movimentação de materiais no estoque.
 * Oferece funcionalidades para adicionar, editar, remover e buscar materiais.
 */
public class TelaMovimentarTabela extends JDialog {

	private static final long serialVersionUID = 1L;

	/** Painel principal da tela. */
	private final JPanel contentPanel = new JPanel();

	/** Tabela que exibe os materiais. */
	private JTable table;

	/** Lista de materiais a ser exibida e manipulada. */
	private ArrayList<Material> materiais;

	/** Campo de texto usado para buscar materiais por nome. */
	private JTextField txtBusqueUmMaterial;

	/**
	 * Método principal para executar a tela isoladamente.
	 * 
	 * @param args Argumentos da linha de comando.
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
	 * Construtor da tela de movimentação de materiais.
	 */
	public TelaMovimentarTabela() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// Componente de rolagem que conterá a tabela
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 964, 448);
		contentPanel.add(scrollPane);

		// Busca os materiais via controller
		EstoqueController controller = EstoqueController.getInstance();
		materiais = controller.getMateriais();

		// Define o modelo da tabela com os dados carregados
		ModeloTabela modeloTabela = new ModeloTabela(materiais);
		{
			table = new JTable();
			scrollPane.setViewportView(table);
			table.setModel(modeloTabela);
		}

		/**
		 * Botão para adicionar um novo material ao estoque.
		 */
		JButton btnAdicionarMaterial = new JButton("Adicionar Material");
		btnAdicionarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Formulário para adicionar um novo material
				JTextField txtNome = new JTextField();
				JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Adesivo", "Solado", "Fivela", "Linha"});
				JComboBox<String> cbMarca = new JComboBox<>(new String[]{"MarcaX", "MarcaY", "MarcaZ"});
				JTextField txtQuantidade = new JTextField();

				JPanel panel = new JPanel(new GridLayout(0, 1));
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
						int id = controller.gerarId();
						String nome = txtNome.getText();
						String tipo = (String) cbTipo.getSelectedItem();
						String marca = (String) cbMarca.getSelectedItem();
						int quantidade = Integer.parseInt(txtQuantidade.getText());

						Material material = new Material(id, nome, tipo, marca, quantidade);
						controller.botaoAdicionarMaterial(material);

						JOptionPane.showMessageDialog(null, "Material adicionado com sucesso!");

						ArrayList<Material> listaAtualizada = controller.getMateriais();
						table.setModel(new ModeloTabela(listaAtualizada));

					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Quantidade deve ser número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAdicionarMaterial.setBounds(23, 23, 157, 23);
		contentPanel.add(btnAdicionarMaterial);

		/**
		 * Botão para remover material a partir do ID informado.
		 */
		JButton btnRemoverMaterial = new JButton("Remover Material");
		btnRemoverMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField txtId = new JTextField();
				JPanel panel = new JPanel(new GridLayout(0, 1));
				panel.add(new JLabel("ID:"));
				panel.add(txtId);

				int result = JOptionPane.showConfirmDialog(
					null, panel, "Remover Material (ID)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					try {
						int id = Integer.parseInt(txtId.getText());
						controller.botaoRemoverMaterial(id);
						JOptionPane.showMessageDialog(null, "Item removido!");

						ArrayList<Material> listaAtualizada = controller.getMateriais();
						table.setModel(new ModeloTabela(listaAtualizada));
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Por favor, digite um número válido para o ID.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnRemoverMaterial.setBounds(225, 23, 157, 23);
		contentPanel.add(btnRemoverMaterial);

		/**
		 * Botão para editar um material existente, a partir do ID informado.
		 */
		JButton btnEditarMaterial = new JButton("Editar Material");
		btnEditarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idParaEditar = 0;
				JTextField txtId = new JTextField();
				JPanel panel = new JPanel(new GridLayout(0, 1));
				panel.add(new JLabel("ID:"));
				panel.add(txtId);

				int result = JOptionPane.showConfirmDialog(
					null, panel, "Editar Material (ID)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					try {
						idParaEditar = Integer.parseInt(txtId.getText());
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Por favor, digite um número válido para o ID.", "Erro", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				Material materialAntigo = controller.getMaterialPorId(idParaEditar);
				if (materialAntigo == null) {
					JOptionPane.showMessageDialog(null, "ID não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Formulário de edição com dados antigos preenchidos
				JTextField txtNome = new JTextField(materialAntigo.getNome());
				JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Adesivo", "Solado", "Fivela", "Linha"});
				cbTipo.setSelectedItem(materialAntigo.getTipo());
				JComboBox<String> cbMarca = new JComboBox<>(new String[]{"MarcaX", "MarcaY", "MarcaZ"});
				cbMarca.setSelectedItem(materialAntigo.getMarca());
				JTextField txtQuantidade = new JTextField(String.valueOf(materialAntigo.getQuantidade()));

				JPanel panel1 = new JPanel(new GridLayout(0, 1));
				panel1.add(new JLabel("Nome:"));
				panel1.add(txtNome);
				panel1.add(new JLabel("Tipo:"));
				panel1.add(cbTipo);
				panel1.add(new JLabel("Marca:"));
				panel1.add(cbMarca);
				panel1.add(new JLabel("Quantidade:"));
				panel1.add(txtQuantidade);

				int result1 = JOptionPane.showConfirmDialog(
					null, panel1, "Editar Material", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (result1 == JOptionPane.OK_OPTION) {
					try {
						String nome = txtNome.getText();
						String tipo = (String) cbTipo.getSelectedItem();
						String marca = (String) cbMarca.getSelectedItem();
						int quantidade = Integer.parseInt(txtQuantidade.getText());

						Material materialEditado = new Material(idParaEditar, nome, tipo, marca, quantidade);
						controller.botaoEditarMaterial(materialEditado);

						JOptionPane.showMessageDialog(null, "Material editado com sucesso!");

						ArrayList<Material> listaAtualizada = controller.getMateriais();
						table.setModel(new ModeloTabela(listaAtualizada));
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Quantidade deve ser número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnEditarMaterial.setBounds(428, 23, 157, 23);
		contentPanel.add(btnEditarMaterial);

		/**
		 * Painel com barra de pesquisa e botão "Buscar".
		 */
		JPanel BarraPesquisa = new JPanel();
		BarraPesquisa.setBounds(624, 23, 350, 30);
		contentPanel.add(BarraPesquisa);
		BarraPesquisa.setLayout(null);

		txtBusqueUmMaterial = new JTextField("Busque um material aqui");
		txtBusqueUmMaterial.setBounds(0, 5, 260, 20);
		txtBusqueUmMaterial.setColumns(10);
		txtBusqueUmMaterial.setToolTipText("");
		BarraPesquisa.add(txtBusqueUmMaterial);

		// Comportamento do placeholder do campo de busca
		txtBusqueUmMaterial.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtBusqueUmMaterial.getText().equals("Busque um material aqui")) {
					txtBusqueUmMaterial.setText("");
				}
			}

			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtBusqueUmMaterial.getText().isEmpty()) {
					txtBusqueUmMaterial.setText("Busque um material aqui");
				}
			}
		});

		/**
		 * Botão que busca material pelo nome digitado.
		 */
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeBuscado = txtBusqueUmMaterial.getText();
				if (nomeBuscado == null || nomeBuscado.isEmpty()) return;

				int idEncontrado = controller.botaoBuscarMaterial(nomeBuscado);
				if (idEncontrado != -1) {
					table.setRowSelectionInterval(idEncontrado, idEncontrado);
					table.scrollRectToVisible(table.getCellRect(idEncontrado, 0, true));
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum material encontrado com esse nome.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnBuscar.setBounds(270, 4, 80, 23);
		BarraPesquisa.add(btnBuscar);

		/**
		 * Painel inferior com botão "Voltar".
		 */
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("Voltar");
		cancelButton.addActionListener(e -> dispose());
		buttonPane.add(cancelButton);
	}
}
