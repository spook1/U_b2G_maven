package nl.hu.bep2.casino.blackjack.application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.lang.Collections;
import nl.hu.bep2.casino.blackjack.data.GameRepository;
import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.GameState;
import nl.hu.bep2.casino.blackjack.domain.Kleur;
import nl.hu.bep2.casino.blackjack.domain.Move;
import nl.hu.bep2.casino.blackjack.domain.MoveChecker;
import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.blackjack.domain.Waarde;
import nl.hu.bep2.casino.chips.application.Balance;
import nl.hu.bep2.casino.chips.application.ChipsService;
import nl.hu.bep2.casino.chips.data.ChipsRepository;
import nl.hu.bep2.casino.chips.domain.Chips;
import nl.hu.bep2.casino.security.data.UserRepository;
import nl.hu.bep2.casino.security.domain.User;

@Service
public class BlackJackService {
	
		@Autowired
		private GameRepository gameRepository;
		@Autowired
		private ChipsRepository chipsRepository;
		@Autowired
		private ChipsService chipsService;
		@Autowired
		private UserRepository userRepository;
		
		//USER BEDENKT SPELERSNAAM, GEEFT AAN MET HOEVEEL DECKS ZE WIL SPELEN EN DOET EEN EERSTE INZET
		//ER WORDT DAN SPELER-INSTANTIE AANGEMAAKT, DIE 1OO CHIPS KRIJGT ALS START EN ER WORDT EEN GAME AANGEMAAKT, MET AUTOMATISCH EEN SET GAMECARDS EN EEN DEALERHAND
		//SPELER KRIJGT INFORMATIE TERUG:
		//    HET OBJECT SPELER TERUG WAARIN DE CHIPS EN KAARTEN-OP-DE-HAND ZIJN OPGENOMEN 
		//    EEN KAART VAN DE DEALER (EN EEN TWEEDE GESLOTEN KAART, HANDIG VOOR DE FRONTEND OM TE WETEN DAT ER EEN LEGE KAART GTETEKEND MOET WORDEN)
		
		public BlackJackService() {
			
		}
		
