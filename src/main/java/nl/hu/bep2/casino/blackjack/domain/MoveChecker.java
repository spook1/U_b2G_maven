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
	
	
	public static GameState checkAndHandleMove(Move move, Game game) throws Exception {
		
		Player player = game.getPlayer();
		Dealer dealer = game.getDealer();
		GameState gameState = game.getGameState();
		GameCards gameCards = game.getGameCards();
		
		 	//handscore heeft twee waarden, eerste waarde telt de aas als 11, tweede waarde telt de aas als 1, meestal zijn ze dus gelijk.
	 if ( (gameState != GameState.waiting) && (gameState != GameState.playing)) {
		 throw new Exception("Het spel is al bezig en kan niet nog een keer gestart worden. De GameState is: " + gameState);
	 }
		  switch(move){ 
		  // eerste keer twee kaarten uitdelen, als er 21 is is dat in de eerste waarde
		  case bet: 
			  if (gameState != GameState.waiting) {
					throw new Exception("Het spel is al gestart. De GameState is : " + gameState);
				}
			  if (player.getHandScore() == 21) {
				  if (dealer.getHandScore()  == 21) {
					  gameState = GameState.push;
				  }
				  else gameState = GameState.blackjack;
			  }
			  else gameState = GameState.playing;	
		  break;
		  
		  case hit: 
			  if (gameState != GameState.playing) {
					throw new Exception("Je kunt nu niet hitten. De GameState is : " + gameState);
			  }	  
			  //neem één kaart uit de Gamecards (deck van deze game) en voeg die toe aan de spelerhand)
			  System.out.println("we gaan nu de speler een kaart egven");
			  player.addCardToHand(game.getGameCards().pullCard());
			  System.out.println("de spelr heeft de kaart");
			 
			  if (player.getHandScore() == 21) {
				  gameState = GameState.blackjack;
			  }
			  else if (player.getHandScore() > 21) {
				  gameState = GameState.bust;
			  }
			  else gameState = GameState.playing;	
		  break;
		  case stand: 
			  if (gameState != GameState.playing) {
					throw new Exception("Je kunt nu niet standen. De GameState is : " + gameState);
				}
			 
			  while (dealer.getHandScore() < 17) {
				  // geef dealer kaarten net zolang tot dealerhandwaarde boven 17 is, daarna vergelijken wat de uitkomst is
				  dealer.addCardToHand(game.getGameCards().pullCard());	  
			  } 
			  if (dealer.getHandScore() > 21) {
				  gameState = GameState.won;
			  }
			  else if (dealer.getHandScore() > player.getHandScore()) {
				  gameState = GameState.lost;
			  }
			  else if ((dealer.getHandScore() < player.getHandScore())){
				  gameState = GameState.won;	
			  }
			  else if ((dealer.getHandScore() == player.getHandScore())){
				  gameState = GameState.push;	
			  }
		  break;
		  case doubleDown:
			  if (gameState != GameState.playing) {
					throw new Exception("Je kunt nu geen double down doen. De GameState is : " + gameState);
			  }
			  player.addCardToHand(game.getGameCards().pullCard());
			  if (dealer.getHandScore() > 21) {
				  gameState = GameState.won;
			  }
			  else if (dealer.getHandScore() > player.getHandScore()) {
				  gameState = GameState.lost;
			  }
			  else if ((dealer.getHandScore() < player.getHandScore())){
				  gameState = GameState.won;	
			  }
			  else if ((dealer.getHandScore() == player.getHandScore())){
				  gameState = GameState.push;	
			  }
		 break;
		 case surrender:
			  if (gameState != GameState.playing) {
					throw new Exception("Je kunt nu niet surrenderen. De GameState is : " + gameState);
			  }
			  gameState = GameState.surrendered;
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
