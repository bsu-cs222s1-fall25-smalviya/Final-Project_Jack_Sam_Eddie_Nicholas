package edu.bsu.cs;

//imports for javafx
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//imports for our classes
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class UIMain extends Application {

    // UI Components
    private Button settingsButton;
    private Button helpButton;
    private TextField zipcodeField;
    private TextArea reportField = new TextArea();
    private ComboBox<String> reportTypeDropdown;
    private ComboBox<String> unitTypeDropdown;
    private Button startButton;
    
    //Our classes
    FileController fileController = new FileController();
    WeatherServiceAPI api = new WeatherServiceAPI();
    APIDataParser dataParser = new APIDataParser();
    DataFormatter dataFormatter = new DataFormatter();
    Converter converter = new Converter();
    OutfitRecommender outfitRecommender = new OutfitRecommender(converter);

    public UIMain() throws IOException {
    }

    @Override
    public void start(Stage primaryStage) {
        VBox mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);

        // Create top row
        HBox topRow = createTopRow();

        // Create second row
        HBox secondRow = createSecondRow();

        //Create third row
        HBox thirdRow = createThirdRow();

        // Add rows
        mainLayout.getChildren().addAll(topRow, secondRow, thirdRow);

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

        // Unit type label and dropdown
        Label unitTypeLabel = new Label("Unit: ");
        unitTypeDropdown = new ComboBox<>();
        unitTypeDropdown.getItems().addAll(
                "Imperial",
                "Metric"
        );
        unitTypeDropdown.setPrefWidth(100);
        unitTypeDropdown.setValue("Imperial");

        // Start button
        startButton = new Button("Start");
        startButton.setId("startButton");
        startButton.setPrefWidth(100);

        // Add components to second row (dropdown first, then start button)
        secondRow.getChildren().addAll(reportTypeLabel, reportTypeDropdown, unitTypeLabel, unitTypeDropdown, startButton);

        return secondRow;
    }

    private HBox createThirdRow(){
        HBox secondRow = new HBox(10);
        secondRow.setAlignment(Pos.CENTER_LEFT);

        // Output area for report
        Label reportLabel = new Label("Output: ");
        reportField.setEditable(false);
        reportField.setPromptText("Report: ");
        reportField.setPrefWidth(350);

        // Add components to second row (dropdown first, then start button)
        secondRow.getChildren().addAll(reportLabel, reportField);

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

    public TextArea getReportField() throws IOException {
        String reportString = "";
        if (reportTypeDropdown.getValue().equals("Today's Report")){
            reportString = getHourlyReport();
        } else if (reportTypeDropdown.getValue().equals("Weekly Report")){
            reportString = getWeeklyReport();
        } else if (reportTypeDropdown.getValue().equals("Outfit Recommender")){
            reportString = getOutfitRecommendation();
        }
        
        reportField.setText(reportString);

        return reportField;
    }
    
    public String getHourlyReport() throws IOException {
        String link;
        InputStream weatherData;
        String units;
        String[] preferences = this.fileController.loadPreferences();
        if (preferences[0].equals("true")) {
            link = api.createURLString(preferences[1]);
            units = preferences[2];
        } else {
            String location = zipcodeField.getText();
            units = unitTypeDropdown.getValue();
            link = api.createURLString(location);
        }

        weatherData = api.getInputStreamFromURL(link);
        String hourlyForecastURLString = this.dataParser.parseWeatherAPILink(weatherData, "forecastHourly");
        InputStream hourlyForecastData = this.api.getInputStreamFromURL(hourlyForecastURLString);
        this.dataParser.setWeatherData(hourlyForecastData);
        this.dataParser.hourlyForecastData();
        HashMap<Integer, ArrayList<String>> hourlyForecast = this.dataParser.getHourlyForecast();

        StringBuilder reportString = new StringBuilder();
        int i;
        for (i=1;i<=7;i++){
            ArrayList<String> forecast = hourlyForecast.get(i);
            reportString.append(this.dataFormatter.formatWeatherData(forecast, units, "Hourly"));
        }
        return reportString.toString();
    }

    public String getWeeklyReport() throws IOException {
        String link;
        InputStream weatherData;
        String units;
        String[] preferences = this.fileController.loadPreferences();
        if (preferences[0].equals("true")) {
            link = api.createURLString(preferences[1]);
            units = preferences[2];
        } else {
            String location = zipcodeField.getText();
            units = unitTypeDropdown.getValue();
            link = api.createURLString(location);
        }

        weatherData = api.getInputStreamFromURL(link);
        String hourlyForecastURLString = this.dataParser.parseWeatherAPILink(weatherData, "forecastHourly");
        InputStream hourlyForecastData = this.api.getInputStreamFromURL(hourlyForecastURLString);
        this.dataParser.setWeatherData(hourlyForecastData);
        this.dataParser.hourlyForecastData();
        HashMap<Integer, ArrayList<String>> weeklyForecast = this.dataParser.getDailyForecast();

        StringBuilder reportString = new StringBuilder();
        int i;
        for (i=1;i<=7;i++){
            ArrayList<String> forecast = weeklyForecast.get(i);
            reportString.append(this.dataFormatter.formatWeatherData(forecast, units, "Hourly"));
        }
        return reportString.toString();
    }

    public String getOutfitRecommendation() throws IOException {
        String link;
        InputStream weatherData;
        String units;
        String[] preferences = this.fileController.loadPreferences();
        if (preferences[0].equals("true")) {
            link = api.createURLString(preferences[1]);
            units = preferences[2];
            weatherData = api.getInputStreamFromURL(link);
        } else {
            String location = zipcodeField.getText();
            units = unitTypeDropdown.getValue();
            link = api.createURLString(location);
            weatherData = api.getInputStreamFromURL(link);
        }

        String forcastURLString = this.dataParser.parseWeatherAPILink(weatherData, "forecast");
        InputStream forecastData = this.api.getInputStreamFromURL(forcastURLString);
        this.dataParser.setWeatherData(forecastData);
        this.dataParser.forecastData();
        HashMap<Integer, ArrayList<String>> weeklyForecast = this.dataParser.getDailyForecast();
        int i;
        double weekTemp = 0;
        for (i=1;i<=13;i=i+2){
            ArrayList<String> forecast = weeklyForecast.get(i);
            weekTemp += Double.parseDouble(forecast.getFirst());
        }
        double averageTemp = weekTemp/7;
        return outfitRecommender.temperatureOutfitRecommender(averageTemp, units);
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