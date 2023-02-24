package nl.hu.bep2.casino.blackjack.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import nl.hu.bep2.casino.blackjack.domain.GameState;
import nl.hu.bep2.casino.blackjack.domain.Move;
import nl.hu.bep2.casino.blackjack.domain.MoveChecker;

@Service
public class MovesService {
	public List<Move> showMoves(GameState gameState){
		
		List<Move> moves = new ArrayList<>();
		moves = MoveChecker.showMoves(gameState);
		return moves;
		
	}
}
