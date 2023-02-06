package nl.hu.bep2.casino.blackjack.data;

import java.io.IOException;
import java.util.List;

import nl.hu.bep2.casino.blackjack.domain.Card;
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
	  	
	    this.gameCards = gameCards;
	  
	  	final ObjectMapper mapper = new ObjectMapper();
	  	
	    String gameCardsJson = null;
	    try {
	        gameCardsJson = mapper.writeValueAsString(gameCards);
	        
	        System.out.println(gameCardsJson);
	    } catch (final JsonProcessingException e) {
	        //logger.error("JSON writing error", e);
	    }
	    return gameCardsJson;
	}
	  
	@Override
	public GameCards convertToEntityAttribute(String gameCardsJson) {
	
		final ObjectMapper mapper = new ObjectMapper();
	   
	    try {
	        gameCards = mapper.readValue(gameCardsJson, GameCards.class );
	        	
	    } catch (final IOException e) {
	       // logger.error("JSON reading error", e);
	}
	
	return gameCards;
}

	/*
	 * private final Gson gson = new Gson();
	 * 
	 * @Override public String convertToDatabaseColumn(GameCards cards) { return
	 * gson.toJson(cards); }
	 * 
	 * @Override public GameCards convertToEntityAttribute(String cardJson) { return
	 * gson.fromJson(cardJson, new TypeToken<List<Card>>() {}.getType()); }
	 */
}
	
	
