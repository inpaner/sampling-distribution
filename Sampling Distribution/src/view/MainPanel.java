package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Binomial;
import model.Sampler;
import net.miginfocom.swing.MigLayout;

public class MainPanel extends JPanel {
    private InputPanel inputPanel;
    private JTabbedPane tabbedPane;
    private MeansTable meansTable;
    private OutputGraph outputGraph;
    
    public MainPanel() {
        setLayout(new MigLayout());
        initComponents();
        addComponents();
    }
    
    private void initComponents() {
        inputPanel = new InputPanel();
        tabbedPane = new JTabbedPane();
        meansTable = new MeansTable();
        outputGraph = new OutputGraph();
        tabbedPane.addTab("Table", meansTable);
        tabbedPane.addTab("Graph", outputGraph);
    }
    
    private void addComponents() {
        add(inputPanel, "wrap");
        add(tabbedPane, "span, split");
    }
    
    public void addListener(InputPanel.Listener listener) {
        inputPanel.addListener(listener);        
    }
    
    public void update(Sampler sampler) {
        outputGraph.updateData(sampler);
        meansTable.updateData(sampler);
    }
    
}
