package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MovimentacaoEstoque implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único da movimentação.
     */
    private int id;

    /**
     * Tipo da movimentação (ex: "ENTRADA" ou "SAIDA").
     */
    private String tipo;

    /**
     * Quantidade movimentada no estoque.
     */
    private int qtdMovimentada;

    /**
     * Data e hora da movimentação.
     */
    private LocalDateTime dataHora;

    /**
     * Material envolvido na movimentação.
     */
    private Material materialMovimentado;

    /**
     * Construtor que inicializa todos os atributos da movimentação.
     * 
     * @param id identificador da movimentação
     * @param tipo tipo da movimentação (ENTRADA ou SAIDA)
     * @param qtdMovimentada quantidade movimentada
     * @param dataHora data e hora da movimentação
     * @param materialMovimentado material envolvido
     */
    public MovimentacaoEstoque(int id, String tipo, int qtdMovimentada, LocalDateTime dataHora, Material materialMovimentado) {
        super();
        this.id = id;
        this.tipo = tipo;
        this.qtdMovimentada = qtdMovimentada;
        this.dataHora = dataHora;
        this.materialMovimentado = materialMovimentado;
    }

    /**
     * Construtor padrão que inicializa a data e hora com o momento atual.
     */
    public MovimentacaoEstoque() {
        this.dataHora = LocalDateTime.now();
    }

    /**
     * Retorna o ID da movimentação.
     * 
     * @return id da movimentação
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID da movimentação.
     * 
     * @param id novo id da movimentação
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o tipo da movimentação.
     * 
     * @return tipo da movimentação
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo da movimentação.
     * 
     * @param tipo novo tipo da movimentação
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna a quantidade movimentada.
     * 
     * @return quantidade movimentada
     */
    public int getQtdNecessaria() {
        return qtdMovimentada;
    }

    /**
     * Define a quantidade movimentada.
     * 
     * @param qtdNecessaria nova quantidade movimentada
     */
    public void setQtdNecessaria(int qtdNecessaria) {
        this.qtdMovimentada = qtdNecessaria;
    }

    /**
     * Retorna a data e hora da movimentação.
     * 
     * @return data e hora da movimentação
     */
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    /**
     * Define a data e hora da movimentação.
     * 
     * @param dataHora nova data e hora da movimentação
     */
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * Retorna o material envolvido na movimentação.
     * 
     * @return material movimentado
     */
    public Material getMaterialMovimentado() {
        return materialMovimentado;
    }

    /**
     * Define o material envolvido na movimentação.
     * 
     * @param materialMovimentado novo material movimentado
     */
    public void setMaterialMovimentado(Material materialMovimentado) {
        this.materialMovimentado = materialMovimentado;
    }

    // Métodos adicionais podem ser implementados aqui
}
