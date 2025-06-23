package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MovimentacaoEstoque implements Serializable {
    private static final long serialVersionUID = 1L;

    // Atributos
    private int id;
    private String tipo;  // ENTRADA ou SAIDA
    private int qtdMovimentada;
    private LocalDateTime dataHora;
    private Material materialMovimentado;

    // Construtores
    public MovimentacaoEstoque(int id, String tipo, int qtdMovimentada, LocalDateTime dataHora, Material materialMovimentado) {
        super();
        this.id = id;
        this.tipo = tipo;
        this.qtdMovimentada = qtdMovimentada;
        this.dataHora = dataHora;
        this.materialMovimentado = materialMovimentado;
    }

    public MovimentacaoEstoque() {
        this.dataHora = LocalDateTime.now();
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
