package nl.hu.bep2.casino.blackjack.domain;

import java.util.List;

public class Dealer extends Hand{
	
	public Dealer () {
		super();
	}
	
	public Card getFirstDealerCard () {
		Card firstDealerCard = this.getKaartenOpHand().get(1);
		return firstDealerCard;
	}
	



}
