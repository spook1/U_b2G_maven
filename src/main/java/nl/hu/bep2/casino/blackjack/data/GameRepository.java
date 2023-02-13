package nl.hu.bep2.casino.blackjack.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.security.domain.User;

public interface GameRepository  extends JpaRepository<Game, Long>{
	Optional<Game> findByPlayer(Player player);
	
//	List<Game> FindAllByPlayerUser(User user);
}


