/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuO;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Junkai BIAN
 * 
 */
public class Past extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JLabel[] labels;

	public JLabel[] getone() {
		return labels;
	}

	public void setone(int i, JLabel one) {
		this.labels[i] = one;
	}

	protected JPanel Show;

	public JPanel getShow() {
		return Show;
	}

	public void setShwo(JPanel show) {
		this.Show = show;
	}

	private ArrayList<Card> CardsBF = new ArrayList<Card>();; // Use for know
																// how many
																// cards we have
																// to show.

	public void paint(Graphics g) {
		for (int i = 0; i < CardsBF.size(); i++) {
			g.drawImage(CardsBF.get(i).getFace(), labels[i].getX(),
					labels[i].getY() + 30, 145, 180, labels[i]);
		}

	}

	public Past(JeuO o, int indice) {

		super("Review");

		if (indice == 0) {
			CardsBF = new ArrayList<Card>();
			labels = new JLabel[26];

		} else {
			if (indice == 1)
				CardsBF = o.getCurrentgame().getPastCardPlayer();
			else
				CardsBF = o.getCurrentgame().getPastCardNPC();

			Show = new JPanel(new FlowLayout(FlowLayout.LEFT));
			labels = new JLabel[CardsBF.size()];
			Show.setBackground(Color.green);
			for (int i = 0; i < labels.length; i++) {
				labels[i] = new JLabel("GUUG");
				Show.add(labels[i]);

			}
			add(Show);

			setSize(o.eachpointL * 33, o.eachpointH * 9);
			setVisible(true);
			setResizable(false);
			setAlwaysOnTop(true);

		}
	}
}