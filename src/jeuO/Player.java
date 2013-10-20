package jeuO;

/**
 * @author Junkai BIAN
 *
 */
public class Player { 
	private String nom;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	private String prenom;
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	private int credit;
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	

	private int dernierjeu;
	public int getDernierjeu() {
		return dernierjeu;
	}
	public void setDernierjeu(int dernierjeu) {
		this.dernierjeu = dernierjeu;
	}
	
	public boolean contains(Player obj){
		if(obj.nom.equals(this.nom)&&obj.prenom.equals(this.prenom))
			return true;
		else
			return false;
	}
	
	public Player() {
	// TODO 自动生成的构造函数存根
	}
	
	public Player(String nom,String prenom){
		this.nom=nom;
		this.prenom=prenom;
		credit=0;
	}
}
