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

import nl.hu.bep2.casino.blackjack.application.StartGameService;
import nl.hu.bep2.casino.blackjack.presentation.dto.GameInfoDto;

import nl.hu.bep2.casino.chips.domain.exception.NegativeNumberException;

import nl.hu.bep2.casino.security.domain.UserProfile;

@RestController
@RequestMapping("/game")
public class StartGameController {
	private final StartGameService service;
	
	public StartGameController(StartGameService service) {
		this.service =service;
	}
	
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
	
}
