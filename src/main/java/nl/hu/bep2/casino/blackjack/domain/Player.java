package nl.hu.bep2.casino.blackjack.domain;



import java.util.Arrays;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.LazyToOne;

import nl.hu.bep2.casino.chips.domain.Chips;
import nl.hu.bep2.casino.security.domain.User;

@Entity
public class Player extends Hand {

	@Id
	@GeneratedValue
	private long id;
	private User user;
	
	private Chips chips;    // niet via id koppelen aan een object, user heeft de link naar gepersisteerde chips.dit private obejct wordt gevuld met de chipsvan de user
	private String playerName;


	public Player(String playerName, Chips chips) {
		super();
		this.playerName = playerName;
		this.chips = chips;
		

	}
	
	public boolean depositChips(long amount) {
		
			chips.deposit(amount);
			return true;
	}
	
	public boolean withdrawChips(long amount) {
		
		chips.withdraw(amount);
		return true;
	}


	public Chips getChips() {
		return chips;
	}


	public void setChips(Chips chips) {
		this.chips = chips;
	}

	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public String toString() {
		return "Player [chips=" + chips + ", playerName=" + playerName + ", getChips()=" + getChips()
				+ ", getPlayerName()=" + getPlayerName() + ", getAllHandCards()=" + getAllHandCards()
				+ ", getHandScore()=" + Arrays.toString(getHandScore()) + ", getKaartenOpHand()=" + getKaartenOpHand()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
}
