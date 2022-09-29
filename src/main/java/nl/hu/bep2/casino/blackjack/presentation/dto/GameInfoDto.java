package nl.hu.bep2.casino.blackjack.presentation.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GameInfoDto {


	@Min(value = 1, message = "number of decks must be greater than zero")
	public int numberOfDecks;
	@Min(value = 1, message = "Bet must be greater than zero")
	public long amount;
	
}
