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
 * @author Junkai BIAN
 *
 */
public class Registre extends JFrame{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JeuO jeu;
    public void setJeuO(JeuO jeu){this.jeu=jeu;}
    public JeuO getJeuO(){return jeu;}
    
    protected JPanel container = new JPanel();
    
    protected String NamePlayer;
    public void setNamePlayer(String NamePlayer){this.NamePlayer =NamePlayer;}
    public String getNaemPlayer(){return NamePlayer;}
    
    protected String PrenomPlayer;   
    
    public String getPrenomPlayer() {
		return PrenomPlayer;
	}
	public void setPrenomPlayer(String prenomPlayer) {
		PrenomPlayer = prenomPlayer;
	}

	protected int Credit;
    public void setCredit(int Credit){this.Credit=Credit;}
    public int getCredit(){return Credit;}
    
    protected JPanel Nom;
    public void setJPanelName(JPanel Nom){this.Nom=Nom;}
    public JPanel getJPanelName(){return this.Nom;}
    
    protected JPanel Prenom;    
    
    public JPanel getPrenom() {
		return Prenom;
	}
	public void setPrenom(JPanel prenom) {
		Prenom = prenom;
	}
	
	private JPanel information;
	
	protected JPanel credit;
		
    public void setCredit(JPanel credit) {
		this.credit = credit;
	}
	public void setJPanelcredit(JPanel credit){this.credit =credit;}
    public JPanel getJPanelcredit(){return credit;}
       
    protected JButton confirms;
    public JButton getconfirms(){return confirms;}
    public void setconfirms(JButton confirms){this.confirms=confirms;}
    
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
	public Registre(JeuO jo) throws Exception
    {
        super("Welcome");        
        jeu=jo;
        card= new CardLayout(2,2);
        container.setLayout(card);
        /* If the type of game is a new game*/
        information = new JPanel();
        
        credit=new JPanel();
        final JLabel ZoneInformation = new JLabel();
        JLabel EnterCredit = new JLabel("Credit:"); // Enter the credit he pays
        final JTextField ZoneCredit = new JTextField(20);
        
        confirmCredit = new JButton("Play!");
        
        ActionListener confcredit = new ActionListener() {
        				
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				Credit=Integer.parseInt(ZoneCredit.getText());
				tmplayer.setCredit(Credit);
				if(find==false){
					jeu.getAllplayers().add(tmplayer);
					jeu.setCurrentplayer(jeu.getAllplayers().size()-1);
					System.out.println(jeu.getAllplayers().size());
					System.out.println(jeu.getCurrentplayer());
				}
				else{
					jeu.getAllplayers().get(jeu.getCurrentplayer()).setCredit(Credit);
					}
				jeu.setStart(true);
				Game currentgame = new Game(jeu);
				jeu.currentgame=currentgame;
				currentgame.GameStart();
				dispose();
				//game start;
				// TODO 
			}
		};
		
		confirmCredit.addActionListener(confcredit);
		
		credit.add(EnterCredit);
		credit.add(ZoneCredit);
		JPanel CreditFinal = new JPanel();
		CreditFinal.add(BorderLayout.NORTH,ZoneInformation);
		CreditFinal.add(BorderLayout.CENTER,credit);
		CreditFinal.add(BorderLayout.SOUTH,confirmCredit);
		
        Nom = new JPanel();
        JLabel EnterName = new JLabel("Nom:"); // Enter the name of the player
        final JTextField ZoneName = new JTextField(20);

        Prenom = new JPanel();
        JLabel EnterPrenom = new JLabel("Prenom:");
        final JTextField zonePrenom = new JTextField(20);

        Nom.add(EnterName);
        Nom.add(ZoneName);

        Prenom.add(EnterPrenom);
        Prenom.add(zonePrenom);

        confirms = new JButton("Confirms");

        ActionListener conf = new ActionListener()
        {
        	public void actionPerformed(ActionEvent ae)
        	{
        		/* We give the value of Name and Credit to the variables in main function, 
//						we also stock them in the zone memory*/                     
        		NamePlayer = ZoneName.getText();
        		PrenomPlayer = zonePrenom.getText();
        		if(jeu.getCurrentplayer()==-1){
        			tmplayer= new Player(NamePlayer,PrenomPlayer);        			
        		}
        		else
        			tmplayer= jeu.getAllplayers().get(jeu.getCurrentplayer());
        		for(Player iterator:jeu.getAllplayers()){
        			if (NamePlayer.equals(iterator.getNom())&& PrenomPlayer.equals(iterator.getPrenom())){
        				jeu.setCurrentplayer(jeu.getAllplayers().indexOf(iterator));
        				Credit = iterator.getCredit();
        				find = true;
        				ZoneInformation.setText("Your current credit here:");
        				ZoneCredit.setText(Integer.toString(Credit));
        				card.show(container, "creditofplayer");
        			}
        		}
        		if(find==false){                    	 
        			ZoneInformation.setText("Welcome New Player! Enter your credit:");
        			card.show(container, "creditofplayer");
        		}                    
        	}
        };            

        information.add(BorderLayout.NORTH,Nom);
        information.add(BorderLayout.CENTER,Prenom);           
        information.add(BorderLayout.SOUTH,confirms);
        confirms.addActionListener(conf);      

        container.add("informationofplayer",information);
        container.add("creditofplayer",CreditFinal);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(container);
        this.setSize(350,200);
        this.setVisible(true);
       
    }
	public Registre(JeuO jeuO,boolean restart) throws HeadlessException {
		super("Welcome");        
        this.jeu=jeuO;
        find =  true;
        System.out.println(jeu.getCurrentplayer());
        System.out.println(jeu.getAllplayers().size());
       
        credit=new JPanel();
        final JLabel ZoneInformation = new JLabel("Your current credit here:");
        JLabel EnterCredit = new JLabel("Credit:"); // Enter the credit he pays
        final JTextField ZoneCredit = new JTextField(20);
        ZoneCredit.setText(Integer.toString(jeu.getAllplayers().get(jeu.getCurrentplayer()).getCredit()));
        
        confirmCredit = new JButton("Play!");
        
        ActionListener confcredit = new ActionListener() {        				
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				Credit = Integer.parseInt(ZoneCredit.getText());
				jeu.getAllplayers().get(jeu.getCurrentplayer()).setCredit(Credit);				
				jeu.setStart(true);
				Game currentgame = new Game(jeu);
				jeu.currentgame=currentgame;
				currentgame.GameStart();
				dispose();
				//game start;
				// TODO 
			}
		};
		
		confirmCredit.addActionListener(confcredit);
		
		credit.add(EnterCredit);
		credit.add(ZoneCredit);
		JPanel CreditFinal = new JPanel();
		CreditFinal.add(BorderLayout.NORTH,ZoneInformation);
		CreditFinal.add(BorderLayout.CENTER,credit);
		CreditFinal.add(BorderLayout.SOUTH,confirmCredit);
	        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(CreditFinal);
        this.setSize(350,200);
        this.setVisible(true);
	}
	
	
    
  

}
