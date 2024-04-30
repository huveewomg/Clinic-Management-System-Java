import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.awt.event.ActionEvent;

public class CollectPayment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField medicationField;
	private JTextField feeField;
	private JTextField statusField;
	private JTable table;
	
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CollectPayment frame = new CollectPayment();
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
	public CollectPayment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 164, 131, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblMedication = new JLabel("Medication :");
		lblMedication.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMedication.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMedication.setBounds(10, 237, 131, 41);
		contentPane.add(lblMedication);
		
		JLabel lblFees = new JLabel("Fees :");
		lblFees.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFees.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFees.setBounds(10, 318, 131, 41);
		contentPane.add(lblFees);
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStatus.setBounds(10, 397, 131, 41);
		contentPane.add(lblStatus);
		
		nameField = new JTextField();
		nameField.setBounds(146, 175, 146, 27);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		medicationField = new JTextField();
		medicationField.setColumns(10);
		medicationField.setBounds(146, 248, 146, 27);
		contentPane.add(medicationField);
		
		feeField = new JTextField();
		feeField.setColumns(10);
		feeField.setBounds(146, 329, 146, 27);
		contentPane.add(feeField);
		
		statusField = new JTextField();
		statusField.setColumns(10);
		statusField.setBounds(146, 408, 146, 27);
		contentPane.add(statusField);
		
		JButton btnNewButton = new JButton("Complete Payment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StoreData();
			}
		});
		btnNewButton.setBounds(102, 484, 146, 47);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Collect Payment");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(46, 44, 246, 97);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 11, 445, 539);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				nameField.setText(model.getValueAt(i, 0).toString());
				medicationField.setText(model.getValueAt(i, 1).toString());
				feeField.setText(model.getValueAt(i, 2).toString());
				statusField.setText(model.getValueAt(i, 3).toString());
			}
		});
		table.setBackground(Color.WHITE);
		model= new DefaultTableModel();
		Object[] column = {"Name", "Medication", "Fees", "Status"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		ShowPendingList();
	}

	// fetch from payment.txt and plot into table
	public void ShowPendingList() {
		try {
			File file = new File("Java Assignment\\\\payment.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			String[] dataRow = new String[4];
			int i = 0;
			while ((line = br.readLine()) != null) {
				// Ignore blank lines
				if (line.trim().isEmpty()) {
					continue;
				}
				// Split the line into name and value, and store the value in dataRow
				String[] parts = line.split(": ");
				if (parts.length > 1) {
					dataRow[i] = parts[1];
				}
				i++;
				// If we've read 5 lines (one record plus one empty line), reset i and dataRow
				if (i == 4) {
					model.addRow(dataRow);
					dataRow = new String[4];
					i = 0;
					// Skip the next line (the empty line)
					br.readLine();
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void StoreData() {
		String filePath = "Java Assignment\\\\credentials.txt";
		String incomeReportPath = "Java Assignment\\\\incomeReport.txt";
		String name = nameField.getText();
		String fee = feeField.getText();

		int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION) {
			try {
				// Read the existing lines
				List<String> lines = Files.readAllLines(Paths.get(filePath));
				// Find the index of the line with the name
				int index = -1;
				for (int i = 0; i < lines.size(); i++) {
					if (lines.get(i).contains("Name: " + name)) {
						index = i;
						break;
					}
				}
				// If the line with the name was found, remove it and the next three lines
				if (index != -1) {
					for (int i = 0; i < 4 && index < lines.size(); i++) {
						lines.remove(index); // Remove the line
					}
				}
				// Write the lines back to the file
				Files.write(Paths.get(filePath), lines);

				// Write the name and fee to the incomeReport.txt file
				String incomeReport = name + ": " + fee + "\n";
				Files.write(Paths.get(incomeReportPath), incomeReport.getBytes(), StandardOpenOption.APPEND);

				// Show success message
				JOptionPane.showMessageDialog(null, "Operation completed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
