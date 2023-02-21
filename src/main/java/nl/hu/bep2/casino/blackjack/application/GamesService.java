package nl.hu.bep2.casino.blackjack.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.hu.bep2.casino.blackjack.data.GameRepository;
import nl.hu.bep2.casino.blackjack.data.PlayerRepository;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.chips.application.ChipsService;
import nl.hu.bep2.casino.chips.data.ChipsRepository;
import nl.hu.bep2.casino.security.application.UserService;
import nl.hu.bep2.casino.security.data.UserRepository;
import nl.hu.bep2.casino.security.domain.User;



@Service
public class GamesService {
	
	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerService playerService;
	
	
	public GamesService(){
	}

	public Game GetGameByPlayer(long playerId) {
		
		Player player = this.playerService.getPlayerById(playerId);
		Game game = this.gameRepository.findByPlayer(player);
		System.out.println("player = "+ player);
		System.out.println("speler:   "+ player.getId() + "  /n vinden we game " + game.getId());
		
		return game;
		
	}
	

	public List<Game> GetGamesByUsername(String username) {
		//User user = userService.loadUserByUsername(username);
		//List<Game> gameList = gameRepository.findAllByPlayerUser(user);
		//OMDAT BOVENSTAANDE AANPAK EEN NESTED NULL ERROR GEEFT, EN OP EEN OF ANDERE MANIER GAME NIET GEVONDEN KAN WORDEN ONDERSTAANDE WORKAAROUND GEVONDEN
		  
		List<Game> gameList = new ArrayList<>(); 
		  List<Player> players = this.playerService.GetPlayerByUsername(username);
		  
		  for ( Player p : players) { Game game =
		  this.gameRepository.findById(p.getGame().getId()).orElse(null);
		  gameList.add(game); }
		 
		return gameList;
		
		
		
	}
	
	   

}
