package nl.hu.bep2.casino.blackjack.domain;

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
	
	//GAME WORDT AANGEMAAKT NA EERSTE BET, DE SPELER N DE DEALER KRIJGEN DAN METEEN TWEE KAARTEN. Dan is het spel op de wagen dus is de gamestate ook meteen playing.
	public Game(Player player, int numberOfDecks, long bet) {
		
		this.numberOfDecks = numberOfDecks;
		this.player = player;
		this.gameCards = new GameCards(numberOfDecks);
		this.gameState = GameState.playing;
		this.dealer = new Dealer();
		
		this.player.addCardToHand(gameCards.getCard());
		this.player.addCardToHand(gameCards.getCard());
		this.dealer.addCardToHand(gameCards.getCard());
		this.dealer.addCardToHand(gameCards.getCard());
		
	}
	
	public Move selectMove(Move move) {
		
		this.last_move = this.current_move;
		this.current_move = move;
		
		return move;
	}
	
	public Dealer getDealer() {
		return this.dealer;
	}
	

	
	
	

}
