import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ChangePass extends JFrame {

	private static final long serialVersionUID = 1L;
	private String username;
	private JPanel contentPane;
	private JPasswordField OldPassBox;
	private JPasswordField NewPassBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePass frame = new ChangePass(username);
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
	public ChangePass(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel CurrentPassLabel = new JLabel("Current Password");
		CurrentPassLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		CurrentPassLabel.setBounds(27, 239, 277, 42);
		contentPane.add(CurrentPassLabel);

		JLabel PageTitle = new JLabel("Change Password");
		PageTitle.setBounds(212, 11, 359, 56);
		PageTitle.setFont(new Font("Tahoma", Font.PLAIN, 46));
		contentPane.add(PageTitle);

		JLabel NewPassLabel = new JLabel("New Password");
		NewPassLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		NewPassLabel.setBounds(27, 364, 277, 42);
		contentPane.add(NewPassLabel);

		OldPassBox = new JPasswordField();
		OldPassBox.setFont(new Font("Tahoma", Font.PLAIN, 26));
		OldPassBox.setBounds(317, 251, 359, 34);
		contentPane.add(OldPassBox);

		NewPassBox = new JPasswordField();
		NewPassBox.setFont(new Font("Tahoma", Font.PLAIN, 26));
		NewPassBox.setBounds(314, 372, 359, 34);
		contentPane.add(NewPassBox);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homepage();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBounds(154, 480, 144, 70);
		contentPane.add(btnNewButton);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ChangePass();
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnSubmit.setBounds(487, 480, 144, 70);
		contentPane.add(btnSubmit);
	}

	public void Homepage() {
		Homepage Homepage = new Homepage(username);
		Homepage.setVisible(true);
		dispose();
	}

	// change pass function
	public void ChangePass() {
		try {
			String username = this.username;
			String oldPass = OldPassBox.getText();
			String newPass = NewPassBox.getText();

			if (oldPass.equals("") || newPass.equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill in all fields");
			} else {
				BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"));
				String line;
				boolean usernameFound = false;
				boolean oldPassCorrect = false;
				StringBuilder fileContent = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					if (line.contains("Username: " + username)) {
						usernameFound = true;
						fileContent.append(line).append(System.lineSeparator());
					} else if (line.contains("Password: " + oldPass)) {
						oldPassCorrect = true;
						fileContent.append("Password: ").append(newPass).append(System.lineSeparator());
					} else {
						fileContent.append(line).append(System.lineSeparator());
					}
				}
				reader.close();

				if (!usernameFound) {
					JOptionPane.showMessageDialog(null, "Username not found! Please Contact Admin");
					return;
				}

				if (!oldPassCorrect) {
					JOptionPane.showMessageDialog(null, "Old password is incorrect!");
					return;
				}

				if (newPass.equals(OldPassBox.getText())) {
					JOptionPane.showMessageDialog(null, "New password can't be same as Old Password");
					return;
				}

				// Write the updated content back to the file
				BufferedWriter writer = new BufferedWriter(new FileWriter("credentials.txt"));
				writer.write(fileContent.toString());
				writer.close();

				JOptionPane.showMessageDialog(null, "Password changed successfully!");
				Homepage(); // Jump back to the homepage
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
