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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ChangePass extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField OldPassBox;
	private JPasswordField NewPassBox;

	public ChangePass() {
		String username = UserSession.getInstance().getUsername();
		setTitle("Change Password");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 300, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel CurrentPassLabel = new JLabel("Current Password");
		CurrentPassLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		CurrentPassLabel.setBounds(27, 239, 277, 42);
		contentPane.add(CurrentPassLabel);

		JLabel PageTitle = new JLabel("Change Password");
		PageTitle.setBounds(212, 11, 407, 56);
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

	public void Homepage(){
		dispose();
	}

	// change pass function
	private void ChangePass() {
		UserSession session = UserSession.getInstance();
		String username = session.getUsername();
		String currentPassword = session.getPassword();
		String oldPass = new String(OldPassBox.getPassword());
		String newPass = new String(NewPassBox.getPassword());

		if (oldPass.isEmpty() || newPass.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill in all fields", "Empty Fields", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!oldPass.equals(currentPassword)) {
			JOptionPane.showMessageDialog(this, "Old password is incorrect!", "Incorrect Password", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (newPass.equals(oldPass)) {
			JOptionPane.showMessageDialog(this, "New password can't be the same as the old password", "Invalid New Password", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (updatePasswordInFile(username, newPass)) {
			session.setPassword(newPass);
			JOptionPane.showMessageDialog(this, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			Homepage(); // Jump back to the homepage
		} else {
			JOptionPane.showMessageDialog(this, "Failed to update password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean updatePasswordInFile(String username, String newPass) {
		String filePath = "credentials.txt";
		List<String> fileContent = new ArrayList<>();
		boolean updated = false;

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("Username: " + username)) {
					fileContent.add(line);
					fileContent.add(reader.readLine()); // Name
					fileContent.add("Password: " + newPass);
					fileContent.add(reader.readLine()); // Role
					updated = true;
				} else {
					fileContent.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		if (updated) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
				for (String line : fileContent) {
					writer.write(line);
					writer.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		return updated;
	}
	
}
