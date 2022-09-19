package nl.hu.bep2.casino.blackjack.domain;

public class Game {
	
	private GameCards gameCards;
	private GameState gameState;
	
	public Game(GameCards gameCards, GameState gameState) {
		super();
		this.gameCards = gameCards;
		this.gameState = gameState;
	}
	
	

}
