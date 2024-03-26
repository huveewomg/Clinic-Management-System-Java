import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class EditStaff extends JFrame {

    private String username;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField UsernameTXT;
    private JPasswordField PasswordTXT;
    private JComboBox<String> RolesBox; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditStaff frame = new EditStaff(username);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public EditStaff(String username) {
        this.username = username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Edit Staff");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 41));
        lblNewLabel.setBounds(288, -1, 229, 107);
        contentPane.add(lblNewLabel);
        
        JButton HomepageBtn = new JButton("Return To Homepage");
        HomepageBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Homepage();
            }
        });
        HomepageBtn.setBounds(572, 53, 189, 23);
        contentPane.add(HomepageBtn);
        
        JLabel lblNewLabel_1 = new JLabel("Role           :");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel_1.setBounds(142, 139, 172, 32);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Username   :");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel_1_1.setBounds(142, 223, 172, 32);
        contentPane.add(lblNewLabel_1_1);
        
        UsernameTXT = new JTextField();
        UsernameTXT.setBounds(494, 237, 201, 20);
        contentPane.add(UsernameTXT);
        UsernameTXT.setColumns(10);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Password    :");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel_1_1_1.setBounds(142, 309, 172, 32);
        contentPane.add(lblNewLabel_1_1_1);
        
        PasswordTXT = new JPasswordField();
        PasswordTXT.setBounds(494, 323, 201, 20);
        contentPane.add(PasswordTXT);
        
        JLabel lblNewLabel_2 = new JLabel("Exp: Recp001");
        lblNewLabel_2.setBounds(494, 262, 201, 14);
        contentPane.add(lblNewLabel_2);
        
        JButton AddStaffBtn = new JButton("Add Staff");
        AddStaffBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddStaff();
            }
        });
        AddStaffBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
        AddStaffBtn.setBounds(478, 461, 201, 70);
        contentPane.add(AddStaffBtn);
        
        JButton DeleteStaffBtn = new JButton("Delete Staff WIP");
        DeleteStaffBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // DeleteStaff();
            }
        });
        DeleteStaffBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
        DeleteStaffBtn.setBounds(82, 461, 280, 70);
        contentPane.add(DeleteStaffBtn);
        
        RolesBox = new JComboBox<>();
        RolesBox.setModel(new DefaultComboBoxModel<>(new String[] {"receptionist", "Test Role 1 ", "Test Role 2"}));
        RolesBox.setBounds(494, 152, 201, 22);
        contentPane.add(RolesBox);
    }
    
    public void Homepage() {
        AdminHomepage AdminHomepage = new AdminHomepage(username);
        AdminHomepage.setVisible(true);
        dispose();
    }
    
    public void DeleteStaff() {
        //WIP
    }
    
    public void AddStaff() {
        try {
            String role = (String) RolesBox.getSelectedItem();
            String username = UsernameTXT.getText();
            String email = username + "@mail.com";
            String password = new String(PasswordTXT.getPassword());

            
            if (username.equals("") | password.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                return;
            }
            
            if (username.contains("admin")) {
                JOptionPane.showMessageDialog(null, "Invalid username! Please choose another username.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Username: " + username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists!");
                    reader.close();
                    return;
                }
            }
            reader.close();

            FileWriter writer = new FileWriter("credentials.txt", true);
            writer.write("Username: " + username + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Password: " + password + "\n");
            writer.write("Role: " + role + "\n");
            writer.write("\n");

            writer.close();
            JOptionPane.showMessageDialog(null, "Account created successfully!");
			Homepage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
