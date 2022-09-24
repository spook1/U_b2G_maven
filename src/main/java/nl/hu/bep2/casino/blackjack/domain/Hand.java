package nl.hu.bep2.casino.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	
	private int owner=0;
	private List<Card> kaartenOpHand = new ArrayList<Card>();
	private int[] handScore = new int[2];
	
	
	
	public Hand(int owner) {

		this.owner = owner;
		handScore[0]=0;
		handScore[1]=0;
	}
	

		// berekent de score van de hand, twee waarden , één maal met aas =1 en eenmaal met aas =11, vaak zijn de waarden identiek, behalve als er een aas op hand is.
	  public int[] getHandScore() { 
		  
	  Card card = null; 
	  int[] score = new int[2];
	  score[0]=0; 
	  score[1]=0;
	  
	  this.kaartenOpHand.forEach(kaart ->{ 
		  switch(kaart.getWaarde() ){ 
		  case _2: score[0] +=2; score[1]+=2; 
		  break; 
		  case _3: score[0] +=3; score[1]+=3; 
		  break;
		  case _4: score[0] +=4; score[1]+=4; 
		  break; 
		  case _5: score[0] +=5; score[1]+=5; 
		  break; 
		  case _6: score[0] +=6; score[1]+=6; 
		  break; 
		  case _7: score[0] +=7; score[1]+=7; 
		  break; 
		  case _8: score[0] +=8; score[1]+=8; 
		  break;
		  case _9: score[0] +=9; score[1]+=9; 
		  break; 
		  case aas: score[0] +=11; score[1]+=1; 
		  break; 
		  case heer: score[0] +=10; score[1]+=10; 
		  break; 
		  case boer: score[0] +=10; score[1]+=10; 
		  break; case vrouw: score[0] +=10; score[1]+=10;
		  break; 
		  }
	  
	  }); 
	  
	 
	  this.handScore = score; 
	  return this.handScore;
	  
	  }
	 

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	public  boolean addCardToHand(Card card) {
		this.kaartenOpHand.add(card);
		return true;
	}

	public List<Card> getKaartenOpHand() {
		return kaartenOpHand;
	}

	public void setKaartenOpHand(List<Card> kaartenOpHand) {
		this.kaartenOpHand = kaartenOpHand;
	}


	
	

}
