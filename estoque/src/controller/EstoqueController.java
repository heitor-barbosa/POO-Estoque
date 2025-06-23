package controller;

import java.io.IOException;
import java.util.ArrayList;
import model.Material;
import model.MovimentacaoEstoque;
import persistencia.MaterialDAO;
import persistencia.MovimentacaoEstoqueDAO;

public class EstoqueController {

    private static EstoqueController instancia;

    private ArrayList<MovimentacaoEstoque> historico;
    private MovimentacaoEstoqueDAO movDAO = new MovimentacaoEstoqueDAO();

    private EstoqueController() {
        historico = movDAO.carregarHistorico();  // Carrega o histórico do arquivo ao iniciar
    }

    public static EstoqueController getInstance() {
        if (instancia == null) {
            instancia = new EstoqueController();
        }
        return instancia;
    }

    // ========== MATERIAIS ==========

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
        MaterialDAO matDAO = new MaterialDAO();
        matDAO.gravarMaterial(material);
        registrarMovimentacao("ENTRADA", material, material.getQuantidade());  // Registra no histórico
    }

    public void botaoRemoverMaterial(int id) {
        MaterialDAO matDAO = new MaterialDAO();
        Material removido = matDAO.getMaterialPorId(id); // Pega material antes de remover
        if (removido != null) {
            matDAO.removerMaterialPorId(id);
            registrarMovimentacao("SAÍDA", removido, removido.getQuantidade());  // Registra saída
        }
    }

    public void botaoEditarMaterial(Material materialParaEditar) {
        MaterialDAO matDAO = new MaterialDAO();
        matDAO.editarMaterial(materialParaEditar);
        registrarMovimentacao("EDIÇÃO", materialParaEditar, materialParaEditar.getQuantidade());  // Opcional
    }

    public int gerarId() {
        MaterialDAO matDAO = new MaterialDAO();
        int novoId = matDAO.gerarId();
        novoId++;
        return novoId;
    }

    // ========== HISTÓRICO ==========

    public void registrarMovimentacao(String tipo, Material material, int quantidade) {
        int id = gerarIdMovimentacao();
        MovimentacaoEstoque mov = new MovimentacaoEstoque(id, tipo, quantidade,
                java.time.LocalDateTime.now(), material);
        historico.add(mov);
        movDAO.salvarHistorico(historico);
    }

    public ArrayList<MovimentacaoEstoque> getHistorico() {
        return historico;
    }

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
