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
import javax.swing.JTextField;

public class AdminHomepage extends JFrame {

	private String username;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchField;
	

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
		
		JButton ReportBtn = new JButton("Payment / Report");
		ReportBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		ReportBtn.setBounds(22, 425, 369, 70);
		contentPane.add(ReportBtn);
		
		JButton AddBtn = new JButton("New User");
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditStaff();
			}
		});
		AddBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		AddBtn.setBounds(22, 132, 369, 70);
		contentPane.add(AddBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Admin UI");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(304, 75, 87, 61);
		contentPane.add(lblNewLabel_1);
		
		JButton AppointmentBtn = new JButton("Appointment");
		AppointmentBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		AppointmentBtn.setBounds(22, 224, 369, 70);
		contentPane.add(AppointmentBtn);
		
		JButton RecordBtn = new JButton("Medical Record");
		RecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String PatientName = searchField.getText();
				if (PatientName.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter Patient Name");
				}
				else
					MedicalRecord(PatientName);
			}
		});
		RecordBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		RecordBtn.setBounds(22, 321, 369, 70);
		contentPane.add(RecordBtn);
		
		JButton QueueBtn = new JButton("Queue");
		QueueBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		QueueBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppointmentForm();
			}
		});
		QueueBtn.setBounds(628, 193, 146, 96);
		contentPane.add(QueueBtn);
		
		searchField = new JTextField();
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		searchField.setBounds(403, 343, 181, 39);
		contentPane.add(searchField);
		searchField.setColumns(10);
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
	
	public void EditStaff() {
		EditUser editstaff = new EditUser(username);
		editstaff.setVisible(true);
		dispose();
	}
	
	public void AppointmentForm() {
		AppointmentForm AppointmentForm = new AppointmentForm(username);
		AppointmentForm.setVisible(true);
		dispose();
	}

	public void MedicalRecord(String PatientName) {
		MedicalRecord MedicalRecord = new MedicalRecord(PatientName);
		MedicalRecord.setVisible(true);
	}
	
}
