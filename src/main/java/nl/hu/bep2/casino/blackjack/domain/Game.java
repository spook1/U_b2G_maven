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
	private GameState gameState= GameState.waiting;
  
	@OneToOne(mappedBy="game", cascade = CascadeType.ALL)  
	private Player player;
	
    @OneToOne(mappedBy="game", cascade = CascadeType.ALL)
	private Dealer dealer;
	
	private Long inzet=(long) 0;
	
	private Integer numberOfDecks=1;

	
	public Game() {
		
	}
	
	public Game(User user, int numberOfDecks, Long inzet) {
		
		this.numberOfDecks = numberOfDecks;   // voor de zekerheid meegeven, misschien willen we later terugvragen hoeveel decks er gebruikt zijn..
		this.gameCards = new GameCards(numberOfDecks);
		
		this.gameState = GameState.waiting;
		
		this.player = new Player(user,this);   // een user maakt een spel aan, en daarmee meteen een speler
		//this.player.setGame(this);
		this.dealer = new Dealer(this);		//make a new dealer for this game
		//this.dealer.setGame(this);
		this.inzet = inzet;
		
	}
	
							// het spel start altijd met een bet van amount
	public GameState start() {
		
			this.player.addCardToHand(this.gameCards.pullCard());
			this.player.addCardToHand(this.gameCards.pullCard());
			this.dealer.addCardToHand(this.gameCards.pullCard());
			this.dealer.addCardToHand(this.gameCards.pullCard());
			
			this.gameState=GameState.playing;
			
			return this.gameState;
	}
	
	
	public Dealer getDealer() {
		return this.dealer;
	}
	
	public GameState getGameState() {
		return this.gameState;
	}
	
	public void setGameState(GameState gamestate) {
		 this.gameState=gamestate;
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

	public Long getInzet() {
		return inzet;
	}

	public void setFirstBet(Long inzet) {
		this.inzet = inzet;
	}


	public GameCards getGameCards() {
		
		System.out.println("getGameCards haalt GameCards : "+ this.gameCards);
		return this.gameCards;
	}


	@Override
	public String toString() {
		return "Game [id=" + id + ", gameCards=" + gameCards + ", gameState=" + gameState + ",  numberOfDecks=" + numberOfDecks + "]";
	}
	
		
	

	
	
	

}
