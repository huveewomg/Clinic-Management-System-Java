import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHomepage extends JFrame {

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
					AdminHomepage frame = new AdminHomepage(username);
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
	public AdminHomepage(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Admin Page: "+ username);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblNewLabel.setBounds(10, 11, 541, 85);
		contentPane.add(lblNewLabel);
		
		JButton SettingBtn = new JButton("Setting");
		SettingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingForm();
			}
		});
		SettingBtn.setBounds(628, 75, 146, 23);
		contentPane.add(SettingBtn);
		
		JButton LogOutBtn = new JButton("Logout");
		LogOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logout();
			}
		});
		LogOutBtn.setBounds(628, 41, 146, 23);
		contentPane.add(LogOutBtn);
		
		JButton ReportBtn = new JButton("View Report");
		ReportBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		ReportBtn.setBounds(39, 154, 208, 70);
		contentPane.add(ReportBtn);
		
		JButton AddBtn = new JButton("Edit Staff (add or remove)");
		AddBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		AddBtn.setBounds(39, 356, 369, 70);
		contentPane.add(AddBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Admin UI");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(289, 79, 87, 61);
		contentPane.add(lblNewLabel_1);
	}
	
	public void SettingForm() {
		SettingPage SettingPage = new SettingPage(username);
		SettingPage.setVisible(true);
		dispose();
	}	
	
	public void Logout() {
		LoginForm loginForm = new LoginForm();
		loginForm.setVisible(true);
		dispose();
		JOptionPane.showMessageDialog(null, "Logout Successfully Thank You For using The System!");

	}
}
