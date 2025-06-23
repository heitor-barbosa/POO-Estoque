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
	private static String nomeDoArquivo = "dados/produtos.csv";
	
	
	// Metodos
	public ArrayList<Material> lerMateriais() throws IOException {
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
		String linha = String.valueOf(material.getId()) + ";" + material.getNome() + ";" + material.getTipo() + ";" + material.getMarca() + ";" + material.getQuantidade();
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDoArquivo, true))) {
            writer.write(linha);
            writer.newLine(); // quebra de linha
        } catch (IOException e) {
            System.out.println("Erro ao gravar: " + e.getMessage());
        }
	}
	
	public void removerMaterialPorId(int id) {
        Path path = Paths.get(nomeDoArquivo);
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
	
	public void editarMaterial(Material materialParaEditar) {
		Path path = Paths.get(nomeDoArquivo);
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
	            int idLinha = Integer.parseInt(partes[0].trim());

	            if (idLinha == materialParaEditar.getId()) {
	                // Substitui a linha pelo material atualizado
	                String novaLinha = materialParaEditar.getId() + ";" +
	                                   materialParaEditar.getNome() + ";" +
	                                   materialParaEditar.getTipo() + ";" +
	                                   materialParaEditar.getMarca() + ";" +
	                                   materialParaEditar.getQuantidade();
	                linhasAtualizadas.add(novaLinha);
	            } else {
	                linhasAtualizadas.add(linha);
	            }
	        }
	    }

	    // Regrava o arquivo inteiro com as alterações
	    try {
			Files.write(path, linhasAtualizadas);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void buscarMaterial(String nomeBuscado) {
//		
//		
//	}
	public int buscarMaterial(String nomeBusca) {
	    try (BufferedReader br = new BufferedReader(new FileReader(nomeDoArquivo))) {
	        String linha;
	        while ((linha = br.readLine()) != null) {
	            String[] partes = linha.split(";");
	            if (partes.length > 1) {
	                String nome = partes[1];
	                if (nome.toLowerCase().contains(nomeBusca.toLowerCase())) {
	                    return Integer.parseInt(partes[0]); // Retorna o ID
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (NumberFormatException e) {
	        System.out.println("Erro ao converter o ID para número.");
	    }

	    return -1; // Se não encontrar
	}

	
	
	public int gerarId() {
	    try (BufferedReader br = new BufferedReader(new FileReader(nomeDoArquivo))) {
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

	public Material getMaterialPorId(int id) {
	    try {
	        ArrayList<Material> lista = lerMateriais();
	        for (Material m : lista) {
	            if (m.getId() == id) {
	                return m;
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null; // se não encontrar
	}

}
