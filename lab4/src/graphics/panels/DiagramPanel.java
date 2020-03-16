package graphics.panels;

import com.orsoncharts.util.json.JSONArray;
import com.orsoncharts.util.json.JSONObject;
import com.orsoncharts.util.json.parser.JSONParser;
import com.orsoncharts.util.json.parser.ParseException;
import myUtil.FilesWork;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("FieldCanBeLocal")
public class DiagramPanel extends JPanel {
    private JFrame owner;

    private JFreeChart freeChart;
    private DefaultPieDataset pieDataset;

    private JButton openButton;

    public DiagramPanel(JFrame owner) {
        super(new BorderLayout());

        this.owner = owner;

        pieDataset = new DefaultPieDataset();
        freeChart = ChartFactory.createPieChart("Расходы", pieDataset);
        openButton = new JButton("Открыть");

        openButton.addActionListener(e -> {
            try {
                initPieDataset();
            } catch (IOException | ParseException ignored) {}
        });
        JPanel openButtonPanel = new JPanel(new FlowLayout());
        openButtonPanel.add(openButton);

        add(new ChartPanel(freeChart), BorderLayout.CENTER);
        add(openButtonPanel, BorderLayout.SOUTH);

    }

    private void initPieDataset() throws IOException, ParseException {
        File file = FilesWork.showOpenFileDialog(owner, (dir, name) -> name.matches(".+\\.json"));
        if(file == null){
            return;
        }

        pieDataset.clear();

        Object object = new JSONParser().parse(new FileReader(file));
        JSONObject jsonObject = (JSONObject) object;
        JSONArray categories = (JSONArray) jsonObject.get("categories");
        for(Object o : categories){
            jsonObject = (JSONObject) o;
            pieDataset.setValue((String)jsonObject.get("name"), (Number) jsonObject.get("value"));
        }
    }
}
