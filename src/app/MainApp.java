package app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        Label inputLabel = new Label("Enter Temperature:");
        TextField inputField = new TextField();

        Label fromLabel = new Label("From:");
        ComboBox<String> fromBox = new ComboBox<>();
        fromBox.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");

        Label toLabel = new Label("To:");
        ComboBox<String> toBox = new ComboBox<>();
        toBox.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");

        Button convertBtn = new Button("Convert");
        Label resultLabel = new Label("Result:");

        convertBtn.setOnAction(e -> {
            try {
                double value = Double.parseDouble(inputField.getText());
                String from = fromBox.getValue();
                String to = toBox.getValue();

                if (from == null || to == null) {
                    resultLabel.setText("Please select units");
                    return;
                }

                double result = convertTemperature(value, from, to);
                resultLabel.setText("Result: " + result);

            } catch (Exception ex) {
                resultLabel.setText("Invalid input");
            }
        });

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(15));
        layout.setVgap(10);
        layout.setHgap(10);

        layout.add(inputLabel, 0, 0);
        layout.add(inputField, 1, 0);
        layout.add(fromLabel, 0, 1);
        layout.add(fromBox, 1, 1);
        layout.add(toLabel, 0, 2);
        layout.add(toBox, 1, 2);
        layout.add(convertBtn, 1, 3);
        layout.add(resultLabel, 1, 4);

        Scene scene = new Scene(layout, 350, 250);
        stage.setTitle("Temperature Converter");
        stage.setScene(scene);
        stage.show();
    }

    private double convertTemperature(double value, String from, String to) {

        if (from.equals(to)) return value;

        double celsius;

        switch (from) {
            case "Celsius":
                celsius = value;
                break;
            case "Fahrenheit":
                celsius = (value - 32) * 5 / 9;
                break;
            case "Kelvin":
                celsius = value - 273.15;
                break;
            default:
                return value;
        }

        switch (to) {
            case "Celsius":
                return celsius;
            case "Fahrenheit":
                return (celsius * 9 / 5) + 32;
            case "Kelvin":
                return celsius + 273.15;
            default:
                return value;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

