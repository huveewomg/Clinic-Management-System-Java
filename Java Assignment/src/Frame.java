import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAlreadyHaveAn;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
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
	public Frame() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 296, 561);
		contentPane.add(panel);
		
		Button button = new Button("Create Account");
		button.setForeground(UIManager.getColor("TextPane.selectionForeground"));
		button.setBackground(UIManager.getColor("TextPane.selectionBackground"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(410, 459, 276, 48);
		contentPane.add(button);
		
		Button button_1 = new Button("Login");
		button_1.setBounds(561, 513, 125, 22);
		contentPane.add(button_1);
		
		txtAlreadyHaveAn = new JTextField();
		txtAlreadyHaveAn.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAlreadyHaveAn.setText("Already Have an Account ?");
		txtAlreadyHaveAn.setBounds(413, 515, 145, 20);
		contentPane.add(txtAlreadyHaveAn);
		txtAlreadyHaveAn.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		passwordField.setBounds(467, 237, 219, 28);
		contentPane.add(passwordField);
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setText("Username");
		txtpnUsername.setBounds(467, 208, 218, 22);
		contentPane.add(txtpnUsername);
	}
}
