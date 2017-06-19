package presentation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrorWindowController {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public ErrorWindowController(String errorMessage) {
		initialize(errorMessage);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String errorMessage) {
		frame = new JFrame();
		frame.setBounds(100, 100, 287, 201);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(errorMessage);
		lblNewLabel.setBounds(15, 16, 247, 103);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnOk.setBounds(75, 134, 117, 29);
		frame.getContentPane().add(btnOk);
		frame.setVisible(true);
	}
}