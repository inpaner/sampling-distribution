package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Binomial;

public class OutputTable extends JPanel {
    JScrollPane scrollPane;
    JTable table;
    DefaultTableModel model;
    
    public OutputTable() {
        initComponents();
        addComponents();
    }
    
    private void initComponents() {
        String[] columnNames = new String[] {"x", "b"};
        
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
    
    public void updateData(Binomial binomial) {
        for (int i = model.getRowCount() - 1; i >= 0 ; i--) {
            model.removeRow(i);
        }
        Object[] data = new Object[2];
        
        
        for (int i = 0; i < binomial.probabilities().length; i++) {
            data[0] = binomial.getStart() + i;
            double rounded = (double) Math.round(binomial.probabilities()[i] * 10000) / 10000;
            data[1] = rounded;
            model.addRow(data);
        }
    }
}
