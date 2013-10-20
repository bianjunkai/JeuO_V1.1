/* 
 * Class for the cards which we will use in this game
 */

package jeuO;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author Junkai BIAN
 */
public class Card extends JPanel {

	private static final long serialVersionUID = 1L;
	private String address = ".//resource";// the address that we stock this projet

	public String getaddress() {
		return address;
	}

	public void setaddress(String address) {
		this.address = address;
	}

	private String Imgaddress;// the address that we stock those pictures

	public String getImgaddress() {
		return Imgaddress;
	}

	public void setImgaddress(String Imgaddress) {
		this.Imgaddress = Imgaddress;
	}

	private String ImgaddressBack;// the address that we stock those pictures

	public String getImgaddressBack() {
		return ImgaddressBack;
	}

	public void setImgaddressBack(String ImgaddressBack) {
		this.ImgaddressBack = ImgaddressBack;
	}

	private int Indice; // the number

	public int getIndice() {
		return Indice;
	}

	public void setIndice(int Indice) {
		this.Indice = Indice;
	}

	private int Number; // the number on the card 1 to 13

	public int getNumber() {
		return Number;
	}

	public void setNumber(int Number) {
		this.Number = Number;
	}

	private char Suit;// four suits ,spade=S,heart=H,diamond=D,club=C

	public char getSuit() {
		return Suit;
	}

	public void setSuit(char Suit) {
		this.Suit = Suit;
	}

	private Image face;

	public Image getFace() {
		return face;
	}

	public void setFace(Image face) {
		this.face = face;
	}

	private Image back;

	public Image getBack() {
		return back;
	}

	public void setBack(Image back) {
		this.back = back;
	}

	public void Initialisation(int Indice) throws Exception // we decide those
															// information basic
	{
		if (0 < Indice && Indice <= 13) {
			Suit = 'S';
			Number = Indice;
		} else if (13 < Indice && Indice <= 26) {
			Suit = 'H';
			Number = Indice - 13;
		} else if (26 < Indice && Indice <= 39) {
			Suit = 'D';
			Number = Indice - 26;
		} else if (39 < Indice && Indice < 53) {
			Suit = 'C';
			Number = Indice - 39;
		}

	}

	public void InitiaIMG()// we prepare those pictures
	{
		Imgaddress = address + "//card//" + Integer.toString(Indice) + ".jpg";
		ImgaddressBack = address + "//card//back//back_0.jpg";
		face = new ImageIcon(Imgaddress).getImage();
		back = new ImageIcon(ImgaddressBack).getImage();
	}

	public Card(int i) {
		Indice = i;
		try {
			Initialisation(Indice);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		InitiaIMG();
	}

	public Card(Card newCard) {
		Indice = newCard.getIndice();
		Number = newCard.getNumber();
	}

}
