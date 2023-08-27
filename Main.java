import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private JFrame mainFrame;
    private JButton calculationsButton;
    private JButton addHistoryButton;
    private JButton addSupermarketButton;

    public Main() {
        mainFrame = new JFrame("Main Menu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(3, 1));

        calculationsButton = new JButton("Calculations");
        calculationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCalculationsGUI();
            }
        });
        addHistoryButton = new JButton("Add History");
        addHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddHistoryGUI();
            }
        });

        addSupermarketButton = new JButton("Add Supermarket");
        addSupermarketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddSupermarketGUI();
            }
        });

        mainFrame.add(calculationsButton);
        mainFrame.add(addHistoryButton);
        mainFrame.add(addSupermarketButton);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    private void openCalculationsGUI() {
        Calculations calculations = new Calculations();
        calculations.calculate(); // Call the calculation logic
    }

    public void openAddHistoryGUI() {
        AddHistory addHistory = new AddHistory(this); // Pass the current Main instance
        addHistory.openAddHistoryGUI(); // Open the AddHistory GUI
    }

    private void openAddSupermarketGUI() {
        new AddSupermarket();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
