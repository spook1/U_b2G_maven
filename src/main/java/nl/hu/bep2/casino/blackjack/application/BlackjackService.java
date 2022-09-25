package nl.hu.bep2.casino.blackjack.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;   // 	moet dit? Staat niet in de handleiding...

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.Player;

@Service
public class BlackjackService {
	
	
	//USER BEDENKT SPELERSNAAM, GEEFT AAN MET HOEVEEL DECKS ZE WIL SPELEN EN DOET EEN EERSTE INZET
	//ER WORDT DAN SPELER-INSTANTIE AANGEMAAKT, DIE 1OO CHIPS KRIJGT ALS START EN ER WORDT EEN GAME AANGEMAAKT, MET AUTOMATISCH EEN SET GAMECARDS EN EEN DEALERHAND
	//SPELER KRIJGT HET OBJECT SPELER TERUG WAARIN DE CHIPS EN KAARTEN-OP-DE-HAND ZIJN OPGENOMEN
	public Map<Player,Card> startGame(String playerName,int numberOfDecks, int bet) {
		 
		Player player = new Player(playerName);
		Game game= new Game(player, numberOfDecks, bet);
		
		Map<Player,Card> map = new HashMap<>();
		Card openDealerCard= game.getDealer().getFirstDealerCard();
		
		map.put(player, game.getFirstDealerCard())
		
		return player;
	}

}
