import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SettingPage extends JFrame {

	private String username;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingPage frame = new SettingPage(username);
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
	public SettingPage(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Setting Page");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 43));
		lblNewLabel.setBounds(259, 11, 284, 113);
		contentPane.add(lblNewLabel);
		
		JButton ChangeBtn = new JButton("Change Password");
		ChangeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassForm();
			}
		});
		ChangeBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ChangeBtn.setBounds(224, 184, 309, 64);
		contentPane.add(ChangeBtn);
		
		JButton DelBtn = new JButton("Delete Account");
		DelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteAcc();
			}
		});
		DelBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		DelBtn.setBounds(224, 362, 309, 64);
		contentPane.add(DelBtn);
		
		JButton HomepageBtn = new JButton("Return To Homepage");
		HomepageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homepage();
			}
		});
		HomepageBtn.setBounds(564, 69, 189, 23);
		contentPane.add(HomepageBtn);
	}
	
		public void ChangePassForm() {
			ChangePass ChangePass = new ChangePass(username);
			ChangePass.setVisible(true);
			dispose();
		}
		
		public void DeleteAcc() {
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				try {
					BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"));
					String line;
					boolean usernameFound = false;
					StringBuilder fileContent = new StringBuilder();
		            while ((line = reader.readLine()) != null) {
		                String[] data = line.split(":");
		                if (data[0].trim().equals("Username") && data[1].trim().equals(username)) {
		                    // Skip lines associated with the username to be deleted
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
						JOptionPane.showMessageDialog(null, "Account deleted successfully.");
						LoginForm LoginForm = new LoginForm();
						LoginForm.setVisible(true);
						dispose();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Account deletion cancelled.");
			}
		}
//		return to homepage based on user roles
		public void Homepage() {
			if (username.contains("admin")) {
				AdminHomepage AdminHomepage = new AdminHomepage(username);
				AdminHomepage.setVisible(true);
				dispose();
				return;
			}
			else {
				Homepage Homepage = new Homepage(username);
				Homepage.setVisible(true);
				dispose();
			}
		}
		
}
