package nl.hu.bep2.casino.blackjack.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.Player;

public interface GameRepository  extends JpaRepository<Game, Long>{
	//Optional<Game> findByPlayer(Player player);
}


