package gui;

import core.DataProcessor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowApp extends Application {

    @Override
    public void start(Stage stage) {
        DataProcessor dp = new DataProcessor();

        Label l1 = new Label("входные данные:");
        TextArea ta = new TextArea();
        ta.setPrefHeight(200);

        Button btn = new Button("обработать");

        Label err = new Label();

        Label l2 = new Label("числа удалены:");
        ListView<String> lv = new ListView<>();
        lv.setPrefHeight(200);

        btn.setOnAction(e -> {
            err.setText("");
            String txt = ta.getText();
            if (txt.trim().isEmpty()) {
                err.setText("введите числа");
                return;
            }

            String[] lines = txt.split("\\r?\\n");
            String[] res = dp.pipeline(lines);

            if (res.length == 0 && lines.length > 0) {
                err.setText("введите корректные числа");
                return;
            }

            lv.getItems().clear();
            lv.getItems().addAll(res);
        });

        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));
        root.getChildren().addAll(l1, ta, btn, err, l2, lv);

        Scene sc = new Scene(root, 400, 500);
        stage.setTitle("удаление дубликатов, вариант 3");
        stage.setScene(sc);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}