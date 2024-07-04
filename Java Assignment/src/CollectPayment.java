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

	public CollectPayment() {
		setTitle("Collect Payment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 300, 800, 600);
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
		nameField.setEditable(false);
		nameField.setColumns(10);

		medicationField = new JTextField();
		medicationField.setColumns(10);
		medicationField.setBounds(146, 248, 146, 27);
		medicationField.setEditable(false);
		contentPane.add(medicationField);

		feeField = new JTextField();
		feeField.setColumns(10);
		feeField.setBounds(146, 329, 146, 27);
		feeField.setEditable(false);
		contentPane.add(feeField);

		statusField = new JTextField();
		statusField.setColumns(10);
		statusField.setBounds(146, 408, 146, 27);
		statusField.setEditable(false);
		contentPane.add(statusField);

		JButton btnNewButton = new JButton("Complete Payment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().isEmpty() || medicationField.getText().isEmpty() || feeField.getText().isEmpty()
						|| statusField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please select a record from the table.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					StoreData();
				}
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
		model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
		Object[] column = { "Name", "Medication", "Fees", "Status" };
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);

		ShowPendingList();
	}

	private void ShowPendingList() {
		try {
			File file = new File("C:/Users/timot/Desktop/AndroidStudioProjects/GitHub/Java-Assignment/Java Assignment/payment.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			String[] dataRow = new String[4];
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (line.trim().isEmpty()) {
					continue;
				}
				String[] parts = line.split(": ");
				if (parts.length > 1) {
					dataRow[i] = parts[1];
				}
				i++;
				if (i == 4) {
					model.addRow(dataRow);
					dataRow = new String[4];
					i = 0;
					// Skip emoty line
					br.readLine();
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void StoreData() {
		String filePath = "C:/Users/timot/Desktop/AndroidStudioProjects/GitHub/Java-Assignment/Java Assignment/credentials.txt";
		String incomeReportPath = "C:/Users/timot/Desktop/AndroidStudioProjects/GitHub/Java-Assignment/Java Assignment/incomeReport.txt";
		String name = nameField.getText();
		String fee = feeField.getText();

		int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to proceed?", "Confirmation",
				JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION) {
			try {
				List<String> lines = Files.readAllLines(Paths.get(filePath));
				int index = -1;
				for (int i = 0; i < lines.size(); i++) {
					if (lines.get(i).contains("Name: " + name)) {
						index = i;
						break;
					}
				}
				// remove line after found name
				if (index != -1) {
					for (int i = 0; i < 4 && index < lines.size(); i++) {
						lines.remove(index); 
					}
				}
				Files.write(Paths.get(filePath), lines);
				String incomeReport = name + ": " + fee + "\n";
				Files.write(Paths.get(incomeReportPath), incomeReport.getBytes(), StandardOpenOption.APPEND);
				JOptionPane.showMessageDialog(null, "Operation completed successfully.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				clearPayment(name);
				this.dispose();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void clearPayment(String name) {
		String filePath = "C:/Users/timot/Desktop/AndroidStudioProjects/GitHub/Java-Assignment/Java Assignment/payment.txt";
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));
			int index = -1;
			for (int i = 0; i < lines.size(); i++) {
				if (lines.get(i).contains("Name: " + name)) {
					index = i;
					break;
				}
			}
			if (index != -1) {
				for (int i = 0; i < 4 && index < lines.size(); i++) {
					lines.remove(index); // Remove the line
				}
			}
			Files.write(Paths.get(filePath), lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
