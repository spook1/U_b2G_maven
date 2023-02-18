package nl.hu.bep2.casino.blackjack.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;

import nl.hu.bep2.casino.blackjack.data.CardListConverter;
import nl.hu.bep2.casino.chips.application.ChipsService;
import nl.hu.bep2.casino.chips.domain.Chips;

//import org.springframework.data.annotation.Id;

import nl.hu.bep2.casino.chips.domain.exception.NegativeNumberException;
import nl.hu.bep2.casino.security.domain.User;

@Transactional
@Entity
@JsonIgnoreProperties({"player","dealer"})
public class Game {
	

	@Id
    @GeneratedValue
	private long id;
	 
	@Convert(converter = CardListConverter.class)
	@Column(length = 20000)
	private GameCards gameCards;
    
    @Enumerated(EnumType.STRING)
	private GameState gameState;
  
	@OneToOne(mappedBy="game", cascade = CascadeType.ALL)  
	private Player player;
	
    @OneToOne(mappedBy="game", cascade = CascadeType.ALL)
	private Dealer dealer;
    
	private Move current_move;
	
	private Integer numberOfDecks;

	
	//GAME WORDT AANGEMAAKT BIJ EERSTE BET, DE SPELER N DE DEALER KRIJGEN DAN METEEN TWEE KAARTEN. Dan is het spel op de wagen dus is de gamestate ook meteen playing.
	// speler plaatst bet, en betaalt ook meteen de bet.
	
	public Game() {
		
	}
	
	public Game(User user, int numberOfDecks) {
		
		this.numberOfDecks = numberOfDecks;   // voor de zekerheid meegeven, misschien willen we later terugvragen hoeveel decks er gebruikt zijn..
		this.gameCards = new GameCards(numberOfDecks);
		this.gameState = GameState.waiting;
		
		this.player = new Player(user,this);   // een user maakt een spel aan, en daarmee meteen een speler
		//this.player.setGame(this);
		this.dealer = new Dealer(this);		//make a new dealer for this game
		//this.dealer.setGame(this);
		
	}
	

									// het spel start altijd met een bet van amount
	public GameState start(long amount, ChipsService chipsService) {
		
			String username = this.player.getUser().getUsername();
			
			this.player.addCardToHand(gameCards.getCard());
			this.player.addCardToHand(gameCards.getCard());
			this.dealer.addCardToHand(gameCards.getCard());
			this.dealer.addCardToHand(gameCards.getCard());
			
			this.gameState=GameState.playing;
			
			// dit blok code moet nog naar service verplaatst worden:
			Chips chips = chipsService.findChipsByUsername(username);
	        chips.withdraw(amount);
			
			try {
				this.gameState = MoveChecker.checkAndHandleMove(Move.bet,this.player,this.dealer,this.gameState);
				this.current_move = Move.bet;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if (this.gameState == GameState.push){
			 chips.deposit(amount);   //speler krijgt inleg terug
			 }
			 else if( this.gameState == GameState.blackjack) {

	     	 chips.deposit((long)(1.5*amount)); 
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

	public Player getPlayer() {
		return player;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
	
	

	public Move getCurrent_move() {
		return current_move;
	}

	public void setCurrent_move(Move current_move) {
		this.current_move = current_move;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", gameCards=" + gameCards + ", gameState=" + gameState + ",  numberOfDecks=" + numberOfDecks + "]";
	}
	
		
	

	
	
	

}
