import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class NewSchedule extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField doctorField;
	private JRadioButton Button_800;
	private JRadioButton Button_815;
	private JRadioButton Button_830;
	private JRadioButton Button_845;
	private JRadioButton Button_900;
	private JRadioButton Button_915;
	private JRadioButton Button_930;
	private JRadioButton Button_945;
	private JRadioButton Button_1000;
	private JRadioButton Button_1015;
	private JRadioButton Button_1030;
	private JRadioButton Button_1045;
	private JRadioButton Button_1100;
	private JRadioButton Button_1115;
	private JRadioButton Button_1130;
	private JRadioButton Button_1145;
	private JRadioButton Button_1200;
	private JRadioButton Button_1215;
	private JRadioButton Button_1230;
	private JRadioButton Button_1245;
	private JRadioButton Button_0100;
	private JRadioButton Button_0115;
	private JRadioButton Button_0130;
	private JRadioButton Button_0145;
	private JRadioButton Button_0200;
	private JRadioButton Button_0215;
	private JRadioButton Button_0230;
	private JRadioButton Button_0245;
	private JRadioButton Button_0300;
	private JRadioButton Button_0315;
	private JRadioButton Button_0330;
	private JRadioButton Button_0345;
	private JRadioButton Button_0400;
	private JRadioButton Button_0415;
	private JRadioButton Button_0430;
	private JRadioButton Button_0445;
	private JButton SubmitButton;
    private ArrayList<String> selectedTimes = new ArrayList<>();
    private JTextField textField;
	private static String username;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//        EventQueue.invokeLater(() -> new NewSchedule().setVisible(true));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewSchedule frame = new NewSchedule(username);
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
	public NewSchedule(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DoctorID :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(35, 121, 143, 38);
		contentPane.add(lblNewLabel);

		JButton ClearButton = new JButton("Clear Schedule");
		ClearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearSchedule(username);
			}
		});
		ClearButton.setBounds(500, 130, 130, 23);
		contentPane.add(ClearButton);
		
		JLabel lblTime = new JLabel("Time Open For Booking :");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTime.setBounds(35, 170, 222, 38);
		contentPane.add(lblTime);
		
		JLabel lblNewSchedule = new JLabel("New Schedule");
		lblNewSchedule.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewSchedule.setBounds(319, 23, 143, 57);
		contentPane.add(lblNewSchedule);
		
		Button_800 = new JRadioButton("8:00");
		Button_800.setBounds(207, 227, 109, 23);
		contentPane.add(Button_800);
		
		Button_830 = new JRadioButton("8:30");
		Button_830.setBounds(429, 227, 109, 23);
		contentPane.add(Button_830);
		
		Button_845 = new JRadioButton("8:45");
		Button_845.setBounds(540, 227, 109, 23);
		contentPane.add(Button_845);
		
		Button_815 = new JRadioButton("8:15");
		Button_815.setBounds(318, 227, 109, 23);
		contentPane.add(Button_815);
		
		Button_945 = new JRadioButton("9:45");
		Button_945.setBounds(540, 253, 109, 23);
		contentPane.add(Button_945);
		
		Button_900 = new JRadioButton("9:00");
		Button_900.setBounds(207, 253, 109, 23);
		contentPane.add(Button_900);
		
		Button_915 = new JRadioButton("9:15");
		Button_915.setBounds(318, 253, 109, 23);
		contentPane.add(Button_915);
		
		Button_930 = new JRadioButton("9:30");
		Button_930.setBounds(429, 253, 109, 23);
		contentPane.add(Button_930);
		
		Button_1045 = new JRadioButton("10:45");
		Button_1045.setBounds(540, 279, 109, 23);
		contentPane.add(Button_1045);
		
		Button_1000 = new JRadioButton("10:00");
		Button_1000.setBounds(207, 279, 109, 23);
		contentPane.add(Button_1000);
		
		Button_1015 = new JRadioButton("10:15");
		Button_1015.setBounds(318, 279, 109, 23);
		contentPane.add(Button_1015);
		
		Button_1030 = new JRadioButton("10:30");
		Button_1030.setBounds(429, 279, 109, 23);
		contentPane.add(Button_1030);
		
		Button_1145 = new JRadioButton("11:45");
		Button_1145.setBounds(540, 305, 109, 23);
		contentPane.add(Button_1145);
		
		Button_1100 = new JRadioButton("11:00");
		Button_1100.setBounds(207, 305, 109, 23);
		contentPane.add(Button_1100);
		
		Button_1115 = new JRadioButton("11:15");
		Button_1115.setBounds(318, 305, 109, 23);
		contentPane.add(Button_1115);
		
		Button_1130 = new JRadioButton("11:30");
		Button_1130.setBounds(429, 305, 109, 23);
		contentPane.add(Button_1130);
		
		Button_1245 = new JRadioButton("12:45");
		Button_1245.setBounds(540, 331, 109, 23);
		contentPane.add(Button_1245);
		
		Button_1200 = new JRadioButton("12:00");
		Button_1200.setBounds(207, 331, 109, 23);
		contentPane.add(Button_1200);
		
		Button_1215 = new JRadioButton("12:15");
		Button_1215.setBounds(318, 331, 109, 23);
		contentPane.add(Button_1215);
		
		Button_1230 = new JRadioButton("12:30");
		Button_1230.setBounds(429, 331, 109, 23);
		contentPane.add(Button_1230);
		
		Button_0145 = new JRadioButton("1:45");
		Button_0145.setBounds(540, 357, 109, 23);
		contentPane.add(Button_0145);
		
		Button_0100 = new JRadioButton("1:00");
		Button_0100.setBounds(207, 357, 109, 23);
		contentPane.add(Button_0100);
		
		Button_0115 = new JRadioButton("1:15");
		Button_0115.setBounds(318, 357, 109, 23);
		contentPane.add(Button_0115);
		
		Button_0130 = new JRadioButton("1:30");
		Button_0130.setBounds(429, 357, 109, 23);
		contentPane.add(Button_0130);
		
		Button_0200 = new JRadioButton("2:00");
		Button_0200.setBounds(207, 383, 109, 23);
		contentPane.add(Button_0200);
		
		Button_0300 = new JRadioButton("3:00");
		Button_0300.setBounds(207, 409, 109, 23);
		contentPane.add(Button_0300);
		
		Button_0400 = new JRadioButton("4:00");
		Button_0400.setBounds(207, 435, 109, 23);
		contentPane.add(Button_0400);
		
		Button_0215 = new JRadioButton("2:15");
		Button_0215.setBounds(318, 383, 109, 23);
		contentPane.add(Button_0215);
		
		Button_0315 = new JRadioButton("3:15");
		Button_0315.setBounds(318, 409, 109, 23);
		contentPane.add(Button_0315);
		
		Button_0415 = new JRadioButton("4:15");
		Button_0415.setBounds(318, 435, 109, 23);
		contentPane.add(Button_0415);
		
		Button_0230 = new JRadioButton("2:30");
		Button_0230.setBounds(429, 383, 109, 23);
		contentPane.add(Button_0230);
		
		Button_0330 = new JRadioButton("3:30");
		Button_0330.setBounds(429, 409, 109, 23);
		contentPane.add(Button_0330);
		
		Button_0430 = new JRadioButton("4:30");
		Button_0430.setBounds(429, 435, 109, 23);
		contentPane.add(Button_0430);
		
		Button_0245 = new JRadioButton("2:45");
		Button_0245.setBounds(540, 383, 109, 23);
		contentPane.add(Button_0245);
		
		Button_0345 = new JRadioButton("3:45");
		Button_0345.setBounds(540, 409, 109, 23);
		contentPane.add(Button_0345);
		
		Button_0445 = new JRadioButton("4:45");
		Button_0445.setBounds(540, 435, 109, 23);
		contentPane.add(Button_0445);
		
		JLabel lblNewLabel_1 = new JLabel("8 AM :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(91, 231, 67, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("9 AM :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(91, 257, 67, 19);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("10 AM :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(81, 285, 74, 19);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("11 AM :");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(81, 309, 74, 19);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("12 PM :");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_2.setBounds(81, 333, 74, 19);
		contentPane.add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_3 = new JLabel("1 PM :");
		lblNewLabel_1_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_3.setBounds(91, 361, 64, 19);
		contentPane.add(lblNewLabel_1_1_1_3);
		
		JLabel lblNewLabel_1_1_1_3_1 = new JLabel("2 PM :");
		lblNewLabel_1_1_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_3_1.setBounds(91, 387, 64, 19);
		contentPane.add(lblNewLabel_1_1_1_3_1);
		
		JLabel lblNewLabel_1_1_1_3_1_1 = new JLabel("3 PM :");
		lblNewLabel_1_1_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_3_1_1.setBounds(91, 413, 64, 19);
		contentPane.add(lblNewLabel_1_1_1_3_1_1);
		
		JLabel lblNewLabel_1_1_1_3_1_1_1 = new JLabel("4 PM :");
		lblNewLabel_1_1_1_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_3_1_1_1.setBounds(91, 439, 64, 19);
		contentPane.add(lblNewLabel_1_1_1_3_1_1_1);
		
		doctorField = new JTextField();
		doctorField.setEditable(false);
		doctorField.setText(username);
		doctorField.setBounds(149, 128, 167, 33);
		contentPane.add(doctorField);
		doctorField.setColumns(10);
		
		JButton SubmitButton = new JButton("Submit");
		SubmitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				storeSchedule(username);
			}
		});
		SubmitButton.setBounds(527, 516, 89, 23);
		contentPane.add(SubmitButton);
		
		addActionListenersToRadioButtons();
	}
    private void addActionListenersToRadioButtons() {
        JRadioButton[] buttons = {Button_800, Button_815, Button_830, Button_845,
                                  Button_900, Button_915, Button_930, Button_945,
                                  Button_1000, Button_1015, Button_1030, Button_1045,
                                  Button_1100, Button_1115, Button_1130, Button_1145,
                                  Button_1200, Button_1215, Button_1230, Button_1245,
                                  Button_0100, Button_0115, Button_0130, Button_0145,
                                  Button_0200, Button_0215, Button_0230, Button_0245,
                                  Button_0300, Button_0315, Button_0330, Button_0345,
                                  Button_0400, Button_0415, Button_0430, Button_0445};

        for (JRadioButton button : buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (button.isSelected()) {
                        selectedTimes.add(button.getText());
                    } else {
                        selectedTimes.remove(button.getText());
                    }
                }
            });
        }
    }  
	
    private void storeSchedule(String username) {
        if (selectedTimes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select at least one time slot.");
            return;
        }

		String directoryPath = "Schedule\\";
		File directory = new File(directoryPath + username + "Schedule.txt");
		if (!directory.exists()) {
			try {
				directory.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error creating schedule file: " + e.getMessage());
				return;
			}
		}

        try (BufferedWriter writer = new BufferedWriter(new FileWriter( directoryPath+ username + "Schedule.txt", true))) {
            for (String time : selectedTimes) {
                writer.write(time + "\n");
            }
            writer.close();
            JOptionPane.showMessageDialog(null, "Schedule saved successfully!");
			dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving schedule: " + ex.getMessage());
        }
    }

	private void clearSchedule(String username){
		String directoryPath = "Schedule\\";
		File directory = new File(directoryPath + username + "Schedule.txt");
		if (!directory.exists()) {
			try {
				directory.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error creating schedule file: " + e.getMessage());
				return;
			}
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter( directoryPath+ username + "Schedule.txt", false))) {
			writer.write("");
			writer.close();
			JOptionPane.showMessageDialog(null, "Schedule cleared successfully!");
			dispose();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error clearing schedule: " + ex.getMessage());
		}
	}
}
