import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;

public class IncomeReport extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ProfitField;
	private JTextField IncomeField;
	private JTextField PatientNumberField;
	private JTextField ExpenseField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IncomeReport frame = new IncomeReport();
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
	public IncomeReport() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Income Report");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(218, 23, 320, 44);
		contentPane.add(lblNewLabel);
		
		ProfitField = new JTextField();
		ProfitField.setEditable(false);
		ProfitField.setFont(new Font("Tahoma", Font.PLAIN, 39));
		ProfitField.setBounds(257, 387, 249, 44);
		contentPane.add(ProfitField);
		ProfitField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Estimate Profit This Month");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(29, 387, 218, 37);
		contentPane.add(lblNewLabel_1);
		
		IncomeField = new JTextField();
		IncomeField.setFont(new Font("Tahoma", Font.PLAIN, 39));
		IncomeField.setEditable(false);
		IncomeField.setColumns(10);
		IncomeField.setBounds(257, 159, 249, 44);
		contentPane.add(IncomeField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Income :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(29, 159, 218, 37);
		contentPane.add(lblNewLabel_1_1);
		
		PatientNumberField = new JTextField();
		PatientNumberField.setFont(new Font("Tahoma", Font.PLAIN, 39));
		PatientNumberField.setEditable(false);
		PatientNumberField.setColumns(10);
		PatientNumberField.setBounds(257, 223, 249, 44);
		contentPane.add(PatientNumberField);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Number of Patient :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(29, 230, 218, 37);
		contentPane.add(lblNewLabel_1_1_1);
		
		ExpenseField = new JTextField();
		ExpenseField.setFont(new Font("Tahoma", Font.PLAIN, 39));
		ExpenseField.setColumns(10);
		ExpenseField.setBounds(257, 298, 249, 44);
		contentPane.add(ExpenseField);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Expenses :");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1.setBounds(29, 305, 218, 37);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JButton calcButton = new JButton("Calculate");
		calcButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		calcButton.addActionListener(e -> calculateProfit());
		calcButton.setBounds(361, 486, 145, 44);
		contentPane.add(calcButton);
		
		JButton backButton = new JButton("Go Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		backButton.addActionListener(e -> goBack());
		backButton.setBounds(118, 486, 145, 44);
		contentPane.add(backButton);

		importData();
	}

	private void importData() {
		String filePath = "incomereport.txt";
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));
			PatientNumberField.setText(String.valueOf(lines.size()));
			double totalIncome = 0;
			for (String line : lines) {
				String[] parts = line.split(": ");
				if (parts.length > 1) {
					try {
						double income = Double.parseDouble(parts[1]);
						totalIncome += income;
					} catch (NumberFormatException e) {
					}
				}
			}
			IncomeField.setText(String.format("%.2f", totalIncome));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void calculateProfit() {
		double income = Double.parseDouble(IncomeField.getText());
		double expenses = Double.parseDouble(ExpenseField.getText());
		double profit = income - expenses;
		ProfitField.setText(String.format("%.2f", profit));
	}

	private void goBack() {
		this.dispose();
	}

}
