import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ItemPage extends JFrame {

	private static final long serialVersionUID = 1L;
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
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		backBtn.setBounds(121, 480, 144, 70);
		contentPane.add(backBtn);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnSubmit.setBounds(480, 480, 144, 70);
		contentPane.add(btnSubmit);
	}
}
