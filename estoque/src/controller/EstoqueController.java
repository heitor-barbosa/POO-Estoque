package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.Material;
import persistencia.MaterialDAO;

public class EstoqueController {

	
	
	// Metodos

	public ArrayList<Material> getMateriais() {
		MaterialDAO matDAO = new MaterialDAO();
		ArrayList<Material> lista = new ArrayList<>();
		try {
			lista = matDAO.lerMateriais();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void botaoAdicionarMaterial(Material material) {
		// gravação do material no arquivo
		MaterialDAO matDAO = new MaterialDAO();
		matDAO.gravarMaterial(material);
	}
	
	public void botaoRemoverMaterial(int id) {
		MaterialDAO matDAO = new MaterialDAO();
		matDAO.removerMaterialPorId(id);
	}
	
	public void botaoEditarMaterial(Material materialParaEditar) {
		MaterialDAO matDAO = new MaterialDAO();
		matDAO.editarMaterial(materialParaEditar);
		
	}
	
	
	
	public int gerarId() {
		MaterialDAO matDAO = new MaterialDAO();
		int novoId = matDAO.gerarId();
		novoId++;
		
		return novoId;
	}

	

	

}
