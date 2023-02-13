package nl.hu.bep2.casino.blackjack.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.FetchType;
import javax.persistence.Entity;


public class GameCards implements Serializable {

	private List<Card> gameCards = new ArrayList<>();  
	
	public GameCards() {
	};
	
	public GameCards(int numberOfDecks) {  //wordt in game.start aangeroepen en game kent aantal decks.
		
		this.gameCards = GameCards.initiateDeck(numberOfDecks);
	}
	
	// ##### NAMED CONSTRUCTIOR : roep static methode aan om meteen in de constructor de deck te vullen.
	
	public static List<Card> initiateDeck(int numberOfDecks) {
		
		
		int loop =numberOfDecks;
		Kleur kleuren[] = Kleur.values();
		Waarde waarden[] = Waarde.values();
		List<Card> cards = new ArrayList<>();
		
		while (loop > 0) {
			
			for(Kleur kleur: kleuren) {
				for( Waarde waarde: waarden) {
					Card card = new Card(kleur, waarde);
					cards.add(card);
				}
			}
			loop--;   // loop met één verlagen als er één compleet deck is toegevoegd
		}
		Collections.shuffle(cards);
		return cards;
		
		
	}

	public  List<Card> getGameCards(){
		return this.gameCards;
	}

	public Card getCard() {
		
		Card card = null; //new Card(Kleur.klaver,Waarde.aas);
		
		card = this.gameCards.get(1);
		this.gameCards.remove(1);
		return card;	

	}
}
