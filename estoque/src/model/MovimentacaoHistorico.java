package model;

import java.util.Date;

public class MovimentacaoHistorico {
    /**
     * Identificador único da movimentação no histórico.
     */
    private int id;

    /**
     * Tipo da movimentação (ex: "ENTRADA" ou "SAÍDA").
     */
    private String tipo;

    /**
     * Nome do material movimentado.
     */
    private String nomeMaterial;

    /**
     * Quantidade movimentada.
     */
    private int quantidade;

    /**
     * Data da movimentação.
     */
    private Date data;

    /**
     * Construtor que inicializa todos os atributos da movimentação no histórico.
     * 
     * @param id identificador da movimentação
     * @param tipo tipo da movimentação
     * @param nomeMaterial nome do material movimentado
     * @param quantidade quantidade movimentada
     * @param data data da movimentação
     */
    public MovimentacaoHistorico(int id, String tipo, String nomeMaterial, int quantidade, Date data) {
        this.id = id;
        this.tipo = tipo;
        this.nomeMaterial = nomeMaterial;
        this.quantidade = quantidade;
        this.data = data;
    }

    /**
     * Retorna o identificador da movimentação.
     * 
     * @return id da movimentação
     */
    public int getId() {
        return id;
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
     * Retorna o nome do material movimentado.
     * 
     * @return nome do material
     */
    public String getNomeMaterial() {
        return nomeMaterial;
    }

    /**
     * Retorna a quantidade movimentada.
     * 
     * @return quantidade movimentada
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Retorna a data da movimentação.
     * 
     * @return data da movimentação
     */
    public Date getData() {
        return data;
    }
}
