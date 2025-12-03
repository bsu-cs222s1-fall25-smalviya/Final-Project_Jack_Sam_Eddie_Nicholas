package edu.bsu.cs;

//imports for javafx
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Tooltip;

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
    private BarChart<String, Number> temperatureChart;
    private StackPane diagramStack;
    private Button toggleViewButton = new Button("Toggle View");

    // Settings UI Components
    private final TextField locationPreferences = new TextField();
    private final ComboBox<String> unitPreferences = new ComboBox<>();
    private final ComboBox<String> themeDropdown = new ComboBox<>();
    private Button saveButton = new Button("Save Settings");
    private Button resetButton = new Button("Reset Settings");
    private String css = "light-style";

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
    public void start(Stage primaryStage) {
        VBox mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);

        // Create rows
        HBox topRow = createTopRow();
        HBox secondRow = createSecondRow();
        HBox thirdRow = createThirdRow();
        HBox fourthRow = createFourthRow();

        // Add rows
        mainLayout.getChildren().addAll(topRow, secondRow, thirdRow, fourthRow);

        Scene scene = new Scene(mainLayout, 600, 330);

        // Load CSS stylesheet
        String css = getClass().getResource("/edu/bsu/cs/" + this.css + ".css").toExternalForm();
        String tempChartCSS = getClass().getResource("/edu/bsu/cs/temperatureChart.css").toExternalForm();
        scene.getStylesheets().add(css);
        scene.getStylesheets().add(tempChartCSS);

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
                "Select report type",
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
                "Pick a unit",
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

    private HBox createThirdRow() {
        HBox thirdRow = new HBox(10);
        thirdRow.setAlignment(Pos.CENTER_LEFT);

        // Output area for report
        Label reportLabel = new Label("Output: ");
        reportField.setEditable(false);
        reportField.setPromptText("Report: ");
        reportField.setPrefWidth(500);

        //Added the Bar chart to the third Row
        //Graph set up
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Day");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Temp");

        temperatureChart = new BarChart<>(xAxis, yAxis);
        temperatureChart.setTitle("Weekly Temperatures");
        temperatureChart.setLegendVisible(false);
        temperatureChart.setAnimated(true);
        temperatureChart.setPrefWidth(500);
        temperatureChart.setPrefHeight(200);
        temperatureChart.setBarGap(5);
        temperatureChart.setCategoryGap(15);

        diagramStack = new StackPane(reportField, temperatureChart);
        reportField.setVisible(true);
        temperatureChart.setVisible(false);

        temperatureChart.getStyleClass().add("tempChart");

        // Add components to second row (dropdown first, then start button)
        thirdRow.getChildren().addAll(reportLabel, diagramStack);

        return thirdRow;
    }

    private HBox createFourthRow() {
        HBox fourthRow = new HBox(10);
        fourthRow.setAlignment(Pos.CENTER_LEFT);

        toggleViewButton.setOnAction(e -> toggleOutputView());
        fourthRow.getChildren().add(toggleViewButton);

        return fourthRow;
    }

    // create the settings stage

    private void startSettingsStage() throws FileNotFoundException {
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
        String css = getClass().getResource("/edu/bsu/cs/" + this.css + ".css").toExternalForm();
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

    private HBox createSettingsTopRow() throws FileNotFoundException {
        HBox topRow = new HBox();
        topRow.setAlignment(Pos.CENTER);
        topRow.setSpacing(10);

        Label locationLabel = new Label("Zipcode:");
        String[] preferences = fileController.loadPreferences();
        if (preferences[0].equals("false")){
            locationPreferences.clear();
        }
        locationPreferences.setPromptText("Enter zipcode");
        locationPreferences.setPrefWidth(150);

        // Unit type label and dropdown
        Label unitTypeLabel = new Label("Unit:");
        if (unitPreferences.getItems().isEmpty()) {
            unitPreferences.getItems().addAll(
                    "Pick a unit",
                    "Imperial",
                    "Metric"
            );
        }
        unitPreferences.setPrefWidth(115);
        if (preferences[0].equals("true")){
            unitPreferences.setValue(preferences[2]);
        } else {
            unitPreferences.setValue("Pick a unit");
        }

        // Add components to top row
        topRow.getChildren().addAll(locationLabel, locationPreferences, unitTypeLabel, unitPreferences);

        return topRow;
    }

    private HBox createSettingsSecondRow(){
        HBox secondRow = new HBox();
        secondRow.setAlignment(Pos.CENTER);
        secondRow.setSpacing(10);

        Label themeLabel = new Label("Theme:");
        if (themeDropdown.getItems().isEmpty()) {
            themeDropdown.getItems().addAll(
                    "None",
                    "Dark Mode",
                    "Ball State",
                    "Cityscape",
                    "Frutiger Aero",
                    "Nature"
            );
        }
        themeDropdown.setPrefWidth(317);
        String themeDropdownText = switch (this.css) {
            case "light-style" -> "None";
            case "dark-style" -> "Dark Mode";
            case "ball-state-style" -> "Ball State";
            case "city-style" -> "Cityscape";
            case "frutiger-aero" -> "Frutiger Aero";
            case "nature-style" -> "Nature";
            default -> "";
        };
        themeDropdown.setValue(themeDropdownText);

        secondRow.getChildren().addAll(themeLabel, themeDropdown);

        return secondRow;
    }

    private HBox createSettingsThirdRow(){
        HBox thirdRow = new HBox();
        thirdRow.setAlignment(Pos.CENTER);
        thirdRow.setSpacing(10);

        saveButton = getSaveButton();
        saveButton.setPrefWidth(200);

        resetButton = getResetButton();
        resetButton.setPrefWidth(200);

        thirdRow.getChildren().addAll(saveButton, resetButton);

        return thirdRow;
    }


    //Getter methods for UI components (for future implementation)

    private Button getSettingsButton() {
        settingsButton.setOnAction(event -> {
            try {
                startSettingsStage();
                //Stage stage = (Stage) settingsButton.getScene().getWindow();
                //stage.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        return settingsButton;
    }

    private Button getSaveButton(){
        saveButton.setOnAction(event -> {
            try {
                saveSettings();
                Scene scene = settingsButton.getScene();
                scene.getStylesheets().clear();
                String css = getClass().getResource("/edu/bsu/cs/" + this.css + ".css").toExternalForm();
                scene.getStylesheets().add(css);
                Stage stage = (Stage) saveButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //start();
        });

        return saveButton;
    }

    private Button getResetButton(){
        resetButton.setOnAction(event -> {
            try {
                fileController.savePreferences(new String[] {"false","",""});
                Scene scene = settingsButton.getScene();
                scene.getStylesheets().clear();
                this.css = "light-style";
                String css = getClass().getResource("/edu/bsu/cs/light-style.css").toExternalForm();
                scene.getStylesheets().add(css);
                Stage stage = (Stage) resetButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return resetButton;
    }

    private Button getHelpButton(){
        helpButton.setOnAction(event -> {reportField.setText(
                "How to Use the Weather App:\n" +
                        "- Enter a zipcode in the Zipcode field.\n" +
                        "- Choose a report type (Today's Report, Daily, or Outfit).\n" +
                        "- Select units (Imperial or Metric).\n" +
                        "- Click Start to generate your report.\n" +
                        "- Settings allows you to save preferences.");
        });

        return helpButton;
    }

    //Toggle Button for Diagrams
    private void toggleOutputView(){
        boolean showingReport = reportField.isVisible();
        reportField.setVisible(!showingReport);
        temperatureChart.setVisible(showingReport);

        if (showingReport) {
            toggleViewButton.setText("Show Weather Report");
        } else{
            toggleViewButton.setText("Show Chart");
        }
    }

    private TextArea getReportField() throws IOException {
        String reportString = "";
        try {
            if (reportTypeDropdown.getValue().equals("Today's Report")) {
                reportString = getHourlyReport();
            } else if (reportTypeDropdown.getValue().equals("Daily Report")) {
                reportString = getDailyReport();
            } else if (reportTypeDropdown.getValue().equals("Outfit Recommender")) {
                reportString = getOutfitRecommendation();
            }
            reportField.setText(reportString);
        } catch (Exception e){
            reportField.setText("Sorry, if you want to use your preferences, you must leave zipcode blank and not set unit. If you do not wish to use your preferences, you must provide a zipcode and unit. ");
        }

        return reportField;
    }
    
    private String getHourlyReport() throws IOException {
        String link;
        InputStream weatherData;
        String units;
        String[] preferences = this.fileController.loadPreferences();
        if (zipcodeField.getText().isEmpty()&&unitTypeDropdown.getValue().equalsIgnoreCase("Pick a unit")) {
            link = api.createURLString(preferences[1]);
            units = preferences[2];
        } else {
            if (zipcodeField.getText().isEmpty()||unitTypeDropdown.getValue().equals("Pick a unit")){
                throw new IllegalArgumentException("Empty zipcode/unit");
            }
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
        if (zipcodeField.getText().isEmpty()&&unitTypeDropdown.getValue().equalsIgnoreCase("Pick a unit")) {
            link = api.createURLString(preferences[1]);
            units = preferences[2];
        } else {
            if (zipcodeField.getText().isEmpty()||unitTypeDropdown.getValue().equals("Pick a unit")){
                throw new IllegalArgumentException("Empty zipcode/unit");
            }
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

        updateTemperatureChart(dailyForecast, units);

        return reportString.toString();
    }

    private String getOutfitRecommendation() throws IOException {
        String link;
        InputStream weatherData;
        String units;
        String[] preferences = this.fileController.loadPreferences();
        if (zipcodeField.getText().isEmpty()&&unitTypeDropdown.getValue().equalsIgnoreCase("Pick a unit")) {
            link = api.createURLString(preferences[1]);
            units = preferences[2];
        } else {
            if (zipcodeField.getText().isEmpty()||unitTypeDropdown.getValue().equals("Pick a unit")){
                throw new IllegalArgumentException("Empty zipcode/unit");
            }
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
                }  else {
                    reportField.setText(e.toString());
                }
            }
        });

        return startButton;
    }

    // other methods

    private void saveSettings() throws IOException {
        String[] preferences = new String[3];
        String locationPreference = locationPreferences.getText();
        String unitPreference = unitPreferences.getValue();

        boolean settingsAlertCalled = false;

        if (locationPreference.length()==5 && locationPreference.chars().allMatch(Character::isDigit)) {
            preferences[1] = databaseParser.getCoordinates(locationPreference);
            preferences[0] = "true";
        } else {
            preferences[0] = "false";
            callSettingsAlert();
            settingsAlertCalled = true;
        }
        if (unitPreference.equals("Imperial")||unitPreference.equals("Metric")){
            preferences[2] = unitPreference;
            preferences[0] = "true";
        } else {
            preferences[0] = "false";
            if (!settingsAlertCalled) {
                callSettingsAlert();
            }
        }
        fileController.savePreferences(preferences);

        css = switch (themeDropdown.getValue()){
            case "None" -> "light-style";
            case "Dark Mode" -> "dark-style";
            case "Ball State" -> "ball-state-style";
            case "Cityscape" -> "city-style";
            case "Frutiger Aero" -> "frutiger-aero";
            case "Nature" -> "nature-style";
            default -> "";
        };
    }

    public void callSettingsAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Settings Alert");
        alert.setHeaderText("Invalid settings");
        alert.setContentText("If you wish to set a default zipcode and unit, make sure you set the zipcode to be 5 digits and the units to be a valid option!");
        alert.showAndWait();
    }

    private void updateTemperatureChart(HashMap<Integer, ArrayList<String>> dailyForecast, String units) {
        if (temperatureChart == null) {
            return;
        }
        temperatureChart.getData().clear();
        String unitsOfTemp = units.equalsIgnoreCase("imperial") ? "°F" : "°C";

        // Update axis label
        NumberAxis yAxis = (NumberAxis) temperatureChart.getYAxis();
        yAxis.setLabel("Temperature (" + unitsOfTemp + ")");

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        int dayNumber = 1;
        double minTemp = Double.POSITIVE_INFINITY;
        double maxTemp = Double.NEGATIVE_INFINITY;
        for (int i = 1; i <= 13; i += 2) {
            ArrayList<String> forecast = dailyForecast.get(i);
            if (forecast == null || forecast.isEmpty()) {
                continue;
            }

            try {
                double temp = Double.parseDouble(forecast.getFirst());
                String dayLabel = "Day " + dayNumber;
                series.getData().add(new XYChart.Data<>(dayLabel, temp));

                //help better format axis for graph
                minTemp = Math.min(minTemp, temp);
                maxTemp = Math.max(maxTemp, temp);
                dayNumber++;
            } catch (NumberFormatException _) {
            }
        }

        temperatureChart.getData().add(series);

        double padding = 5.0;
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(Math.floor(minTemp - padding));
        yAxis.setUpperBound(Math.floor(maxTemp + padding));
        yAxis.setTickUnit(5);

        for (XYChart.Data<String, Number> data : series.getData()) {
            String toolText = data.getXValue() + ": "  + data.getYValue() + " "  + unitsOfTemp;
            Tooltip tooltip = new Tooltip(toolText);
            Tooltip.install(data.getNode(), tooltip);
        }
    }

    static void main(String[] args) {
        launch(args);
    }
}