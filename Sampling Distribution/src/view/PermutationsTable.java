package view;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Sampler;

@SuppressWarnings("serial")
public class PermutationsTable extends JPanel {
    JScrollPane scrollPane;
    JTable table;
    DefaultTableModel model;
    
    public PermutationsTable() {
        String[] columnNames = new String[] {"Permutation", "Mean"};
        
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);
        scrollPane = new JScrollPane(table);
        
        add(scrollPane);
    }
    
    
    public void updateData(Sampler sampler) {
        for (int i = model.getRowCount() - 1; i >= 0 ; i--) {
            model.removeRow(i);
        }
        
        List<String> permutations = sampler.getPermutations();
        List<Double> means= sampler.getPermutationMeans();
        
        Object[] data = new Object[2];
        for (int i = 0; i < permutations.size(); i++) {
            data[0] = permutations.get(i);
            data[1] = means.get(i);
            model.addRow(data);
        }
    }
    
}
