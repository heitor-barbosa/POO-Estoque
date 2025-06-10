package model;

import java.util.List;

public class Estoque {
	// Atributos
	private List <Material> materiais;

	
	// Construtores
	public Estoque(List<Material> materiais) {
		this.materiais = materiais;
	} 
	public Estoque() {
		
	}
	
	
	// Getter e Setter
	public List<Material> getMateriais() {
		return materiais;
	}
	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
	}
	
	
	// Metodos
	
	

}
