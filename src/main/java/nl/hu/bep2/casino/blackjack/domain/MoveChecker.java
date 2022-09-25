package nl.hu.bep2.casino.blackjack.domain;

public class MoveChecker {
	
	private Move move;
	private Hand playerHand;
	private Hand dealerHand;
	private GameState gameState;
	
	public MoveChecker() {
		super();
	}
	
	
	public static GameState calculateGameState(Move move, Hand playerHand, Hand dealerHand, GameState gameState) {
		
		return gameState;
		
		
	}
	
	
	

}
