package model;

import java.util.List;

public class Estoque {
    /**
     * Lista de materiais que compõem o estoque.
     */
    private List<Material> materiais;

    /**
     * Construtor que inicializa o estoque com uma lista de materiais fornecida.
     * 
     * @param materiais lista inicial de materiais do estoque
     */
    public Estoque(List<Material> materiais) {
        this.materiais = materiais;
    } 

    /**
     * Construtor padrão que cria um estoque vazio.
     */
    public Estoque() {
    }

    /**
     * Retorna a lista de materiais do estoque.
     * 
     * @return lista de materiais
     */
    public List<Material> getMateriais() {
        return materiais;
    }

    /**
     * Define a lista de materiais do estoque.
     * 
     * @param materiais nova lista de materiais
     */
    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
    }

    // Métodos adicionais podem ser implementados aqui

}
