import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class MedicalRecord extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField DateField;
	private JTextField FeeField;
	private JTextPane IssueField;
	private JTextPane MedicationField;
	private JTable table;
	private static String PatientName;

	DefaultTableModel model;

	public MedicalRecord(String PatientName) {
		this.PatientName = "PatientName";
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(PatientName + "s Medical Record");
		setBounds(550, 300, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(PatientName + "s Medical Record");
		lblNewLabel.setBounds(38, 35, 402, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 442, 444);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				DateField.setText(model.getValueAt(i, 0).toString());
				IssueField.setText(model.getValueAt(i, 1).toString());
				MedicationField.setText(model.getValueAt(i, 2).toString());
				FeeField.setText(model.getValueAt(i, 3).toString());
			}
		});
		table.setBackground(Color.WHITE);
		model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
		Object[] column = { "Date", "Issue", "Medication", "Fees" };
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);

		DateField = new JTextField();
		DateField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		DateField.setBounds(569, 153, 190, 37);
		contentPane.add(DateField);
		DateField.setColumns(10);
		DateField.setEditable(false);

		JLabel lblNewLabel_1 = new JLabel("Date of Visit :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(453, 161, 115, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Issue :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(488, 263, 80, 29);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Medication :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(465, 385, 103, 29);
		contentPane.add(lblNewLabel_1_1_1);

		MedicationField = new JTextPane();
		MedicationField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		MedicationField.setBounds(569, 355, 190, 83);
		contentPane.add(MedicationField);
		MedicationField.setEditable(false);

		IssueField = new JTextPane();
		IssueField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		IssueField.setBounds(569, 240, 190, 77);
		contentPane.add(IssueField);
		IssueField.setEditable(false);

		JLabel lblNewLabel_1_1_2 = new JLabel("Fees :");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_2.setBounds(502, 483, 57, 29);
		contentPane.add(lblNewLabel_1_1_2);

		FeeField = new JTextField();
		FeeField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		FeeField.setColumns(10);
		FeeField.setBounds(569, 482, 190, 37);
		contentPane.add(FeeField);
		FeeField.setEditable(false);

		importRecord(PatientName);

	}

	public boolean importRecord(String PatientName) {
		try {
			String directoryPath = "PatientRecords\\";
			String filePath = directoryPath + PatientName + ".txt";
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			List<String[]> data = new ArrayList<>();
			String[] currentRow = new String[5];
			int i = 0;
			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) {
					data.add(currentRow);
					currentRow = new String[5];
					i = 0;
				} else {
					String[] lineSplit = line.split(": ");
					if (lineSplit.length > 1) {
						currentRow[i] = lineSplit[1];
						i++;
					}
				}
			}
			// Add the last row if it wasn't added yet
			if (i > 0) {
				data.add(currentRow);
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (String[] row : data) {
				model.addRow(row);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(this, "File Not Found: " + e.getMessage(),
			// 		"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

}
