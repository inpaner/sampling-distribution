package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Sampler;
import net.miginfocom.swing.MigLayout;

public class MainPanel extends JPanel {
    private InputPanel inputPanel;
    private JTabbedPane tabbedPane;
    private MeansTable meansTable;
    private OutputGraph outputGraph;
    private PermutationsTable permutationsTable;
    private OutputValues outputValues;
    
    public MainPanel() {
        inputPanel = new InputPanel();
        tabbedPane = new JTabbedPane();
        meansTable = new MeansTable();
        permutationsTable = new PermutationsTable();
        outputGraph = new OutputGraph();
        outputValues = new OutputValues();
        tabbedPane.addTab("Permutations", permutationsTable);
        tabbedPane.addTab("Means", meansTable);
        tabbedPane.addTab("Graph", outputGraph);
        
        setLayout(new MigLayout());
        add(inputPanel);
        add(outputValues, "wrap");
        
        add(tabbedPane, "span, split");
    }

    public void addListener(InputPanel.Listener listener) {
        inputPanel.addListener(listener);        
    }
    
    public void update(Sampler sampler) {
        outputValues.updateData(sampler);
        outputGraph.updateData(sampler);
        meansTable.updateData(sampler);
        permutationsTable.updateData(sampler);
    }
    
}
