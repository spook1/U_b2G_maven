package nl.hu.bep2.casino.blackjack.domain;

import java.io.Serializable;

@Entity
public class Card {
	private Kleur kleur;
	private Waarde waarde;
	

	public Card(Kleur kleur, Waarde waarde) {
		
		this.kleur = kleur;
		this.waarde = waarde;
		// TODO Auto-generated constructor stub
	}

	

	public Kleur getKleur() {
		return kleur;
	}



	public void setKleur(Kleur kleur) {
		this.kleur = kleur;
	}



	public Waarde getWaarde() {
		return waarde;
	}



	public void setWaarde(Waarde waarde) {
		this.waarde = waarde;
	}



	@Override
	public String toString() {
		return "Card [kleur=" + kleur + ", waarde=" + waarde + "]";
	}
	
	

}
