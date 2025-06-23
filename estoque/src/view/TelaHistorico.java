package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import controller.EstoqueController;
import model.MovimentacaoHistorico;
import model.ModeloTabelaHistorico;
import model.MovimentacaoEstoque;

public class TelaHistorico extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

    public TelaHistorico() {
        setTitle("Histórico de Movimentações");
        setModal(true);
        setBounds(100, 100, 800, 500);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        EstoqueController controller = EstoqueController.getInstance();
        ArrayList<MovimentacaoEstoque> historico = controller.getHistorico();

        table = new JTable(new ModeloTabelaHistorico(historico));
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        panel.add(btnFechar);
        getContentPane().add(panel, BorderLayout.SOUTH);
    }
}
