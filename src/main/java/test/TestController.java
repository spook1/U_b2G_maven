package test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import nl.hu.bep2.casino.blackjack.domain.GameCards;

@RestController
public class TestController {
	
	@GetMapping("/test")
	public GameCards gameCards(@RequestParam(value = "numberOfDecks") int numberOfDecks){
	
		return new GameCards(1);
	}
}


