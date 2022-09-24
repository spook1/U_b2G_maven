package nl.hu.bep2.casino.blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameCards {

	private int numberOfDecks;
	private List<Card> gameCards;
	
	public GameCards(int numberOfDecks) {
		this.numberOfDecks = numberOfDecks;
		
		this.gameCards = GameCards.initiateDeck(numberOfDecks);
	}
	
	// ##### NAMED CONSTRUCTIOR : roep static methode aan om meteen in de constructor de deck te vullen. Is dat wat ze bedoelen?

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
	
	
	public int getNumberOfDecks() {
		return this.numberOfDecks;
	}


	public Card getCard() {
		
		Card card = null; //new Card(Kleur.klaver,Waarde.aas);
		
		card = this.gameCards.get(1);
		this.gameCards.remove(1);
		return card;
		
		
	}
	public void setNumberOfDecks(int numberOfDecks) {
		this.numberOfDecks = numberOfDecks;
	}
	/*
	 * public void setGameCards(ArrayList<Card> gameCards) { this.gameCards =
	 * gameCards; }
	 */
	

	
}
