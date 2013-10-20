package jeuO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Junkai BIAN
 *
 */
public class Change extends JPanel{
	
	private static final long serialVersionUID = 1L;

	protected JeuO jeu;
	
	private int[][] zone;
	
	private String adress = ".//resource";
	
	private Image[] imagelibrary;
	
	private JTextField choice;
	
	private JButton confirm;
		
	public void readimage(int n){
		String Imageadress = adress+"//card//back//back_"+n+".jpg";
		Image tmpimage = new ImageIcon(Imageadress).getImage();
		imagelibrary[n] = tmpimage;
	}


	public void paint(Graphics g)
	{
		super.paint(g);
		/* Draw the zones that we use*/
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Times", Font.BOLD, 16));
		for(int i=0;i<9;i++){
			g.drawString(Integer.toString(i),zone[i][0]-20 ,zone[i][1]+zone[i][3]-8 );
			g.drawRect(zone[i][0], zone[i][1], zone[i][2], zone[i][3]);
			if(imagelibrary[i]!=null)
				g.drawImage(imagelibrary[i],zone[i][0],zone[i][1], zone[i][2], zone[i][3], this);
		}
		g.setFont(new Font("Times", Font.BOLD, 20));
		g.drawString("Your choice of new background:",jeu.eachpointL, zone[3][1]+jeu.eachpointH*5);
				
	}

	public Change(JeuO jeuO) {
		super();
		this.jeu = jeuO;
		this.setLayout(null);
		zone = new int[9][4];
		imagelibrary = new Image[9];
		choice = new JTextField(20);
		confirm = new JButton("Confirm");
		this.add(choice);
		this.add(confirm);
		choice.setBounds(jeu.eachpointL, jeu.eachpointH*16, 50, 25);
		confirm.setBounds(jeu.eachpointL+75,jeu.eachpointH*16,100,25);
		
		 ActionListener conf = new ActionListener()
	     {
	        	public void actionPerformed(ActionEvent ae)
	        	{
	        		int i = Integer.parseInt(choice.getText());
				jeu.getNewgame().setCardBackground((imagelibrary[i]));
	        		System.out.println(i);
//	        		for(Card iter:jeu.getAllcards()){
//	        			iter.setBack(imagelibrary[i]);
//	        		}
	        	}
	     };
	     confirm.addActionListener(conf);
	     
		zone[0]= new int[]{jeu.eachpointL*16,jeu.eachpointH*1,jeu.eachpointL*5,jeu.eachpointH*7};
		zone[1]= new int[]{jeu.eachpointL*29,jeu.eachpointH*1,jeu.eachpointL*5,jeu.eachpointH*7};
		zone[2]= new int[]{jeu.eachpointL*42,jeu.eachpointH*1,jeu.eachpointL*5,jeu.eachpointH*7};
		zone[3]= new int[]{jeu.eachpointL*16,jeu.eachpointH*10,jeu.eachpointL*5,jeu.eachpointH*7};
		zone[4]= new int[]{jeu.eachpointL*29,jeu.eachpointH*10,jeu.eachpointL*5,jeu.eachpointH*7};
		zone[5]= new int[]{jeu.eachpointL*42,jeu.eachpointH*10,jeu.eachpointL*5,jeu.eachpointH*7};
		zone[6]= new int[]{jeu.eachpointL*16,jeu.eachpointH*19,jeu.eachpointL*5,jeu.eachpointH*7};
		zone[7]= new int[]{jeu.eachpointL*29,jeu.eachpointH*19,jeu.eachpointL*5,jeu.eachpointH*7};
		zone[8]= new int[]{jeu.eachpointL*42,jeu.eachpointH*19,jeu.eachpointL*5,jeu.eachpointH*7};
		for(int i=0;i<9;i++){
			readimage(i);
		}
		setBackground(Color.GREEN); 
		setVisible(true);
	}

}
