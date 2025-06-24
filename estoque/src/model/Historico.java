package model;

import java.util.List;

public class Historico {
    /**
     * Lista de movimentações de estoque registradas no histórico.
     */
    private List<MovimentacaoEstoque> movimentacoes;

    /**
     * Construtor que inicializa o histórico com uma lista de movimentações fornecida.
     * 
     * @param movimentacoes lista inicial de movimentações do histórico
     */
    public Historico(List<MovimentacaoEstoque> movimentacoes) {
        super();
        this.movimentacoes = movimentacoes;
    }

    /**
     * Retorna a lista de movimentações do histórico.
     * 
     * @return lista de movimentações de estoque
     */
    public List<MovimentacaoEstoque> getMovimentacoes() {
        return movimentacoes;
    }

    /**
     * Define a lista de movimentações do histórico.
     * 
     * @param movimentacoes nova lista de movimentações
     */
    public void setMovimentacoes(List<MovimentacaoEstoque> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    // Métodos adicionais podem ser implementados aqui

}
