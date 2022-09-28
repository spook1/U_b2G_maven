package nl.hu.bep2.casino.blackjack.domain;



import java.util.Arrays;

import nl.hu.bep2.casino.chips.domain.Chips;
import nl.hu.bep2.casino.security.domain.User;

public class Player extends Hand{

	private Chips chips;
	private String playerName;

	

	public Player(String playerName) {
		super();
		this.playerName = playerName;
		long startChips = 100;
		this.chips = new Chips(this.playerName, startChips);

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
