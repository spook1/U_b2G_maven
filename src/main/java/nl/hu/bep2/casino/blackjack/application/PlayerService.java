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
import nl.hu.bep2.casino.security.application.UserService;
import nl.hu.bep2.casino.security.domain.User;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private UserService userService;


	public List<Player> GetPlayerByUsername(String username) {
		
		User user = userService.loadUserByUsername(username);
		List<Player> players = playerRepository.findByUser(user);          //.orElse(null);
		
		return players;
	}
	
	public Player getPlayerById(Long playerId) {
		Player player = playerRepository.findById(playerId).orElse(null);
		if (player !=null) {
			return player;
		}
		else {
			return null;
		}
	}
}
	
