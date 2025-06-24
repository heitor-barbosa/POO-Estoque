package model;

import java.io.Serializable;

public class Material implements Serializable {
    // private static final long serialVersionUID = 1L;

    /**
     * Identificador único do material.
     */
    private int id;

    /**
     * Nome do material.
     */
    private String nome;

    /**
     * Tipo do material (ex: adesivo, solado, fivela, linha).
     */
    private String tipo;

    /**
     * Marca do material (exemplo: marcaX, marcaY, marcaZ).
     */
    private String marca;

    /**
     * Quantidade disponível do material no estoque.
     */
    private int quantidade;

    /**
     * Construtor que inicializa o material com todos os atributos.
     * 
     * @param id identificador do material
     * @param nome nome do material
     * @param tipo tipo do material
     * @param marca marca do material
     * @param quantidade quantidade disponível
     */
    public Material(int id, String nome, String tipo, String marca, int quantidade) {
        super();
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.marca = marca;
        this.quantidade = quantidade;
    }

    /**
     * Construtor padrão sem parâmetros.
     */
    public Material() {
    }

    /**
     * Retorna o identificador do material.
     * 
     * @return id do material
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do material.
     * 
     * @param id novo id do material
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome do material.
     * 
     * @return nome do material
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do material.
     * 
     * @param nome novo nome do material
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o tipo do material.
     * 
     * @return tipo do material
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo do material.
     * 
     * @param tipo novo tipo do material
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna a marca do material.
     * 
     * @return marca do material
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define a marca do material.
     * 
     * @param marca nova marca do material
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Retorna a quantidade disponível do material.
     * 
     * @return quantidade disponível
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade disponível do material.
     * 
     * @param quantidade nova quantidade disponível
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Método para converter o material em formato CSV.
     * (Implementação não fornecida)
     */
    public void toCSV() {
        // Implementar se necessário
    }

    /**
     * Cria uma instância de Material a partir de uma linha CSV.
     * 
     * @param linha linha CSV com dados separados por ';'
     * @return objeto Material criado a partir da linha
     */
    public static Material fromCSV(String linha) {
        String[] partes = linha.split(";");
        int id = Integer.parseInt(partes[0]);
        String nome = partes[1];
        String tipo = partes[2];
        String marca = partes[3];
        int quantidade = Integer.parseInt(partes[4]);
        return new Material(id, nome, tipo, marca, quantidade);
    }

    /**
     * Retorna uma representação em string do material com seus atributos.
     * 
     * @return string formatada contendo os dados do material
     */
    @Override
    public String toString() {
        return "Material [id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", marca=" + marca + ", quantidade="
                + quantidade + "]";
    }

}
