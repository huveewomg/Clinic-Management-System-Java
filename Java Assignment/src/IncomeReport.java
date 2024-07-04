import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IncomeReport extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField ProfitField;
    private JTextField IncomeField;
    private JTextField PatientNumberField;
    private JTextField ExpenseField;

    public IncomeReport() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(550, 300, 800, 600);
        setTitle("Income Report");
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
        calcButton.addActionListener(e -> calculateProfitFromFields());
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
        importData("C:/Users/timot/Desktop/AndroidStudioProjects/GitHub/Java-Assignment/Java Assignment/incomeReport.txt");
    }

    private void importData(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            importData(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importData(List<String> lines) {
        PatientNumberField.setText(String.valueOf(lines.size()));
        double totalIncome = 0;
        for (String line : lines) {
            String[] parts = line.split(": ");
            if (parts.length > 1) {
                try {
                    double income = Double.parseDouble(parts[1]);
                    totalIncome += income;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        IncomeField.setText(String.format("%.2f", totalIncome));
    }

    private void calculateProfit(double income, double expenses) {
        double profit = income - expenses;
        ProfitField.setText(String.format("%.2f", profit));
      }
      
      private void calculateProfit(int income, int expenses) {
        double profit = income - expenses; 
        ProfitField.setText(String.format("%.0f", profit)); 
      }
      
      private void calculateProfitFromFields() {
        try {
          double income = Double.parseDouble(IncomeField.getText());
          double expenses = Double.parseDouble(ExpenseField.getText());
          
          if (income % 1 == 0 && expenses % 1 == 0) {
            calculateProfit((int) income, (int) expenses); 
          } else {
            calculateProfit(income, expenses);
          }
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(null, "Invalid input in fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }

	private void goBack() {
		this.dispose();
	}
}
