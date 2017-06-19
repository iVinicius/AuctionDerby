package presentation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import business.Facade;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterOrLoginController {

	private JFrame frame;
	private JTextField txtLoginId;
	private JTextField txtRegistroNome;
	private JTextField txtRegistroEmail;
	private JTextField txtRegistroCpf;
	
	//MANUALLY GENERATED ATTRIBUTES
	private Long userId;

	/**
	 * Create the application.
	 */
	public RegisterOrLoginController() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLoginId = new JLabel("ID:");
		lblLoginId.setBounds(15, 16, 69, 20);
		frame.getContentPane().add(lblLoginId);
		
		txtLoginId = new JTextField();
		txtLoginId.setBounds(15, 48, 146, 26);
		frame.getContentPane().add(txtLoginId);
		txtLoginId.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedLogin();
			}
		});
		btnLogin.setBounds(170, 47, 85, 29);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblCadastrese = new JLabel("Cadastre-se");
		lblCadastrese.setBounds(15, 112, 113, 20);
		frame.getContentPane().add(lblCadastrese);
		
		txtRegistroNome = new JTextField();
		txtRegistroNome.setBounds(15, 168, 146, 26);
		frame.getContentPane().add(txtRegistroNome);
		txtRegistroNome.setColumns(10);
		
		JLabel lblRegistroNome = new JLabel("Nome:");
		lblRegistroNome.setBounds(15, 144, 69, 20);
		frame.getContentPane().add(lblRegistroNome);
		
		JLabel lblRegistroEmail = new JLabel("Email:");
		lblRegistroEmail.setBounds(15, 197, 69, 20);
		frame.getContentPane().add(lblRegistroEmail);
		
		txtRegistroEmail = new JTextField();
		txtRegistroEmail.setBounds(15, 220, 146, 26);
		frame.getContentPane().add(txtRegistroEmail);
		txtRegistroEmail.setColumns(10);
		
		JLabel lblRegistroCpf = new JLabel("CPF/CNPJ:");
		lblRegistroCpf.setBounds(196, 144, 113, 20);
		frame.getContentPane().add(lblRegistroCpf);
		
		txtRegistroCpf = new JTextField();
		txtRegistroCpf.setBounds(196, 168, 146, 26);
		frame.getContentPane().add(txtRegistroCpf);
		txtRegistroCpf.setColumns(10);
		
		JButton btnRegistro = new JButton("Cadastrar");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedRegister();
			}
		});
		btnRegistro.setBounds(196, 219, 117, 29);
		frame.getContentPane().add(btnRegistro);
		frame.setVisible(true);
	}
	
	private void actionPerformedLogin(){
		try{
			Long result = Facade.userLogin("");
			userId = result;
			throw new Exception();
		}catch(Exception e){
			// TODO:
			new ErrorWindowController("Usuario nao encontrado");
		}
	}
	
	private void actionPerformedRegister(){
		try{
			Long result = Facade.userRegister("", "", "");
			userId = result;
		}catch(Exception e){
			// TODO:
		}
	}
	
	public Long getUserId(){
		return userId;
	}
}