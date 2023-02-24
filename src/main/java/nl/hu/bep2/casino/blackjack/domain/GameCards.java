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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.FetchType;
import javax.persistence.Entity;

public class GameCards implements Serializable {

	private List<Card> gameDeck = new ArrayList<>();  
	
	public GameCards() {
	}
	
	public GameCards(int numberOfDecks) {  //wordt in game.start aangeroepen en game kent aantal decks.
		
		this.gameDeck = GameCards.initiateDeck(numberOfDecks);
	}
	
	// ##### NAMED CONSTRUCTIOR : roep static methode aan om meteen in de constructor de deck te vullen.
	
	public static List<Card> initiateDeck(int numberOfDecks) {
		
		
		int loop =numberOfDecks;
		Kleur kleuren[] = Kleur.values();
		Waarde waarden[] = Waarde.values();
		List<Card> cards = new ArrayList<>();
		
		while (loop > 0) {
			
			for(Kleur kleur: kleuren) {
				if (kleur == Kleur.achterkant) {
					continue;
				}
				for( Waarde waarde: waarden) {
					if (waarde == Waarde.achterkant) {
						continue;
					}
					Card card = new Card(kleur, waarde);
					cards.add(card);
				}
			}
			loop--;   // loop met één verlagen als er één compleet deck is toegevoegd
		}
		Collections.shuffle(cards);
		return cards;
		
		
	}

	public  List<Card> getGameDeck(){
		return this.gameDeck;
	}

	public Card pullCard() {
		
		Card card = null; ;
		
		card = this.gameDeck.get(1); // kies de bovenste kaart van het geschudde deck
		this.gameDeck.remove(1);     //en verwijder deze uit de deck
		return card;				 //geef deze kaart aan bv een speler of dealer

	}

	@Override
	public String toString() {
		return "GameCards [gameDeck=" + gameDeck + "]";
	}
	
	
}
