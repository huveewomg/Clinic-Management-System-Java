import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistoryPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoryPage frame = new HistoryPage(username);
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
	public HistoryPage(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Medical Record");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedicalRecord(username);
			}
		});
		btnNewButton.setBounds(290, 101, 202, 70);
		contentPane.add(btnNewButton);
		
		JButton btnAppointment = new JButton("Appointment");
		btnAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppointmentRecord(username);
			}
		});
		btnAppointment.setBounds(290, 354, 202, 70);
		contentPane.add(btnAppointment);
	}
	
	public void MedicalRecord(String username) {
		MedicalRecord MedicalRecord = new MedicalRecord(username);
		MedicalRecord.setVisible(true);
	}
	
	public void AppointmentRecord(String username) {
		AppointmentRecord AppointmentRecord = new AppointmentRecord(username);
		AppointmentRecord.setVisible(true);
	}
}
