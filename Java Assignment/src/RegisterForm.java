import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Label;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.io.BufferedReader;
import java.io.FileReader;

public class RegisterForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameTXT;
	private JPasswordField PasswordTXT;
	private JPasswordField CpasswordTXT;
	private JTextField EmailTXT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForm frame = new RegisterForm();
					frame.setUndecorated(true);
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
	public RegisterForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 279, 656);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(RegisterForm.class.getResource("/images/Capture.PNG")));
		lblNewLabel.setBounds(0, 0, 279, 421);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Register Now !");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 48));
		lblNewLabel_1.setBounds(371, 9, 319, 112);
		contentPane.add(lblNewLabel_1);

		JLabel uname = new JLabel("USERNAME");
		uname.setBounds(428, 132, 69, 14);
		contentPane.add(uname);

		UsernameTXT = new JTextField();
		UsernameTXT.setColumns(10);
		UsernameTXT.setBounds(427, 157, 219, 28);
		contentPane.add(UsernameTXT);

		JLabel Email = new JLabel("Email");
		Email.setBounds(428, 210, 69, 14);
		contentPane.add(Email);

		PasswordTXT = new JPasswordField();
		PasswordTXT.setToolTipText("Password");
		PasswordTXT.setBounds(428, 317, 219, 28);
		contentPane.add(PasswordTXT);

		JLabel Password = new JLabel("PASSWORD");
		Password.setBounds(429, 292, 69, 14);
		contentPane.add(Password);

		CpasswordTXT = new JPasswordField();
		CpasswordTXT.setToolTipText("Password");
		CpasswordTXT.setBounds(427, 394, 219, 28);
		contentPane.add(CpasswordTXT);

		JLabel Cpassword = new JLabel("CONFIRM PASSWORD");
		Cpassword.setBounds(428, 369, 156, 14);
		contentPane.add(Cpassword);

		Button RegisterBtn = new Button("SIGN UP");
		RegisterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storeCredentials();
			}
		});
		RegisterBtn.setForeground(Color.WHITE);
		RegisterBtn.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		RegisterBtn.setBounds(359, 446, 386, 48);
		contentPane.add(RegisterBtn);

		JLabel lblNewLabel_1_1 = new JLabel("ALREADY HAVE AN ACCOUNT ?");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setBounds(359, 500, 200, 38);
		contentPane.add(lblNewLabel_1_1);

		Button ToLoginBtn = new Button("PROCEED TO LOGIN");
		ToLoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginForm();
			}
		});
		ToLoginBtn.setForeground(Color.WHITE);
		ToLoginBtn.setBackground(Color.BLUE);
		ToLoginBtn.setBounds(565, 500, 180, 38);
		contentPane.add(ToLoginBtn);

		EmailTXT = new JTextField();
		EmailTXT.setColumns(10);
		EmailTXT.setBounds(428, 235, 219, 28);
		contentPane.add(EmailTXT);

	}

	public void storeCredentials() {
		try {
			String username = UsernameTXT.getText();
			String email = EmailTXT.getText();
			String password = new String(PasswordTXT.getPassword());
			String confirmPassword = new String(CpasswordTXT.getPassword());
			String role = "Patient";

			if (username.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill in all fields!");
				return;
			}

			if (email.indexOf('@') == -1) {
				JOptionPane.showMessageDialog(null, "Invalid email address!");
				return;
			}

			if (!password.equals(confirmPassword)) {
				JOptionPane.showMessageDialog(null, "Passwords do not match!");
				return;
			}

			if (username.contains("admin")) {
				JOptionPane.showMessageDialog(null, "Invalid username! Please choose another username.");
				return;
			}

			BufferedReader reader = new BufferedReader(new FileReader("Java Assignment\\credentials.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains("Username: " + username)) {
					JOptionPane.showMessageDialog(null, "Username already exists!");
					reader.close();
					return;
				}
			}
			reader.close();

			//Write new line to file
			BufferedReader newReader = new BufferedReader(new FileReader("Java Assignment\\credentials.txt"));
			FileWriter writer = new FileWriter("Java Assignment\\credentials.txt", true);

			if (newReader.readLine() != null) {
				System.out.println("File is not empty");
			} else {
				System.out.println("File is empty");
			}
			reader.close();


			writer.write("Username: " + username + "\n");
			writer.write("Email: " + email + "\n");
			writer.write("Password: " + password + "\n");
			writer.write("Role: " + role + "\n");

			writer.close();
			JOptionPane.showMessageDialog(null, "Account created successfully!");
			// Navigate to LoginForm
			LoginForm loginForm = new LoginForm();
			loginForm.setVisible(true);
			dispose(); // Close the current frame

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void LoginForm() {
		LoginForm LoginForm = new LoginForm();
		LoginForm.setVisible(true);
		dispose();
	}
}
