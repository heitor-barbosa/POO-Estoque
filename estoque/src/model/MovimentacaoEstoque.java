package model;

import java.time.LocalDateTime;

public class MovimentacaoEstoque {
	// Atributos
	private int id;
	private String tipo; 	// ENTRADA ou SAIDA
	private int qtdMovimentada;
	private LocalDateTime dataHora;
	private Material materialMovimentado;
//	private Usuario usuarioResponsavel; 	// podemos colocar ou nao, vamos fazendo e no final a gente decide
	
	
	// Construtor
	public MovimentacaoEstoque(int id, String tipo, int qtdNecessaria, LocalDateTime dataHora, Material materialMovimentado) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.qtdMovimentada = qtdNecessaria;
		this.dataHora = dataHora;
		this.materialMovimentado = materialMovimentado;
	}
	public MovimentacaoEstoque() {
		this.dataHora = LocalDateTime.now();	 // Data e hora sao registradas na criação da movimentação 
	}

	
	// Getter e Setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getQtdNecessaria() {
		return qtdMovimentada;
	}
	public void setQtdNecessaria(int qtdNecessaria) {
		this.qtdMovimentada = qtdNecessaria;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public Material getMaterialMovimentado() {
		return materialMovimentado;
	}
	public void setMaterialMovimentado(Material materialMovimentado) {
		this.materialMovimentado = materialMovimentado;
	}
	
	
	// Metodos
}
