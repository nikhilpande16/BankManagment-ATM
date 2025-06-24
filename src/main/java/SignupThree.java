import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

public class SignupThree extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2 , c3, c4, c5, c6, c7;
    JButton sumbit, cancel;
    String formno;

    SignupThree(String formno){
        this.formno = formno;
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel l1 = new JLabel("Page 3: Account Details");
        l1.setFont(new Font("Raleway", Font.PLAIN, 22));
        l1.setBounds(280, 40, 400, 40);
        add(l1);

        JLabel type = new JLabel("Account Type:");
        type.setFont(new Font("Raleway", Font.PLAIN, 22));
        type.setBounds(100, 100, 200, 30);
        add(type);

        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway", Font.PLAIN, 16));
        r1.setBackground(Color.white);
        r1.setBounds(100, 140, 250, 20);
        add(r1);

        r2 = new JRadioButton("Current Account");
        r2.setFont(new Font("Raleway", Font.PLAIN, 16));
        r2.setBackground(Color.WHITE);
        r2.setBounds(350, 140, 150, 20);
        add(r2);

        r3 = new JRadioButton("Fixed Deposit Account");
        r3.setFont(new Font("Raleway", Font.PLAIN, 16));
        r3.setBackground(Color.WHITE);
        r3.setBounds(100, 180, 250, 20);
        add(r3);

        r4 = new JRadioButton("Recurring Deposit Account");
        r4.setFont(new Font("Raleway", Font.PLAIN, 16));
        r4.setBackground(Color.WHITE);
        r4.setBounds(350, 180, 250, 20);
        add(r4);

        ButtonGroup groupAcount = new ButtonGroup();
        groupAcount.add(r1);
        groupAcount.add(r2);
        groupAcount.add(r3);
        groupAcount.add(r4);

        JLabel card = new JLabel("Card Number:");
        card.setFont(new Font("Raleway", Font.PLAIN, 22));
        card.setBounds(100, 250, 230, 30);
        add(card);

        JLabel number = new JLabel("XXXX-XXXX-XXXX-4184");
        number.setFont(new Font("Raleway", Font.PLAIN, 22));
        number.setBounds(330, 250, 300, 30);
        add(number);

        JLabel cardDetails = new JLabel("Your 16 Digit Card Number");
        cardDetails.setFont(new Font("Raleway", Font.PLAIN, 12));
        cardDetails.setBounds(100, 280, 300, 20);
        add(cardDetails);

        JLabel pin = new JLabel("Pin:");
        pin.setFont(new Font("Raleway", Font.PLAIN, 22));
        pin.setBounds(100, 330, 230, 30);
        add(pin);

        JLabel pinDetails = new JLabel("Your 4 Digit Pin");
        pinDetails.setFont(new Font("Raleway", Font.PLAIN, 12));
        pinDetails.setBounds(100, 360, 300, 20);
        add(pinDetails);

        JLabel pinNo = new JLabel("XXXX");
        pinNo.setFont(new Font("Raleway", Font.PLAIN, 22));
        pinNo.setBounds(330, 330, 300, 30);
        add(pinNo);

        JLabel services = new JLabel("Services Required:");
        services.setFont(new Font("Raleway", Font.PLAIN, 22));
        services.setBounds(100, 400, 200, 30);
        add(services);

        c1 = new JCheckBox("ATM CARD");
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway", Font.PLAIN, 16));
        c1.setBounds(100, 450, 200, 30);
        add(c1);

        c2 = new JCheckBox("Internate Banking");
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway", Font.PLAIN, 16));
        c2.setBounds(350, 450, 200, 30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway", Font.PLAIN, 16));
        c3.setBounds(100, 500, 200, 30);
        add(c3);

        c4 = new JCheckBox("EMAIl & SMS Alerts");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway", Font.PLAIN, 16));
        c4.setBounds(350, 500, 200, 30);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Raleway", Font.PLAIN, 16));
        c5.setBounds(100, 550, 200, 30);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setBackground(Color.WHITE);
        c6.setFont(new Font("Raleway", Font.PLAIN, 16));
        c6.setBounds(350, 550, 200, 30);
        add(c6);

        c7 = new JCheckBox("I hereby declare that the above entered details are correct to best of my knowledge.");
        c7.setBackground(Color.WHITE);
        c7.setFont(new Font("Raleway", Font.PLAIN, 12));
        c7.setBounds(100, 600, 600, 30);
        add(c7);

        sumbit = new JButton("Submit");
        sumbit.setBackground(Color.black);
        sumbit.setForeground(Color.white);
        sumbit.setBounds(250, 640, 100, 30);
        sumbit.setFont(new Font("Raleway", Font.PLAIN, 14));
        sumbit.addActionListener(this);
        add(sumbit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(420, 640, 100, 30);
        cancel.setFont(new Font("Raleway", Font.PLAIN, 14));
        cancel.addActionListener(this);
        add(cancel);

        setSize(850, 800);
        setLocation(250, 0);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SignupThree(" ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == sumbit){

            String accountType = null;
            if (r1.isSelected()){
                accountType = "Savings Account";
            } else if (r2.isSelected()) {
                accountType = "Current Account";
            } else if (r3.isSelected()) {
                accountType = "Fixed Deposit Account";
            } else if (r4.isSelected()) {
                accountType = "Recurring Deposit Account";
            }

            Random random = new Random();
            //to generate card number
            String cardNumber = "" + Math.abs((random.nextLong() % 90000000L) + 5040936000000000L);

            //to generate pin number
            String pinNumber = "" + Math.abs((random.nextLong() % 9000L) + 1000L);

            String facility = null;
            if (c1.isSelected()){
                facility = facility + " ATM Card";
            } else if (c2.isSelected()) {
                facility = facility + " Internate Banking";
            } else if (c3.isSelected()) {
                facility = facility + " Mobile Banking";
            } else if (c4.isSelected()) {
                facility = facility + " EMAIL & SMS Alerts";
            } else if (c5.isSelected()) {
                facility = facility + " Cheque Book";
            } else if (c6.isSelected()) {
                facility = facility + " E-Statement";
            }

            try {
                if (accountType.equals("")){
                    JOptionPane.showMessageDialog(null, "Account Type is Required");
                }else {
                    DBConnection dbConnection = new DBConnection();
                    Connection conn = dbConnection.getConnection();

                    if (conn != null){
                        String query1 = "INSERT INTO signupthree (formno, accountType, cardNumber, pinNo, facility) VALUES (?, ?, ?, ?, ?)";
                        String query2 = "INSERT INTO login (formno, cardNumber, pinNo) VALUES (?, ?, ?)";

                        PreparedStatement stmt1 = conn.prepareStatement(query1);
                        PreparedStatement stmt2 = conn.prepareStatement(query2);

                        stmt1.setString(1, formno);
                        stmt1.setString(2, accountType);
                        stmt1.setString(3, cardNumber);
                        stmt1.setString(4, pinNumber);
                        stmt1.setString(5, facility);

                        stmt2.setString(1, formno);
                        stmt2.setString(2, cardNumber);
                        stmt2.setString(3, pinNumber);

                        int rowsAffected = stmt1.executeUpdate();
                        int rowsAffected2 = stmt2.executeUpdate();
                        System.out.println(rowsAffected + " row(s) inserted.");
                        System.out.println(rowsAffected2 + " row(s) inserted.");
                        JOptionPane.showMessageDialog(null, "Details saved successfully");

                        stmt1.close();
                        stmt2.close();

                        conn.close();

                    }else {
                        System.out.println("Failed to establish database connection.");
                    }
                }

                JOptionPane.showMessageDialog(null, "Card Number: " + cardNumber + "\n Pin: " + pinNumber);

                setVisible(false);
                new Deposit(pinNumber).setVisible(true);
            } catch (Exception ex){
                ex.printStackTrace();
            }

        } else if (e.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }

    }
}
