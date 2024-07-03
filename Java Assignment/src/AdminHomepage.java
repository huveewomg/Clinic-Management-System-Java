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

public class AdminHomepage extends BaseFrame implements UserHomepage{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchField;
	
	public AdminHomepage() {
		super(false);	
		initialize();
	}
		@Override
		protected void initialize() {
		String username = UserSession.getInstance().getUsername();
		setTitle("Admin Homepage");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Admin Page: "+ username);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setBounds(97, 31, 457, 54);
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
		
		JButton ReportBtn = new JButton("Collect Payment");
		ReportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CollectPayment();
			}
		});
		ReportBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		ReportBtn.setBounds(22, 425, 369, 70);
		contentPane.add(ReportBtn);
		
		JButton AddBtn = new JButton("Edit User");
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditStaff();
			}
		});
		AddBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		AddBtn.setBounds(22, 132, 369, 70);
		contentPane.add(AddBtn);
		
		
		JButton AppointmentBtn = new JButton("Appointment");
		AppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TodayAppointment();
			}
		});
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

		JButton IncomeReportBtn = new JButton("Report");
		IncomeReportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IncomeReport IncomeReport = new IncomeReport();
				IncomeReport.setVisible(true);
			}
		});
		IncomeReportBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		IncomeReportBtn.setBounds(620, 400, 150, 96);
		contentPane.add(IncomeReportBtn);
	}
	
	private void SettingForm() {
		MainClass.SettingForm();
	}	

	private void Logout() {
		dispose();
		MainClass.Logout();
	}
	
	private void EditStaff() {
		EditUser editstaff = new EditUser();
		editstaff.setVisible(true);
		dispose();
	}
	
	private void AppointmentForm() {
		AppointmentForm AppointmentForm = new AppointmentForm();
		AppointmentForm.setVisible(true);
		dispose();
	}

	private void MedicalRecord(String PatientName) {
		MedicalRecord MedicalRecord = new MedicalRecord(PatientName);
		if (MedicalRecord.importRecord(PatientName)){
			MedicalRecord.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "No Record Found");
		}
	}
	
	private void TodayAppointment() {
		TodayAppointment TodayAppointment = new TodayAppointment();
		TodayAppointment.setVisible(true);
	}
	
	private void CollectPayment() {
		CollectPayment CollectPayment = new CollectPayment();
		CollectPayment.setVisible(true);
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}
}
