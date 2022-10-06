package nl.hu.bep2.casino.blackjack.domain;

import java.util.List;

@Entity
public class Dealer extends Hand{
	 @Id
	 @GeneratedValue
	private long id;
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
