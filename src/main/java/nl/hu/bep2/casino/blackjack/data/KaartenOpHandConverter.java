package nl.hu.bep2.casino.blackjack.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.GameCards;
import nl.hu.bep2.casino.blackjack.domain.Hand;

@Converter
public class KaartenOpHandConverter implements AttributeConverter<List<Card>, String> {
	
	List<Card> kaartenOpHand = new ArrayList<Card>();
	@Override
	public String convertToDatabaseColumn(List<Card> kaartenOpHand) {
	  	
	  	final ObjectMapper mapper = new ObjectMapper();
	  	
	    String kaartenOpHandJson = null;
	    try {
	    	kaartenOpHandJson = mapper.writeValueAsString(kaartenOpHand);
	    	System.out.println("========================================== ");
	    	System.out.println("========================================== ");
	        System.out.println("kaartenOpHandConverter maakt deze JSON "+kaartenOpHandJson);
	    } catch (final JsonProcessingException e) {
	        //logger.error("JSON writing error", e);
	    }
	    return kaartenOpHandJson;
	}
	  
	@Override
	public List<Card> convertToEntityAttribute(String kaartenOpHandJson) {
	
		if (kaartenOpHandJson == null) {
	        System.out.println("kaartenOpHandJson is empty!!!");
	    }
		final ObjectMapper mapper = new ObjectMapper();
	   
	    try {
	        return  mapper.readValue(kaartenOpHandJson, new TypeReference<List<Card>>(){});
	        	
	    } catch (final IOException e) {
	       // logger.error("JSON reading error", e);
	    }
	
	    return null;
	}
}

