
Dit documentje beschrijft de api van de blackjack component:

*/blackjack/start*
POST username, bet (number of chips)
RETURNs List<Object>( playercards, dealercard (one is hole-card), gamestate, new amount of chips)

*/blackjack/makeMove/{move}*
GET move
RETURNs List<Object>( playercards, dealercard + hole-card, gamestate, new amaount of chips)

*/blackjack/listmoves/{game_id}*
GET game_id
RETURNS array of possible moves

*/blackjack/listgames/{user_id}*
GET user_id
RETURNS a list of active games

*/blackjack/resumegame/{game_id}*
GET game_id
RETURNS List<Object>( playercards, dealercard (one is hole-card), gamestate, new amount of chips)