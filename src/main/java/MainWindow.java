
import Target.Target;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class MainWindow extends JFrame {

    JTextField clusterTextField, steppingTextField, budgetTextField, regressionsTextField;
    JLabel clusterLabel, steppingLabel, regressionsLabel, budgetLabel;
    Button addTargetButton, addFilterButton, generateCommandButton;

    //Dynamically targets
    int numberOfTargets = 0;
    List<JTextField> textFields = new ArrayList<JTextField>();

    List<Target> targets = new ArrayList<Target>();

    public MainWindow() {
        clusterLabel = new JLabel("Cluster");
        clusterLabel.setBounds(350, 10, 100, 20);
        clusterTextField = new JTextField();
        clusterTextField.setBounds(350, 30, 100, 30);

        steppingLabel = new JLabel("Stepping");
        steppingLabel.setBounds(450, 10, 100, 20);
        steppingTextField = new JTextField();
        steppingTextField.setBounds(450, 30, 100, 30);

        budgetLabel = new JLabel("Budget");
        budgetLabel.setBounds(550, 10, 100, 20);
        budgetTextField = new JTextField();
        budgetTextField.setBounds(550, 30, 100, 30);

        regressionsLabel = new JLabel("Regressions");
        regressionsLabel.setBounds(450, 80, 100, 20);
        regressionsTextField = new JTextField();
        regressionsTextField.setBounds(50, 100, 900, 30);

        addTargetButton = new Button("Add Target");
        addTargetButton.setBounds(400, 140, 100, 40);

        generateCommandButton = new Button("Create Command");
        generateCommandButton.setBounds(500, 140, 120, 40);

        addTargetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (numberOfTargets == 0) {
                    add(addFilterButton);
                }

                if (numberOfTargets > 0) {
                    cleanScreen();
                }

                numberOfTargets += 1;
            }
        });

        addFilterButton = new Button("Add Filter");
        addFilterButton.setBounds(450, 180, 100, 40);
        addFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField filterTextField;
                if (!textFields.isEmpty()) {
                    filterTextField = new JTextField("Filter");
                    JTextField view = textFields.get(textFields.size() - 1);
                    filterTextField.setBounds(view.getBounds().x, view.getBounds().y + 30, 900, 30);
                } else {
                    filterTextField = new JTextField("Connector");
                    filterTextField.setBounds(50, 250, 900, 30);
                }
                add(filterTextField);
                textFields.add(filterTextField);
            }
        });

        generateCommandButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String myString = "--cluster msid --regressions '123 456' --budget 50 --stepping a0 --targets 'bucket_string like 'a' and bucket_id like 'b''";
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });

        add(generateCommandButton);
        add(addTargetButton);

        add(clusterLabel);
        add(steppingLabel);
        add(budgetLabel);
        add(regressionsLabel);

        add(budgetTextField);
        add(clusterTextField);
        add(steppingTextField);
        add(regressionsTextField);
    }

    private void cleanScreen() {
        for (JTextField textField : textFields) {
            remove(textField);
        }
        textFields = new ArrayList<JTextField>();
        invalidate();
        repaint();
    }

    public static void main(String[] args) {
        MainWindow ex = new MainWindow();

        ex.setSize(1000, 1000);
        ex.setLayout(null);
        ex.setVisible(true);
    }
}
