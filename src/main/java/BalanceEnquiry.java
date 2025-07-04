import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;
    String pinNumber;

    public BalanceEnquiry(String pinNumber){
        this.pinNumber = pinNumber;
        setLayout(null);

        // ATM background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Back button
        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        // Calculate and show balance
        try {
            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.getConnection();

            double balance = 0;
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM bank WHERE pin = '" + pinNumber + "'");
            while (rs.next()) {
                String type = rs.getString("type");
                double txnAmount = rs.getDouble("amount");
                if (type.equalsIgnoreCase("Deposit")) {
                    balance += txnAmount;
                } else {
                    balance -= txnAmount;
                }
            }

            JLabel text = new JLabel("Your Current Account Balance is Rs " + String.format("%.2f", balance));
            text.setForeground(Color.WHITE);
            text.setBounds(170, 300, 400, 30);
            text.setFont(new Font("System", Font.BOLD, 16));
            image.add(text);

        } catch (Exception ae) {
            ae.printStackTrace();
        }

        // Frame setup
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("1234"); // sample PIN for testing
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Transactions(pinNumber).setVisible(true);
    }
}
