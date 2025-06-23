package persistencia;

import java.io.*;
import java.util.ArrayList;
import model.MovimentacaoHistorico;

public class MovimentacaoDAO {

    private static final String ARQUIVO = "dados/historico_movimentacoes.dat";

    // Salva a lista de movimentações no arquivo
    public void salvarHistorico(ArrayList<MovimentacaoHistorico> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carrega a lista de movimentações do arquivo
    @SuppressWarnings("unchecked")
    public ArrayList<MovimentacaoHistorico> carregarHistorico() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (ArrayList<MovimentacaoHistorico>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
