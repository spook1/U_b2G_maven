package nl.hu.bep2.casino.blackjack.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import nl.hu.bep2.casino.blackjack.data.GameRepository;
import nl.hu.bep2.casino.blackjack.data.PlayerRepository;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.chips.application.ChipsService;
import nl.hu.bep2.casino.security.application.UserService;
import nl.hu.bep2.casino.security.domain.User;

public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private UserService userService;
	
	public PlayerService(){
	}

	public Player GetPlayerByUsername(String username) {
		
		User user = userService.loadUserByUsername(username);
		Player player = playerRepository.findByUser(user).orElse(null);
		
		return player;
	}
}
	
