package nl.hu.bep2.casino.blackjack.presentation.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.Move;

public class MakeMoveDto { 
	
	@NotNull(message = "Move enum value kan niet ontbreken")
	public Move move;
	
	@NotNull(message = "Game object kanniet ontbreken")
	public Long gameId;
	
	@Min(value = 1, message = "Bet must be greater than zero")
	@NotNull(message = "Bet amount cannot be null")
	public long amount;

	@Override
	public String toString() {
		return "MakeMoveDto [move=" + move + ", gameId=" + gameId + ", amount=" + amount + "]";
	}

	
	
}
