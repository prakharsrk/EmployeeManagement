import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

public class EmployeeUI implements ActionListener {

public static String div = "------------------------------------------";
public static List<Employee2> employees = new ArrayList<Employee2>();
public static JTextArea display;
public static JButton[] buttons = new JButton[3];
public static JLabel[] subTitles = new JLabel[5];
public static JTextField[] inputs = new JTextField[5];

public static void main(String[] args) {

    // Fonts
    Font titleFont = new Font("Courier New", 1, 24);
    Font subFont = new Font("Courier New", 1, 16);

    // Frame
    JFrame frame = new JFrame("Employee Records");
    frame.setSize(550, 450);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);

    // Container
    JPanel container = new JPanel();
    container.setLayout(null);
    frame.setContentPane(container);

    // Title
    JLabel title = new JLabel("Employee Records");
    title.setFont(titleFont);
    title.setForeground(Color.blue);
    title.setBounds(160, 10, 250, 24);

    // Lablels and text fields
    for (int i = 0; i < subTitles.length; i++) {
        subTitles[i] = new JLabel();
        subTitles[i].setFont(subFont);
        subTitles[i].setBounds(5, 50 + (i * 35), 190, 16);
    }
    subTitles[0].setText("Employee ID#: ");
    subTitles[1].setText("First Name: ");
    subTitles[2].setText("Last Name: ");
    subTitles[3].setText("Annual Salary: ");
    subTitles[4].setText("Start Date: ");

    for (int i = 0; i < subTitles.length; i++) {
        inputs[i] = new JTextField();
        inputs[i].setBounds(160, 47 + (35 * i), 150, 22);
    }

    // Buttons
    for (int i = 0; i < buttons.length; i++) {
        buttons[i] = new JButton();
        buttons[i].addActionListener(new EmployeeUI());
        buttons[i].setBounds(330, 47 + (35 * i), 200, 20);
    }
    buttons[0].setText("Add (REQUIRES ALL FIELDS)");
    buttons[1].setText("Remove (by ID#)");
    buttons[2].setText("List");

    // Text area
    display = new JTextArea();
    display.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(display);
    scrollPane.setBounds(5, 217, 535, 200);

    // Adding everything
    container.add(title);
    container.add(scrollPane);
    // Since # of textfields will always equal # of subtitles, we can use the
    // max value of subtitles for the loop
    for (int i = 0; i < subTitles.length; i++) {
        container.add(subTitles[i]);
        container.add(inputs[i]);
    }
    for (int i = 0; i < buttons.length; i++) {
        container.add(buttons[i]);
    }

    // Extras
    frame.toFront();
    frame.setVisible(true);
}

public void actionPerformed(ActionEvent event) {
    if (event.getSource().equals(buttons[0])) {
        // Pass boolean to check if the program should continue or not
        boolean pass = true;
        // Loop to check if all textfields have data
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].getText().equals("")) {
                display.setText("Error: enter data for ALL fields.");
                pass = false;
            }
        }

        // If the user passed, the program continues
        if (pass == true) {
            // Checking if ID# already exists
            if (employees.contains(Integer.parseInt(inputs[0].getText()))) {
                // Displaying error message if entered ID# exists
                display.setText("Error: employee ID# exists, use another.");
                // If not, it adds all the data
            } else {
                // Adding all the info to the array
                employees.add(new Employee2(Integer.parseInt(inputs[0].getText()),//id
                        inputs[1].getText(),                //firstname
                        inputs[2].getText(),                //last name
                        Integer.parseInt(inputs[3].getText()),  //salary
                        new Date(inputs[4].getText())                   //startDate
                        ));
                display.setText("Employee #" + inputs[0].getText() + " added to record(s).");
                // Loop to set all textfields to empty
                for (int i = 0; i < inputs.length; i++) {
                    inputs[i].setText(null);
                }
            }
        }
    } else if (event.getSource().equals(buttons[1])) {
        // Loop to search list for requested removal
        for (int i = employees.size() - 1; i >= 0; i--) {
            // If the request is found, it removes all data
            if (Integer.parseInt(inputs[0].getText()) == employees.get(i).getId()) {
                display.setText("Employee #" + employees.get(i).getId() + " has been removed from the records.");
                employees.remove(i);
                break;
                // If not, the ID# does not exist
            } else {
                display.setText("Error: employee ID# does not exist, try again.");
            }
        }
    } else {
        // Resets text area and lists all the data
        display.setText(null);
        for (int i = 0; i < employees.size(); i++) {
            display.append(div + "\nEmployee ID#: " + employees.get(i).getId() + "\nFirst Name: " + employees.get(i).getFirstName()
                    + "\nLast Name: " + employees.get(i).getLastName() + "\nAnnual Salary: $" + employees.get(i).getSalary()
                    + "\nStart Date: " + employees.get(i).getStartDate() + "\n");
        }
    }
}

}