/* 
 * Class for the game
 */

package jeuO;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Junkai BIAN
 * 
 */
public class JeuO extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit()
			.getScreenSize(); // Size of screen
	int hauteur = (int) tailleEcran.getHeight();
	int largeur = (int) tailleEcran.getWidth();

	public int eachpointH = (int) (hauteur - 50) / 28;
	public int eachpointL = (int) (largeur) / 50;

	public String fileadress = (new File(".")).getAbsolutePath(); // Adress of
																	// the
																	// fonction

	private ArrayList<Card> Allcards;// We prepare all cards

	public ArrayList<Card> getAllcards() {
		return Allcards;
	}

	public void setAllcards(ArrayList<Card> Allcards) {
		this.Allcards = Allcards;
	}

	private ArrayList<Player> Allplayers;

	public ArrayList<Player> getAllplayers() {
		return Allplayers;
	}

	public void setAllplayers(ArrayList<Player> allplayers) {
		Allplayers = allplayers;
	}

	private ArrayList<Game> Allgames;

	public ArrayList<Game> getAllgames() {
		return Allgames;
	}

	public void setAllgames(ArrayList<Game> allgames) {
		Allgames = allgames;
	}

	protected Table newgame;

	public Table getNewgame() {
		return newgame;
	}

	public void setNewgame(Table newgame) {
		this.newgame = newgame;
	}

	protected int currentplayer = -1;

	protected Game currentgame;

	public Game getCurrentgame() {
		return currentgame;
	}

	public void setCurrentgame(Game currentgame) {
		this.currentgame = currentgame;
	}

	public int getCurrentplayer() {
		return currentplayer;
	}

	public void setCurrentplayer(int currentplayer) {
		this.currentplayer = currentplayer;
	}

	protected JMenuBar barreMenu; // Menu

	public JMenuBar getBarreMenu() {
		return barreMenu;
	}

	public void setBarreMenu(JMenuBar barreMenu) {
		this.barreMenu = barreMenu;
	}

	protected Registre infor; // Ecran of the information

	public Registre getinfor() {
		return infor;
	}

	public void setinfor(Registre infor) {
		this.infor = infor;
	}

	//
	// protected Record rec; // Ecran of the record
	// public Record getrecord(){return rec;}
	// public void setrecord(Record rec){this.rec=rec;}

	protected JPanel Screen; // utiliser pour changer des screen

	public JPanel getScreen() {
		return Screen;
	}

	public void setScreen(JPanel Screen) {
		this.Screen = Screen;
	}

	private CardLayout Screenlayout = new CardLayout();

	// private Image background ; // Image that we will show as the back
	// public Image getback(){return background;}
	// public void setback(Image back){ this.background=back;}
	//
	protected Change changeback; // Ecran of changer le backgourd of the cards

	public Change getchangeback() {
		return changeback;
	}

	public void setchangeback(Change changeback) {
		this.changeback = changeback;
	}

	//
	protected boolean Start; // Indice of the game statue

	public void setStart(boolean Start) {
		this.Start = Start;
	}

	public boolean getStart() {
		return Start;
	}

	//
	// protected int numberplayer=0; // Indice of the number of player
	// public void setnumberplayer(int i){this.numberplayer=i;}
	// public int getnumberplayer(){return numberplayer;}
	//
	// protected int placeplayer=0;
	// public void setplaceplayer(int i){this.placeplayer=i;}
	// public int getplaceplayer(){return this.placeplayer;}

	/* Fonction for initialize */

	public void InitializeCard() {
		Allcards = new ArrayList<Card>();
		for (int i = 0; i < 52; i++) {
			Card tmpcard = new Card(i + 1);
			Allcards.add(tmpcard);
		}
		Allplayers = new ArrayList<Player>();
		Allgames = new ArrayList<Game>();
	}

	/* Fonction for create new window of information */
	public void newinformation(boolean restart) {
		Screenlayout.show(Screen, "GAME");
		if (!restart) {
			try {
				if (!Start) {
					newgame.removeAll();
					infor = new Registre(this);
				} else
					JOptionPane.showMessageDialog(this,
							"Please Finish this game before the new one",
							"ATTENTION", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else {
			newgame.removeAll();
			infor = new Registre(this, restart);
		}

	}

	// /* Fonction for create new part of record*/
	// public void newRecord()
	// {
	// rec = new Record(this);
	// }
	//
	public void changeScreen() {
		Screenlayout.show(Screen, "CHANGE");
		;
	}

	public void newChange() {
		changeback = new Change(this);
		changeScreen();
	}

	/* Fonction for quitter this game */
	public void Quitter() {
		int choixQuitter = JOptionPane.showConfirmDialog(this,
				"You really want to quit ?", "QUIT", JOptionPane.YES_NO_OPTION);
		if (choixQuitter == JOptionPane.YES_OPTION)
			System.exit(0); // Si l'utilisateur confirme, on quitte
	}

	public JeuO() {
		super("!o! V1.0");
		InitializeCard();
		/* Part for create Screen */
		Screen = new JPanel();
		Screen.setLayout(Screenlayout);
		/* Part of menus */
		barreMenu = new JMenuBar();
		JMenu PartMenuBegin = new JMenu("Game");
		JMenu PartMenuImage = new JMenu("Image");
		JMenu PartMenuHelp = new JMenu("?");

		barreMenu.add(PartMenuBegin);
		barreMenu.add(PartMenuImage);
		barreMenu.add(PartMenuHelp);

		/* MeMu File */
		JMenuItem BoutonBegin = new JMenuItem("New Game");
		JMenuItem BoutonRecord = new JMenuItem("Record");
		JMenuItem BoutonQuit = new JMenuItem("Quit");

		PartMenuBegin.add(BoutonBegin);
		PartMenuBegin.add(BoutonRecord);
		PartMenuBegin.add(BoutonQuit);

		/* Menu Image */
		JMenuItem BoutonChange = new JMenuItem("Change Background");

		PartMenuImage.add(BoutonChange);

		/* Menu Help */
		JMenuItem BoutonHelp = new JMenuItem("Help");
		JMenuItem BoutonCR = new JMenuItem("CopyRight");

		PartMenuHelp.add(BoutonHelp);
		PartMenuHelp.add(BoutonCR);

		/**
		 * Fonctions for every boutons
		 */
		/*
		 * Start game : It should be controlled by the buton "New Game" OR
		 * "Continue"
		 */
		/* "Sart" */
		ActionListener SartGame = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				newinformation(false);

			}
		};
		BoutonBegin.addActionListener(SartGame);

		//
		// ActionListener RecordGame = new ActionListener()
		// {
		// public void actionPerformed(ActionEvent ae)
		// {
		// newRecord();
		// }
		// };
		// BoutonRecord.addActionListener(RecordGame);
		//
		ActionListener Changebackground = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				newChange();
			}
		};
		BoutonChange.addActionListener(Changebackground);
		//
		ActionListener Quitter = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Quitter();
			}
		};
		BoutonQuit.addActionListener(Quitter);

		WindowAdapter wa = new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				Quitter(); // Gestion de la fermeture
			}
		};
		addWindowListener(wa);

		newgame = new Table(this);
		Screen.add(newgame, "GAME");

		changeback = new Change(this);
		Screen.add(changeback, "CHANGE");

		add(Screen);
		setVisible(true);
		setSize(largeur, hauteur - 50);
		setJMenuBar(barreMenu);

		// TODO code application logic here
	}

	public static void main(String[] args) {
		new JeuO();
		// TODO code application logic here
	}

}