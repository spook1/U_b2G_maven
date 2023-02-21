package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.GeneratedValue;


@Transactional
@Entity
public class Dealer extends Hand{
	
	@Id
	@GeneratedValue
	private long id;
	@OneToOne(fetch=FetchType.LAZY)
	private Game game;
	
	public Dealer () {
		super();
	}
	
	public Dealer (Game game) {
		super();
		this.game = game;
	}
	
	public Card getFirstDealerCard () {
		Card firstDealerCard = this.getKaartenOpHand().get(0);
		return firstDealerCard;
	}
	
	public Card getSecondDealerCard () {
		Card secondDealerCard = this.getKaartenOpHand().get(1);
		return secondDealerCard;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public String toString() {
		return "Dealer [id=" + id + ", game=" + game + "kaartenophand"+ kaartenOpHand +"]";
	}
	
	
	



}
