package tp1.logic.gameobjects;

import java.util.Arrays;

import tp1.exceptions.InitializationException;
import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

import java.util.List;

public class ShipFactory {
		
	private static final List<AlienShip> AVAILABLE_ALIEN_SHIPS = Arrays.asList(
			new RegularAlien(),
			new DestroyerAlien(),
			new ExplosiveAlien()
			
	);
	
	public static AlienShip spawnAlienShip(String input, GameWorld game, Position pos, AlienManager am, int cyclesToMove) throws InitializationException {
	    AlienShip ship = null;
	   
	    for (AlienShip s : AVAILABLE_ALIEN_SHIPS) {
	        if (input.equals(s.getSymbol())) {
	            ship = s.copy(game, pos, am, cyclesToMove);
	        }
	    }

	    if(ship == null)
	    	throw new InitializationException(Messages.INITIAL_CONFIGURATION_ERROR + "\n"+ String.format(Messages.UNKNOWN_SHIP, input));
	    return ship;
	}
	
	public static String list() {
		StringBuilder sb = new StringBuilder();
		for (AlienShip s: AVAILABLE_ALIEN_SHIPS) {
			sb.append(s.list()+ "\n");
		}
		return sb.toString();
	}
}
