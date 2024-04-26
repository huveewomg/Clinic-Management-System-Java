import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class TodayAppointment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField doctorField;
	private JTextField patientField;
	private JTextField timeField;
	private JTextField detailField;
	private JTable table;

	DefaultTableModel model;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TodayAppointment frame = new TodayAppointment();
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
	public TodayAppointment() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Doctor :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(20, 172, 112, 25);
		contentPane.add(lblNewLabel_1);
		
		doctorField = new JTextField();
		doctorField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		doctorField.setEditable(false);
		doctorField.setColumns(10);
		doctorField.setBounds(142, 168, 168, 41);
		contentPane.add(doctorField);
		
		patientField = new JTextField();
		patientField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		patientField.setEditable(false);
		patientField.setColumns(10);
		patientField.setBounds(142, 243, 168, 55);
		contentPane.add(patientField);
		
		JLabel lblNewLabel_1_1 = new JLabel("PatientID :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(20, 254, 112, 25);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Time :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(54, 334, 78, 25);
		contentPane.add(lblNewLabel_1_1_1);
		
		timeField = new JTextField();
		timeField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		timeField.setEditable(false);
		timeField.setColumns(10);
		timeField.setBounds(142, 323, 168, 55);
		contentPane.add(timeField);
		
		detailField = new JTextField();
		detailField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		detailField.setEditable(false);
		detailField.setColumns(10);
		detailField.setBounds(142, 407, 168, 55);
		contentPane.add(detailField);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Detail :");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(54, 418, 78, 25);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel = new JLabel("Today's Appointment");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(39, 15, 296, 73);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(360, 52, 414, 498);
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

		showAppointment();
	}

	// fetch all the appointments for today from appointment.txt
	public void showAppointment() {
		try {
			File file = new File("appointment.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			Object[] tableLines = br.lines().toArray();
			for(int i = 0; i < tableLines.length; i++) {
				String line = tableLines[i].toString().trim();
				if (line.isEmpty()) {
					continue; // Skip this iteration if line is empty
				}
				String[] dataRow = line.split(",");
				model.addRow(dataRow);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
