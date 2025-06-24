import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {

    JTextField amount;
    JButton withdraw, back;
    String pinNumber;

    public Withdrawl(String pinNumber) {
        this.pinNumber = pinNumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(190, 300, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(175, 350, 320, 25);
        image.add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(355, 485, 150, 30);
        withdraw.addActionListener(this);
        image.add(withdraw);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(250, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdraw) {
            String number = amount.getText();
            Date date = new Date();

            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
            } else {
                try {
                    DBConnection dbConnection = new DBConnection();
                    Connection con = dbConnection.getConnection();
                    String query = "INSERT INTO bank (pin, date, type, amount) VALUES (?, ?, ?, ?)";

                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, pinNumber);
                    stmt.setDate(2, new java.sql.Date(date.getTime())); // proper conversion
                    stmt.setString(3, "Withdraw");
                    stmt.setDouble(4, Double.parseDouble(number));

                    int rows = stmt.executeUpdate();
                    if (rows > 0) {
                        JOptionPane.showMessageDialog(null, "Rs. " + number + " Deposited Successfully");
                        setVisible(false);
                        new Transactions(pinNumber).setVisible(true);
                    }

                    stmt.close();
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Transactions(pinNumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Withdrawl("");
    }
}
