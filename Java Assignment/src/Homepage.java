import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Homepage extends JFrame {

	private String username;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homepage frame = new Homepage(username);
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
	public Homepage(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome back " + username);
		lblNewLabel.setBounds(34, 11, 671, 62);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 51));
		contentPane.add(lblNewLabel);
		
		JButton LogoutBtn = new JButton("Logout");
		LogoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logout();
			}
		});
		LogoutBtn.setBounds(628, 34, 146, 23);
		contentPane.add(LogoutBtn);
		
		JButton AddBtn = new JButton("Add Item");
		AddBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		AddBtn.setBounds(34, 172, 208, 70);
		contentPane.add(AddBtn);
		
		JButton EditBtn = new JButton("Edit Item");
		EditBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		EditBtn.setBounds(425, 172, 208, 70);
		contentPane.add(EditBtn);
		
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnDeleteItem.setBounds(34, 344, 208, 70);
		contentPane.add(btnDeleteItem);
		
		JLabel lblNewLabel_1 = new JLabel("User UI");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(297, 87, 87, 61);
		contentPane.add(lblNewLabel_1);
		
		JButton ViewBtn = new JButton("View Item");
		ViewBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		ViewBtn.setBounds(425, 344, 208, 70);
		contentPane.add(ViewBtn);
		
		JButton ChangePassBTN = new JButton("Change Password");
		ChangePassBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassForm();
			}
		});
		ChangePassBTN.setBounds(628, 68, 146, 23);
		contentPane.add(ChangePassBTN);
	}
	
		public void Logout() {
			LoginForm loginForm = new LoginForm();
			loginForm.setVisible(true);
			dispose();
			JOptionPane.showMessageDialog(null, "Logout Successfully Thank You For using The System!");

		}
		
		public void ChangePassForm() {
			ChangePass ChangePass = new ChangePass(username);
			ChangePass.setVisible(true);
			dispose();
		}
}
