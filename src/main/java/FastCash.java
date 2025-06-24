import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FastCash extends JFrame implements ActionListener {

    JButton deposit, withdrawl, miniStatement, pinChange, fastCash, balanceEnquiry, exit;
    String pinNumber;

    public FastCash(String pinNumber){
        this.pinNumber = pinNumber;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("SELECT WITHDRAWL AMOUNT");
        text.setBounds(220, 300, 700, 35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        deposit = new JButton("Rs 100");
        deposit.setBounds(170, 415, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl = new JButton("Rs 500");
        withdrawl.setBounds(355, 415, 150, 30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        fastCash = new JButton("Rs 1000");
        fastCash.setBounds(170, 450, 150, 30);
        fastCash.addActionListener(this);
        image.add(fastCash);

        miniStatement = new JButton("Rs 2000");
        miniStatement.setBounds(355, 450, 150, 30);
        miniStatement.addActionListener(this);
        image.add(miniStatement);

        pinChange = new JButton("Rs 5000");
        pinChange.setBounds(170, 485, 150, 30);
        pinChange.addActionListener(this);
        image.add(pinChange);

        balanceEnquiry = new JButton("Rs 10000");
        balanceEnquiry.setBounds(355, 485, 150, 30);
        balanceEnquiry.addActionListener(this);
        image.add(balanceEnquiry);

        exit = new JButton("BACK");
        exit.setBounds(355, 520, 150, 30);
        exit.addActionListener(this);
        image.add(exit);

        setSize(900, 900);
        setLocation(250, 0);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FastCash("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            setVisible(false);
            new Transactions(pinNumber).setVisible(true);
        } else {
            String amountStr = ((JButton)e.getSource()).getText().substring(3); // remove "Rs " prefix

            try {
                double amount = Double.parseDouble(amountStr); // use double for amounts

                DBConnection dbConnection = new DBConnection();
                Connection connection = dbConnection.getConnection();

                // Calculate current balance
                double balance = 0;
                ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM bank WHERE pin = '" + pinNumber + "'");
                while (rs.next()) {
                    String type = rs.getString("type");
                    double txnAmount = rs.getDouble("amount"); // directly get double
                    if (type.equalsIgnoreCase("Deposit")) {
                        balance += txnAmount;
                    } else {
                        balance -= txnAmount;
                    }
                }

                if (balance < amount) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                // Perform withdrawal
                String query = "INSERT INTO bank (pin, date, type, amount) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = connection.prepareStatement(query);

                java.util.Date date = new java.util.Date();

                stmt.setString(1, pinNumber);
                stmt.setDate(2, new java.sql.Date(date.getTime()));
                stmt.setString(3, "Withdrawal");
                stmt.setDouble(4, amount);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Rs " + amountStr + " Debited Successfully");

                setVisible(false);
                new Transactions(pinNumber).setVisible(true);

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Invalid amount format.");
                nfe.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
