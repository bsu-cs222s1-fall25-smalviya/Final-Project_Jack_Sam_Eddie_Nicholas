# Weather App - CS222 Final Project

## Project Summary
Weather Service Program Iteration 1: This program uses Command Line Interface (CLI) to get the user's preferences, such as location and hourly vs. daily weather conditions. Then it prints general weather conditions, including temperature, humidity, wind speed, wind direction, precipitation chance, and dew point to Terminal. It also gives the user the ability to save preferences to get results quicker. 

## Team Members
- Jack Wagner 
- Sam Allen
- Eddie Julian-Caballero
- Nicholas Braeman

## Build Instructions
1. Clone the repository. 
2. Ensure Java 11+ is installed. 
3. Ensure Oracle OpenJDK 25 is installed and that it is compatible with your system

How to run Original Command Line Interface (CLI)
1. Go to TerminalMain.java
2. Click run TerminalMain.java
3. Wait a moment for it to run
4. Users are presented with 8 choices: 0: Exit the program, 1: Reset preferences, 2: Set preferences, 3: Print preferences, 4: Get hourly weather conditions, 5: Get daily weather conditions, 6: Get severe weather alerts, and 7: Get outfit recommendations.
5. In the terminal enter in a number (ex: 2).
6. After entering 2, 4, or 5 you will be asked for a zipcode (ex: 47374).
7. Then Imperial or Metric, enter i for Imperial and m for Metric (capitalization doesn’t matter).
8. Wait a moment for it to run.
9. For 4 and 5 you will have to repeat steps 6-7 unless you did 6-7 after selecting number 2 as you set your preference.
10. The program will keep running until you enter 0 for step 5.

How to run Graphical User Interface (GUI)
1. Go to the right and press the elephant symbol that’s named Gradle when hovered over.
2. Under application double click run
3. Wait for the pop up window.
4. In the Zipcode: box type in a zip code (ex: 47374).
5. Then in the Report Type: drop down box select one of the three reports you would like (ex Today’s Report).
6. Then in the Unit: drop down box pick either Imperial or Metric (ex Imperial)
7. Finally click on the Start button for the Weather App to Output the report.
8. 12. You can change the output to a bar graph by pressing Toggle View button and the press the button again to go back to Output
9. To close the app click on the X on the top right of the pop up

Alternative steps
You can also save your preferences like number 2 Set preferences from Original Command Line Interface (CLI)
4. Click on the Settings button.
5. In the Zipcode: box type in a zip code (ex: 47374).
6. Then in the Unit: drop down box pick either Imperial or Metric (ex Imperial)
7. In the theme: dropdown box select one of six themes (ex Ball State)
8. Click the Save Settings button to save settings
9. If unhappy with settings click the Reset Setting Button
10. Then in the Unit: drop down box pick either Imperial or Metric (ex Imperial)
11. Finally click on the Start button for the Weather App to Output the report.
12. You can change the output to a bar graph by pressing Toggle View button and the press the button again to go back to Output
13. To close the app click on the X on the top right of the pop up


## Dependencies
- Java 11+
- Gradle 8.14
- Oracle OpenJDK 25
- JUnit 5 (testing)
- JSONPath (JSON parsing)

