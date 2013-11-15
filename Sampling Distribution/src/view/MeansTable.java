package view;

import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Sampler;

public class MeansTable extends JPanel {
    JScrollPane scrollPane;
    JTable table;
    DefaultTableModel model;
    
    public MeansTable() {
        initComponents();
        addComponents();
    }
    
    private void initComponents() {
        String[] columnNames = new String[] {"mean", "count"};
        
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

    }
    
    private void addComponents() {
        add(scrollPane);
    }
    
    public void updateData(Sampler sampler) {
        for (int i = model.getRowCount() - 1; i >= 0 ; i--) {
            model.removeRow(i);
        }
        
        Object[] data = new Object[2];
        
        for (Map.Entry<Double, Integer> entry : sampler.getMeansCount().entrySet()) {
            data[0] = entry.getKey();
            data[1] = entry.getValue();
            model.addRow(data);
        }
    }
}
