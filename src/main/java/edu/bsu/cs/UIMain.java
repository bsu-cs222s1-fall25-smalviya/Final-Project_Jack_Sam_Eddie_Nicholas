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
import javafx.stage.Modality;

//imports for our classes
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.*;

public class UIMain extends Application {

    // Main UI Components
    private Button settingsButton = new Button("Settings");
    private Button helpButton = new Button("Help");
    private final TextField zipcodeField = new TextField();
    private TextArea reportField = new TextArea();
    private final ComboBox<String> reportTypeDropdown = new ComboBox<>();
    private final ComboBox<String> unitTypeDropdown = new ComboBox<>();
    private Button startButton = new Button("Start");

    // Settings UI Components
    private final TextField locationPreferences = new TextField();
    private final ComboBox<String> unitPreferences = new ComboBox<>();
    private final ComboBox<String> themeDropdown = new ComboBox<>();
    private Button saveButton = new Button("Save Settings");

    // Our classes
    FileController fileController = new FileController();
    WeatherServiceAPI api = new WeatherServiceAPI();
    APIDataParser dataParser = new APIDataParser();
    DataFormatter dataFormatter = new DataFormatter();
    Converter converter = new Converter();
    OutfitRecommender outfitRecommender = new OutfitRecommender(converter);
    CitiesDatabaseParser databaseParser = new CitiesDatabaseParser();

    public UIMain() throws IOException {
    }


    // Create the main stage

    @Override
    public void start(Stage primaryStage) throws IOException {
        VBox mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);

        // Create rows
        HBox topRow = createTopRow();
        HBox secondRow = createSecondRow();
        HBox thirdRow = createThirdRow();

        // Add rows
        mainLayout.getChildren().addAll(topRow, secondRow, thirdRow);

        Scene scene = new Scene(mainLayout, 600, 330);

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
        zipcodeField.setPromptText("Enter zipcode");
        zipcodeField.setPrefWidth(245);

        // Settings button
        settingsButton = getSettingsButton();
        settingsButton.setId("settingsButton");
        settingsButton.setPrefWidth(115);

        // Help button
        helpButton = getHelpButton();
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
        reportTypeDropdown.getItems().addAll(
                "Today's Report",
                "Daily Report",
                "Outfit Recommender"
        );
        reportTypeDropdown.setPromptText("Select report type");
        reportTypeDropdown.setPrefWidth(185);
        reportTypeDropdown.setValue("Pick an option"); // Set default selection

        // Unit type label and dropdown
        Label unitTypeLabel = new Label("Unit: ");
        unitTypeDropdown.getItems().addAll(
                "Imperial",
                "Metric"
        );
        unitTypeDropdown.setPrefWidth(115);
        unitTypeDropdown.setValue("Pick a unit");

        // Start button
        startButton = getStartButton();
        startButton.setId("startButton");
        startButton.setPrefWidth(100);

        // Add components to second row (dropdown first, then start button)
        secondRow.getChildren().addAll(reportTypeLabel, reportTypeDropdown, unitTypeLabel, unitTypeDropdown, startButton);

