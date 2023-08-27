import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Calculations {
    private String[][] historyData;

    private String[][] supermarketData;

    private JFrame frame;
    private JComboBox<String> timeframeDropdown;
    private JButton calculateButton;
    private JTextField outputTextField1;
    private JTextField outputTextField2;
    private JTextField outputTextField3;



    public String getCurrentDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(currentDate);
    }


    public Calculations() {
        historyData = new String[200][2];
        readHistoryDataFromFile();
        supermarketData = new String[50][2];
        readSupermarketDataFromFile();

        frame = new JFrame("Gasoline Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        // Dropdown box for selecting timeframe
        timeframeDropdown = new JComboBox<>(new String[]{"7 days", "30 days", "90 days"}); // Customize as needed

        // Button to trigger calculation
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        // Text field to display output
        outputTextField1 = new JTextField();
        outputTextField1.setEditable(false); // Make it read-only
        outputTextField2 = new JTextField();
        outputTextField2.setEditable(false);
        outputTextField3 = new JTextField();
        outputTextField3.setEditable(false);


        // Adding components to the frame
        frame.add(new JLabel("Select Timeframe:"));
        frame.add(timeframeDropdown);
        frame.add(new JLabel()); // Empty label for spacing
        frame.add(new JLabel());
        frame.add(calculateButton);
        frame.add(new JLabel()); // Empty label for spacing
        frame.add(new JLabel());
        frame.add(outputTextField1);
        frame.add(outputTextField2);
        frame.add(outputTextField3);


        frame.pack();
        frame.setVisible(true);
    }

    public void calculate() {
        // Create arrays to store supermarket names and counts
        String[] supermarketNames = new String[supermarketData.length];
        int[] supermarketCounts = new int[supermarketData.length];

        String selectedTimeFrame = (String) timeframeDropdown.getSelectedItem();
        String currentDateStr = getCurrentDate();
        // Convert currentDateStr to a Date object
        double totalKilometersDriven = 0.0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Java understands dat objects
        Date currentDate;
        try {
            currentDate = dateFormat.parse(currentDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return; // Handle the error appropriately
        }

        int days;
        if (selectedTimeFrame.equals("7 days")) {
            days = 7;
        } else if (selectedTimeFrame.equals("30 days")) {
            days = 30;
        } else {
            days = 90;
        }
        // Calculate the start date for the selected timeframe
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        Date startDate = calendar.getTime();
        // Populate supermarket names array
        for (int i = 0; i < supermarketData.length && supermarketData[i][0] != null; i++) {
            supermarketNames[i] = supermarketData[i][0];
        }

        // Iterate through historyData and find relevant entries
        for (int i = 0; i < historyData.length && historyData[i][0] != null; i++) {
            String historyDateStr = historyData[i][1];
            try {
                Date historyDate = dateFormat.parse(historyDateStr);
                if (historyDate.after(startDate) && historyDate.before(currentDate)) {
                    String supermarketName = historyData[i][0];
                    //Finding the index of the supermarket name
                    int index = Arrays.asList(supermarketNames).indexOf(supermarketName);
                    if (index != -1) {
                        // Increment the count for the supermarket
                        supermarketCounts[index]++;
                    }
                    int count = supermarketCounts[index];
                    // Find the corresponding supermarket distance
                    for (int j = 0; j < supermarketData.length && supermarketData[j][0] != null; j++) {
                        if (supermarketData[j][0].equals(supermarketName)) {
                            double distance = Double.parseDouble(supermarketData[j][1]);
                            totalKilometersDriven += distance * count;
                            break;
                        }
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
                // Handle the parsing error appropriately
            }
        }
        double gasolineLitres = (totalKilometersDriven/100)*10;
        double moneySpent =  gasolineLitres*36;
        // Update the outputTextField with the calculated total distance
        outputTextField1.setText("Total Distance Driven: " + totalKilometersDriven + " km");
        outputTextField2.setText("Total Gasoline used: " + gasolineLitres + "L");
        outputTextField3.setText("Total Money spent on Gasoline: " + moneySpent + "฿");

    }



    private void readHistoryDataFromFile() {
        int index = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("history_db.txt"))) {
            String line;
            while ((line = reader.readLine()) != null && index < historyData.length) {
                String[] parts = line.split(" "); //Create String array, splits the line at " " and stores the values
                if (parts.length >= 2) {
                    historyData[index][0] = parts[0]; // Supermarket name
                    historyData[index][1] = parts[1]; // Date
                    index++;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading history data.");
        }
    }

    public void readSupermarketDataFromFile() {
        int index = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("supermarket_db.txt"))) {
            String line;
            while ((line = reader.readLine()) != null && index < supermarketData.length) {
                String[] parts = line.split(" "); //Create String array, splits the line at " " and stores the values
                if (parts.length >= 2) {
                    supermarketData[index][0] = parts[0]; // Supermarket name
                    supermarketData[index][1] = parts[1]; // distance
                    index++;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading history data.");
        }
    }


    public void displayHistoryData() {
        for (int i = 0; i < historyData.length && historyData[i][0] != null; i++) {
            System.out.println("Supermarket: " + historyData[i][0] + " Date: " + historyData[i][1]);
        }
    }

    public void displaySupermarketData() {
        for (int i = 0; i < supermarketData.length && supermarketData[i][0] != null; i++) {
            System.out.println("Supermarket: " + supermarketData[i][0] + " Distance: " + supermarketData[i][1]);
        }
    }



    public static void main(String[] args) {
    Calculations calculations = new Calculations();

    calculations.displayHistoryData();
    calculations.displaySupermarketData();

    }
}