		public List<Object> start(String username,int numberOfDecks, long inzet){
		
			Chips chips = chipsRepository.findByUsername(username).orElse(null);
			User user = userRepository.findByUsername(username).orElse(null);
			List<Object> gameInfo = new ArrayList<>();
			List<Card> dealerCards = new ArrayList<>();
			
		    Game game= new Game(user, numberOfDecks, inzet);
		
			chips.withdraw(inzet);    // bet plaatsen
			
			game.start() ;   // kaarten delen aan speler en dealer
			
			//berekenen wat de stand van zaken is
			//we starten de game dus de eerste Move is een bet, die is al ingevuld, eerste inzet was al bekend gemaakt bij creatie van gameobject
			// inzet is opgeslagen in een game zodat die ook bewaard is in de databse.
			try {
				game.setGameState( MoveChecker.checkAndHandleMove(Move.bet,game));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// nu moeten we kijken welke kaarten de speler te zien krijgt, als 
			//als de speler 21 heeft en de dealer ook eindigt het spel (push)
			//als de speler 21 heeft en de dealer niet (blackjack) 
			// mag de speler toch ook nog even de dealerkaarten zien.
			
			
			
			 if (game.getGameState() == GameState.push){
				 dealerCards = game.getDealer().getKaartenOpHand();
				 chips.deposit(inzet);   //speler krijgt inleg terug
			 }
			 else if( game.getGameState() == GameState.blackjack) {
				 dealerCards = game.getDealer().getKaartenOpHand();
					chips.deposit((long)(2.5*inzet)); //speler krijgt inzet terug + 1,5 inzet winst totaal 2,5
			 }
			 else {
			 
				 Card blindeKaart = new Card(Kleur.achterkant,Waarde.achterkant);
				 
				 dealerCards.add(game.getDealer().getFirstDealerCard());
				 dealerCards.add(blindeKaart);
				 
			 }
			 
			 						// de hele ronde is nu afgehandeld, speler is betaald, nieuwe game wordt opgeslagen
		     this.gameRepository.save(game);
			
			 
									 // nu heeft de server alles uitgerekend, nu nog  aan de gebruiker laten weten wat er gebeurd is.
									 // we sturen 
									 // - de kaarten op tafel van speler en van de dealer, 
									 // - de inzet(hoewel de speler dat zelf heeft opgegeven)
									 // - de nieuwe balance
									 // - de gamestate, ter verklaring van de nieuwe balans
        
	    	Balance balance = chipsService.findBalance(username);
					
	    	gameInfo.add(game.getId());  // kaarten op hand speler
	    	gameInfo.add(game.getPlayer().getKaartenOpHand());  // kaarten op hand speler
			gameInfo.add(dealerCards); //dealer kaarten een open en een dichte kaart, tenzij spel al is afgelopen
			gameInfo.add(game.getInzet());// wat ahd de speler ingezet
			gameInfo.add(game.getGameState());//nodig om volgende moves te bepalen
			gameInfo.add(balance.getChips());  //  chips, naam van de speler, laate maal geupdated, en huidge aantal chips
			
			return gameInfo;
		}
		
		
		
		public List<Object> makeMove(long gameId,Move move, long inzet){
			
		    System.out.println("nu gaan we de game opsnorren");
		    Game game = this.gameRepository.getGameById(gameId);
		    if ( game==null) {
		        throw new IllegalArgumentException("Er is geen game gevonden met deze id: " + gameId);
		    }
		    //System.out.println("de dealer is"+ game.getDealer());
				
			String username=null;
			try {
				username = game.getPlayer().getUser().getUsername();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Chips chips = chipsRepository.findByUsername(username).orElse(null);
			List<Object> gameInfo = new ArrayList<>();
			List<Card> dealerCards = new ArrayList<>();
			
			//check of een geldige move is gekozen, zo ja, voer uit en retrun de nieuwe gamestate
			if (MoveChecker.showMoves(game.getGameState()).contains(move)) {
				try {
					game.setGameState( MoveChecker.checkAndHandleMove(move,game) );
				
					if (move == Move.doubleDown) {
						chips.withdraw(inzet); // bij doubldedown nog een keer de inzet innen
					}
					else if (move == Move.surrender) {
						chips.deposit((long) Math.round(inzet/2));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
			if (game.getGameState() == GameState.blackjack){
				chips.deposit((long)(1.5*inzet)); //speler krijgt 1,5 x inzet terug 
				dealerCards = game.getDealer().getKaartenOpHand();
			}
			else if (game.getGameState() == GameState.won) {
				chips.deposit((long)(2*inzet)); //speler krijgt 2x inzet terug 
				dealerCards = game.getDealer().getKaartenOpHand();
			}
			else if (game.getGameState() == GameState.lost) {
											//speler krijgt niks terug 
				dealerCards = game.getDealer().getKaartenOpHand();
			}
			else if (game.getGameState() == GameState.push) {
				chips.deposit((long)(inzet)); //speler krijgt  inzet terug 
				dealerCards = game.getDealer().getKaartenOpHand();
			}
			else if (game.getGameState() == GameState.bust) {
				dealerCards = game.getDealer().getKaartenOpHand(); //speler krijgt geen chips, maar mag wel de dealercards zien
			}
			else if (game.getGameState() == GameState.playing) {
				
				dealerCards.add(game.getDealer().getFirstDealerCard());  // als nog steeds gewoon playing krijgen we de dealerkaarten nog niet allebei te zien
				
				Card blindeKaart = new Card(Kleur.achterkant,Waarde.achterkant);
				dealerCards.add(blindeKaart);
			}
			
			
			Balance balance = chipsService.findBalance(username);
			
			
			
			gameInfo.add(game.getPlayer().getKaartenOpHand());  // kaarten op hand speler
			gameInfo.add(dealerCards); //dealer kaarten een open en een dichte kaart
			gameInfo.add(game.getInzet());// wat ahd de speler ingezet
			gameInfo.add(game.getGameState());//nodig om volgende moves te bepalen
			gameInfo.add(balance.getChips());  //  chips, naam van de speler, laate maal geupdated, en huidge aantal chips
			
			return gameInfo;
		
		}
	}

