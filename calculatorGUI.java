import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class calculatorGUI extends Application {

    private calculator calculator = new calculator();
    private TextField display;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");

        display = new TextField();
        display.setEditable(false);
        display.setPrefHeight(50);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(5);

        Button[] buttons = new Button[16];
        String[] labels = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "C", "0", "=", "+"};
        for (int i = 0; i < 16; i++) {
            Button button = new Button(labels[i]);
            button.setOnAction(event -> handleButtonAction(button.getText()));
            button.setStyle("-fx-background-color: #D3D3D3; -fx-font-size: 16px;");
            buttons[i] = button;
        }

        grid.add(display, 0, 0, 4, 1);

        int buttonIndex = 0;
        for (int row = 1; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                buttons[buttonIndex].setStyle("-fx-background-color: #A9A9A9; -fx-font-size: 16px;");
                grid.add(buttons[buttonIndex], col, row);
                buttonIndex++;
            }
        }

        Scene scene = new Scene(grid, 250, 250);
        scene.setFill(Color.LIGHTGRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonAction(String text) {
        if (text.equals("C")) {
            display.clear();
        } else if (text.equals("=")) {
            String expression = display.getText();
            double result = calculator.evaluateExpression(expression);
            if (!Double.isNaN(result)) {
                display.setText(Double.toString(result));
            } else {
                display.setText("Error");
            }
        } else {
            display.appendText(text);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
