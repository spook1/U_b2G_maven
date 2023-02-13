package nl.hu.bep2.casino.blackjack.domain;



import java.util.Arrays;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Entity;


import org.hibernate.annotations.LazyToOne;
import org.springframework.transaction.annotation.Transactional;

import nl.hu.bep2.casino.chips.domain.Chips;
import nl.hu.bep2.casino.security.domain.User;

@Transactional
@Entity
public class Player extends Hand {

	@Id
	@GeneratedValue
	private long id;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="game_id")
	private Game game;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;    // niet via id koppelen aan een object, user heeft de link naar gepersisteerde chips. dit private object wordt in de applicatielaag gevuld met de chips van de user
							//dependency injection van chips in player in applicatielaag, blackjackservice, startgame

	public Player(){
	}	
	
	public Player(User user, Game game) {
		super();
		this.user = user;
		this.game=game;
		
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", game=" + game + ", user=" + user + "]";
	}
	
	
	
}
