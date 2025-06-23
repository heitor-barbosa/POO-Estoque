package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
	
	public void removerMaterialPorId(int id) {
        Path path = Paths.get("dados/produtos.csv");
        List<String> linhas = null;
		try {
			linhas = Files.readAllLines(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<String> linhasAtualizadas = new ArrayList<>();

        for (String linha : linhas) {
            if (!linha.trim().isEmpty()) {
                String[] partes = linha.split(";");
                try {
                    int idLinha = Integer.parseInt(partes[0].trim());
                    if (idLinha != id) {
                        linhasAtualizadas.add(linha);  // mantém linhas com id diferente
                    }
                } catch (NumberFormatException e) {
                    // Linha inválida (id não numérico), mantemos para não perder dados
                    linhasAtualizadas.add(linha);
                }
            }
        }

        // Regrava o arquivo com as linhas que sobraram (sem o material removido)
        try {
			Files.write(path, linhasAtualizadas);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	public int gerarId() {
	    try (BufferedReader br = new BufferedReader(new FileReader("dados/produtos.csv"))) {
	        String linha;
	        String ultimaLinhaValida = null;

	        while ((linha = br.readLine()) != null) {
	            if (!linha.trim().isEmpty()) { 
	                ultimaLinhaValida = linha;
	            }
	        }

	        if (ultimaLinhaValida != null && !ultimaLinhaValida.isEmpty()) {
	            String[] partes = ultimaLinhaValida.split(";");
	            if (partes.length > 0) {
	                return Integer.parseInt(partes[0].trim());
	            }
	        } else {
	            System.out.println("O arquivo está vazio ou só contém linhas vazias.");
	        }
	    } catch (IOException | NumberFormatException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}

	
}
