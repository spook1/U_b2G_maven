package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.List;

import javax.persistence.GeneratedValue;



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
	
	public Card getFirstDealerCard () {
		Card firstDealerCard = this.getKaartenOpHand().get(1);
		return firstDealerCard;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	



}
