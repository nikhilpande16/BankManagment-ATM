import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pinTextField, rePinTextField;
    JButton change, back;
    String pinNumber;

    PinChange(String pinNumber) {
        this.pinNumber = pinNumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 280, 500, 35);
        image.add(text);

        JLabel pinText = new JLabel("NEW PIN");
        pinText.setForeground(Color.WHITE);
        pinText.setFont(new Font("System", Font.BOLD, 16));
        pinText.setBounds(165, 320, 180, 25);
        image.add(pinText);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(330, 320, 180, 25);
        pinTextField.setFont(new Font("Raleway", Font.BOLD, 25));
        image.add(pinTextField);

        JLabel rePin = new JLabel("Re-ENTER YOUR PIN");
        rePin.setForeground(Color.WHITE);
        rePin.setFont(new Font("System", Font.BOLD, 16));
        rePin.setBounds(165, 360, 180, 25);
        image.add(rePin);

        rePinTextField = new JPasswordField();
        rePinTextField.setBounds(330, 360, 180, 25);
        rePinTextField.setFont(new Font("Raleway", Font.BOLD, 25));
        image.add(rePinTextField);

        change = new JButton("CHANGE");
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("BACK");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == change) {
            try {
                String npin = pinTextField.getText().trim();
                String rePin = rePinTextField.getText().trim();

                if (!npin.equals(rePin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }

                if (npin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }

                DBConnection dbConnection = new DBConnection();
                Connection connection = dbConnection.getConnection();

                Statement stmt = connection.createStatement();
                stmt.executeUpdate("UPDATE bank SET pin = '" + rePin + "' WHERE pin = '" + pinNumber + "'");
                stmt.executeUpdate("UPDATE login SET pinno = '" + rePin + "' WHERE pinno = '" + pinNumber + "'");
                stmt.executeUpdate("UPDATE signupthree SET pinno = '" + rePin + "' WHERE pinno = '" + pinNumber + "'");

                JOptionPane.showMessageDialog(null, "PIN changed successfully");

                setVisible(false);
                new Transactions(rePin).setVisible(true); // use new pin for next screen

            } catch (Exception ae) {
                ae.printStackTrace();
                JOptionPane.showMessageDialog(null, "Something went wrong: " + ae.getMessage());
            }

        } else {
            setVisible(false);
            new Transactions(pinNumber).setVisible(true);
        }
    }
}
