import Target.Target;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {

    JPanel constantInfoArea, targetsArea;
    Button addTargetButton, addFilterButton, generateCommand, resetTargetButton, resetAllButton;
    JTextField cluster, stepping, budget, regressions;

    JLabel numberOfTargetsLabel;

    JPanel filtersPanel;
    List<JTextField> filtersTextFieldsList = new ArrayList<JTextField>();

    JComboBox connectorsDrop;
    String[] connectors = {"OR", "AND"};

    List<Target> targets = new ArrayList<Target>();

    public MainWindow() {
        createConstantArea();
        createTargetArea();

        add(constantInfoArea);
        add(targetsArea);

        addTargetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFilterButton.setEnabled(true);
                connectorsDrop.setEnabled(true);
                generateCommand.setEnabled(true);

                if (!targets.isEmpty()) {
                    setCurrentTargetInfo();
                    cleanTargetPanel();
                    numberOfTargetsLabel.setText("Number Of Targets: " + targets.size());
                }
                addFilterTextField();
                targets.add(new Target());
            }
        });

        addFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFilterTextField();
            }
        });

        generateCommand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int budgetText = Integer.parseInt(budget.getText());
                    String clusterText = cluster.getText();
                    String steppingText = stepping.getText();
                    String regressiosText = regressions.getText();

                    if (clusterText.isEmpty() || steppingText.isEmpty() || regressiosText.isEmpty() || budgetText == 0) {
                        JOptionPane.showMessageDialog(null, "You have to fill cluster, stepping, regressions and budget :)");
                        return;
                    }

                    setCurrentTargetInfo();
                    cleanTargetPanel();
                    cleanConstantInfoPanel();
                    CommandsManager commandsManager = new CommandsManager(clusterText, steppingText, regressiosText, budgetText, targets);
                    String finalCommand = commandsManager.generateCommand();
                    copyCommandToClipboard(finalCommand);
                    JOptionPane.showMessageDialog(null, finalCommand);

                    deleteTargetsAndLockButtons();
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "The budget should be an integer :)");
                }
            }
        });

        resetTargetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanTargetPanel();
            }
        });

        resetAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanTargetPanel();
                cleanConstantInfoPanel();
                deleteTargetsAndLockButtons();
            }
        });
    }

    private void deleteTargetsAndLockButtons() {
        targets = new ArrayList<Target>();
        numberOfTargetsLabel.setText("Number Of Targets: " + targets.size());
        addFilterButton.setEnabled(false);
        connectorsDrop.setEnabled(false);
        generateCommand.setEnabled(false);
    }

    private void copyCommandToClipboard(String finalCommand) {
        StringSelection stringSelection = new StringSelection(finalCommand);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    private void cleanConstantInfoPanel() {
        cluster.setText("");
        regressions.setText("");
        budget.setText("");
        stepping.setText("");
    }

    private void addFilterTextField() {
        JTextField filterTextField = new JTextField("Filter", 40);
        filtersTextFieldsList.add(filterTextField);

        filtersPanel.add(filterTextField);
        targetsArea.revalidate();
        targetsArea.repaint();
    }

    private void cleanTargetPanel() {
        for (JTextField filterTextField : filtersTextFieldsList) {
            filtersPanel.remove(filterTextField);
        }
        filtersTextFieldsList = new ArrayList<JTextField>();
        targetsArea.revalidate();
        targetsArea.repaint();
    }

    private void setCurrentTargetInfo() {
        List<String> filters = new ArrayList<String>();
        Target target = targets.get(targets.size() - 1);
        for (JTextField filterTextField : filtersTextFieldsList) {
            filters.add(filterTextField.getText());
        }

        target.setConnector(connectorsDrop.getSelectedItem().toString());
        target.setFilter(filters);
    }

    private void createConstantArea() {
        JLabel clusterTitle, steppingTitle, budgetTitle;
        JLabel regressionsTitle;

        constantInfoArea = new JPanel(new GridLayout(5, 1));

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
        regressions = new JTextField(100);
        regressionsPanel.add(regressions);

        JPanel numberOfTargetsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 175, 10));
        numberOfTargetsLabel = new JLabel("Number Of Targets: " + targets.size());
        numberOfTargetsPanel.add(numberOfTargetsLabel);

        constantInfoArea.add(titlesPanel);
        constantInfoArea.add(textFieldsPanel);
        constantInfoArea.add(regressionsTitlePanel);
        constantInfoArea.add(regressionsPanel);
        constantInfoArea.add(numberOfTargetsPanel);
    }

    private void createTargetArea() {
        targetsArea = new JPanel(new GridLayout(1, 2));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        addTargetButton = new Button("Add Target");
        addFilterButton = new Button("Add Filter");
        generateCommand = new Button("Generate Command");
        resetTargetButton = new Button("Reset Target");
        resetAllButton = new Button("Reset All");
        connectorsDrop = new JComboBox(connectors);

        addFilterButton.setEnabled(false);
        connectorsDrop.setEnabled(false);
        generateCommand.setEnabled(false);
        buttonPanel.add(resetAllButton);
        buttonPanel.add(resetTargetButton);
        buttonPanel.add(addTargetButton);
        buttonPanel.add(addFilterButton);
        buttonPanel.add(connectorsDrop);
        buttonPanel.add(generateCommand);

        filtersPanel = new JPanel();
        filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.Y_AXIS));

        targetsArea.add(buttonPanel);
        targetsArea.add(filtersPanel);
    }


    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setUndecorated(true);
        mainWindow.setLayout(new FlowLayout());
        mainWindow.setVisible(true);
    }
}
