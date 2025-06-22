package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Material;

public class MaterialDAO {
	
	
	
	// Metodos
	public ArrayList<Material> lerMateriais() throws IOException {
	    String nomeDoArquivo = "dados/produtos.csv";
	    File arquivo = new File(nomeDoArquivo);

	    if (!arquivo.exists()) {
	        System.err.println("Arquivo não encontrado: " + arquivo.getAbsolutePath());
	        return null;
	    }

	    ArrayList<Material> lista = new ArrayList<>();

	    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
	        String linha;
	        while ((linha = br.readLine()) != null) {
	            Material m = Material.fromCSV(linha);
	            lista.add(m);
	        }
	    }
	    return lista;
	}
	
	public void gravarMaterial(Material material) {
		String nomeDoArquivo = "dados/produtos.csv";
		String linha = String.valueOf(material.getId()) + ";" + material.getNome() + ";" + material.getTipo() + ";" + material.getMarca() + ";" + material.getQuantidade();
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDoArquivo, true))) {
            writer.write(linha);
            writer.newLine(); // quebra de linha
        } catch (IOException e) {
            System.out.println("Erro ao gravar: " + e.getMessage());
        }
	}
}
