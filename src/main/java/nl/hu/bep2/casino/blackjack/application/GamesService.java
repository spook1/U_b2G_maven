package nl.hu.bep2.casino.blackjack.application;

import java.util.ArrayList;
import java.util.List;

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
	
	public GamesService(){
	}

	public List<Game> GetGamesByPlayer(Player player) {
		
		List<Game> gameList = new ArrayList<>();
		this.gameRepository.findByPlayer(player);
		
		return gameList;
		
	}
	
	public List<Game> GetGamesByUsername(String username) {
		
		List<Game> gameList = new ArrayList<>();
		User user = this.userService.loadUserByUsername(username);
		gameList = this.gameRepository.findByPlayerUser(user);
		System.out.println("voor user:"+ user+ "vinden we gameList size: " + gameList.size());
		return gameList;
		
	}
	
	   

}
