package nl.hu.bep2.casino.blackjack.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.security.domain.User;

public interface PlayerRepository extends JpaRepository<Player, Long>{
		Optional<Player> findByUser(User user);
}



