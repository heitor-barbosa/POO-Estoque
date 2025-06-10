package model;

public class Material {
	// Atributos
	private int id;
	private String nome;
	private String tipo; 	// adesivo, solado, fivela, linha 
	private String marca;
	private int quantidade;
	
	
	// Construtores
	public Material(int id, String nome, String tipo, String marca, int quantidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.marca = marca;
		this.quantidade = quantidade;
	}
	
	public Material() {
		
	}
	
	
	// Getters e Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
	// Metodos
	
	
	
}
