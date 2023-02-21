package nl.hu.bep2.casino.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;

import nl.hu.bep2.casino.blackjack.data.KaartenOpHandConverter;


					// hier een superclass van maken zodat deze in de dealer en player meegenoemen worden en ook meegeperisteerd worden.
@MappedSuperclass
public class Hand {
	
	@Convert(converter = KaartenOpHandConverter.class )
	protected List<Card> kaartenOpHand = new ArrayList<Card>();
	
	public Hand() {
		super();
	}

		// berekent de score van de hand, twee waarden , één maal met aas =1 en eenmaal met aas =11, vaak zijn de waarden identiek, behalve als er een aas op hand is.
	   // eerste getal van de array geeft waarde waarin aas als 11 meetelt
	  public int getHandScore() { 
		  
	   int aantalAzen = 0;
	    int score = 0;
	    for (Card card : kaartenOpHand) {
	        switch (card.getWaarde()) {
	            case _2:
	                score += 2;
	                break;
	            case _3:
	                score += 3;
	                break;
	            case _4:
	                score += 4;
	                break;
	            case _5:
	                score += 5;
	                break;
	            case _6:
	                score += 6;
	                break;
	            case _7:
	                score += 7;
	                break;
	            case _8:
	                score += 8;
	                break;
	            case _9:
	                score += 9;
	                break;
	            case aas:
	                aantalAzen++;
	                score += 11;
	                break;
	            case heer:
	            case boer:
	            case vrouw:
	                score += 10;
	                break;
	        }
	    }
	    while (aantalAzen > 0 && score > 21) {
	        score -= 10;
	        aantalAzen--;
	    }
	    return score ;
	}
	 

	
	public  boolean addCardToHand(Card card) {
		this.kaartenOpHand.add(card);
		return true;
	}

	public List<Card> getKaartenOpHand() {
		return kaartenOpHand;
	}

	


	
	

}
