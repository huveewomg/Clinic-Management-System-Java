
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecepHomepage extends JFrame {
	
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
					RecepHomepage frame = new RecepHomepage(username);
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
	public RecepHomepage(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton SettingBtn = new JButton("Setting");
		SettingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingForm();
			}
		});
		SettingBtn.setBounds(605, 65, 146, 23);
		contentPane.add(SettingBtn);
		
		JLabel lblNewLabel = new JLabel("Welcome Back: " + username);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setBounds(97, 31, 457, 54);
		contentPane.add(lblNewLabel);
		
		JButton LogOutBtn = new JButton("Logout");
		LogOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logout();
			}
		});
		LogOutBtn.setBounds(605, 31, 146, 23);
		contentPane.add(LogOutBtn);
	}
	
		public void Logout() {
			LoginForm loginForm = new LoginForm();
			loginForm.setVisible(true);
			dispose();
			JOptionPane.showMessageDialog(null, "Logout Successfully Thank You For using The System!");
	
		}
		
		public void SettingForm() {
			SettingPage SettingPage = new SettingPage(username);
			SettingPage.setVisible(true);
			dispose();
		}
	
}
