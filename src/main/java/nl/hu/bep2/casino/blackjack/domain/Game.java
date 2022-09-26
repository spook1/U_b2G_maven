package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.chips.domain.exception.NegativeNumberException;
import nl.hu.bep2.casino.security.domain.User;

public class Game {
	
	private GameCards gameCards;
	private GameState gameState;
	private Player player;
	private Dealer dealer= new Dealer();
	private boolean firstBet = false;
	private int numberOfDecks=1;
	private Move current_move;
	private Move last_move;
	
	//GAME WORDT AANGEMAAKT BIJ EERSTE BET, DE SPELER N DE DEALER KRIJGEN DAN METEEN TWEE KAARTEN. Dan is het spel op de wagen dus is de gamestate ook meteen playing.
	// speler plaatst bet, en betaalt ook meteen de bet.
	public Game(Player player, int numberOfDecks) {
		
		this.numberOfDecks = numberOfDecks;
		this.player = player;
		this.gameCards = new GameCards(numberOfDecks);
		this.gameState = GameState.waiting;
		this.dealer = new Dealer();
				
	}
	

									// het spel start altijd met een bet van amount
	public GameState start(long amount) {
		
			this.player.withdrawChips(amount);
			
			this.player.addCardToHand(gameCards.getCard());
			this.player.addCardToHand(gameCards.getCard());
			this.dealer.addCardToHand(gameCards.getCard());
			this.dealer.addCardToHand(gameCards.getCard());
			
			try {
				this.gameState = MoveChecker.checkAndHandleMove(Move.bet,this.player,this.dealer,this.gameState);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if (this.gameState == GameState.push){
				 player.depositChips(amount);   //speler krijgt inleg terug
			 }
			 else if( this.gameState == GameState.blackjack) {

				 player.depositChips((long)(1.5*amount)); 
			 }
			
			return this.gameState;
	}
	

	
	public Move selectMove(Move move) {
		
		this.last_move = this.current_move;
		this.current_move = move;
		
		return move;
	}
	
	public Dealer getDealer() {
		return this.dealer;
	}
	
	public GameState getGameState() {
		return this.gameState;
	}
	

	
	
	

}
