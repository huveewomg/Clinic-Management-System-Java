import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHomepage extends JFrame {

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
					AdminHomepage frame = new AdminHomepage(username);
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
	public AdminHomepage(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Admin Page: "+ username);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblNewLabel.setBounds(10, 25, 541, 85);
		contentPane.add(lblNewLabel);
		
		JButton ChangePassBTN = new JButton("Change Password");
		ChangePassBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassForm();
			}
		});
		ChangePassBTN.setBounds(628, 75, 146, 23);
		contentPane.add(ChangePassBTN);
	}
	
	public void ChangePassForm() {
		ChangePass ChangePass = new ChangePass(username);
		ChangePass.setVisible(true);
		dispose();
	}
}
