import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class MiniStatement extends JFrame {

    MiniStatement(String pinNumber){
        setTitle("Mini Statement");
        setLayout(null);

        JLabel bank = new JLabel("Indian Bank");
        bank.setFont(new Font("System", Font.BOLD, 20));
        bank.setBounds(150, 20, 200, 30);
        add(bank);

        JLabel card = new JLabel();
        card.setFont(new Font("System", Font.PLAIN, 14));
        card.setBounds(20, 60, 300, 20);
        add(card);

        JLabel balance = new JLabel();
        balance.setBounds(20, 400, 300, 20);
        add(balance);

        JTextArea mini = new JTextArea();
        mini.setEditable(false);
        mini.setFont(new Font("System", Font.PLAIN, 14));
        mini.setBounds(20, 100, 350, 400);
        add(mini);

        try {
            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.getConnection();

            // Get masked card number
            ResultSet rsCard = connection.createStatement().executeQuery("SELECT * FROM login WHERE pinno = '" + pinNumber + "' LIMIT 1");
            if (rsCard.next()) {
                String cardNum = rsCard.getString("cardnumber");
                if (cardNum.length() >= 16) {
                    card.setText("Card Number: " + cardNum.substring(0, 4) + "XXXXXXXX" + cardNum.substring(12));
                } else {
                    card.setText("Card Number: [Invalid Length]");
                }
            }

            // Get transactions
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM bank WHERE pin = '" + pinNumber + "'");
            StringBuilder statement = new StringBuilder();
            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");
                statement.append(date).append("    ").append(type).append("    Rs. ").append(amount).append("\n");
            }

            // Calculate current balance
            double bal = 0;
            ResultSet r = connection.createStatement().executeQuery("SELECT * FROM bank WHERE pin = '" + pinNumber + "'");
            while (r.next()) {
                String type = r.getString("type");
                double txnAmount = r.getDouble("amount"); // directly get double
                if (type.equalsIgnoreCase("Deposit")) {
                    bal += txnAmount;
                } else {
                    bal -= txnAmount;
                }
            }

            mini.setText(statement.toString());
            balance.setText("Your current account balance is Rs " + bal);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // JFrame settings
        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatement("1234"); // Replace with a valid pin for testing
    }
}
