package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel {
    /**
     * Nomes das colunas da tabela.
     */
    private static final String[] colunas = {
        "id", "Nome", "Tipo", "Marca", "Quantidade"
    };

    /**
     * Lista de materiais que serão exibidos na tabela.
     */
    private ArrayList<Material> materiais;

    /**
     * Construtor que recebe a lista de materiais para popular a tabela.
     * 
     * @param materiais lista de materiais a ser exibida na tabela
     */
    public ModeloTabela(ArrayList<Material> materiais) {
        super();
        this.materiais = materiais;
    }

    /**
     * Retorna a quantidade de linhas da tabela, equivalente ao número de materiais.
     * 
     * @return número de linhas da tabela
     */
    @Override
    public int getRowCount() {
        return materiais.size();
    }

    /**
     * Retorna o número de colunas da tabela.
     * 
     * @return número de colunas
     */
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    /**
     * Retorna o valor da célula localizada na linha e coluna indicadas.
     * 
     * @param rowIndex índice da linha
     * @param columnIndex índice da coluna
     * @return valor do material na posição especificada
     */
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

    /**
     * Retorna o nome da coluna para o índice informado.
     * 
     * @param column índice da coluna
     * @return nome da coluna
     */
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
