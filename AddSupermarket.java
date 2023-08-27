import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AddSupermarket {
    private JFrame frame;
    private JTextField newSupermarketNameTextField;
    private JTextField newSupermarketDistanceTextField;
    private JButton saveSupermarketButton;

    public AddSupermarket() {
        frame = new JFrame("Add new supermarket");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        JLabel newSupermarketNameLabel = new JLabel("New Supermarket Name (e.g Tops_Pattaya):");
        newSupermarketNameTextField = new JTextField();
        JLabel newSupermarketDistanceLabel = new JLabel("New Supermarket Distance in Km (e.g 5):");
        newSupermarketDistanceTextField = new JTextField();
        saveSupermarketButton = new JButton("Save Supermarket");

        saveSupermarketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveSupermarketToFile();
                frame.dispose(); // Close the current GUI
                restartProgram(); // Restart the program
            }
        });

        frame.add(newSupermarketNameLabel);
        frame.add(newSupermarketNameTextField);
        frame.add(newSupermarketDistanceLabel);
        frame.add(newSupermarketDistanceTextField);
        frame.add(saveSupermarketButton);

        frame.pack();
        frame.setVisible(true);
    }

    private void saveSupermarketToFile() {
        String newName = newSupermarketNameTextField.getText();
        String distance = newSupermarketDistanceTextField.getText();

        if (!newName.isEmpty() && !distance.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("supermarket_db.txt", true))) {
                writer.write(newName + " " + distance);
                writer.newLine();
                writer.flush();
                JOptionPane.showMessageDialog(frame, "Supermarket added successfully");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error while saving");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please fill out all fields");
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
                new AddSupermarket();
            }
        });
    }
}
