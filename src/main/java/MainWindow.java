
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

    JPanel constantInfoArea, targetsArea;
    Button addTargetButton, addFilterButton;
    List<JTextField> filtersTextFields = new ArrayList<JTextField>();

    public MainWindow() {
        createConstantArea();
        createTargetArea();

        add(constantInfoArea);
        add(targetsArea);

        addTargetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFilterButton.setEnabled(true);
            }
        });
    }

    private void createConstantArea() {
        JLabel clusterTitle, steppingTitle, budgetTitle;
        JTextField cluster, stepping, budget;
        JLabel regressionsTitle;
        JTextArea regressions;

        constantInfoArea = new JPanel(new GridLayout(4, 1));

        JPanel titlesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 175, 10));
        clusterTitle = new JLabel("Cluster");
        steppingTitle = new JLabel("Stepping");
        budgetTitle = new JLabel("Budget");
        titlesPanel.add(clusterTitle);
        titlesPanel.add(steppingTitle);
        titlesPanel.add(budgetTitle);

        JPanel textFieldsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));
        cluster = new JTextField(10);
        stepping = new JTextField(10);
        budget = new JTextField(10);
        textFieldsPanel.add(cluster);
        textFieldsPanel.add(stepping);
        textFieldsPanel.add(budget);

        JPanel regressionsTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 175, 10));
        regressionsTitle = new JLabel("Regressions");
        regressionsTitlePanel.add(regressionsTitle);

        JPanel regressionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 175, 10));
        regressions = new JTextArea(2, 100);
        regressionsPanel.add(regressions);

        constantInfoArea.add(titlesPanel);
        constantInfoArea.add(textFieldsPanel);
        constantInfoArea.add(regressionsTitlePanel);
        constantInfoArea.add(regressionsPanel);
    }

    private void createTargetArea() {
        targetsArea = new JPanel(new GridLayout(4, 1));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        addTargetButton = new Button("Add Target");
        addFilterButton = new Button("Add filter");
        addFilterButton.setEnabled(false);
        buttonPanel.add(addTargetButton);
        buttonPanel.add(addFilterButton);

        JPanel filtersPanel = new JPanel(new GridLayout(1, 1));


        targetsArea.add(buttonPanel);
    }


    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setUndecorated(true);
        mainWindow.setLayout(new FlowLayout());
        mainWindow.setVisible(true);
    }
}
