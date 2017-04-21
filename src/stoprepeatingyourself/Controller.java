package stoprepeatingyourself;

import javafx.fxml.FXML;
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
    void calculate() {

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
            sort();
        }};

        IntStream.range(0, heap.size()).forEachOrdered(x -> {

            BorderPane entry = new BorderPane() {{
                setLeft(new Label(heap.getWords()[x]));
                setRight(new Label(heap.getNumbers()[x].toString()));
            }};

            entry.setPrefWidth(750);

            listView.getItems().add(entry);
        });
    }

    @FXML
    private void back() throws IOException {


        main.Main.mainScene();
    }
}
