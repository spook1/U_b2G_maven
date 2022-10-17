package nl.hu.bep2.casino.blackjack.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.Id;

//import org.springframework.data.annotation.Id;

import nl.hu.bep2.casino.chips.domain.exception.NegativeNumberException;
import nl.hu.bep2.casino.security.domain.User;

@Transactional
@Entity
public class Game implements Serializable{
	
	@Id
    @GeneratedValue
	private long id;
	 
    @Lob
	private GameCards gameCards;
    
    @Enumerated(EnumType.STRING)
	private GameState gameState;
  
	@OneToOne(mappedBy="game", cascade = CascadeType.ALL)  //MAG JE IN DIT GEVAL MAPPED BY WEGLATEN OMDAT DAT VOOR ZOCH SPREEKT?
	private Player player;
	
    @OneToOne(mappedBy="game", cascade = CascadeType.ALL)
	private Dealer dealer;
    
	private Move current_move;

	
	//GAME WORDT AANGEMAAKT BIJ EERSTE BET, DE SPELER N DE DEALER KRIJGEN DAN METEEN TWEE KAARTEN. Dan is het spel op de wagen dus is de gamestate ook meteen playing.
	// speler plaatst bet, en betaalt ook meteen de bet.
	
	public Game() {
		
	}
	
	public Game(Player player, int numberOfDecks) {
		
		
		this.player = player;
		this.gameCards = new GameCards(numberOfDecks);
		this.gameState = GameState.waiting;
		this.dealer = new Dealer();
	
				
	}
	

									// het spel start altijd met een bet van amount
	public GameState start(long amount) {
		
			String username = this.player.getUser().getUsername();
			getChips().withdraw(amount);
			
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
		
		this.current_move = move;
		
		return move;
	}
	
	public Dealer getDealer() {
		return this.dealer;
	}
	
	public GameState getGameState() {
		return this.gameState;
	}
	
	   public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
		
		

	
	
	

}
