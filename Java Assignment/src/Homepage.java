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

public class Homepage extends JFrame {

	private static String username;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homepage frame = new Homepage(username);
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
	public Homepage(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome back " + username);
		lblNewLabel.setBounds(10, 5, 618, 116);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
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
		
		JLabel lblNewLabel_1 = new JLabel("Patient UI");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(297, 87, 87, 61);
		contentPane.add(lblNewLabel_1);
		
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
	
		public void Logout() {
			MainClass.Logout();
		}
		
		public void SettingForm() {
			MainClass.SettingForm(username);
		}
		
		public void HistoryPage() {
			HistoryPage HistoryPage = new HistoryPage(username);
			HistoryPage.setVisible(true);
		}
		
		public void MakeAppointment() {
			MakeAppointment MakeAppointment = new MakeAppointment(username);
			MakeAppointment.setVisible(true);
		}
		
		public void CancelAppointment() {
			CancelAppointment CancelAppointment = new CancelAppointment(username);
			CancelAppointment.setVisible(true);
		}
}
