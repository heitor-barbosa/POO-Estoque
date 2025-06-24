package model;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ModeloTabelaHistorico extends AbstractTableModel {
    // private static final long serialVersionUID = 1L;

    /**
     * Nomes das colunas da tabela de histórico de movimentações.
     */
    private String[] colunas = {"ID", "Tipo", "Material", "Quantidade", "Data"};

    /**
     * Lista de movimentações de estoque exibidas na tabela.
     */
    private ArrayList<MovimentacaoEstoque> movimentacoes;

    /**
     * Formatador para exibir data e hora no formato "dd/MM/yyyy HH:mm".
     */
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Construtor que recebe a lista de movimentações para popular a tabela.
     * 
     * @param historico lista de movimentações de estoque
     */
    public ModeloTabelaHistorico(ArrayList<MovimentacaoEstoque> historico) {
        this.movimentacoes = historico;
    }

    /**
     * Retorna o número de linhas da tabela, correspondente ao número de movimentações.
     * 
     * @return número de linhas
     */
    @Override
    public int getRowCount() {
        return movimentacoes.size();
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
     * Retorna o valor da célula na linha e coluna especificadas.
     * 
     * @param rowIndex índice da linha
     * @param columnIndex índice da coluna
     * @return valor correspondente da movimentação
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MovimentacaoEstoque m = movimentacoes.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> m.getId();
            case 1 -> m.getTipo();
            case 2 -> m.getMaterialMovimentado().getNome(); // assume método getNome() em Material
            case 3 -> m.getQtdNecessaria();
            case 4 -> m.getDataHora().format(formatter);
            default -> null;
        };
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
