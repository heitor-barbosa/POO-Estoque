package persistencia;

import java.io.*;
import java.util.ArrayList;
import model.MovimentacaoEstoque;

public class MovimentacaoEstoqueDAO {

    private static final String ARQUIVO = "dados/historico_movimentacoes.dat";
    
    public MovimentacaoEstoqueDAO() {
        File pasta = new File("dados");
        if (!pasta.exists()) {
            pasta.mkdir(); // cria a pasta "dados" se ainda não existir
        }
    }

    public void salvarHistorico(ArrayList<MovimentacaoEstoque> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
