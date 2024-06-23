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

public class PatientHomepage extends BaseFrame implements UserHomepage {

	private static String username;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PatientHomepage(String username) {
		super(false);	
		this.username = username;
		initialize();
	}
		@Override
		protected void initialize() {
		setTitle(username + " Homepage");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		LogOutBtn.setBounds(628, 34, 146, 23);
		contentPane.add(LogOutBtn);
		
		JButton AddBtn = new JButton("Cancel Appointment");
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelAppointment();
			}
		});
		AddBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		AddBtn.setBounds(34, 172, 321, 70);
		contentPane.add(AddBtn);
		
		JButton EditBtn = new JButton("Make Appointment");
		EditBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MakeAppointment();
			}
		});
		EditBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		EditBtn.setBounds(34, 308, 321, 70);
		contentPane.add(EditBtn);
		
		
		JButton ViewBtn = new JButton("Track History");
		ViewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoryPage();
			}
		});
		ViewBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		ViewBtn.setBounds(34, 454, 321, 70);
		contentPane.add(ViewBtn);
		
		JButton SettingBtn = new JButton("Setting");
		SettingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingForm();
			}
		});
		SettingBtn.setBounds(628, 68, 146, 23);
		contentPane.add(SettingBtn);
	}
	
		private void Logout() {
			dispose();
			MainClass.Logout();
		}
		
		private void SettingForm() {
			MainClass.SettingForm(username);
		}
		
		private void HistoryPage() {
			HistoryPage HistoryPage = new HistoryPage(username);
			HistoryPage.setVisible(true);
		}
		
		private void MakeAppointment() {
			MakeAppointment MakeAppointment = new MakeAppointment(username);
			MakeAppointment.setVisible(true);
		}
		
		private void CancelAppointment() {
			CancelAppointment CancelAppointment = new CancelAppointment(username);
			CancelAppointment.setVisible(true);
		}

		@Override
		public void setVisible(boolean visible) {
			super.setVisible(visible);
		}
}
