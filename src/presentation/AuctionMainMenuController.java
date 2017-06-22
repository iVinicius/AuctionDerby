package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

import business.Facade;

import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuctionMainMenuController {

	private JFrame frame;

	private RegisterOrLoginController registerOrLoginWindow;

	private AuctionDetailsController auctionDetailsWindow;

	private JLabel lblLoggedUserId;

	private List listAuction;

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
		frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				focusGainedd();
			}

			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		frame.setBounds(100, 100, 588, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnLogarOuCadastrar = new JButton("Logar ou Cadastrar");
		btnLogarOuCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedLogarOuCadastrar();
			}
		});
		btnLogarOuCadastrar.setBounds(15, 243, 186, 29);
		frame.getContentPane().add(btnLogarOuCadastrar);

		listAuction = new List();
		listAuction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		listAuction.setBounds(21, 42, 154, 80);
		frame.getContentPane().add(listAuction);

		JLabel lblListaDeLeiloes = new JLabel("Lista de Leiloes:");
		lblListaDeLeiloes.setBounds(26, 16, 160, 20);
		frame.getContentPane().add(lblListaDeLeiloes);

		List list_1 = new List();
		list_1.setBounds(221, 42, 154, 80);
		frame.getContentPane().add(list_1);

		JLabel lblListaDe = new JLabel("Lista de lances:");
		lblListaDe.setBounds(221, 16, 154, 20);
		frame.getContentPane().add(lblListaDe);

		List list_2 = new List();
		list_2.setBounds(414, 42, 154, 80);
		frame.getContentPane().add(list_2);

		JLabel lblListaDeBens = new JLabel("Lista de bens:");
		lblListaDeBens.setBounds(414, 16, 132, 20);
		frame.getContentPane().add(lblListaDeBens);

		JButton btnLeilaoDetalhes = new JButton("Ver detalhes");
		btnLeilaoDetalhes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedAuctionDetails();
			}
		});
		btnLeilaoDetalhes.setBounds(28, 143, 132, 29);
		frame.getContentPane().add(btnLeilaoDetalhes);

		JButton button = new JButton("Ver detalhes");
		button.setBounds(231, 143, 132, 29);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("Ver detalhes");
		button_1.setBounds(431, 143, 132, 29);
		frame.getContentPane().add(button_1);

		JButton btnCriarLeilao = new JButton("Criar leilao");
		btnCriarLeilao.setBounds(38, 188, 117, 29);
		frame.getContentPane().add(btnCriarLeilao);

		JButton btnCriarLance = new JButton("Criar lance");
		btnCriarLance.setBounds(241, 188, 117, 29);
		frame.getContentPane().add(btnCriarLance);

		JLabel lblUsuarioLogado = new JLabel("Usuario Logado: ");
		lblUsuarioLogado.setBounds(251, 247, 124, 20);
		frame.getContentPane().add(lblUsuarioLogado);

		lblLoggedUserId = new JLabel("");
		lblLoggedUserId.setBounds(378, 247, 69, 20);
		frame.getContentPane().add(lblLoggedUserId);
	}

	// do things when this window gains focus
	private void focusGainedd() {
		updateWindow();
	}

	// refresh dynamic data on the window
	private void updateWindow() {
		try {
			// TODO:
			if (registerOrLoginWindow != null) {
				userId = registerOrLoginWindow.getUserId();
				if (userId != null) {
					lblLoggedUserId.setText(userId.toString());
				}
			}
			updateAuctionList();
		} catch (Exception e) {
			new ErrorWindowController("Ocorreu algum erro ao atualizar a janela.");
		}
	}

	// open the login window
	private void actionPerformedLogarOuCadastrar() {
		registerOrLoginWindow = new RegisterOrLoginController();
	}

	// open the auction controller
	private void actionPerformedAuctionDetails() {
		// find selected auction id
		String auctionIdStr = listAuction.getSelectedItem();

		auctionDetailsWindow = new AuctionDetailsController(auctionIdStr, userId);
	}

	// update auction list
	private void updateAuctionList() throws Exception {
		java.util.List<String> list = Facade.getInstance().getAuctionList();
		listAuction.removeAll();
		for (String s : list) {
			listAuction.add(s);
		}
	}
}