package nl.hu.bep2.casino.blackjack.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.security.domain.User;
import nl.hu.bep2.casino.security.domain.UserProfile;

public interface PlayerRepository extends JpaRepository<Player, Long>{
		List<Player> findByUser(User user);
}



