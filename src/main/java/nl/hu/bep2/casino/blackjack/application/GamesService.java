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
	private UserService userService;
	@Autowired
	private PlayerService playerService;
	
	
	public GamesService(){
	}

	public Game GetGameByPlayer(long playerId) {
		
		Player player = this.playerService.getPlayerById(playerId);
		Game game = this.gameRepository.findByPlayer(player).orElse(null);
		System.out.println("player = "+ player);
		System.out.println("speler:   "+ player.getId() + "  /n vinden we game " + game.getId());
		
		return game;
		
	}
	
	public void GetGamesByUsername(String username) {
		
		List<Player> players = this.playerService.GetPlayerByUsername(username);
		for ( Player p : players) {	
			System.out.println("=====================1");
			System.out.println("player = "+ p);
			System.out.println("=====================2");
			
			Game game =  this.gameRepository.findByPlayer(p).orElse(null);
			System.out.println("====================3");
			System.out.println("player ="+ p);
			if (game != null) {
				System.out.println("speler:   "+ p.getId() + "  /n vinden we game " + game.getId());
			}else{
				System.out.println("speler:   "+ p.getId() + "  heeft geen game");
			}
			
			
		}
	
		List<Game> gameList = this.gameRepository.findAll();
		for (Game g : gameList ) {
            System.out.println("Game =  " + g);
        }
		
		//return this.gameRepository.findAll();
		
	}
	
	   

}
