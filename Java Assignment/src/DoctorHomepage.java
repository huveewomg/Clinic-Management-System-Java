
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

public class DoctorHomepage extends JFrame {
	
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
					DoctorHomepage frame = new DoctorHomepage(username);
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
	public DoctorHomepage(String username) {
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
		
		JButton btnAddItemTo = new JButton("Schedule");
		btnAddItemTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddItem();
			}
		});
		btnAddItemTo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnAddItemTo.setBounds(47, 161, 335, 70);
		contentPane.add(btnAddItemTo);
		
		JButton AppointmentBtn = new JButton("Appointment");
		AppointmentBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		AppointmentBtn.setBounds(47, 280, 335, 70);
		contentPane.add(AppointmentBtn);
		
		JButton btnCheckPatientRecord = new JButton("Patient Record");
		btnCheckPatientRecord.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnCheckPatientRecord.setBounds(47, 401, 335, 70);
		contentPane.add(btnCheckPatientRecord);
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
		
		public void AddItem() {
			ItemPage ItemPage = new ItemPage(username);
			ItemPage.setVisible(true);
			dispose();
		}
}
