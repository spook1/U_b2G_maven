package nl.hu.bep2.casino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nl.hu.bep2.casino.blackjack.domain.GameCards;

@SpringBootApplication
public class CasinoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CasinoApplication.class, args);
        
        GameCards playdeck = new GameCards(1);
        playdeck.initiateDeck(1);
    }
    
}
