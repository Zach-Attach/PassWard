package stoprepeatingyourself;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Created by: Brittany Safford & Zachary Laborde
 */

public class Controller {

    @FXML
    TextArea textArea;
    @FXML
    ListView<BorderPane> listView;
    @FXML
    CheckBox ignoreCommonWords;
    @FXML
    BarChart barChart;

    @FXML
    void calculate() {

        barChart.getData().clear();
        listView.getItems().clear();

        String in = textArea.getText();

        Pattern pattern = Pattern.compile("(?<!a-zA-Z0-9_-)([a-zA-Z0-9_-]+)(?<!a-zA-Z0-9_-)");

        Matcher matcher = pattern.matcher(in);

        HashMap<String, Integer> dict = new HashMap<>();

        while (matcher.find()) {

            String word = matcher.group(1).toLowerCase();

            dict.put(word, dict.getOrDefault(word, 0) + 1);
        }

        Integer[] numArr = dict.values().toArray(new Integer[dict.size()]);
        String[] wordArr = dict.keySet().toArray(new String[dict.size()]);

        Heap heap = new Heap(numArr, wordArr) {{

            if (ignoreCommonWords.isSelected())
                specialSort();
            else
                sort();
        }};

        XYChart.Series<String, Number> xy = new XYChart.Series<>();

        IntStream.range(0, heap.size()).filter(x -> (heap.getWords()[x] != null)).forEachOrdered(x -> {

            BorderPane entry = new BorderPane() {{
                setLeft(new Label(heap.getWords()[x]));
                setRight(new Label(heap.getNumbers()[x].toString()));
            }};

            entry.setPrefWidth(750);
            listView.getItems().add(entry);


            xy.getData().add(new XYChart.Data<>(heap.getWords()[x], heap.getNumbers()[x]));

        });

        barChart.getData().add(xy);
        barChart.getXAxis().setTickLabelGap(0);
        barChart.getXAxis().setTickLabelsVisible(false);
        barChart.getYAxis().autosize();
    }

    @FXML
    private void back() throws IOException {

        main.Main.mainScene();
    }
}
