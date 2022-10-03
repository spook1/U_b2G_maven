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
import nl.hu.bep2.casino.blackjack.domain.GameState;
import nl.hu.bep2.casino.blackjack.domain.Move;
import nl.hu.bep2.casino.blackjack.presentation.dto.GameInfoDto;
import nl.hu.bep2.casino.chips.domain.exception.NegativeNumberException;
import nl.hu.bep2.casino.security.domain.UserProfile;

@RestController
@RequestMapping("/game")
public class ShowMovesController {

	private final BlackJackService service;
	// StartGameService injecteren, en service noemen
	public ShowMovesController(BlackJackService service) {
		this.service =service;
	}
	
	@PostMapping("/showmoves")
	public List<Move> showMoves(Authentication authentication, @Validated @RequestBody GameState gameState){
		 UserProfile profile = (UserProfile) authentication.getPrincipal();
		 try {
			 	List<Move> moves = new ArrayList<>();
			 	
			 	moves = this.service.showMoves(gameState);
	            return moves;
	        } catch (NegativeNumberException exception) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
	        }
	}
		
}
