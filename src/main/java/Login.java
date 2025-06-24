import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login(){

        setTitle("ATM Machine");

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        getContentPane().setBackground(Color.WHITE);

        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel cardNo = new JLabel("Card No");
        cardNo.setFont(new Font("Raleway", Font.BOLD, 28));
        cardNo.setBounds(120, 150, 150, 30);
        add(cardNo);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 250, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        JLabel pin = new JLabel("PIN");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 250, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(450, 300, 100, 30);
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 250, 30);
        signup.setBackground(Color.black);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        add(signup);

        setSize(800, 480);
        setVisible(true);
        setLocation(300, 100);
    }

    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == clear){
            cardTextField.setText("");
            pinTextField.setText("");

        } else if (e.getSource() == login) {

            DBConnection dbConnection = new DBConnection();

            String cardNumber = cardTextField.getText();
            String pinNumber = pinTextField.getText();
            String query = "SELECT * FROM login WHERE cardnumber = ? AND pinno =?";

            try{
                Connection connection = dbConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query);

                stmt.setString(1, cardNumber);
                stmt.setString(2, pinNumber);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()){
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    setVisible(false);
                    new Transactions(pinNumber).setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid card number or PIN");
                }

                rs.close();
                stmt.close();
                connection.close();

            }catch (Exception ex){
                System.out.println(ex);
            }
            
        } else if (e.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
            
        }

    }

    public static void main(String[] args) {
        new Login();
    }

}
