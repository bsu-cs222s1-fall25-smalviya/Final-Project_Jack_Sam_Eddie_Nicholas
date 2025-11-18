package edu.bsu.cs;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UIMain extends Application {

    // UI Components
    private Button settingsButton;
    private Button helpButton;
    private TextField zipcodeField;
    private ComboBox<String> reportTypeDropdown;
    private Button startButton;

    @Override
    public void start(Stage primaryStage) {
        VBox mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);

        // Create top row
        HBox topRow = createTopRow();

        // Create second row
        HBox secondRow = createSecondRow();

        // Add rows
        mainLayout.getChildren().addAll(topRow, secondRow);

        Scene scene = new Scene(mainLayout, 600, 200);

        // Load CSS stylesheet
        String css = getClass().getResource("/edu/bsu/cs/style.css").toExternalForm();
        scene.getStylesheets().add(css);


        // Configure the stage
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    private HBox createTopRow() {
        HBox topRow = new HBox(10);
        topRow.setAlignment(Pos.CENTER_LEFT);

        // Zipcode label and text field
        Label zipcodeLabel = new Label("Zipcode:");
        zipcodeField = new TextField();
        zipcodeField.setPromptText("Enter zipcode");
        zipcodeField.setPrefWidth(150);

        // Settings button
        settingsButton = new Button("Settings");
        settingsButton.setId("settingsButton");
        settingsButton.setPrefWidth(100);

        // Help button
        helpButton = new Button("Help");
        helpButton.setId("helpButton");
        helpButton.setPrefWidth(100);

        // Add components to top row
        topRow.getChildren().addAll(zipcodeLabel, zipcodeField, settingsButton, helpButton);

        return topRow;
    }

    private HBox createSecondRow() {
        HBox secondRow = new HBox(10);
        secondRow.setAlignment(Pos.CENTER_LEFT);

        // Report type label and dropdown
        Label reportTypeLabel = new Label("Report Type:");
        reportTypeDropdown = new ComboBox<>();
        reportTypeDropdown.getItems().addAll(
                "Today's Report",
                "Weekly Report",
                "Outfit Recommender"
        );
        reportTypeDropdown.setPromptText("Select report type");
        reportTypeDropdown.setPrefWidth(200);
        reportTypeDropdown.setValue("Today's Report"); // Set default selection

        // Start button
        startButton = new Button("Start");
        startButton.setId("startButton");
        startButton.setPrefWidth(100);

        // Add components to second row (dropdown first, then start button)
        secondRow.getChildren().addAll(reportTypeLabel, reportTypeDropdown, startButton);

        return secondRow;
    }


    //Getter methods for UI components (for future implementation)

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Button getHelpButton() {
        return helpButton;
    }

    public TextField getZipcodeField() {
        return zipcodeField;
    }

    public ComboBox<String> getReportTypeDropdown() {
        return reportTypeDropdown;
    }

    public Button getStartButton() {
        return startButton;
    }

    public static void main(String[] args) {
        launch(args);
    }
}