        return secondRow;
    }

    private HBox createThirdRow() throws IOException {
        HBox thirdRow = new HBox(10);
        thirdRow.setAlignment(Pos.CENTER_LEFT);

        // Output area for report
        Label reportLabel = new Label("Output: ");
        reportField.setEditable(false);
        reportField.setPromptText("Report: ");
        reportField.setPrefWidth(500);

        // Add components to second row (dropdown first, then start button)
        thirdRow.getChildren().addAll(reportLabel, reportField);

        return thirdRow;
    }

    // create the settings stage

    private void startSettingsStage(){
        VBox secondaryLayout = new VBox(15);
        secondaryLayout.setPadding(new Insets(20));
        secondaryLayout.setAlignment(Pos.TOP_CENTER);

        // Create rows
        HBox topRow = createSettingsTopRow();
        HBox secondRow = createSettingsSecondRow();
        HBox thirdRow = createSettingsThirdRow();

        // Add rows
        secondaryLayout.getChildren().addAll(topRow, secondRow, thirdRow);

        Scene scene = new Scene(secondaryLayout, 450, 170);

        // Load CSS stylesheet
        String css = getClass().getResource("/edu/bsu/cs/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        Stage secondaryStage = new Stage();
        secondaryStage.setScene(scene);
        secondaryStage.initModality(Modality.APPLICATION_MODAL);

        // Configure the stage
        secondaryStage.setTitle("Settings");
        secondaryStage.setScene(scene);
        secondaryStage.setResizable(false);
        secondaryStage.show();
    }

    private HBox createSettingsTopRow(){
        HBox topRow = new HBox();
        topRow.setAlignment(Pos.CENTER);
        topRow.setSpacing(10);

        Label locationLabel = new Label("Zipcode:");
        locationPreferences.setPromptText("Enter zipcode");
        locationPreferences.setPrefWidth(150);

        // Unit type label and dropdown
        Label unitTypeLabel = new Label("Unit:");
        unitPreferences.getItems().addAll(
                "Imperial",
                "Metric"
        );
        unitPreferences.setPrefWidth(115);
        unitPreferences.setValue("Pick a unit");

        // Add components to top row
        topRow.getChildren().addAll(locationLabel, locationPreferences, unitTypeLabel, unitPreferences);

        return topRow;
    }

    private HBox createSettingsSecondRow(){
        HBox secondRow = new HBox();
        secondRow.setAlignment(Pos.CENTER);
        secondRow.setSpacing(10);

        Label themeLabel = new Label("Theme:");
        themeDropdown.getItems().addAll(
                "None",
                "Theme 1",
                "Theme 2",
                "Theme 3",
                "Etc"
        );
        themeDropdown.setPrefWidth(317);
        themeDropdown.setValue("Pick a theme");

        secondRow.getChildren().addAll(themeLabel, themeDropdown);

        return secondRow;
    }

    private HBox createSettingsThirdRow(){
        HBox thirdRow = new HBox();
        thirdRow.setAlignment(Pos.CENTER);

        saveButton = getSaveButton();
        saveButton.setPrefWidth(200);

        thirdRow.getChildren().addAll(saveButton);

        return thirdRow;
    }


    //Getter methods for UI components (for future implementation)

    private Button getSettingsButton() {
        settingsButton.setOnAction(event -> startSettingsStage());
        return settingsButton;
    }

    private Button getSaveButton(){
        return saveButton;
    }

    private Button getHelpButton() {
        return helpButton;
    }

    private TextArea getReportField() throws IOException {
        String reportString = "";
        if (reportTypeDropdown.getValue().equals("Today's Report")){
            reportString = getHourlyReport();
            //reportString = "hello world today";
        } else if (reportTypeDropdown.getValue().equals("Daily Report")){
            reportString = getDailyReport();
            //reportString = "hello world week";
        } else if (reportTypeDropdown.getValue().equals("Outfit Recommender")) {
            reportString = getOutfitRecommendation();
            //reportString = "hello world outfit";
        }
        
        reportField.setText(reportString);

        return reportField;
    }
    
    private String getHourlyReport() throws IOException {
        String link;
        InputStream weatherData;
        String units;
        String[] preferences = this.fileController.loadPreferences();
        if (preferences[0].equals("true")) {
            link = api.createURLString(databaseParser.getCoordinates(preferences[1]));
            units = preferences[2];
        } else {
            String location = zipcodeField.getText();
            units = unitTypeDropdown.getValue().toLowerCase();
            link = api.createURLString(databaseParser.getCoordinates(location));
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

    private String getDailyReport() throws IOException {
        String link;
        InputStream weatherData;
        String units;
        String[] preferences = this.fileController.loadPreferences();
        if (preferences[0].equals("true")) {
            link = api.createURLString(databaseParser.getCoordinates(preferences[1]));
            units = preferences[2];
        } else {
            String location = zipcodeField.getText();
            units = unitTypeDropdown.getValue().toLowerCase();
            link = api.createURLString(databaseParser.getCoordinates(location));
        }

        weatherData = api.getInputStreamFromURL(link);
        String dailyForecastURLString = this.dataParser.parseWeatherAPILink(weatherData, "forecast");
        InputStream dailyForecastData = this.api.getInputStreamFromURL(dailyForecastURLString);
        this.dataParser.setWeatherData(dailyForecastData);
        this.dataParser.forecastData();
        HashMap<Integer, ArrayList<String>> dailyForecast = this.dataParser.getDailyForecast();

        StringBuilder reportString = new StringBuilder();
        int i;
        for (i=1;i<=13;i=i+2){
            ArrayList<String> forecast = dailyForecast.get(i);
            reportString.append(this.dataFormatter.formatWeatherData(forecast, units, "Daily"));
        }
        return reportString.toString();
    }

    private String getOutfitRecommendation() throws IOException {
        String link;
        InputStream weatherData;
        String units;
        String[] preferences = this.fileController.loadPreferences();
        if (preferences[0].equals("true")) {
            link = api.createURLString(databaseParser.getCoordinates(preferences[1]));
            units = preferences[2];
        } else {
            String location = zipcodeField.getText();
            units = unitTypeDropdown.getValue().toLowerCase();
            link = api.createURLString(databaseParser.getCoordinates(location));
        }

        weatherData = api.getInputStreamFromURL(link);
        String forcastURLString = this.dataParser.parseWeatherAPILink(weatherData, "forecast");
        InputStream forecastData = this.api.getInputStreamFromURL(forcastURLString);
        this.dataParser.setWeatherData(forecastData);
        this.dataParser.forecastData();
        HashMap<Integer, ArrayList<String>> dailyForecast = this.dataParser.getDailyForecast();
        int i;
        double weekTemp = 0;
        for (i=1;i<=13;i=i+2){
            ArrayList<String> forecast = dailyForecast.get(i);
            weekTemp += Double.parseDouble(forecast.getFirst());
        }
        double averageTemp = weekTemp/7;
        return outfitRecommender.temperatureOutfitRecommender(averageTemp, units);
    }

    private Button getStartButton() {
        startButton.setOnAction(event -> {
            try {
                reportField = getReportField();
            } catch (IOException e) {
                if (e instanceof MalformedURLException) {
                    reportField.setText("Sorry, there was a problem with the URL. Try again in a bit. ");
                } else if (e instanceof UnknownHostException || e instanceof SocketException || e instanceof SocketTimeoutException) {
                    reportField.setText("Sorry, there was a problem with your internet. Try again in a bit. ");
                } else if (e instanceof FileNotFoundException) {
                    reportField.setText("Sorry, something went wrong. Make sure your zipcode is correct. ");
                } else {
                    reportField.setText(e.toString());
                }
            }
        });

        return startButton;
    }

    static void main(String[] args) {
        launch(args);
    }
}