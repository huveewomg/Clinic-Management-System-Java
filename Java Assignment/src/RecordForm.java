import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.io.File;

public class RecordForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FeesField;
	private JTextPane IssueField;
	private JTextPane MedicationField; 
	private String PatientName;
	

	
	private 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecordForm frame = new RecordForm(PatientName);
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
	public RecordForm(String PatientName) {
		this.PatientName = "PatientName";
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Medical Record For " + PatientName);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(195, 22, 401, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Issue :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(90, 160, 154, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Medication :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(90, 253, 154, 39);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fees :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(90, 341, 154, 51);
		contentPane.add(lblNewLabel_1_1_1);
		
		IssueField = new JTextPane();
		IssueField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		IssueField.setBounds(293, 160, 291, 51);
		contentPane.add(IssueField);
		
		MedicationField = new JTextPane();
		MedicationField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MedicationField.setBounds(293, 255, 291, 51);
		contentPane.add(MedicationField);
		
		FeesField = new JTextField();
		FeesField.setBounds(293, 353, 291, 39);
		contentPane.add(FeesField);
		FeesField.setColumns(10);
		
		JButton btnNewButton = new JButton("Finish Consultation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WriteRecord(PatientName);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(246, 464, 291, 51);
		contentPane.add(btnNewButton);
	}
	
	public void WriteRecord(String PatientName) {
		try {
			String Issue = IssueField.getText();
			String Medication = MedicationField.getText();
			String Fees = FeesField.getText();
			
			if (Issue.isEmpty() || Medication.isEmpty() || Fees.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill all the fields");
				return;
			}

			FileWriter writer = new FileWriter("Payment.txt", true);
			writer.write("Name: "+ PatientName + "\n");
			writer.write("Medication: " + Medication + "\n");
			writer.write("Fees: " + Fees + "\n");
			writer.write("Status: Pending" + "\n");
			writer.write("\n");
			writer.close();

			String directoryPath = "PatientRecords/";
			File patientFile = new File(directoryPath + PatientName + ".txt");
			if (!patientFile.exists()) {
				try {
					patientFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error creating file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			FileWriter SecWriter = new FileWriter(directoryPath + PatientName + ".txt", true);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			SecWriter.write("Date: " + dtf.format(now) + "\n");
			SecWriter.write("Issue: " + Issue + "\n");
			SecWriter.write("Medication: " + Medication + "\n");
			SecWriter.write("Fees: " + Fees + "\n");
			SecWriter.write("\n");
			SecWriter.close();
			JOptionPane.showMessageDialog(null, "Consultation Finished");
			dispose();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error writing file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

//	public void RemoveFromQueue() {
//		
//	}
}
