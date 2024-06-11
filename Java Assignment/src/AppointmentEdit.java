import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppointmentEdit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField detailField;
	private JTable table;
	private JTextField dateField;
	private static String username;
	private JTextField RemarkField;
	private JTextField doctorField;
	private JComboBox<String> StatusBox;

	public static DefaultTableModel model;
	List<String> data = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentEdit frame = new AppointmentEdit(username);
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
	public AppointmentEdit(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Edit Appointment");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(302, 0, 179, 80);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 135, 118, 39);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Details :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 279, 118, 39);
		contentPane.add(lblNewLabel_1_1);

		JButton btnCompleteAppointment = new JButton("Update Appointment\r\n");
		btnCompleteAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAppointment();
			}
		});
		btnCompleteAppointment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCompleteAppointment.setBounds(65, 493, 195, 23);
		contentPane.add(btnCompleteAppointment);

		JButton btnBack = new JButton("Go Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoctorHomepage doctorHomepage = new DoctorHomepage(username);
				doctorHomepage.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(65, 530, 195, 23);
		contentPane.add(btnBack);

		JLabel lblNewLabel_1_1_1 = new JLabel("Status :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(25, 413, 104, 39);
		contentPane.add(lblNewLabel_1_1_1);

		usernameField = new JTextField();
		usernameField.setEditable(false);
		usernameField.setBounds(149, 145, 144, 33);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		detailField = new JTextField();
		detailField.setEditable(false);
		detailField.setColumns(10);
		detailField.setBounds(149, 291, 144, 33);
		contentPane.add(detailField);

		StatusBox = new JComboBox<>();
		StatusBox.setModel(new DefaultComboBoxModel(new String[] { "Cancelled", "Completed" }));
		StatusBox.setBounds(149, 426, 144, 21);
		contentPane.add(StatusBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(334, 72, 442, 444);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				usernameField.setText(model.getValueAt(i, 0).toString());
				dateField.setText(model.getValueAt(i, 1).toString());
				detailField.setText(model.getValueAt(i, 2).toString());
			}
		});
		table.setBackground(Color.WHITE);
		model = new DefaultTableModel();
		Object[] column = { "Username", "Date", "Details" };
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);

		dateField = new JTextField();
		dateField.setEditable(false);
		dateField.setColumns(10);
		dateField.setBounds(149, 216, 144, 29);
		contentPane.add(dateField);

		JLabel lblNewLabel_1_2 = new JLabel("Date :");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 206, 118, 39);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_2 = new JLabel("Remark :");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_2.setBounds(10, 342, 118, 39);
		contentPane.add(lblNewLabel_1_1_2);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(148, 348, 144, 33);
		contentPane.add(scrollPane_1);

		RemarkField = new JTextField();
		scrollPane_1.setViewportView(RemarkField);
		RemarkField.setColumns(10);

		JLabel lblNewLabel_1_3 = new JLabel("Doctor :");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(10, 72, 118, 39);
		contentPane.add(lblNewLabel_1_3);

		doctorField = new JTextField();
		doctorField.setEditable(false);
		doctorField.setText(username);
		doctorField.setColumns(10);
		doctorField.setBounds(149, 89, 144, 33);
		contentPane.add(doctorField);

		AppointmentList(username, model);

	}

	// filter by doctor name
	public static void AppointmentList(String doctorName, DefaultTableModel model) {
		try {
			String filePath = "Appointment.txt";
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			List<String[]> data = new ArrayList<>(); // List to store appointment data

			// Create a regex pattern to match the doctor's name
			Pattern pattern = Pattern.compile(doctorName + ",");

			while ((line = reader.readLine()) != null) {
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					// Split the line by comma to separate doctor name, patient name, date, and
					// remark
					String[] parts = line.split(",", 4);
					if (parts.length == 4) { // Ensure there are four parts
						String patientName = parts[1].trim();
						String date = parts[2].trim();
						String remark = parts[3].trim();
						// Add the appointment data to the list
						data.add(new String[] { patientName, date, remark });
					}
				}
			}
			// Populate the table with the appointment data
			for (String[] row : data) {
				model.addRow(row);
			}
			reader.close(); // Close the reader
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// fetch from textfield and update the appointment if completed / cancelled
	// store in Appointment folder patientname.txt and remove from appointment.txt
	private void updateAppointment() {
		try {
			String Doctor = doctorField.getText();
			String PatientName = usernameField.getText();
			String Date = dateField.getText();
			String Details = detailField.getText();
			String Remark = RemarkField.getText();
			String Status = (String) StatusBox.getSelectedItem();
			if (Details.isEmpty() || Remark.isEmpty() || Status.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill all the fields");
				return;
			}
			// potential issue
			File folder = new File("Appointment");
			if (!folder.exists()) {
				folder.mkdir();
			}

			File appointmentFile = new File("Appointment/" + PatientName + ".txt");
			if (!appointmentFile.exists()) {
				try {
					appointmentFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error creating appointment file: " + e.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String currentDate = LocalDate.now().format(formatter);
			FileWriter writer = new FileWriter(appointmentFile, true);
			writer.write("Doctor: " + Doctor + "\n");
			writer.write("Date: " + currentDate + " " + dateField.getText() + "\n");
			writer.write("Details: " + detailField.getText() + "\n");
			writer.write("Remark: " + Remark + "\n");
			writer.write("Status: " + Status + "\n");
			writer.write("\n");
			writer.close();

			deleteFromAppointment(Doctor, PatientName, Date, Details);
			JOptionPane.showMessageDialog(null, "Appointment Updated");
			DoctorHomepage doctorHomepage = new DoctorHomepage(username);
			doctorHomepage.setVisible(true);
			dispose();

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error writing file: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// delete from appointment.txt
	private void deleteFromAppointment(String doctorName, String patientName, String date, String remark) {
		try {
			File appointmentFile = new File("Appointment.txt");

			String lineToRemove = doctorName + "," + patientName + "," + date + "," + remark;

			StringBuilder updatedContent = new StringBuilder();
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(appointmentFile));
			System.out.println(lineToRemove);

			while ((line = reader.readLine()) != null) {
				if (line.equals(lineToRemove)) {
					continue; // Skip line if it matches the lineToRemove
				}

				updatedContent.append(line).append(System.getProperty("line.separator"));
			}
			reader.close();
			System.out.println("Debug2");

			FileWriter writer = new FileWriter(appointmentFile);
			writer.write(updatedContent.toString());
			writer.close();
			System.out.println("Debug end");
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error deleting appointment: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
