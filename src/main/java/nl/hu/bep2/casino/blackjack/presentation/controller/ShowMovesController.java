package nl.hu.bep2.casino.blackjack.presentation.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import nl.hu.bep2.casino.blackjack.application.BlackJackService;
import nl.hu.bep2.casino.blackjack.application.MovesService;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.GameState;
import nl.hu.bep2.casino.blackjack.domain.Move;
import nl.hu.bep2.casino.blackjack.presentation.dto.GameInfoDto;
import nl.hu.bep2.casino.chips.domain.exception.NegativeNumberException;
import nl.hu.bep2.casino.security.domain.UserProfile;

@RestController
@RequestMapping("/game")
public class ShowMovesController {

	private final MovesService service;
	// StartGameService injecteren, en service noemen
	public ShowMovesController(MovesService service) {
		this.service =service;
	}
	// deze controller biedt de frontend de koeglijkheid om de speler een lijstje met mogelijke moves te toenen waaruit gekzoen kan worden. 
	//is technisch gezien niet echt noodzakelijk
	@PostMapping("/showmoves")
	@ResponseBody
	public List<Move> showMoves(Authentication authentication, @Validated @RequestBody GameState gameState){
	
		 try {
			 List<Move> moves = this.service.showMoves(gameState);
			 if (moves == null) {
	            
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Er zijn geen geldige moves meer voor dit spel, de gamestate is : " + gameState);
	            //return Collections.emptyList();
	            }
		        return moves;
		 }
		 catch (NegativeNumberException exception) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
	       }
	}
		
}
