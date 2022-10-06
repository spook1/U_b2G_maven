package nl.hu.bep2.casino.blackjack.application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.GameState;
import nl.hu.bep2.casino.blackjack.domain.Kleur;
import nl.hu.bep2.casino.blackjack.domain.Move;
import nl.hu.bep2.casino.blackjack.domain.MoveChecker;
import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.blackjack.domain.Waarde;
import nl.hu.bep2.casino.chips.data.ChipsRepository;
import nl.hu.bep2.casino.chips.domain.Chips;

@Service
public class BlackJackService {
	
		private Game game;
		private ChipsRepository chipsRepository;
		
		//USER BEDENKT SPELERSNAAM, GEEFT AAN MET HOEVEEL DECKS ZE WIL SPELEN EN DOET EEN EERSTE INZET
		//ER WORDT DAN SPELER-INSTANTIE AANGEMAAKT, DIE 1OO CHIPS KRIJGT ALS START EN ER WORDT EEN GAME AANGEMAAKT, MET AUTOMATISCH EEN SET GAMECARDS EN EEN DEALERHAND
		//SPELER KRIJGT INFORMATIE TERUG:
		//    HET OBJECT SPELER TERUG WAARIN DE CHIPS EN KAARTEN-OP-DE-HAND ZIJN OPGENOMEN 
		//    EEN KAART VAN DE DEALER (EN EEN TWEEDE GESLOTEN KAART, HANDIG VOOR DE FRONTEND OM TE WETEN DAT ER EEN LEGE KAART GTETEKEND MOET WORDEN)
		
		public BlackJackService() {
			
		}
		
		public List<Object> start(String playerName,int numberOfDecks, long amount){

			
			Chips chips = chipsRepository.findByUsername(playerName).orElse(null);
			Player player = new Player(playerName, chips);
			this.game= new Game(player, numberOfDecks);
			List<Object> gameInfo = new ArrayList<>();
			
			// gamestart plaatst bet ter hoogte van amount en rekent resultaat uit mvb van MoveChecker, past player aan ( hand en chips)
			this.game.start(amount) ;
				
					
			//Map<Player,Card[]> map = new HashMap<>();
			Card openDealerCard= game.getDealer().getFirstDealerCard();
			Card holeDealerCard = new Card(Kleur.achterkant, Waarde.achterkant);
			Card[] dealerCards = new Card[2];
			dealerCards[0]= openDealerCard;
			dealerCards[1]= holeDealerCard;
			//map.put(player, dealerCards);		
		
			gameInfo.add(player);  // hand en chips
			gameInfo.add(dealerCards); //een open en een dichte kaart
			gameInfo.add(game);
			
			
			//SAVE GAME
			Map<String, Game> gameMap = new HashMap<>();
			gameMap.put(playerName,  game);
			
			return gameInfo;
		}
		
		public List<Move> showMoves(GameState gameState){
			
			List<Move> moves = new ArrayList<>();
			moves = MoveChecker.showMoves(gameState);
			return moves;
			
		}
	}

