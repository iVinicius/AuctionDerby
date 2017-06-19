package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AuctionMainMenuController {

	private JFrame frame;
	
	private RegisterOrLoginController registerOrLoginWindow;
	
	// MANUALLY GENERATED ATTRIBUTES
	private Long userId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuctionMainMenuController window = new AuctionMainMenuController();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AuctionMainMenuController() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				focusGainedd();
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogarOuCadastrar = new JButton("Logar ou Cadastrar");
		btnLogarOuCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedLogarOuCadastrar();
			}
		});
		btnLogarOuCadastrar.setBounds(15, 38, 186, 29);
		frame.getContentPane().add(btnLogarOuCadastrar);
	}
	
	// do things when this window gains focus
	private void focusGainedd(){
		updateWindow();
	}
	
	// refresh dynamic data on the window
	private void updateWindow(){
		//TODO:
		userId = registerOrLoginWindow.getUserId();
	}
	
	// open the login window
	private void actionPerformedLogarOuCadastrar(){
		registerOrLoginWindow = new RegisterOrLoginController();	
	}
}