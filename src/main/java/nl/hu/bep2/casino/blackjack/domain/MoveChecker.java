package nl.hu.bep2.casino.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class MoveChecker {
	
	private Move move;
	private Hand playerHand;
	private Hand dealerHand;
	private GameState gameState;
	
	public MoveChecker() {
		super();
	}
	
	
	public static GameState checkAndHandleMove(Move move, Player player , Dealer dealer, GameState gameState) throws Exception {
		
		 	//handscore heeft twee waarden, eerste waarde telt de aas als 11, tweede waarde telt de aas als 1, meestal zijn ze dus gelijk.
		 if (gameState != GameState.playing) {
			 throw new Exception("Het spel is al afgelopen. De GameState is: " + gameState);
		 }
			  switch(move){ 
			  // eerste keer twee kaarten uitdelen, als er 21 is is dat in de eerste waarde
			  case bet: 
				  if (gameState != GameState.waiting) {
						throw new Exception("Het spel is al gestart. De GameState is : " + gameState);
					}
				  if (player.getHandScore()[1] == 21) {
					  if (dealer.getHandScore()[1]  == 21) {
						  gameState = GameState.push;
					  }
					  else gameState = GameState.blackjack;
				  }
				  else gameState = GameState.playing;	
			  break;
		  } 
		  
		return gameState;
	}
	
	public static List<Move> showMoves(GameState gameState) {
		List<Move> moveOptions = new ArrayList<>();
		switch(gameState) {
		case waiting:
			moveOptions.add(Move.bet);
		break;
		case playing:
			moveOptions.add(Move.hit);
			moveOptions.add(Move.stand);
			moveOptions.add(Move.doubleDown);
			moveOptions.add(Move.surrender);
		break;
		case push:
			moveOptions= null;
		break;
		case blackjack:
			moveOptions= null;
		break;
		case bust:
			moveOptions= null;
		break;
		case lost:
			moveOptions= null;
		break;
		case surrendered:
			moveOptions= null;
		break;
		case won:
			moveOptions= null;
		break;
		}
		return moveOptions;
	}
}
