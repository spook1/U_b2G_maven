package nl.hu.bep2.casino.test;

import java.util.ArrayList;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.GameCards;

@RestController
public class TestController {
	
	private final static int numDecks =1;

	@GetMapping("/test")
	//public ArrayList<Card> gameDeck() {
	public Card gameCard() {
		Card returnCard = null;
		GameCards gameCards = new GameCards(numDecks);
		gameCards.initiateDeck(numDecks);
		//return gameCards.getGameCards();
		returnCard = gameCards.getFirstCard();
		return returnCard;
	}
}


