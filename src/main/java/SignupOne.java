import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

public class SignupOne extends JFrame implements ActionListener {

    long random;
    JTextField nameTextField, fnameTextField, emailTextField, addressTextField, cityTextField, stateTextField, pinTextField;
    JButton next;
    JRadioButton male, female, married, unmarried;
    JDateChooser dateChooser;


    SignupOne(){
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        JLabel formno = new JLabel("APPLICATION FORM NO. " + random);
        formno.setFont(new Font("Raleway", Font.BOLD, 38));
        formno.setBounds(140, 20, 600, 40);
        add(formno);

        JLabel personalDetails = new JLabel("Page 1: Personal Details");
        personalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personalDetails.setBounds(290, 80, 400, 30);
        add(personalDetails);

        JLabel name = new JLabel("Name:");
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBounds(100, 140, 100, 30);
        add(name);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        nameTextField.setBounds(300, 140, 400,30);
        add(nameTextField);

        JLabel fatherName = new JLabel("Father's Name:");
        fatherName.setFont(new Font("Raleway", Font.BOLD, 20));
        fatherName.setBounds(100, 190, 200, 30);
        add(fatherName);

        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        fnameTextField.setBounds(300, 190, 400, 30);
        add(fnameTextField);

        JLabel dob = new JLabel("Date of Birth:");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100, 240, 200, 30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 240, 400, 30);
        add(dateChooser);

        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        gender.setBounds(100, 290, 200, 30);
        add(gender);

        // radio buttons
        male = new JRadioButton("Male");
        male.setBounds(300, 290, 60, 30);
        male.setBackground(Color.white);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(400, 290, 100, 30);
        female.setBackground(Color.white);
        add(female);

        // to make only one radio button selected at a time
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JLabel email = new JLabel("Email Address:");
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        email.setBounds(100, 340, 200, 30);
        add(email);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        emailTextField.setBounds(300, 340, 400, 30);
        add(emailTextField);

        JLabel marital = new JLabel("Marital Status:");
        marital.setFont(new Font("Raleway", Font.BOLD, 20));
        marital.setBounds(100, 390, 200, 30);
        add(marital);

        //radio button for maritial status
        married = new JRadioButton("Married");
        married.setBackground(Color.white);
        married.setBounds(300, 390, 100, 30);
        add(married);

        unmarried = new JRadioButton("UnMarried");
        unmarried.setBounds(400, 390, 120, 30);
        unmarried.setBackground(Color.white);
        add(unmarried);

        //to make one radio button work at a time
        ButtonGroup Mstatus = new ButtonGroup();
        Mstatus.add(married);
        Mstatus.add(unmarried);

        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        address.setBounds(100, 440, 400, 30);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        addressTextField.setBounds(300, 440, 400, 30);
        add(addressTextField);

        JLabel city = new JLabel("City:");
        city.setFont(new Font("Raleway", Font.BOLD, 20));
        city.setBounds(100, 490, 200, 30);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        cityTextField.setBounds(300, 490, 400, 30);
        add(cityTextField);

        JLabel state = new JLabel("State:");
        state.setFont(new Font("Raleway", Font.BOLD, 20));
        state.setBounds(100, 540, 200, 30);
        add(state);

        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        stateTextField.setBounds(300, 540, 400, 30);
        add(stateTextField);

        JLabel pin = new JLabel("Pincode:");
        pin.setFont(new Font("Raleway", Font.BOLD, 20));
        pin.setBounds(100, 590, 200, 30);
        add(pin);

        pinTextField = new JTextField();
        pinTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        pinTextField.setBounds(300, 590, 400, 30);
        add(pinTextField);

        next = new JButton("Next");
        next.setBounds(620, 660, 80, 30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.addActionListener(this);
        add(next);

        setSize(850, 800);
        setLocation(250,10);
        setVisible(true);

    }

    public static void main(String[] args) {
        new SignupOne();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String formno = " " + random;
        String name = nameTextField.getText();
        String fName = fnameTextField.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected()){
            gender = "male";
        } else if (female.isSelected()) {
            gender = "female";
        }
        String email = emailTextField.getText();
        String mStatus = null;
        if (married.isSelected()){
            mStatus = "married";
        } else if (unmarried.isSelected()) {
            mStatus = "unmarried";
        }

        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String pin = pinTextField.getText();

        try {
            if (name.equals("")){
                JOptionPane.showMessageDialog(null, "Name is Required");
            } else {
                DBConnection dbConnection = new DBConnection();
                Connection conn = dbConnection.getConnection();
                if (conn != null) {
                    String query = "INSERT INTO signup VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);

                    stmt.setString(1, formno);
                    stmt.setString(2, name);
                    stmt.setString(3, fName);
                    stmt.setString(4, dob);
                    stmt.setString(5, gender);
                    stmt.setString(6, email);
                    stmt.setString(7, mStatus);
                    stmt.setString(8, address);
                    stmt.setString(9, city);
                    stmt.setString(10, pin);
                    stmt.setString(11, state);

                    int rowsAffected = stmt.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted.");

                    stmt.close();
                    conn.close();

                    setVisible(false);
                    new SignupTwo(formno).setVisible(true);
                } else {
                    System.out.println("Failed to establish database connection.");
                }

            }
        } catch (Exception ex){
            System.out.println(ex);
        }

    }
}
