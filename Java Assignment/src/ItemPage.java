import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ItemPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private String username;
	private JPanel contentPane;
	private JTextField PatientNameTXT;
	private JTextField PriceTXT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemPage frame = new ItemPage();
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
	public ItemPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payment");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel.setBounds(79, 11, 118, 81);
		contentPane.add(lblNewLabel);
		
		JLabel ItemNameLbl = new JLabel("Patient Name  :");
		ItemNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ItemNameLbl.setBounds(10, 151, 187, 37);
		contentPane.add(ItemNameLbl);
		
		PatientNameTXT = new JTextField();
		PatientNameTXT.setBounds(160, 156, 118, 34);
		contentPane.add(PatientNameTXT);
		PatientNameTXT.setColumns(10);
		
		JButton backBtn = new JButton("Cancel");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homepage();
			}
		});
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		backBtn.setBounds(10, 406, 118, 57);
		contentPane.add(backBtn);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRecord();
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSubmit.setBounds(149, 406, 125, 57);
		contentPane.add(btnSubmit);
		
		JLabel lblItemPrice = new JLabel("Price   :");
		lblItemPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblItemPrice.setBounds(20, 247, 81, 37);
		contentPane.add(lblItemPrice);
		
		PriceTXT = new JTextField();
		PriceTXT.setColumns(10);
		PriceTXT.setBounds(160, 247, 118, 34);
		contentPane.add(PriceTXT);
	}
	
		public void Homepage() {
	        DoctorHomepage DoctorHomepage = new DoctorHomepage(username);
	        DoctorHomepage.setVisible(true);
	        dispose();
		}

		// get the last item id from the file
		public String getLastItemId() throws IOException{
			BufferedReader reader = new BufferedReader(new FileReader("Payment.txt"));
			String line;
			String lastItemId = "";
			while ((line = reader.readLine()) != null) {
				if (line.contains("PaymentID: ")) {
					lastItemId = line.split(" ")[1];
				}
			}
			reader.close();
			return lastItemId;
		}

		public void AddRecord() {
			String PatientName = PatientNameTXT.getText();
			String Price = PriceTXT.getText();

			if (PatientName.isEmpty() || Price.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill in all fields!");
				return;
			}

			if (!Price.matches("[0-9]+")) {
				JOptionPane.showMessageDialog(null, "Please enter a valid number!");
				return;
			}

			try (BufferedReader reader = new BufferedReader(new FileReader("Payment.txt"))) {
				String line;
				String lastItemId = null;
				while ((line = reader.readLine()) != null) {
					if (line.startsWith("PaymentID: ")) {
						lastItemId = line.substring("PaymentID: ".length());
					}
				}

				String newItemId;
				if (lastItemId == null) {
					newItemId = "ID001"; // default ID if no ID was found
				} else {
					int idNumber = Integer.parseInt(lastItemId.substring(2)); // get the number part of the ID
					idNumber++; // increment the number
					newItemId = "ID" + String.format("%03d", idNumber); // create the new ID with leading zeros
				}

				try (FileWriter writer = new FileWriter("Payment.txt", true)) {
					writer.write("PaymentID: " + newItemId + "\n");
					writer.write("Patient Name: " + PatientName + "\n");
					writer.write("Price: " + Price + "\n");
					writer.write("\n");
					JOptionPane.showMessageDialog(null, "Item added successfully!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

