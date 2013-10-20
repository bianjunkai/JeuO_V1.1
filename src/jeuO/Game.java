package jeuO;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Junkai BIAN
 * 
 */
public class Game {

	protected JeuO JeuO;

	private int number;

	private Player player;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	/* variables */
	protected ArrayList<Card> cards;// We prepare all cards for the game

	public ArrayList<Card> getcards() {
		return cards;
	}

	public void setcards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	/* credit for the player when he begins */
	protected int credit;

	public int getcredit() {
		return credit;
	}

	public void setcredit(int credit) {
		this.credit = credit;
	}

	protected String name = " ";

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	/* Point player get */
	protected int PointPlayer;

	public int getPointPlayer() {
		return PointPlayer;
	}

	public void setPointPlayer(int PointPlayer) {
		this.PointPlayer = PointPlayer;
	}

	/* Point NPC get */
	protected int PointNPC;

	public int getPointNPC() {
		return PointNPC;
	}

	public void setPointNPC(int PointNPC) {
		this.PointNPC = PointNPC;
	}

	/* Indice of the card */
	private Card CardPlayer;

	/* Indice of the card of NPC */
	private Card CardNPC;

	public Card getCardPlayer() {
		return CardPlayer;
	}

	public void setCardPlayer(Card cardPlayer) {
		CardPlayer = cardPlayer;
	}

	public Card getCardNPC() {
		return CardNPC;
	}

	public void setCardNPC(Card cardNPC) {
		CardNPC = cardNPC;
	}

	public boolean equal;// resulat after compare tmpPLAYER and tmpNPC

	private ArrayList<Card> PastCardPlayer = new ArrayList<Card>();

	public ArrayList<Card> getPastCardPlayer() {
		return PastCardPlayer;
	}

	private ArrayList<Card> PastCardNPC = new ArrayList<Card>();

	public ArrayList<Card> getPastCardNPC() {
		return PastCardNPC;
	}

	private int Point = 0;

	protected Past PastPLAYER;

	public void setPastPlayer(Past PastPlayer) {
		this.PastPLAYER = PastPlayer;
	}

	public Past getPastPlayer() {
		return PastPLAYER;
	}

	protected Past PastNPC;

	public void setPastNPC(Past PastNPC) {
		this.PastNPC = PastNPC;
	}

	public Past getPastNPC() {
		return PastNPC;
	}

	private ArrayList<Card> Past = new ArrayList<Card>();

	public void neworder(JeuO JO) {
		cards = new ArrayList<Card>();
		Random random = new Random();
		while (cards.size() < JO.getAllcards().size()) {
			Integer ii = new Integer(random.nextInt(JO.getAllcards().size()));
			if (!cards.contains(JO.getAllcards().get(ii))) {
				cards.add(JO.getAllcards().get(ii));
			}
		}
	}

	protected void GameProcess(int Initia) {
		if (Initia == 0) {
			Point = 0;
			Past = new ArrayList<Card>();
		}
		CardPlayer = cards.get(0);
		CardNPC = cards.get(1);
		cards.remove(0);
		cards.remove(0);
		Calcul(CardPlayer, CardNPC);

	}

	/* Function to caculate the resulat of each time */
	protected void Calcul(Card CardP, Card CardN) {

		Past.add(CardP);
		Past.add(CardN);
		Point += CardP.getNumber() + CardN.getNumber();

		if (CardP.getNumber() > CardN.getNumber()) {
			PointPlayer += Point;
			PastCardPlayer.addAll(Past);
			equal = false;
		} else if (CardP.getNumber() < CardN.getNumber()) {
			PointNPC += Point;
			PastCardNPC.addAll(Past);
			equal = false;
		} else {
			equal = true;
		}
		JeuO.getNewgame().repaint();

	}

	public void GameStart() {
		int credit = JeuO.getCurrentgame().getcredit();
		credit -= 50;
		JeuO.getCurrentgame().setcredit(credit);
		JeuO.getAllplayers().get(JeuO.getCurrentplayer()).setCredit(credit);
		JeuO.getNewgame().repaint();
	}

	public void GameFini() {
		JeuO.setStart(false);
		int choixContinue = 0;
		JeuO.getAllgames().add(this);
		JeuO.getNewgame().ChoiceInfor(choixContinue);
	}

	public void newPast(int indice) {
		if (indice == 1) {
			PastPLAYER = new Past(JeuO, 1);
		} else {
			PastNPC = new Past(JeuO, 2);
		}
	}

	public Game() {
		// TODO 自动生成的构造函数存根
	}

	public Game(JeuO JO) {
		super();
		PastPLAYER = new Past(JO, 0);
		PastNPC = new Past(JO, 0);
		PastCardPlayer = new ArrayList<Card>();
		PastCardNPC = new ArrayList<Card>();
		equal = true;
		JeuO = JO;
		number = JeuO.getAllgames().size();
		if (JeuO.getCurrentplayer() != -1) {
			name = JeuO.getAllplayers().get(JeuO.getCurrentplayer()).getNom()
					+ " "
					+ JeuO.getAllplayers().get(JeuO.getCurrentplayer())
							.getPrenom();
			credit = JO.getAllplayers().get(JO.getCurrentplayer()).getCredit();
		}

		PointNPC = 0;
		neworder(JO);

	}

}
