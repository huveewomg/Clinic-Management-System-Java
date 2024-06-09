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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Choice;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class AppointmentForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private static String username;
	private JPanel contentPane;
	private JTable table;
	private JTextField NameTXT;
	private JTextField UsernameTXT;
	private JTextField searchField;
	private JComboBox<String> severityBox;
	private String keyword;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentForm frame = new AppointmentForm();
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
	public AppointmentForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Walk In Appointment");
		lblNewLabel.setBounds(24, 47, 331, 35);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(374, 133, 400, 406);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				UsernameTXT.setText(model.getValueAt(i, 0).toString());
				NameTXT.setText(model.getValueAt(i, 1).toString());
			}
		});
		table.setBackground(Color.PINK);
		model = new DefaultTableModel();
		Object[] column = { "Username", "Name" };
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = searchField.getText().toString();
				Search(keyword);
			}
		});
		btnNewButton.setBounds(685, 99, 89, 23);
		contentPane.add(btnNewButton);

		searchField = new JTextField();
		searchField.setBounds(495, 102, 180, 20);
		contentPane.add(searchField);
		searchField.setColumns(10);

		NameTXT = new JTextField();
		NameTXT.setColumns(10);
		NameTXT.setBounds(144, 295, 180, 23);
		contentPane.add(NameTXT);

		JButton btnAddToQueue = new JButton("Add To Queue");
		btnAddToQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToQueue();
			}
		});
		btnAddToQueue.setBounds(199, 486, 142, 23);
		contentPane.add(btnAddToQueue);

		JButton btnNewButton_1_2 = new JButton("Go Back");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homepage();
			}
		});
		btnNewButton_1_2.setBounds(24, 486, 142, 23);
		contentPane.add(btnNewButton_1_2);

		JLabel lblAppointment = new JLabel("Name  :");
		lblAppointment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAppointment.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAppointment.setBounds(10, 284, 117, 35);
		contentPane.add(lblAppointment);

		JLabel lblSeverity = new JLabel("Severity  :");
		lblSeverity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeverity.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSeverity.setBounds(10, 347, 117, 35);
		contentPane.add(lblSeverity);

		severityBox = new JComboBox<>();
		severityBox.setModel(new DefaultComboBoxModel(new String[] { "Minor", "Moderate", "Major", "Extreme" }));
		severityBox.setBounds(144, 358, 180, 22);
		contentPane.add(severityBox);

		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUsername.setBounds(-18, 227, 145, 35);
		contentPane.add(lblUsername);

		UsernameTXT = new JTextField();
		UsernameTXT.setColumns(10);
		UsernameTXT.setBounds(144, 238, 180, 23);
		contentPane.add(UsernameTXT);
	}

	public void Search(String username) {
		try (BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"))) {
			String line;
			List<String[]> data = new ArrayList<>();
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("Username: " + username)) {
					String[] currentRow = new String[2];
					currentRow[0] = line.split(": ")[1];
					currentRow[1] = line.split(": ")[1];
					data.add(currentRow);
				}
			}

			// Populate table
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0); // Clear existing data
			for (String[] row : data) {
				model.addRow(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "File Not Found: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void addToQueue() {
		try {
			username = UsernameTXT.getText();
			String name = NameTXT.getText();
			String severity = (String) severityBox.getSelectedItem();

			if (username.isEmpty() || name.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			FileWriter writer = new FileWriter("PatientQueue.txt", true);
			writer.write("Username: " + username + "\n");
			writer.write("Name: " + name + "\n");
			writer.write("Severity: " + severity + "\n");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			writer.write("Date: " + dtf.format(now) + "\n");
			writer.write("Status: Pending\n");
			writer.write("\n");
			writer.close();
			JOptionPane.showMessageDialog(this, "Added to queue successfully", "Success",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error writing to file: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void Homepage() {
		AdminHomepage AdminHomepage = new AdminHomepage(username);
		AdminHomepage.setVisible(true);
		dispose();
	}
}