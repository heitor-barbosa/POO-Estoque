package model;

import java.util.Date;

public class MovimentacaoHistorico {
    private int id;
    private String tipo;
    private String nomeMaterial;
    private int quantidade;
    private Date data;

    public MovimentacaoHistorico(int id, String tipo, String nomeMaterial, int quantidade, Date data) {
        this.id = id;
        this.tipo = tipo;
        this.nomeMaterial = nomeMaterial;
        this.quantidade = quantidade;
        this.data = data;
    }

    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public String getNomeMaterial() { return nomeMaterial; }
    public int getQuantidade() { return quantidade; }
    public Date getData() { return data; }
}

