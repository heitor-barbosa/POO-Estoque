package controller;

import java.io.IOException;
import java.util.ArrayList;
import model.Material;
import model.MovimentacaoEstoque;
import persistencia.MaterialDAO;
import persistencia.MovimentacaoEstoqueDAO;

public class EstoqueController {

    /**
     * Instância única do controlador (Singleton).
     */
    private static EstoqueController instancia;

    /**
     * Lista que armazena o histórico de movimentações no estoque.
     */
    private ArrayList<MovimentacaoEstoque> historico;

    /**
     * Objeto para acesso às operações de persistência das movimentações de estoque.
     */
    private MovimentacaoEstoqueDAO movDAO = new MovimentacaoEstoqueDAO();

    /**
     * Objeto para acesso às operações de persistência dos materiais.
     */
    private MaterialDAO matDAO = new MaterialDAO();

    /**
     * Construtor privado que inicializa o histórico carregando os dados do arquivo.
     */
    private EstoqueController() {
        historico = movDAO.carregarHistorico();  // Carrega o histórico do arquivo ao iniciar
    }

    /**
     * Retorna a instância única do controlador, criando-a se ainda não existir.
     *
     * @return a instância singleton do EstoqueController
     */
    public static EstoqueController getInstance() {
        if (instancia == null) {
            instancia = new EstoqueController();
        }
        return instancia;
    }

    // ========== MATERIAIS ==========

    /**
     * Retorna a lista de todos os materiais cadastrados.
     * 
     * @return lista de materiais lidos do arquivo de persistência
     */
    public ArrayList<Material> getMateriais() {
        ArrayList<Material> lista = new ArrayList<>();
        try {
            lista = matDAO.lerMateriais();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Adiciona um material no estoque e registra essa entrada no histórico.
     * 
     * @param material material a ser adicionado
     */
    public void botaoAdicionarMaterial(Material material) {
        matDAO.gravarMaterial(material);
        registrarMovimentacao("ENTRADA", material, material.getQuantidade());  // Registra no histórico
    }

    /**
     * Remove um material do estoque pelo seu ID e registra essa saída no histórico.
     * 
     * @param id identificador do material a ser removido
     */
    public void botaoRemoverMaterial(int id) {
        Material removido = matDAO.getMaterialPorId(id); // Pega material antes de remover
        if (removido != null) {
            matDAO.removerMaterialPorId(id);
            registrarMovimentacao("SAÍDA", removido, removido.getQuantidade());  // Registra saída
        }
    }

    /**
     * Edita um material existente no estoque e registra essa edição no histórico.
     * 
     * @param materialParaEditar material com os dados atualizados
     */
    public void botaoEditarMaterial(Material materialParaEditar) {
        matDAO.editarMaterial(materialParaEditar);
        registrarMovimentacao("EDIÇÃO", materialParaEditar, materialParaEditar.getQuantidade());  // Opcional
    }
    
    /**
     * Busca um material pelo nome e retorna o índice encontrado menos um,
     * ou -1 se não encontrar.
     * 
     * @param nomeBuscado nome do material a ser buscado
     * @return índice do material encontrado menos um, ou -1 se não encontrado
     */
    public int botaoBuscarMaterial(String nomeBuscado) {
        if (matDAO.buscarMaterial(nomeBuscado) != -1) {
            int idMaterialEncontrado = matDAO.buscarMaterial(nomeBuscado);
            return --idMaterialEncontrado;
        }
        return -1;
    }
    
    /**
     * Retorna o material correspondente ao ID informado.
     * 
     * @param id identificador do material
     * @return material encontrado ou null se não existir
     */
    public Material getMaterialPorId(int id) {
        return matDAO.getMaterialPorId(id);
    }
    
    /**
     * Gera um novo ID para um material, incrementando o maior ID existente.
     * 
     * @return novo ID para material
     */
    public int gerarId() {
        int novoId = matDAO.gerarId();
        novoId++;
        return novoId;
    }

    // ========== HISTÓRICO ==========

    /**
     * Registra uma movimentação no histórico com os dados fornecidos e salva no arquivo.
     * 
     * @param tipo tipo da movimentação (ex: "ENTRADA", "SAÍDA", "EDIÇÃO")
     * @param material material envolvido na movimentação
     * @param quantidade quantidade movimentada
     */
    public void registrarMovimentacao(String tipo, Material material, int quantidade) {
        int id = gerarIdMovimentacao();
        MovimentacaoEstoque mov = new MovimentacaoEstoque(id, tipo, quantidade,
                java.time.LocalDateTime.now(), material);
        historico.add(mov);
        movDAO.salvarHistorico(historico);
    }

    /**
     * Retorna a lista com o histórico de todas as movimentações realizadas.
     * 
     * @return lista de movimentações do estoque
     */
    public ArrayList<MovimentacaoEstoque> getHistorico() {
        return historico;
    }

    /**
     * Gera um novo ID para movimentação, com base no maior ID já existente no histórico.
     * 
     * @return novo ID para movimentação
     */
    private int gerarIdMovimentacao() {
        int maiorId = 0;
        for (MovimentacaoEstoque m : historico) {
            if (m.getId() > maiorId) {
                maiorId = m.getId();
            }
        }
        return maiorId + 1;
    }

}
