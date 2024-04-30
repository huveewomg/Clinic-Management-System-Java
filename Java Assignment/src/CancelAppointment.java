import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.awt.event.ActionEvent;

public class CancelAppointment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField doctorField;
	private JTextField patientField;
	private JTextField timeField;
	private JTextField detailField;
	private JTable table;
	private static String username;

	DefaultTableModel model;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelAppointment frame = new CancelAppointment(username);
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
	public CancelAppointment(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cancel Appointment");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(164, 26, 325, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Doctor :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 155, 112, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PatientID :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 224, 112, 25);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Time :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(44, 290, 78, 25);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Detail :");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(44, 366, 78, 25);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		doctorField = new JTextField();
		doctorField.setEditable(false);
		doctorField.setBounds(132, 161, 125, 20);
		contentPane.add(doctorField);
		doctorField.setColumns(10);
		
		patientField = new JTextField();
		patientField.setEditable(false);
		patientField.setColumns(10);
		patientField.setBounds(132, 230, 125, 20);
		contentPane.add(patientField);
		
		timeField = new JTextField();
		timeField.setEditable(false);
		timeField.setColumns(10);
		timeField.setBounds(132, 296, 125, 20);
		contentPane.add(timeField);
		
		detailField = new JTextField();
		detailField.setEditable(false);
		detailField.setColumns(10);
		detailField.setBounds(132, 372, 125, 20);
		contentPane.add(detailField);
		
		JButton btnNewButton = new JButton("Cancel Appointment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelBooking();
			}
		});
		btnNewButton.setBounds(58, 478, 199, 50);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(347, 113, 427, 437);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				doctorField.setText(model.getValueAt(i, 0).toString());
				patientField.setText(model.getValueAt(i, 1).toString());
				timeField.setText(model.getValueAt(i, 2).toString());
				detailField.setText(model.getValueAt(i, 3).toString());
			}
		});
		table.setBackground(Color.WHITE);
		model= new DefaultTableModel();
		Object[] column = {"Doctor", "PatientID", "Time", "Detail"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);

		showAppointment(username);
	}

	public void showAppointment(String username) {
		String filePath = "Java Assignment\\Appointment.txt";
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));
			for (String line : lines) {
				String[] parts = line.split(",");
				if (parts.length > 1 && parts[1].trim().equals(username)) {
					model.addRow(parts);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cancelBooking(){
		String filePath = "Java Assignment\\Appointment.txt";
		String doctor = doctorField.getText();
		String patient = patientField.getText();
		String time = timeField.getText();
		String detail = detailField.getText();
		String newLine = doctor + "," + patient + "," + time + "," + detail;

		// Ask for confirmation before deleting
		int response = JOptionPane.showConfirmDialog(this,
				"Are you sure you want to cancel this booking?",
				"Confirm Cancel", JOptionPane.YES_NO_OPTION);
		if (response != JOptionPane.YES_OPTION) {
			return;
		}

		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));
			for (String line : lines) {
				if (line.equals(newLine)) {
					lines.remove(line);
					break;
				}
			}
			Files.write(Paths.get(filePath), lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.setRowCount(0);
		showAppointment(username);
	}
}
