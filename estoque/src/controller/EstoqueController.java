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

}
