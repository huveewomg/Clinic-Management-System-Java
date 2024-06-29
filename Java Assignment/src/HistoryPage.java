import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistoryPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String username = UserSession.getInstance().getUsername();

	public HistoryPage() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("History Page");
		setBounds(550, 300, 800, 600);
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
				AppointmentRecord();
			}
		});
		btnAppointment.setBounds(290, 354, 202, 70);
		contentPane.add(btnAppointment);
	}
	
	private void MedicalRecord(String username) {
		MedicalRecord MedicalRecord = new MedicalRecord(username);
		if (MedicalRecord.importRecord(username)){
			MedicalRecord.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "No Record Found");
		}
	}
	
	private void AppointmentRecord() {
		AppointmentRecord AppointmentRecord = new AppointmentRecord();
		if (AppointmentRecord.importAppointment()){
			AppointmentRecord.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "No Record Found");
		}
		AppointmentRecord.setVisible(true);
	}
}
