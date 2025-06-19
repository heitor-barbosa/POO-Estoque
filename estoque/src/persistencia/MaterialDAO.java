package persistencia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.Material;

public class MaterialDAO {
	
	
	
	// Metodos
	public ArrayList<Material> lerMateriais() throws IOException {
		String nomeDoArquivo = "produto.csv";
		InputStream inputStream = getClass().getResourceAsStream(nomeDoArquivo);
		if (inputStream == null) {
            System.err.println("Arquivo não encontrado no classpath: " + nomeDoArquivo);
            return null; // Encerra o método se o arquivo não for encontrado
        }
		
		ArrayList<Material> lista = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
			String linha;
			while ((linha = br.readLine()) != null) {
				Material m = Material.fromCSV(linha);
				lista.add(m);
			}
		}
		return lista;
	}
}
