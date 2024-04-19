import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    private JTextField NameTXT;

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
        lblNewLabel_1_1_1.setBounds(142, 375, 172, 32);
        contentPane.add(lblNewLabel_1_1_1);
        
        PasswordTXT = new JPasswordField();
        PasswordTXT.setBounds(494, 389, 201, 20);
        contentPane.add(PasswordTXT);
        
        JLabel lblNewLabel_2 = new JLabel("Exp: DrRamesh");
        lblNewLabel_2.setBounds(494, 262, 201, 14);
        contentPane.add(lblNewLabel_2);
        
        JButton AddStaffBtn = new JButton("Add Staff");
        AddStaffBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddStaff();
            }
        });
        AddStaffBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
        AddStaffBtn.setBounds(478, 461, 217, 70);
        contentPane.add(AddStaffBtn);
        
        JButton DeleteStaffBtn = new JButton("Delete User");
        DeleteStaffBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteStaff();
            }
        });
        DeleteStaffBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
        DeleteStaffBtn.setBounds(82, 461, 280, 70);
        contentPane.add(DeleteStaffBtn);
        
        RolesBox = new JComboBox<>();
        RolesBox.setModel(new DefaultComboBoxModel(new String[] {"Doctor", "Patient"}));
        RolesBox.setBounds(494, 152, 201, 22);
        contentPane.add(RolesBox);
        
        NameTXT = new JTextField();
        NameTXT.setColumns(10);
        NameTXT.setBounds(494, 314, 201, 20);
        contentPane.add(NameTXT);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Name         :");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel_1_1_2.setBounds(142, 300, 172, 32);
        contentPane.add(lblNewLabel_1_1_2);
    }
    
    public void Homepage() {
        AdminHomepage AdminHomepage = new AdminHomepage(username);
        AdminHomepage.setVisible(true);
        dispose();
    }
    
    public void DeleteStaff() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"));
                String line;
                String username = UsernameTXT.getText();
                boolean usernameFound = false;
                StringBuilder fileContent = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(":");
                    if (data[0].trim().equals("Username") && data[1].trim().equals(username)) {
                        for (int i = 0; i < 3; i++) {
                            reader.readLine();
                        }
                        usernameFound = true;
                    } else {
                        fileContent.append(line).append("\n");
                    }
                }
                reader.close();
                // debug purposes
                if (!usernameFound) {
                    JOptionPane.showMessageDialog(null, "Account not found.");
                } else {	
                    BufferedWriter writer = new BufferedWriter(new FileWriter("credentials.txt"));
                    writer.write(fileContent.toString());
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Staff Account deleted successfully.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Account deletion cancelled.");
        }
    }
    
    public void AddStaff() {
        try {
            String role = (String) RolesBox.getSelectedItem();
            String username = UsernameTXT.getText();
            String name = NameTXT.getText();
            String password = new String(PasswordTXT.getPassword());

            
            if (username.equals("") | password.equals("")| (name.equals("")) ) {
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
            writer.write("Name: " + name + "\n");
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
