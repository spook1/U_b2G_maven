package nl.hu.bep2.casino.test;

import java.util.ArrayList;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.GameCards;

@RestController

public class TestController {
	
	private final static int numDecks =2;

	@GetMapping("/test")
	//public ArrayList<Card> gameDeck() {
	public ArrayList<Card> gameCard() {
	
		GameCards gameCards = new GameCards(numDecks);
		gameCards.initiateDeck(numDecks);
		return gameCards.getGameCards();

	}
}


