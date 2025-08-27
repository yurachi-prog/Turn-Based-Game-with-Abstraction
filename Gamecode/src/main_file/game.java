package main_file;

import javax.swing.*;
import java.awt.*;

public class game extends JFrame {

    private GameCharacter warrior;
    private GameCharacter mage;
    private JLabel warriorHpLabel;
    private JLabel mageHpLabel;
    private JTextArea warriorMessageArea;
    private JTextArea mageMessageArea;
    private JButton attackButton;
    private JButton specialButton;

    private GameCharacter currentAttacker;
    private GameCharacter currentDefender;

    public game() {
        setTitle("Turn-based Combat Game");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        warrior = new Warrior("Yohane");
        mage = new mage("Riko"); // Assuming Mage class (capital M)

        currentAttacker = warrior;
        currentDefender = mage;

        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        warriorHpLabel = new JLabel(warrior.getName() + " HP: " + warrior.getHp(), SwingConstants.CENTER);
        warriorHpLabel.setFont(new Font("Serif", Font.BOLD, 18));
        mageHpLabel = new JLabel(mage.getName() + " HP: " + mage.getHp(), SwingConstants.CENTER);
        mageHpLabel.setFont(new Font("Serif", Font.BOLD, 18));

        warriorMessageArea = new JTextArea(5, 20);
        warriorMessageArea.setEditable(false);
        warriorMessageArea.setLineWrap(true);
        warriorMessageArea.setWrapStyleWord(true);
        warriorMessageArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane warriorScroll = new JScrollPane(warriorMessageArea);

        mageMessageArea = new JTextArea(5, 20);
        mageMessageArea.setEditable(false);
        mageMessageArea.setLineWrap(true);
        mageMessageArea.setWrapStyleWord(true);
        mageMessageArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane mageScroll = new JScrollPane(mageMessageArea);

        topPanel.add(warriorHpLabel);
        topPanel.add(mageHpLabel);
        topPanel.add(warriorScroll);
        topPanel.add(mageScroll);

        add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        attackButton = new JButton("Attack");
        specialButton = new JButton("Special Move");
        buttonPanel.add(attackButton);
        buttonPanel.add(specialButton);

        add(buttonPanel, BorderLayout.SOUTH);

        attackButton.addActionListener(e -> handleMove(false));
        specialButton.addActionListener(e -> handleMove(true));

        updateStatus();
        warriorMessageArea.append("Game started! " + warrior.getName() + " ready.\n");
        mageMessageArea.append("Game started! " + mage.getName() + " ready.\n");
        appendToCurrentAttacker("It's your turn.\n");
    }

    private void handleMove(boolean special) {
        String actionMessage = special
                ? currentAttacker.specialMove(currentDefender)
                : currentAttacker.attack(currentDefender);

        appendToCurrentAttacker(actionMessage + "\n");
        updateStatus();
        appendToCurrentDefender(currentDefender.getName() + "'s HP is now: " + currentDefender.getHp() + "\n");

        if (!currentDefender.isAlive()) {
            Object[] options = {"Exit Now"};
            int choice = JOptionPane.showOptionDialog(this,
                    currentDefender.getName() + " has been defeated!\n" +
                    currentAttacker.getName() + " wins!",
                    "Game Over",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == 0) {
                System.exit(0);
            }
            return;
        }

        GameCharacter temp = currentAttacker;
        currentAttacker = currentDefender;
        currentDefender = temp;

        appendToCurrentAttacker("Now it's your turn.\n");
    }

    private void updateStatus() {
        warriorHpLabel.setText(warrior.getName() + " HP: " + warrior.getHp());
        mageHpLabel.setText(mage.getName() + " HP: " + mage.getHp());
    }

    private void appendToCurrentAttacker(String message) {
        (currentAttacker == warrior ? warriorMessageArea : mageMessageArea).append(message);
    }

    private void appendToCurrentDefender(String message) {
        (currentDefender == warrior ? warriorMessageArea : mageMessageArea).append(message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new game().setVisible(true));
    }
}
