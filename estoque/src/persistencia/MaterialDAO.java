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

    /**
     * Caminho do arquivo onde os materiais são armazenados.
     */
    private static String nomeDoArquivo = "dados/produtos.csv";

    /**
     * Lê todos os materiais do arquivo CSV.
     * 
     * @return lista de materiais lidos do arquivo
     * @throws IOException caso ocorra erro na leitura do arquivo
     */
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

    /**
     * Grava um material no arquivo CSV.
     * 
     * @param material material a ser gravado
     */
    public void gravarMaterial(Material material) {
        String linha = String.valueOf(material.getId()) + ";" + material.getNome() + ";" + material.getTipo() + ";" + material.getMarca() + ";" + material.getQuantidade();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDoArquivo, true))) {
            writer.write(linha);
            writer.newLine(); // quebra de linha
        } catch (IOException e) {
            System.out.println("Erro ao gravar: " + e.getMessage());
        }
    }

    /**
     * Remove um material do arquivo com base no seu ID.
     * 
     * @param id ID do material a ser removido
     */
    public void removerMaterialPorId(int id) {
        Path path = Paths.get(nomeDoArquivo);
        List<String> linhas = null;
        try {
            linhas = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> linhasAtualizadas = new ArrayList<>();

        for (String linha : linhas) {
            if (!linha.trim().isEmpty()) {
                String[] partes = linha.split(";");
                try {
                    int idLinha = Integer.parseInt(partes[0].trim());
                    if (idLinha != id) {
                        linhasAtualizadas.add(linha); // mantém linhas com id diferente
                    }
                } catch (NumberFormatException e) {
                    linhasAtualizadas.add(linha); // mantém linhas inválidas
                }
            }
        }

        try {
            Files.write(path, linhasAtualizadas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edita um material já existente no arquivo.
     * 
     * @param materialParaEditar material atualizado que substituirá o antigo
     */
    public void editarMaterial(Material materialParaEditar) {
        Path path = Paths.get(nomeDoArquivo);
        List<String> linhas = null;
        try {
            linhas = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> linhasAtualizadas = new ArrayList<>();

        for (String linha : linhas) {
            if (!linha.trim().isEmpty()) {
                String[] partes = linha.split(";");
                int idLinha = Integer.parseInt(partes[0].trim());

                if (idLinha == materialParaEditar.getId()) {
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

        try {
            Files.write(path, linhasAtualizadas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca um material pelo nome e retorna seu ID.
     * 
     * @param nomeBusca nome a ser buscado (pode ser parcial)
     * @return ID do material encontrado ou -1 se não encontrar
     */
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

    /**
     * Gera o próximo ID disponível para um novo material com base no arquivo.
     * 
     * @return último ID utilizado ou 0 se o arquivo estiver vazio
     */
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

    /**
     * Busca um material pelo ID.
     * 
     * @param id ID do material a ser buscado
     * @return objeto Material correspondente ou null se não encontrar
     */
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
        return null;
    }
}
