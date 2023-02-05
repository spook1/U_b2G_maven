package nl.hu.bep2.casino.blackjack.data;

import java.util.List;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.GameCards;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Converter
public class CardListConverter implements AttributeConverter<GameCards, String> {

    private final Gson gson = new Gson();

    @Override
    public String convertToDatabaseColumn(GameCards cards) {
        return gson.toJson(cards);
    }

    @Override
    public GameCards convertToEntityAttribute(String cardJson) { 
        return gson.fromJson(cardJson, new TypeToken<List<Card>>() {}.getType());
    }
}
	
	
