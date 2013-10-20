package jeuO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Junkai BIAN
 *
 */
public class Table extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JeuO JO;

	private Image CardBackground; 

	public Image getCardBackground() {
		return CardBackground;
	}

	public void setCardBackground(Image cardBackground) {
		CardBackground = cardBackground;
	}

	private int[] zone1 ;// zone for the cards initialize

	private int[] zone2 ;// zone for player

	private int[] zone3;// zone for NPC

	private int[] zone4 ;// zone for the cards used PLAYER

	private int[] zone5 ;// zone for the cards used NPC

	public void drawnoequal(Graphics g)
	{
		if (!JO.getCurrentgame().cards.isEmpty()) 
		{
		g.drawImage(JO.getCurrentgame().getCardPlayer().getFace(),zone2[0],zone2[1], zone2[2], zone2[3], this);
		g.drawImage(JO.getCurrentgame().getCardNPC().getFace(),zone3[0],zone3[1], zone3[2], zone3[3], this);
		g.drawImage(CardBackground,zone4[0],zone4[1], zone4[2], zone4[3], this);
		g.drawImage(CardBackground,zone5[0],zone5[1], zone5[2], zone5[3], this);
		}             
		else
		{
		g.setColor(Color.GREEN);
		g.fillOval(zone2[0],zone2[1],zone2[2],zone2[3]);
		g.fillOval(zone3[0],zone3[1],zone3[2],zone3[3]);
		g.drawImage(CardBackground,zone4[0],zone4[1], zone4[2], zone4[3], this);
		g.drawImage(CardBackground,zone5[0],zone5[1], zone5[2], zone5[3], this);
		}

	}

	public void drawequal(Graphics g)
	{
		g.drawImage( CardBackground,zone2[0],zone2[1], zone2[2], zone2[3], this);
		g.drawImage( CardBackground,zone3[0],zone3[1], zone3[2], zone3[3], this);
		g.drawImage( CardBackground,zone4[0],zone4[1], zone4[2], zone4[3], this);
		g.drawImage( CardBackground,zone5[0],zone5[1], zone5[2], zone5[3], this);
	}

	public void dessineInfor(Graphics g)
	{
	    /* Points*/
		g.setFont(new Font("Times", Font.PLAIN, 18));
		g.setColor(Color.YELLOW);
		g.drawString("POINT: "+Integer.toString(JO.getCurrentgame().PointPlayer),zone4[0]+20 ,zone4[1]-10 );
		g.drawString("POINT: "+Integer.toString(JO.getCurrentgame().PointNPC), zone5[0]+20, zone5[1]-10);
		/* Credit*/
		g.setFont(new Font("Times", Font.PLAIN, 48));
		g.setColor(Color.RED); 
		g.drawString("CREDIT: "+Integer.toString(JO.getCurrentgame().credit), zone3[0]+200, zone1[2]);
		/* PLAYER */
		g.setFont(new Font ("Times",Font.PLAIN,48));
		g.setColor(Color.RED);
		g.drawString("Welcome: "+JO.getCurrentgame().name,zone2[0]-100,zone1[2]);

		/* First Zone*/
		if(JO.getStart()&&!JO.getCurrentgame().cards.isEmpty())
		g.drawImage(CardBackground,zone1[0],zone1[1], zone1[2], zone1[3], this);
		else if ( JO.getCurrentgame().cards.isEmpty())
		{
		g.setColor(Color.GREEN);
		g.fillOval(zone1[0],zone1[1],zone1[2],zone1[3]);

		}
		//Free this zone

		/*Second Zone*/
		if (!JO.getCurrentgame().equal){                
		drawnoequal(g);
		}
		else
		{
		if(JO.getCurrentgame().cards.size()!=JO.getAllcards().size())
		{                 
			drawequal(g);
		} 
		}
	}
	


	public void paint(Graphics g)
	{
		super.paint(g);
		/* Draw the zones that we use*/
		g.setColor(Color.YELLOW);
		g.drawRect(zone1[0], zone1[1], zone1[2], zone1[3]);
		g.drawRect(zone2[0], zone2[1], zone2[2], zone2[3]);
		g.drawRect(zone3[0], zone3[1], zone3[2], zone3[3]);
		g.drawRect(zone4[0], zone4[1], zone4[2], zone4[3]);
		g.drawRect(zone5[0], zone5[1], zone5[2], zone5[3]);
	        
	       if (JO.getStart())
	       {
	           dessineInfor(g);
	       }

		
	}
	
	/* Listener for licening cards*/
	private MouseListener Licensing = new MouseAdapter() 
	{                        
		public void mouseClicked(MouseEvent e) 
		{

			if(JO.getStart()&&!JO.getCurrentgame().cards.isEmpty()){

			if ( e.getX()>zone1[0] && e.getX()< zone1[2]+zone1[0] && e.getY() > zone1[1] && e.getY() < zone1[3]+zone1[1])
				{
	               if(!JO.getCurrentgame().equal)
	            	   JO.getCurrentgame().GameProcess(0);		
	               else
	            	   JO.getCurrentgame().GameProcess(1);
				}
			}
	        else 
	        	JO.getCurrentgame().GameFini();
		/*else
		" GAME OVER "
		*/
		}
	};

	private MouseListener TurnPastPlayer = new MouseAdapter() 
    {
            public void mouseClicked(MouseEvent e) 
            {
                          if(JO.getStart()){
                            if ( e.getX()>zone4[0] && e.getX()< zone4[0]+zone4[2] && e.getY() > zone4[1] && e.getY() < zone4[3]+zone4[1])
                            {
                            	JO.getCurrentgame().newPast(1);
                            }
                }
            }
    };	

	private MouseListener TurnPastNPC = new MouseAdapter() 
	    {
	            public void mouseClicked(MouseEvent e) 
	             {
	                            if(JO.getStart()){
	                            if ( e.getX()>zone5[0] && e.getX()< zone5[0]+zone5[2] && e.getY() > zone5[1] && e.getY() < zone5[3]+zone5[1])
	                            {
	                            	JO.getCurrentgame().newPast(2);
	                            }
	                            }
	            }
	    };	
	    
	    public void ChoiceInfor(int choixContinue){
	    	if (JO.getCurrentgame().PointPlayer>JO.getCurrentgame().PointNPC)
		    {
	    		choixContinue = JOptionPane.showConfirmDialog(this,"Congratulations! You win! Do you want to continuer?","WIN",JOptionPane.YES_NO_OPTION);
		        if(choixContinue == JOptionPane.YES_OPTION)
		        	JO.newinformation(true);
		        else
		        	this.removeAll();
		            this.repaint();
		    }
		    else
		    {
		        choixContinue = JOptionPane.showConfirmDialog(this,"Sorry! You lost! Do you want to continuer?","Lost",JOptionPane.YES_NO_OPTION);
		        if(choixContinue == JOptionPane.YES_OPTION)
		            JO.newinformation(true);
		        else
		        	removeAll();
		        	repaint();
		    }
			
	    }
		
	public Table(JeuO jeu){
		 super();
		 this.JO=jeu; 
		 CardBackground=JO.getAllcards().get(0).getBack();  // We use background here for easy use after    PointPlayer = 0;
		 zone1 = new int[]{JO.eachpointL*4,JO.eachpointH,JO.eachpointL*5,JO.eachpointH*7};
		 zone2 = new int[]{JO.eachpointL*15,JO.eachpointH*9,JO.eachpointL*5,JO.eachpointH*7};
		 zone3 = new int[]{JO.eachpointL*28,JO.eachpointH*9,JO.eachpointL*5,JO.eachpointH*7};
		 zone4 = new int[]{JO.eachpointL*4,JO.eachpointH*16,JO.eachpointL*5,JO.eachpointH*7};
		 zone5 = new int[]{JO.eachpointL*39,JO.eachpointH*16,JO.eachpointL*5,JO.eachpointH*7};
		 addMouseListener(Licensing);
		 addMouseListener(TurnPastPlayer);
		 addMouseListener(TurnPastNPC);   
		 setBackground(Color.GREEN); 
		 setVisible(true);
	}
	
	

}
