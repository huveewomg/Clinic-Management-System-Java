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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

public class AppointmentEdit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField detailField;
	private JTable table;
	private JTextField dateField;
	private String username;

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
		lblNewLabel.setBounds(66, 11, 179, 80);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 171, 118, 39);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Details :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 315, 118, 39);
		contentPane.add(lblNewLabel_1_1);

		JButton btnCompleteAppointment = new JButton("Update Appointment\r\n");
		btnCompleteAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// updateAppointment(PatientName);
			}
		});
		btnCompleteAppointment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCompleteAppointment.setBounds(65, 493, 195, 23);
		contentPane.add(btnCompleteAppointment);

		JLabel lblNewLabel_1_1_1 = new JLabel("Status :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(24, 392, 104, 39);
		contentPane.add(lblNewLabel_1_1_1);

		usernameField = new JTextField();
		usernameField.setBounds(149, 181, 144, 33);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		usernameField.setEditable(false);

		detailField = new JTextField();
		detailField.setColumns(10);
		detailField.setBounds(149, 327, 144, 33);
		contentPane.add(detailField);
		detailField.setEditable(false);

		JComboBox<Object> comboBox = new JComboBox<>();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Cancelled", "Completed" }));
		comboBox.setBounds(148, 405, 144, 21);
		contentPane.add(comboBox);

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
		dateField.setBounds(149, 252, 144, 29);
		contentPane.add(dateField);

		JLabel lblNewLabel_1_2 = new JLabel("Date :");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 242, 118, 39);
		contentPane.add(lblNewLabel_1_2);

		AppointmentList(username, model);

	}

	// filter by doctor name
	public static void AppointmentList(String username, DefaultTableModel model) {
		try {
			String filePath = "Appointment.txt";
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			List<String[]> data = new ArrayList<>(); // List to store appointment data
			String[] currentRow = new String[3];

			// Create a regex pattern to match the doctor's name
			Pattern pattern = Pattern.compile("Doctor: " + username);

			boolean skipRow = true; // Flag to skip the row with doctor's name

			while ((line = reader.readLine()) != null) {
				if (skipRow) {
					Matcher matcher = pattern.matcher(line);
					if (matcher.find()) {
						skipRow = false; // Stop skipping rows
					}
				} else {
					// Split the line by colon to separate key and value
					String[] parts = line.split(": ", 2);
					if (parts.length == 2) { // Ensure there are two parts
						String key = parts[0].trim();
						String value = parts[1].trim();
						// Process only if the key is one of the expected fields (Username, Date, Remark)
						if (key.equals("Username")) {
							currentRow[0] = value;
						} else if (key.equals("Date")) {
							currentRow[1] = value;
						} else if (key.equals("Remark")) {
							currentRow[2] = value;
							// Add the current appointment data to the list
							data.add(currentRow.clone()); // Add a clone of the currentRow to avoid overwriting
							currentRow = new String[3]; // Clear currentRow for the next appointment
						}
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
}

