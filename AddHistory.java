import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class AddHistory {
    private Main main; // Reference to the Main class instance

    public AddHistory(Main mainInstance) {
        this.main = mainInstance;
    }

    public void openAddHistoryGUI() {
        JFrame frame = new JFrame("Add To History");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        JLabel newSupermarketNameLabel = new JLabel("Select the supermarket:");
        ArrayList<String> supermarketNames = loadSupermarketNamesFromFile();
        JComboBox<String> newSupermarketDropDown = new JComboBox<>(supermarketNames.toArray(new String[0]));

        JLabel newSupermarketHistoryLabel = new JLabel("Enter the date (DD-MM-YYYY):");
        JTextField newHistory = new JTextField();

        JButton saveHistoryButton = new JButton("Save History");
        saveHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveHistoryToFile(newHistory.getText(), (String) newSupermarketDropDown.getSelectedItem());
                frame.dispose(); // Close the current GUI
                restartProgram(); // Restart the program
            }
        });

        frame.add(newSupermarketNameLabel);
        frame.add(newSupermarketDropDown);
        frame.add(newSupermarketHistoryLabel);
        frame.add(newHistory);
        frame.add(saveHistoryButton);

        frame.pack();
        frame.setVisible(true);
    }

    private ArrayList<String> loadSupermarketNamesFromFile() {
        ArrayList<String> names = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("supermarket_db.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length > 0) {
                    names.add(parts[0]); // Assuming the supermarket name is the first part
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading supermarket names.");
        }
        return names;
    }

    private void saveHistoryToFile(String history, String selectedSupermarket) {
        if (!history.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("history_db.txt", true))) {
                writer.write(selectedSupermarket + " " + history);
                writer.newLine();
                writer.flush();
                JOptionPane.showMessageDialog(null, "History added successfully");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while saving");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please fill out all fields");
        }
    }

    private void restartProgram() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddHistory(new Main()).openAddHistoryGUI();
            }
        });
    }
}
