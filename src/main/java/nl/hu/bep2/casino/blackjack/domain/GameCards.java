package nl.hu.bep2.casino.blackjack.domain;

import java.util.Collections;
import java.util.List;

public class GameCards {

	private int numberOfDecks;
	private List<Card> gameCards = null;


	
	
	public GameCards(int numberOfDecks) {
		this.numberOfDecks = numberOfDecks;
	}
	
	public void initiateDeck(int numberOfDecks) {
		
		this.numberOfDecks = numberOfDecks;
		int loop =numberOfDecks;
		Kleur kleuren[] = Kleur.values();
		Waarde waarden[] = Waarde.values();
		List<Card> cards = null;
		Card card = null;
		
		while (loop > 0) {
			int i=0;
			for(Kleur kleur: kleuren) {
				for( Waarde waarde: waarden) {
					card = new Card( kleur, waarde);
					cards.add(card);
					i++;
				}
			}
			loop--;   // loop met één verlagen als er één compleet deck is toegevoegd
		}
		Collections.shuffle(cards);
		this.gameCards = cards;
		System.out.println(" hoi hoi");
	}

	
}
