/*
 * Class for enter the information
 */

package jeuO;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @ClassName: Registre
 * @Description: TODO
 * @author Junkai BIAN
 * 
 */
public class Registre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected final JeuO jeu;

	public JeuO getJeuO() {
		return jeu;
	}

	protected JPanel container = new JPanel();

	protected String NamePlayer;

	public void setNamePlayer(String NamePlayer) {
		this.NamePlayer = NamePlayer;
	}

	public String getNaemPlayer() {
		return NamePlayer;
	}

	protected String PrenomPlayer;

	public String getPrenomPlayer() {
		return PrenomPlayer;
	}

	public void setPrenomPlayer(String prenomPlayer) {
		PrenomPlayer = prenomPlayer;
	}

	protected int credit;

	public void setCredit(int Credit) {
		this.credit = Credit;
	}

	public int getCredit() {
		return credit;
	}

	protected JPanel name;

	public void setJPanelName(JPanel Nom) {
		this.name = Nom;
	}

	public JPanel getJPanelName() {
		return this.name;
	}

	protected JPanel secondName;

	public JPanel getPrenom() {
		return secondName;
	}

	public void setPrenom(JPanel prenom) {
		secondName = prenom;
	}

	private JPanel information;

	protected JPanel creditPanel;

	public void setCredit(JPanel credit) {
		this.creditPanel = credit;
	}

	public void setJPanelcredit(JPanel credit) {
		this.creditPanel = credit;
	}

	public JPanel getJPanelcredit() {
		return creditPanel;
	}

	protected JButton confirms;

	public JButton getconfirms() {
		return confirms;
	}

	public void setconfirms(JButton confirms) {
		this.confirms = confirms;
	}

	protected JButton confirmCredit;

	public JButton getConfirmCredit() {
		return confirmCredit;
	}

	public void setConfirmCredit(JButton confirmCredit) {
		this.confirmCredit = confirmCredit;
	}

	protected Boolean initial;

	private boolean find = false;

	private Player tmplayer = new Player();

	private CardLayout card;

	public CardLayout getCard() {
		return card;
	}

	public void setCard(CardLayout card) {
		this.card = card;
	}

	private JPanel informationOfDifferentPlayer() {
		information = new JPanel();
		final JLabel ZoneInformation = new JLabel();
		name = new JPanel();
		JLabel EnterName = new JLabel("Nom:"); // Enter the name of the player
		final JTextField ZoneName = new JTextField(20);

		secondName = new JPanel();
		JLabel EnterPrenom = new JLabel("Prenom:");
		final JTextField zonePrenom = new JTextField(20);

		name.add(EnterName);
		name.add(ZoneName);

		secondName.add(EnterPrenom);
		secondName.add(zonePrenom);

		confirms = new JButton("Confirms");

		ActionListener conf = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				/*
				 * We give the value of Name and Credit to the variables in main
				 * function, // we also stock them in the zone memory
				 */
				NamePlayer = ZoneName.getText();
				PrenomPlayer = zonePrenom.getText();
				if (jeu.getCurrentplayer() == -1) {
					tmplayer = new Player(NamePlayer, PrenomPlayer);
				} else
					tmplayer = jeu.getAllplayers().get(jeu.getCurrentplayer());
				for (Player iterator : jeu.getAllplayers()) {
					if (NamePlayer.equals(iterator.getNom())
							&& PrenomPlayer.equals(iterator.getPrenom())) {
						jeu.setCurrentplayer(jeu.getAllplayers().indexOf(
								iterator));
						credit = iterator.getCredit();
						find = true;
						container.add("creditofplayer",
								creditOfDifferentPlayer());
						card.show(container, "creditofplayer");

					}
				}
				if (find == false) {
					ZoneInformation
							.setText("Welcome New Player! Enter your credit:");
					container.add("creditofplayer", creditOfDifferentPlayer());
					card.show(container, "creditofplayer");

				}
			}
		};

		information.add(BorderLayout.NORTH, name);
		information.add(BorderLayout.CENTER, secondName);
		information.add(BorderLayout.SOUTH, confirms);
		confirms.addActionListener(conf);
		return information;
	}

	private JPanel creditOfDifferentPlayer() {
		creditPanel = new JPanel();
		final JLabel ZoneInformation = new JLabel();
		JLabel EnterCredit = new JLabel("Credit:"); // Enter the credit he pays
		final JTextField ZoneCredit = new JTextField(20);
		if (find == true) {
			ZoneInformation.setText("<html><body>Welcome back!<br>"+tmplayer.getPrenom()+"."+tmplayer.getNom()+", Your current credit: </body></html>");
			ZoneCredit.setText(Integer.toString(credit));
		}

		confirmCredit = new JButton("Play!");

		ActionListener confcredit = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (find == false) {
					credit = Integer.parseInt(ZoneCredit.getText());
					tmplayer.setCredit(credit);
					jeu.getAllplayers().add(tmplayer);
					jeu.setCurrentplayer(jeu.getAllplayers().size() - 1);
				} 
				jeu.setStart(true);
				Game currentgame = new Game(jeu);
				jeu.currentgame = currentgame;
				currentgame.GameStart();
				dispose();
				// game start;
			}
		};

		confirmCredit.addActionListener(confcredit);

		creditPanel.add(EnterCredit);
		creditPanel.add(ZoneCredit);
		JPanel CreditFinal = new JPanel();
		CreditFinal.add(BorderLayout.NORTH, ZoneInformation);
		CreditFinal.add(BorderLayout.CENTER, creditPanel);
		CreditFinal.add(BorderLayout.SOUTH, confirmCredit);
		return CreditFinal;
	}

	public Registre(JeuO jeuO, boolean samePlayer) throws HeadlessException {
		super("Welcome");
		this.jeu = jeuO;
		find = false;
		if (samePlayer) {
			this.add(continueGameForSamePlayer());
		} else {
			card = new CardLayout(2, 2);
			container.setLayout(card);
			container
					.add("informationofplayer", informationOfDifferentPlayer());
			this.add(container);

		}

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(350, 200);
		this.setVisible(true);
	}

	private JPanel continueGameForSamePlayer() {
		creditPanel = new JPanel();
		final JLabel ZoneInformation = new JLabel("Your current credit here:");
		JLabel EnterCredit = new JLabel("Credit:"); // Enter the credit he pays
		final JTextField ZoneCredit = new JTextField(20);
		ZoneCredit.setText(Integer.toString(jeu.getAllplayers()
				.get(jeu.getCurrentplayer()).getCredit()));

		confirmCredit = new JButton("Play!");

		ActionListener confcredit = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				credit = Integer.parseInt(ZoneCredit.getText());
				jeu.getAllplayers().get(jeu.getCurrentplayer())
						.setCredit(credit);
				jeu.setStart(true);
				Game currentgame = new Game(jeu);
				jeu.currentgame = currentgame;
				currentgame.GameStart();
				dispose();
				// game start;
			}
		};

		confirmCredit.addActionListener(confcredit);

		creditPanel.add(EnterCredit);
		creditPanel.add(ZoneCredit);
		JPanel CreditFinal = new JPanel();
		CreditFinal.add(BorderLayout.NORTH, ZoneInformation);
		CreditFinal.add(BorderLayout.CENTER, creditPanel);
		CreditFinal.add(BorderLayout.SOUTH, confirmCredit);
		return CreditFinal;
	}

}
