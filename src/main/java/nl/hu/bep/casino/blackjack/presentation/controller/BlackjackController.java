package nl.hu.bep.casino.blackjack.presentation.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import nl.hu.bep2.casino.blackjack.application.BlackjackService;
import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.chips.application.Balance;
import nl.hu.bep2.casino.chips.domain.exception.NegativeNumberException;
import nl.hu.bep2.casino.chips.presentation.dto.Deposit;
import nl.hu.bep2.casino.security.domain.UserProfile;

@RestController
@RequestMapping("/game")
public class BlackjackController {
	private final BlackjackService service;
	
	public BlackjackController(BlackjackService service) {
		this.service =service;
	}
	
	@PostMapping("/start")
	public  Map<Player,Card[]> startGame(Authentication authentication, @Validated @RequestBody GameInfo gameInfo){
		 UserProfile profile = (UserProfile) authentication.getPrincipal();
		 try {
	            Game game = this.service.depositChips(profile.getUsername(), deposit.amount);
	            return balance;
	        } catch (NegativeNumberException exception) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
	        }
	}
	
	this.service (String playerName,int numberOfDecks, long amount)

}
