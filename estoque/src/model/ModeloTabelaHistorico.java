package model;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ModeloTabelaHistorico extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private String[] colunas = {"ID", "Tipo", "Material", "Quantidade", "Data"};
    private ArrayList<MovimentacaoEstoque> movimentacoes;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public ModeloTabelaHistorico(ArrayList<MovimentacaoEstoque> historico) {
        this.movimentacoes = historico;
    }

    @Override
    public int getRowCount() {
        return movimentacoes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

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

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
