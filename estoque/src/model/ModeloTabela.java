package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel{
	// Atributos
	private static final String[] colunas = {
			"id", "Nome", "Tipo",  "Marca", "Quantidade"
		};
	private ArrayList<Material> materiais;
	
	// Construtor
	public ModeloTabela(ArrayList<Material> materiais) {
		super();
		this.materiais = materiais;
	}

	@Override
	public int getRowCount() {
		return materiais.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Material material = materiais.get(rowIndex);
		
		if (columnIndex == 0) {
			return material.getId();
		} else if (columnIndex == 1) {
			return material.getNome();
		} else if (columnIndex == 2) {
			return material.getTipo();
		} else if (columnIndex == 3) {
			return material.getMarca();
		} else if (columnIndex == 4) {
			return material.getQuantidade();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column){
		return colunas[column];
	}

}
