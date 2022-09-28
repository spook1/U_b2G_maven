
package nl.hu.bep2.casino.blackjack.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Game;

import nl.hu.bep2.casino.blackjack.domain.Kleur;
import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.blackjack.domain.Waarde;

@RestController
//@Service
public class StartGame {
		
		
		//USER BEDENKT SPELERSNAAM, GEEFT AAN MET HOEVEEL DECKS ZE WIL SPELEN EN DOET EEN EERSTE INZET
		//ER WORDT DAN SPELER-INSTANTIE AANGEMAAKT, DIE 1OO CHIPS KRIJGT ALS START EN ER WORDT EEN GAME AANGEMAAKT, MET AUTOMATISCH EEN SET GAMECARDS EN EEN DEALERHAND
		//SPELER KRIJGT INFORMATIE TERUG:
		//    HET OBJECT SPELER TERUG WAARIN DE CHIPS EN KAARTEN-OP-DE-HAND ZIJN OPGENOMEN 
		//    EEN KAART VAN DE DEALER (EN EEN TWEEDE GESLOTEN KAART, HANDIG VOOR DE FRONTEND OM TE WETEN DAT ER EEN LEGE KAART GTETEKEND MOET WORDEN)
		
		public List<Object> startGame(String playerName,int numberOfDecks, long amount) {

			Player player = new Player(playerName);
			Game game= new Game(player, numberOfDecks);
			List<Object> gameInfo = new ArrayList<>();
			
			// gamestart plaatst bet ter hoogte van amount en rekent resultaat uit mvb van MoveChecker, past player aan ( hand en chips)
			game.start(amount) ;
				
					
			//Map<Player,Card[]> map = new HashMap<>();
			Card openDealerCard= game.getDealer().getFirstDealerCard();
			Card holeDealerCard = new Card(Kleur.achterkant, Waarde.achterkant);
			Card[] dealerCards = new Card[2];
			dealerCards[0]= openDealerCard;
			dealerCards[1]= holeDealerCard;
			//map.put(player, dealerCards);		
		
			gameInfo.add(player);  // hand en chips
			gameInfo.add(dealerCards); //een open en een dichte kaart
			gameInfo.add(game.getGameState());
			
			
			//SAVE GAME
			Map<String, Game> gameMap = new HashMap<>();
			gameMap.put(playerName,  game);
			
			return gameInfo;
		}
	}

