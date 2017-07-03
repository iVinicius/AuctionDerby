package presentation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import business.BusinessException;
import business.Facade;
import business.utils.TimestampConverter;
import persistence.entities.Auction;

import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuctionDetailsController {

	private JFrame frame;
	private JTextField txtAuctionId;
	private JTextField txtDataInicio;
	private JTextField txtDataFim;
	private JTextField txtLotId;
	private JTextField txtLotValue;

	private JRadioButton radioByDemand;

	private JRadioButton radioByOffer;

	private JRadioButton radioHiddenBids;

	private JButton btnAuctionCreate;

	private Long userId;

	/**
	 * Create the application.
	 */
	public AuctionDetailsController(String auctionidStr, Long userId, boolean detailsView) {
		initialize(auctionidStr, detailsView);

		if (userId != null) {
			this.userId = userId;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String auctionidStr, boolean detailsView) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 495);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JLabel lblAuctionid = new JLabel("ID:");
		lblAuctionid.setBounds(11, 16, 39, 20);
		frame.getContentPane().add(lblAuctionid);

		txtAuctionId = new JTextField();
		txtAuctionId.setBounds(48, 13, 39, 26);
		frame.getContentPane().add(txtAuctionId);
		txtAuctionId.setColumns(10);

		radioByDemand = new JRadioButton("Por demanda");
		radioByDemand.setSelected(true);
		radioByDemand.setBounds(11, 48, 149, 29);
		frame.getContentPane().add(radioByDemand);

		radioByOffer = new JRadioButton("Por oferta");
		radioByOffer.setBounds(15, 79, 149, 29);
		frame.getContentPane().add(radioByOffer);

		ButtonGroup group = new ButtonGroup();
		group.add(radioByDemand);
		group.add(radioByOffer);

		radioHiddenBids = new JRadioButton("Lances escondidos");
		radioHiddenBids.setBounds(167, 48, 178, 29);
		frame.getContentPane().add(radioHiddenBids);

		JLabel lblDataInicio = new JLabel("Data in\u00EDcio:");
		lblDataInicio.setBounds(25, 120, 95, 20);
		frame.getContentPane().add(lblDataInicio);

		JLabel lblDataFinal = new JLabel("Data final:");
		lblDataFinal.setBounds(167, 120, 95, 20);
		frame.getContentPane().add(lblDataFinal);

		txtDataInicio = new JTextField();
		txtDataInicio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDataInicio.setText("DD/mm/YY:hh:MM");
		txtDataInicio.setToolTipText("safasf");
		txtDataInicio.setBounds(14, 150, 116, 26);
		frame.getContentPane().add(txtDataInicio);
		txtDataInicio.setColumns(10);

		txtDataFim = new JTextField();
		txtDataFim.setToolTipText("safasf");
		txtDataFim.setText("DD/mm/YY:hh:MM");
		txtDataFim.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDataFim.setColumns(10);
		txtDataFim.setBounds(145, 148, 116, 26);
		frame.getContentPane().add(txtDataFim);

		JLabel lblInfoDoLote = new JLabel("Info do Lote:");
		lblInfoDoLote.setBounds(18, 211, 112, 20);
		frame.getContentPane().add(lblInfoDoLote);

		JLabel lvlLotId = new JLabel("ID:");
		lvlLotId.setBounds(15, 247, 35, 20);
		frame.getContentPane().add(lvlLotId);

		txtLotId = new JTextField();
		txtLotId.setBounds(48, 247, 56, 26);
		frame.getContentPane().add(txtLotId);
		txtLotId.setColumns(10);

		List listAsset = new List();
		listAsset.setBounds(211, 247, 154, 80);
		frame.getContentPane().add(listAsset);

		JLabel lblAssetList = new JLabel("Bens:");
		lblAssetList.setBounds(211, 221, 69, 20);
		frame.getContentPane().add(lblAssetList);

		JLabel lblLotValue = new JLabel("Valor min/max: ");
		lblLotValue.setBounds(15, 291, 128, 20);
		frame.getContentPane().add(lblLotValue);

		txtLotValue = new JTextField();
		txtLotValue.setBounds(14, 318, 146, 26);
		frame.getContentPane().add(txtLotValue);
		txtLotValue.setColumns(10);

		JButton btnAssetDetail = new JButton("Ver/Criar bem");
		btnAssetDetail.setBounds(211, 333, 154, 29);
		frame.getContentPane().add(btnAssetDetail);

		btnAuctionCreate = new JButton("Criar leilao");
		btnAuctionCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedCreateAuction();
			}
		});
		btnAuctionCreate.setBounds(132, 395, 117, 29);
		frame.getContentPane().add(btnAuctionCreate);

		Auction auc = Facade.getInstance().getAuctionById(auctionidStr);
		if (detailsView && auc != null) {
			btnAuctionCreate.hide();
			/*
			 * private JTextField txtAuctionId; 
			 * private JTextField txtDataInicio; 
			 * private JTextField txtDataFim; 
			 * private JTextField txtLotId; 
			 * private JTextField txtLotValue;
			 * 
			 * private JRadioButton radioByDemand;
			 * 
			 * private JRadioButton radioByOffer;
			 * 
			 * private JRadioButton radioHiddenBids;
			 */
			txtAuctionId.setText(auc.getId().toString());
			txtDataInicio.setText(TimestampConverter.getInstance().convertFromTimestamp(auc.getStartBidding()));
			txtDataFim.setText(TimestampConverter.getInstance().convertFromTimestamp(auc.getEndBidding()));
			txtLotId.setText(auc.getLot().getId().toString());
			txtLotValue.setText(auc.getLot().getValue().toString());
			
			if(auc.isByDemand()){
				radioByDemand.doClick();
			} else{
				radioByOffer.doClick();
			}
			
			if(auc.isHiddenBids()){
				radioHiddenBids.doClick();
			}
		}
	}

	// create auction object
	private void actionPerformedCreateAuction() {
		try {
			validateHasUser();

			String byDemand = radioByDemand.isSelected() ? "Y" : "N";
			String hiddenBids = radioHiddenBids.isSelected() ? "Y" : "N";

			Facade.getInstance().createAuction(byDemand, hiddenBids, txtDataInicio.getText(), txtDataFim.getText(),
					userId.toString(), txtLotValue.getText());
		} catch (BusinessException bE) {
			new ErrorWindowController(bE.getMessage());
			frame.dispose();
		} catch (Exception e) {
			new ErrorWindowController(e.getMessage() != null ? e.getMessage() : "Ocorreu um erro ao criar o leilao");
		}
	}

	// check if there's a user logged in, otherwise show a message telling to
	// login
	private void validateHasUser() throws Exception {
		if (userId == null) {
			throw new BusinessException("Faça login antes de criar algo.");
		}
	}
}
