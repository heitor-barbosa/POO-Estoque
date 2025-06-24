package persistencia;

import java.io.*;
import java.util.ArrayList;
import model.MovimentacaoEstoque;

public class MovimentacaoEstoqueDAO {

    /**
     * Caminho do arquivo onde o histórico de movimentações será armazenado.
     */
    private static final String ARQUIVO = "dados/historico_movimentacoes.dat";

    /**
     * Construtor que garante a existência da pasta "dados" onde o arquivo será salvo.
     */
    public MovimentacaoEstoqueDAO() {
        File pasta = new File("dados");
        if (!pasta.exists()) {
            pasta.mkdir(); // cria a pasta "dados" se ainda não existir
        }
    }

    /**
     * Salva o histórico completo de movimentações no arquivo.
     *
     * @param lista lista de movimentações a ser salva
     */
    public void salvarHistorico(ArrayList<MovimentacaoEstoque> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega o histórico de movimentações do arquivo.
     *
     * @return lista de movimentações carregadas ou uma lista vazia se o arquivo não existir ou ocorrer erro
     */
    @SuppressWarnings("unchecked")
    public ArrayList<MovimentacaoEstoque> carregarHistorico() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (ArrayList<MovimentacaoEstoque>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
