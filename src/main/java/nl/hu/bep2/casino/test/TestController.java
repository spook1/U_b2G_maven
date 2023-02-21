package nl.hu.bep2.casino.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.GameCards;
import nl.hu.bep2.casino.blackjack.domain.Hand;


@RestController

public class TestController {
	
	private final static int numDecks =1;

	@GetMapping("/test")
	//public ArrayList<Card> gameDeck() {
	public Map<Integer,Hand> testGameCards() {
	
		Hand playerHand = new Hand();
		GameCards gameCards = new GameCards(numDecks);
		
		int score = 0;
		//return gameCards.getGameCards();
		
		Map<Integer,Hand> map = new HashMap<>();
		
		playerHand.addCardToHand(gameCards.getCard());
		playerHand.addCardToHand(gameCards.getCard());
		 
		score = playerHand.getHandScore();
		map.put( score, playerHand);
		
		return map;
		//	return gameCards.getCard();
			
	}
}


