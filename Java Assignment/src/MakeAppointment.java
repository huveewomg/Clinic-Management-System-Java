import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.io.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;    

import javax.swing.table.DefaultTableModel;



public class MakeAppointment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String username;
	private JTable table;
	private JTextField doctorField;
	private JTextField patientField;
	private JTextField timeField;
	private JTextField detailsField;
	private JComboBox comboBox;
	private JButton searchBtn;
	private JButton submitBtn;
	private JTextField statusField;

	DefaultTableModel model;

	public MakeAppointment(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 300, 800, 600);
		setTitle("Book An Appointment");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book An Appointment ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel.setBounds(53, 11, 280, 64);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox();
		List<String> doctorUsernames = getDoctorUsernames("credentials.txt");
		comboBox.setModel(new DefaultComboBoxModel(doctorUsernames.toArray(new String[0])));
		comboBox.setBounds(548, 92, 140, 22);
		contentPane.add(comboBox);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchSlot(doctorUsernames);
				doctorField.setText(comboBox.getSelectedItem().toString());
			}
		});
		searchBtn.setBounds(704, 92, 76, 22);
		contentPane.add(searchBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(548, 126, 226, 411);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				timeField.setText(model.getValueAt(i, 0).toString());
				statusField.setText(model.getValueAt(i, 1).toString());
			}
		});
		table.setBackground(Color.WHITE);
		model= new DefaultTableModel();
		Object[] column = {"Time", "Status"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Doctor :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(10, 173, 165, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Patient Name :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setBounds(10, 242, 165, 30);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Time :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1.setBounds(10, 306, 165, 30);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Details :");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_1_1.setBounds(10, 427, 165, 30);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		doctorField = new JTextField();
		doctorField.setEditable(false);
		doctorField.setBounds(185, 173, 181, 33);
		contentPane.add(doctorField);
		doctorField.setColumns(10);
		
		patientField = new JTextField();
		patientField.setEditable(false);
		patientField.setText(username);
		patientField.setColumns(10);
		patientField.setBounds(185, 242, 181, 33);
		contentPane.add(patientField);
		
		timeField = new JTextField();
		timeField.setEditable(false);
		timeField.setColumns(10);
		timeField.setBounds(185, 306, 181, 33);
		contentPane.add(timeField);
		
		detailsField = new JTextField();
		detailsField.setColumns(10);
		detailsField.setBounds(185, 424, 181, 33);
		contentPane.add(detailsField);
		
		JButton submitBtn = new JButton("Make Appointment");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitAppointment();
			}
		});
		submitBtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		submitBtn.setBounds(53, 485, 259, 37);
		contentPane.add(submitBtn);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Status :");
		lblNewLabel_1_1_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_1_2.setBounds(10, 366, 165, 30);
		contentPane.add(lblNewLabel_1_1_1_2);
		
		statusField = new JTextField();
		statusField.setEditable(false);
		statusField.setColumns(10);
		statusField.setBounds(185, 366, 181, 33);
		contentPane.add(statusField);
		
	}

	public static List<String> getDoctorUsernames(String filePath) {
		List<String> doctorUsernames = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			String currentUsername = "";
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("Username: ")) {
					currentUsername = line.substring("Username: ".length()).trim();
				} else if (line.startsWith("Role: ")) {
					String role = line.substring("Role: ".length()).trim();
					if ("Doctor".equals(role)) {
						doctorUsernames.add(currentUsername);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doctorUsernames;
	}

	private void searchSlot(List<String> doctorUsernames) {
		String doctorUsername = comboBox.getSelectedItem().toString();
		String directoryPath = "Schedule\\";
		String filePath = directoryPath + doctorUsername + "Schedule.txt";
		File file = new File(filePath);
		if (file.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(filePath));
				String line;
				List<String[]> data = new ArrayList<>();
				while ((line = reader.readLine()) != null) {
					String[] lineSplit = line.split(",");
					String[] currentRow = new String[2];
					currentRow[0] = lineSplit[0]; // Time slot
					if (lineSplit.length > 1) {
						currentRow[1] = lineSplit[1]; // Status
					} else {
						currentRow[1] = ""; // Empty status
					}
					data.add(currentRow);
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				for (String[] row : data) {
					model.addRow(row);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0); 
		}
	}

    private void updateSchedule(String doctorName, String time, String status) {
        // Construct the file path
        String directoryPath = "Schedule\\";
        String filePath = directoryPath + doctorName + "Schedule.txt";

        // Create a list to store the updated schedule
        List<String> updatedSchedule = new ArrayList<>();

        try {
            // Read the existing schedule file
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            // Iterate through each line in the schedule
			for (String line : lines) {
				String updatedLine = line;
				if (line.contains(time)) {
					updatedLine += ",Booked";
				}
				updatedSchedule.add(updatedLine);
			}


            // Write the updated schedule back to the file
            Files.write(Paths.get(filePath), updatedSchedule);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	private void submitAppointment() {
		String doctorName = doctorField.getText();
		String patientName = patientField.getText();
		String time = timeField.getText();
		String details = detailsField.getText();
		String status = "Booked";
		String filePath = "Appointment.txt";
		String data = doctorName + "," + patientName + "," + time + "," + details + "\n"; // Removed extra newline

		try {
			// Check if status field is filled
			if (!statusField.getText().isEmpty()) {
				// Alert user to choose a different time
				JOptionPane.showMessageDialog(this,
						"Selected time slot is already booked. Please choose a different time.",
						"Time Slot Conflict", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// Write appointment to file
			Files.write(Paths.get(filePath), (data + "\n").getBytes(), StandardOpenOption.APPEND); // Added extra newline

			// Update schedule (assuming updateSchedule handles potential conflicts)
			updateSchedule(doctorName, time, status);

			// Display success message
			JOptionPane.showMessageDialog(this, "Appointment added successfully!");

			// Dispose of the current window
			this.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
