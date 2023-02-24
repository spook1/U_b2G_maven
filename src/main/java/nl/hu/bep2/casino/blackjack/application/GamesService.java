package nl.hu.bep2.casino.blackjack.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChipsService chipsService;
	
	
	public GamesService(){
	}

	public Game GetGameByPlayer(long playerId) {
		
		Player player = this.playerService.getPlayerById(playerId);
		Game game = this.gameRepository.findByPlayer(player);
		System.out.println("player = "+ player);
		System.out.println("speler:   "+ player.getId() + "  /n vinden we game " + game.getId());
		
		return game;
		
	}
	

	public List<Object> ShowGamesByUsername(String username) {
		  
		  List<Object> gameList = new ArrayList<>();
		  List<Player> players = this.playerService.GetPlayerByUsername(username);
		  User user = userService.loadUserByUsername(username);
		  
		  for (Player p : players) {
		        Game game = this.gameRepository.findById(p.getGame().getId()).orElse(null);
		        Map<String, Object> gameInfo = new HashMap<>();
		        gameInfo.put("username", username);
		        gameInfo.put("Uitslag", game.getGameState());
		        gameInfo.put("Inzet", game.getInzet());
		        gameInfo.put("Totaal aantal chips", chipsService.findChipsByUsername(username).getAmount());
		        gameInfo.put("Spelers kaarten", game.getPlayer().getKaartenOpHand());
		        gameInfo.put("Dealers kaarten", game.getDealer().getKaartenOpHand());
		        gameInfo.put("GameId", game.getId());
		        gameList.add(gameInfo);
		    } 
		return gameList;
	}
	
	   

}
