package nl.hu.bep2.casino.blackjack.domain;

import java.io.Serializable;

@Entity
public class Card implements Serializable {
	private Kleur kleur;
	private Waarde waarde;
	

	public Card(Kleur kleur, Waarde waarde) {
		
		this.kleur = kleur;
		this.waarde = waarde;
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "Card [kleur=" + kleur + ", waarde=" + waarde + "]";
	}
	
	

}
