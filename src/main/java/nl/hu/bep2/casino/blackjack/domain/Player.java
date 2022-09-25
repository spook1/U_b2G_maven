package nl.hu.bep2.casino.blackjack.domain;

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
	
}
