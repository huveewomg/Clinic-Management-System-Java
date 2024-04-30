import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;

public class LoginForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameTXT;
	private JPasswordField PasswordTXT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(229, 37, 1, 1);
		panel.setLayout(null);
		panel.setBackground(Color.GRAY);
		contentPane.add(panel);
		
		JLabel LoginTitle = new JLabel("Welcome Back !");
		LoginTitle.setBounds(410, 37, 319, 55);
		LoginTitle.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(LoginTitle);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(152, 251, 152));
		panel_1.setBounds(0, 0, 330, 561);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setBounds(455, 243, 69, 14);
		contentPane.add(lblNewLabel);
		
		UsernameTXT = new JTextField();
		UsernameTXT.setColumns(10);
		UsernameTXT.setBounds(454, 268, 219, 28);
		contentPane.add(UsernameTXT);
		
		PasswordTXT = new JPasswordField();
		PasswordTXT.setToolTipText("Password");
		PasswordTXT.setBounds(454, 346, 219, 28);
		contentPane.add(PasswordTXT);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(455, 321, 69, 14);
		contentPane.add(lblPassword);
		
		Button LoginBtn = new Button("LOGIN");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkCredentials();
			}
		});
		LoginBtn.setForeground(Color.BLACK);
		LoginBtn.setBackground(Color.YELLOW);
		LoginBtn.setBounds(372, 441, 386, 48);
		LoginBtn.setFont(new Font("Arial", Font.PLAIN, 30));
		contentPane.add(LoginBtn);
		
		JLabel lblNewLabel_1 = new JLabel("DON'T HAVE AN ACCOUNT ?");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(372, 495, 200, 38);
		contentPane.add(lblNewLabel_1);
		
		Button ToRegisterBtn = new Button("SIGN UP NOW !");
		ToRegisterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterForm();
			}
		});
		ToRegisterBtn.setForeground(Color.WHITE);
		ToRegisterBtn.setBackground(Color.BLUE);
		ToRegisterBtn.setBounds(578, 495, 180, 38);
		contentPane.add(ToRegisterBtn);
	}
		
		// Functions starts here
		public void RegisterForm() {
			RegisterForm RegisterForm = new RegisterForm();
			RegisterForm.setVisible(true);
			dispose(); 
		}
		public String getRoleFromCredentials(String username) {
			String filepath = "Java Assignment\\credentials.txt";
			try {
				File file = new File(filepath);
				Scanner scanner = new Scanner(file);
				String role = null;

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (line.startsWith("Username:") && line.substring(10).trim().equals(username)) {
						while (scanner.hasNextLine()) {
							line = scanner.nextLine();
							if (line.startsWith("Role:")) {
								role = line.substring(6).trim();
//								debug purposes v
								System.out.println(role);
								if (role != null) {
									if (role.equalsIgnoreCase("Admin")) {
										return "Admin";
									} else if (role.equalsIgnoreCase("Patient")) {
										return "Patient";
									} else if (role.equalsIgnoreCase("Doctor")) {
										return "Doctor";
									} else {
										return "invalid";
									}
								}
								break;
							}
						}
					}
				}

				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}

		// Check Login Credentials
		public void checkCredentials() {
			String filepath = "Java Assignment\\credentials.txt";
			String usernameInput = UsernameTXT.getText();
			String passwordInput = PasswordTXT.getText();

			try {
				File file = new File(filepath);
				Scanner scanner = new Scanner(file);
				String username = null;
				String password = null;
				boolean found = false;

				if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Empty Fields", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Read each line and search for username and password
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (line.startsWith("Username:")) {
						username = line.substring(10).trim(); // Extract username
					} else if (line.startsWith("Password:")) {
						password = line.substring(10).trim(); // Extract password
					}

					// Check if both username and password are found
					if (username != null && password != null) {
						// Check if the found credentials match the input
						if (username.equals(usernameInput) && password.equals(passwordInput)) {
							found = true;
							break;
						} else {
							// Reset the found credentials and continue searching
							username = null;
							password = null;
						}
					}
				}

				scanner.close();

				if (found) {
					// Check the role
					String role = getRoleFromCredentials(usernameInput);
					if (role.equals("Patient")) {
						Homepage homepage = new Homepage(usernameInput);
						homepage.setVisible(true);
						dispose();
					} else if (role.equals("Admin")) {
						AdminHomepage AdminHomepage = new AdminHomepage(usernameInput);
						AdminHomepage.setVisible(true);
						dispose();
					}else if (role.equals("Doctor")) {
						DoctorHomepage DoctorHomepage = new DoctorHomepage(usernameInput);
						DoctorHomepage.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Invalid role.", "Invalid Role", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					// If failed, display a popup message
					JOptionPane.showMessageDialog(null, "Login failed. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
}

