package presentation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import business.utils.StringUtils;

import javax.swing.JButton;

public class BidDetailsController {

	private JFrame frame;
	private JTextField txtBidValue;

	private Long auctionId;
	
	private Long userId;
	
	private Long bidId;
	
	/**
	 * Create the application.
	 */
	public BidDetailsController(String auctionidStr, Long userId, String bidId) {
		if(!StringUtils.getInstance().isEmpty(auctionidStr)){
			auctionId = Long.parseLong(auctionidStr);
		}
		if(userId != null){
			userId = userId;
		}
		if(!StringUtils.getInstance().isEmpty(bidId)){
			this.bidId = Long.parseLong(bidId);
		}
		
		initialize();	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 186);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLanceMinimomaximo = new JLabel("Lance minimo/maximo:");
		lblLanceMinimomaximo.setBounds(15, 16, 182, 20);
		frame.getContentPane().add(lblLanceMinimomaximo);
		
		JLabel lblMinMaxValue = new JLabel("placeholder");
		lblMinMaxValue.setBounds(193, 16, 69, 20);
		frame.getContentPane().add(lblMinMaxValue);
		
		JLabel lblIdDoLeilao = new JLabel("Id do leilao:");
		lblIdDoLeilao.setBounds(15, 66, 103, 20);
		frame.getContentPane().add(lblIdDoLeilao);
		
		JLabel lblAuctionId = new JLabel("placeholder");
		lblAuctionId.setBounds(111, 66, 69, 20);
		frame.getContentPane().add(lblAuctionId);
		
		JLabel lblValorDoLance = new JLabel("Valor do lance:");
		lblValorDoLance.setBounds(15, 112, 131, 20);
		frame.getContentPane().add(lblValorDoLance);
		
		txtBidValue = new JTextField();
		txtBidValue.setBounds(135, 109, 82, 26);
		frame.getContentPane().add(txtBidValue);
		txtBidValue.setColumns(10);
		
		JButton btnCreateBid = new JButton("Fazer lance!");
		btnCreateBid.setBounds(271, 108, 117, 29);
		frame.getContentPane().add(btnCreateBid);
		
		JLabel lblVendedorcomprador = new JLabel("Vendedor/Comprador:");
		lblVendedorcomprador.setBounds(203, 66, 161, 20);
		frame.getContentPane().add(lblVendedorcomprador);
		
		JLabel lvlUserId = new JLabel("placeholder");
		lvlUserId.setBounds(379, 66, 29, 20);
		frame.getContentPane().add(lvlUserId);
	}
}
