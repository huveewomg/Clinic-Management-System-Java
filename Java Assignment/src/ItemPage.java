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
	private JTextField ItemNameTXT;
	private JTextField ItemQuantityTXT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemPage frame = new ItemPage(username);
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
	public ItemPage(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Item to Store");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel.setBounds(268, 11, 241, 81);
		contentPane.add(lblNewLabel);
		
		JLabel ItemNameLbl = new JLabel("Item Name:");
		ItemNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		ItemNameLbl.setBounds(69, 176, 187, 37);
		contentPane.add(ItemNameLbl);
		
		JLabel lblItemQuantity = new JLabel("Item Quantity: ");
		lblItemQuantity.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblItemQuantity.setBounds(69, 337, 187, 37);
		contentPane.add(lblItemQuantity);
		
		ItemNameTXT = new JTextField();
		ItemNameTXT.setBounds(316, 183, 308, 34);
		contentPane.add(ItemNameTXT);
		ItemNameTXT.setColumns(10);
		
		ItemQuantityTXT = new JTextField();
		ItemQuantityTXT.setColumns(10);
		ItemQuantityTXT.setBounds(316, 344, 308, 34);
		contentPane.add(ItemQuantityTXT);
		
		JButton backBtn = new JButton("Cancel");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homepage();
			}
		});
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		backBtn.setBounds(121, 480, 144, 70);
		contentPane.add(backBtn);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddItem();
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnSubmit.setBounds(480, 480, 144, 70);
		contentPane.add(btnSubmit);
	}
	
		public void Homepage() {
	        RecepHomepage RecepHomepage = new RecepHomepage(username);
	        RecepHomepage.setVisible(true);
	        dispose();
		}

		// get the last item id from the file
		public String getLastItemId() throws IOException{
			BufferedReader reader = new BufferedReader(new FileReader("items.txt"));
			String line;
			String lastItemId = "";
			while ((line = reader.readLine()) != null) {
				if (line.contains("ItemID: ")) {
					lastItemId = line.split(" ")[1];
				}
			}
			reader.close();
			return lastItemId;
		}
		
		public void AddItem() {
			String ItemName = ItemNameTXT.getText();
			String ItemQuantity = ItemQuantityTXT.getText();

			if (ItemName.equals("") || ItemQuantity.equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill in all fields!");
				return;
			}

			if (!ItemQuantity.matches("[0-9]+")) {
				JOptionPane.showMessageDialog(null, "Please enter a valid quantity!");
				return;
			}

			try {
				BufferedReader reader = new BufferedReader(new FileReader("items.txt"));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(",");
					if (parts[0].equals(ItemName)) {
						JOptionPane.showMessageDialog(null, "Item already exists!");
						reader.close();
						return;
					}
				}
				reader.close();

				String lastItemId = getLastItemId();
				String newItemId;
				if (lastItemId == null) {
					newItemId = "ID001"; // default ID if no ID was found
				} else {
					int idNumber = Integer.parseInt(lastItemId.substring(2)); // get the number part of the ID
					idNumber++; // increment the number
					newItemId = "ID" + String.format("%03d", idNumber); // create the new ID with leading zeros
				}

				FileWriter writer = new FileWriter("items.txt", true);
				writer.write("ItemID: " + newItemId + "\n");
				writer.write("ItemName: " + ItemName + "\n");
				writer.write("ItemQuantity: " + ItemQuantity + "\n");
				writer.write("\n");
				writer.close();
				JOptionPane.showMessageDialog(null, "Item added successfully!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
