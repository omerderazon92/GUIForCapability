
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

    public MainWindow() {
        createConstantArea();
        createTargetArea();

        add(constantInfoArea);
        add(targetsArea);
    }

    private void createConstantArea() {
        constantInfoArea = new JPanel(new GridBagLayout());
        JLabel clusterTitle, steppingTitle, budgetTitle, regressionsTitle;
        JTextField cluster, stepping, budget;
        JTextArea regressions;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0 / 9.0;
        gbc.weighty = 1.0 / 7.0;

        //FIRST ROW
        clusterTitle = new JLabel("Cluster");
        gbc.gridx = 2;
        gbc.gridy = 0;
        constantInfoArea.add(clusterTitle, gbc);

        steppingTitle = new JLabel("Stepping");
        gbc.gridx = 4;
        gbc.gridy = 0;
        constantInfoArea.add(steppingTitle, gbc);

        budgetTitle = new JLabel("Budget");
        gbc.gridx = 6;
        gbc.gridy = 0;
        constantInfoArea.add(budgetTitle, gbc);


        //SECOND ROW
        cluster = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 1;
        constantInfoArea.add(cluster, gbc);

        stepping = new JTextField(10);
        gbc.gridx = 4;
        gbc.gridy = 1;
        constantInfoArea.add(stepping, gbc);

        budget = new JTextField(10);
        gbc.gridx = 6;
        gbc.gridy = 1;
        constantInfoArea.add(budget, gbc);

        //THIRD ROW
        regressionsTitle = new JLabel("Regressions");
        gbc.gridx = 4;
        gbc.gridy = 5;
        constantInfoArea.add(regressionsTitle, gbc);

        //FOURTH ROW
        regressions = new JTextArea(2, 40);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 6;
        constantInfoArea.add(regressions, gbc);
    }

    private void createTargetArea() {
        targetsArea = new JPanel(null);
        targetsArea.setBackground(Color.red);
    }


    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setUndecorated(true);
        mainWindow.setLayout(new GridLayout(2, 1));
        mainWindow.setVisible(true);
    }
}
