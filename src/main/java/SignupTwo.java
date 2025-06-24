
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SignupTwo extends JFrame implements ActionListener {

    JTextField categoryTextField, panTextField, aadharTextField;
    JButton next;
    JRadioButton Yes, No, eYes, eNo;
    JComboBox religion, income, education, occupation;
    String formno;


    SignupTwo(String formno){
        this.formno = formno;
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setTitle("NEW APPLICATION FORM - PAGE 2");

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);

        JLabel religionLable = new JLabel("Religion:");
        religionLable.setFont(new Font("Raleway", Font.PLAIN, 20));
        religionLable.setBounds(100, 140, 100, 30);
        add(religionLable);

        //Combobox
        String valReligion[] = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religion = new JComboBox(valReligion);
        religion.setBounds(300, 140, 400,30);
        religion.setBackground(Color.WHITE);
        add(religion);

        JLabel categoryLable = new JLabel("Category:");
        categoryLable.setFont(new Font("Raleway", Font.PLAIN, 20));
        categoryLable.setBounds(100, 190, 200, 30);
        add(categoryLable);

        categoryTextField = new JTextField();
        categoryTextField.setFont(new Font("Raleway",Font.PLAIN, 20));
        categoryTextField.setBounds(300, 190, 400, 30);
        add(categoryTextField);

        JLabel dob = new JLabel("Income:");
        dob.setFont(new Font("Raleway", Font.PLAIN, 20));
        dob.setBounds(100, 240, 200, 30);
        add(dob);

        String incomeCategory[] = {"--Select--", "<1,50,000", "<2,50,000", "<5,00000", "upto 10,00000"};
        income = new JComboBox(incomeCategory);
        income.setBounds(300, 240, 400,30);
        income.setBackground(Color.WHITE);
        add(income);

        JLabel educationLable = new JLabel("Educational:");
        educationLable.setFont(new Font("Raleway", Font.PLAIN, 20));
        educationLable.setBounds(100, 290, 200, 30);
        add(educationLable);

        JLabel qualificationLable = new JLabel("Qualification:");
        qualificationLable.setFont(new Font("Raleway", Font.PLAIN, 20));
        qualificationLable.setBounds(100, 315, 200, 30);
        add(qualificationLable);

        String educationValues[] = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Other"};
        education = new JComboBox(educationValues);
        education.setBounds(300, 315, 400,30);
        education.setBackground(Color.WHITE);
        add(education);

        JLabel occupationLable = new JLabel("Occupation:");
        occupationLable.setFont(new Font("Raleway", Font.PLAIN, 20));
        occupationLable.setBounds(100, 390, 200, 30);
        add(occupationLable);

        String occupationValues[] = {"Salaried", "Self-Employed", "Student", "Retired", "Other"};
        occupation = new JComboBox(occupationValues);
        occupation.setBounds(300, 390, 400,30);
        occupation.setBackground(Color.WHITE);
        add(occupation);

        JLabel panLable = new JLabel("PAN no:");
        panLable.setFont(new Font("Raleway", Font.PLAIN, 20));
        panLable.setBounds(100, 440, 400, 30);
        add(panLable);

        panTextField = new JTextField();
        panTextField.setFont(new Font("Raleway", Font.PLAIN, 20));
        panTextField.setBounds(300, 440, 400, 30);
        add(panTextField);

        JLabel aadharLable = new JLabel("Aadhar no:");
        aadharLable.setFont(new Font("Raleway", Font.PLAIN, 20));
        aadharLable.setBounds(100, 490, 200, 30);
        add(aadharLable);

        aadharTextField = new JTextField();
        aadharTextField.setFont(new Font("Raleway", Font.PLAIN, 20));
        aadharTextField.setBounds(300, 490, 400, 30);
        add(aadharTextField);

        JLabel seniorCitizenLable = new JLabel("Senior Citizen:");
        seniorCitizenLable.setFont(new Font("Raleway", Font.PLAIN, 20));
        seniorCitizenLable.setBounds(100, 540, 200, 30);
        add(seniorCitizenLable);

        //radio button for senior citizen
        Yes = new JRadioButton("Yes");
        Yes.setBackground(Color.white);
        Yes.setBounds(300, 540, 100, 30);
        add(Yes);

        No = new JRadioButton("No");
        No.setBounds(400, 540, 120, 30);
        No.setBackground(Color.white);
        add(No);

        //to make one radio button work at a time
        ButtonGroup seniorCitizen = new ButtonGroup();
        seniorCitizen.add(Yes);
        seniorCitizen.add(No);

        JLabel existingAccountLable = new JLabel("Existing Account:");
        existingAccountLable.setFont(new Font("Raleway", Font.PLAIN, 20));
        existingAccountLable.setBounds(100, 590, 200, 30);
        add(existingAccountLable);

        //radio button for existing account
        eYes = new JRadioButton("Yes");
        eYes.setBackground(Color.white);
        eYes.setBounds(300, 590, 100, 30);
        add(eYes);

        eNo = new JRadioButton("No");
        eNo.setBounds(400, 590, 120, 30);
        eNo.setBackground(Color.white);
        add(eNo);

        //to make one radio button work at a time
        ButtonGroup existingAccount = new ButtonGroup();
        existingAccount.add(eYes);
        existingAccount.add(eNo);

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
        new SignupTwo(" ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String category = categoryTextField.getText();
        String pan = panTextField.getText();
        String aadhar = aadharTextField.getText();

        String seniorCitizen = Yes.isSelected() ? "Yes" : No.isSelected() ? "No" : "";
        String existingAccount = eYes.isSelected() ? "Yes" : eNo.isSelected() ? "No" : "";

        String sReligion = (String) religion.getSelectedItem();
        String sIncome = (String) income.getSelectedItem();
        String sEducation = (String) education.getSelectedItem();
        String sOccupation = (String) occupation.getSelectedItem();

        try {
            if (aadhar.equals("")) {
                JOptionPane.showMessageDialog(null, "Aadhar No is Required");
            } else if (pan.equals("")) {
                JOptionPane.showMessageDialog(null, "PAN is required");
            } else {
                DBConnection dbConnection = new DBConnection();
                Connection conn = dbConnection.getConnection();

                if (conn != null) {
                    String query = "INSERT INTO signupTwo (formno, religion, category, income, education, occupation, pan, aadhar, senior_citizen, existing_account) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement stmt = conn.prepareStatement(query);

                    stmt.setString(1, formno);
                    stmt.setString(2, sReligion);
                    stmt.setString(3, category);
                    stmt.setString(4, sIncome);
                    stmt.setString(5, sEducation);
                    stmt.setString(6, sOccupation);
                    stmt.setString(7, pan);
                    stmt.setString(8, aadhar);
                    stmt.setString(9, seniorCitizen);
                    stmt.setString(10, existingAccount);

                    int rowsAffected = stmt.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted.");
                    JOptionPane.showMessageDialog(null, "Details saved successfully");

                    stmt.close();
                    conn.close();
                } else {
                    System.out.println("Failed to establish database connection.");
                }
            }

            //signup3 object

            setVisible(false);
            new SignupThree(formno).setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

