import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class CalculatorGUI extends Application {

    private final EvaluationOfPostfix evalPostfix = new EvaluationOfPostfix();
    private BigDecimal result = BigDecimal.ZERO;
    private TextField display;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Calculator");

        // Display Field
        display = new TextField();
        display.setEditable(true);
        display.setStyle("-fx-font-size: 18px;");
        display.setPrefHeight(50);

        // Buttons Grid
        GridPane grid = createButtons();

        // Layout
        VBox layout = new VBox(10, display, grid);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        // Scene
        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createButtons() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        // Buttons
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        int row = 0, col = 0;
        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setStyle("-fx-font-size: 16px;");
            button.setPrefSize(50, 50);

            // Set button action
            button.setOnAction(e -> handleButtonClick(label));
            grid.add(button, col, row);

            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }
        return grid;
    }

    private void handleButtonClick(String label) {
        switch (label) {
            case "C": // Clear
                display.clear();
                result = BigDecimal.ZERO;
                break;
            case "=": // Evaluate
                try {
                    String expression = display.getText();
                    String postfix = evalPostfix.infixToPostfix(expression);
                    result = evalPostfix.evaluate(postfix);
                    display.setText(result.stripTrailingZeros().toPlainString());
                } catch (Exception e) {
                    display.setText("Error");
                }
                break;
            default: // Numbers and Operators
                display.appendText(label);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
