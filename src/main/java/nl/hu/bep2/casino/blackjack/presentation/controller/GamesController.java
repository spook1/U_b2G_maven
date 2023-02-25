package nl.hu.bep2.casino.blackjack.presentation.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import nl.hu.bep2.casino.blackjack.application.BlackJackService;
import nl.hu.bep2.casino.blackjack.application.GamesService;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.GameState;
import nl.hu.bep2.casino.blackjack.domain.Move;
import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.blackjack.presentation.dto.GameInfoDto;
import nl.hu.bep2.casino.chips.domain.exception.NegativeNumberException;
import nl.hu.bep2.casino.security.application.UserService;
import nl.hu.bep2.casino.security.domain.UserProfile;


@RestController
@RequestMapping("/game")
public class GamesController {
	
	private final GamesService service;
	// SameService injecteren, en service noemen
	public GamesController(GamesService service) {
		this.service = service;
	}
	
	@PostMapping("/showgames")
	@ResponseBody
	public List<Object> showGames(Authentication authentication, UserService userService){
		UserProfile profile = (UserProfile) authentication.getPrincipal();
		
	 try {
		 	List<Object> gameList = new ArrayList<>();
		 	String username = profile.getUsername();
 	
			gameList = this.service.ShowGamesByUsername(username);
			
			if (gameList == null) {
	            
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Er zijn geen geldige games voor deze user");
	            
	            }
	        return gameList;
	            
	        } catch (NegativeNumberException exception) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
	        }
	}
}
