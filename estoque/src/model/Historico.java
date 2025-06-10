package model;

import java.util.List;

public class Historico {
	// Atributos
	private List <MovimentacaoEstoque> movimentacoes;

	
	// Construtor
	public Historico(List<MovimentacaoEstoque> movimentacoes) {
		super();
		this.movimentacoes = movimentacoes;
	}

	
	// Getter e Setter
	public List<MovimentacaoEstoque> getMovimentacoes() {
		return movimentacoes;
	}
	public void setMovimentacoes(List<MovimentacaoEstoque> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
	
	// Metodos
}
