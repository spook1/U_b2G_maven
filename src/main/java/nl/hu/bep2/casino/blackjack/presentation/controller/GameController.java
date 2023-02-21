package nl.hu.bep2.casino.blackjack.presentation.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import nl.hu.bep2.casino.blackjack.application.BlackJackService;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.presentation.dto.GameInfoDto;
import nl.hu.bep2.casino.blackjack.presentation.dto.MakeMoveDto;
import nl.hu.bep2.casino.chips.domain.exception.NegativeNumberException;

import nl.hu.bep2.casino.security.domain.UserProfile;

@RestController
@RequestMapping("/game")
public class GameController {
	private final BlackJackService service;
																			// StartGameService injecteren, en service noemen
	public GameController(BlackJackService service) {
		this.service =service;
	}
																			// endpoint start start methode startGame en haalt van endpointDto (string, int, long) op 
																			// die weer service.start inschakelt en met de Dto waarden aan het werk zet														
	@PostMapping("/start")
	public List<Object> startGame(Authentication authentication, @Validated @RequestBody GameInfoDto gameInfoDto){
		 UserProfile profile = (UserProfile) authentication.getPrincipal();
		 try {
			 	List<Object> gameInfo = new ArrayList<>();
			 	
			 	gameInfo = this.service.start(profile.getUsername(), gameInfoDto.numberOfDecks,gameInfoDto.amount);
	            return gameInfo;
	        } catch (NegativeNumberException exception) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
	        }
	}
	
	@PostMapping("/makemove")
	public List<Object> makeMove(Authentication authentication, @Validated @RequestBody MakeMoveDto makeMoveDto){
		
		 try {
			 	List<Object> moveInfo = new ArrayList<>();
			 	System.out.println("makeMoveDto =" + makeMoveDto);
			 	moveInfo = this.service.makeMove(makeMoveDto.gameId, makeMoveDto.move,makeMoveDto.amount);
	            return moveInfo;
	        } catch (NegativeNumberException exception) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
	        }
	}
	
	
}
