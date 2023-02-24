package nl.hu.bep2.casino.blackjack.data;

import java.io.IOException;
import java.util.List;

import nl.hu.bep2.casino.blackjack.domain.GameCards;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter
public class CardListConverter implements AttributeConverter<GameCards, String> {
	
	GameCards gameCards = new GameCards();
	@Override
	public String convertToDatabaseColumn(GameCards gameCards) {
		
	  	final ObjectMapper mapper = new ObjectMapper();
	   
	    String gameCardsJson = null;
	    try {
	        gameCardsJson = mapper.writeValueAsString(gameCards);
	    } catch (final JsonProcessingException e) {
	    	System.out.println("CardListConverter To DB Kolom geeft error"+ e);
	    }
	    return gameCardsJson;
	}
	  
	@Override
	public GameCards convertToEntityAttribute(String gameCardsJson) {
	
		if (gameCardsJson == null) {
	        System.out.println("gameCardsJson is empty!!!");
	    }
		final ObjectMapper mapper = new ObjectMapper();
	   
	    try {    	
	        return  mapper.readValue(gameCardsJson, GameCards.class );     	
	    } catch (final IOException e) {
	    	System.out.println("CardListConverter To Entity (from json) geeft error"+ e);   
	    }
	    return null;
	}
}
	
	
