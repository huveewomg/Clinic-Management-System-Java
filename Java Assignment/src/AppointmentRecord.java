import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class AppointmentRecord extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String username;
	private JTable table;
	private JTextField dateField;
	private JTextField statusField;
	private JTextField doctorField;
	private JTextPane remarkField;
	private JTextPane detailField;

	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentRecord frame = new AppointmentRecord(username);
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
	public AppointmentRecord(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 91, 442, 444);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				doctorField.setText(model.getValueAt(i, 0).toString());
				dateField.setText(model.getValueAt(i, 1).toString());
				detailField.setText(model.getValueAt(i, 2).toString());
				remarkField.setText(model.getValueAt(i, 3).toString());
				statusField.setText(model.getValueAt(i, 4).toString());
			}
		});
		table.setBackground(Color.WHITE);
		model= new DefaultTableModel();
		Object[] column = {"Doctor", "Date", "Detail", "Remark", "Status"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JLabel lblsAppointmentRecord = new JLabel( username + " Appointment Record");
		lblsAppointmentRecord.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblsAppointmentRecord.setBounds(20, 27, 671, 37);
		contentPane.add(lblsAppointmentRecord);
		
		JLabel lblNewLabel_1 = new JLabel("Date of Visit :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(468, 170, 115, 14);
		contentPane.add(lblNewLabel_1);
		
		dateField = new JTextField();
		dateField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dateField.setEditable(false);
		dateField.setColumns(10);
		dateField.setBounds(584, 162, 190, 37);
		contentPane.add(dateField);
		
		detailField = new JTextPane();
		detailField.setEditable(false);
		detailField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		detailField.setBounds(584, 222, 190, 77);
		contentPane.add(detailField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Detail :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(503, 245, 80, 29);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Remark :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(480, 367, 103, 29);
		contentPane.add(lblNewLabel_1_1_1);
		
		remarkField = new JTextPane();
		remarkField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		remarkField.setEditable(false);
		remarkField.setBounds(584, 337, 190, 83);
		contentPane.add(remarkField);
		
		statusField = new JTextField();
		statusField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		statusField.setEditable(false);
		statusField.setColumns(10);
		statusField.setBounds(584, 464, 190, 37);
		contentPane.add(statusField);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Status :");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_2.setBounds(503, 465, 71, 29);
		contentPane.add(lblNewLabel_1_1_2);
		
		doctorField = new JTextField();
		doctorField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		doctorField.setEditable(false);
		doctorField.setColumns(10);
		doctorField.setBounds(584, 87, 190, 37);
		contentPane.add(doctorField);
		
		JLabel lblNewLabel_1_2 = new JLabel("Doctor :");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(468, 95, 115, 14);
		contentPane.add(lblNewLabel_1_2);
		
		importAppointment(username);
	}
	
	public void importAppointment(String username) {
		try{
			String directoryPath = "Java Assignment\\Appointment\\";
			String filePath = directoryPath + username + ".txt";
			BufferedReader	reader = new BufferedReader(new FileReader(filePath));
			String line;
			List<String[]> list = new ArrayList<>();
			String[] data = new String[5];
			int i = 0;
			while((line = reader.readLine()) != null) {
				if(line.trim().isEmpty()) {
				list.add(data.clone());
					data = new String[5];
					i = 0;
				} else {
					String[] lineSplit = line.split(": ");
					if (lineSplit.length > 1) {
						data[i] = lineSplit[1];
						i++;
					}
				}
			}
			if (i > 0) {
			list.add(data.clone());
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (String[] row : list) {
				model.addRow(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
