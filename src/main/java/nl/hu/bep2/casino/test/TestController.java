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
	
	private final static int numDecks =2;

	@GetMapping("/test")
	//public ArrayList<Card> gameDeck() {
	public Map<int[],Hand> testMethode() {
	
		Hand playerHand = new Hand(1);
		GameCards gameCards = new GameCards(numDecks);
		
		int[] score = new int[2];
		//return gameCards.getGameCards();
		
		Map<int[],Hand> map = new HashMap<>();
		
		playerHand.addCardToHand(gameCards.getCard());
		playerHand.addCardToHand(gameCards.getCard());
		 
		score = playerHand.getHandScore();
		map.put( score, playerHand);
		
		return map;
		//	return gameCards.getCard();
			

	}
}


