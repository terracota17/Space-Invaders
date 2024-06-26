package tp1.logic;


import tp1.control.InitialConfiguration;
import tp1.exceptions.InitializationException;
import tp1.exceptions.PostionCreationException;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.ShipFactory;
import tp1.logic.gameobjects.Ufo;
import tp1.view.Messages;


public class AlienManager  {
	
	private Level level;
	private Game game;
	private int remainingAliens;	
	
	private boolean squadInFinalRow;
	private int shipsOnBorder = 0;
	private boolean onBorder = false;
	private int cyclesToMoveAlliens = 0;
	
	public AlienManager(Game game, Level level, int cyclesToMove, int num_aliens) {
		this.level = level;
		this.game = game;
		this.remainingAliens = num_aliens;
		this.cyclesToMoveAlliens = cyclesToMove;
	}
	
	private void standardInitialization(GameObjectContainer container) {
		initializeRegularAliens(container);
		initializeDestroyerAliens(container);
	}
	
	private void initializeRegularAliens(GameObjectContainer container) {
		int numRows = level.getNumRowsRegularAliens();
		int numAliensPerRow = level.getNumRegularAliens() / numRows;
		int init_y = 1;
		int init_x = (Game.DIM_X / 2) - (numAliensPerRow / 2);

		for (int y = 0; y < numRows; y++) {
			for (int x = 0; x < numAliensPerRow; x++) {
				container.add(new RegularAlien(game, new Position(x + init_x, y + init_y), this, level.getNumCyclesToMoveOneCell()));
				this.remainingAliens++;
			}
		}
	}

	private void initializeDestroyerAliens(GameObjectContainer container) {
		int numAliensPerRow = level.getNumDestroyerAliens();

		int init_x = (Game.DIM_X / 2) - (numAliensPerRow / 2);
		int init_y = 1 + level.getNumRowsRegularAliens();

		for (int x = 0; x < numAliensPerRow; x++) {
			container.add(new DestroyerAlien(game, new Position(x + init_x, init_y), this, level.getNumCyclesToMoveOneCell()));
			this.remainingAliens++;
		}
	}
	

	public  GameObjectContainer initialize(InitialConfiguration conf) throws InitializationException {
	    
		this.remainingAliens = 0;
	    GameObjectContainer container = new GameObjectContainer();
   
	    if (conf == InitialConfiguration.NONE) {
			standardInitialization(container);
	    	
	    	
	    }else {
	    	//inicializacion custom
	    	initializeAllAliens(container, conf);   
	    }
	 
	     
	    initializeOvni(container);

	    return container;
	}
	
	private String problematicValue(String [] parts, NumberFormatException number) {
		return (number.getMessage().contains(parts[1])) ? parts[1] : parts[2];
	}

	
	public void minusOneAllien() {
		this.remainingAliens--;
	}
	
	public boolean playerWins() {
		return this.remainingAliens == 0;
	}
	
	public  void spawnAlien(String alienType, Position posi) throws InitializationException{
		ShipFactory.spawnAlienShip(alienType, this.game, posi, this, this.cyclesToMoveAlliens);
	}
	
	private void initializeOvni(GameObjectContainer container) {
		container.add(new Ufo(game, null)); //Position null inicialmente
	}
	
	private void initializeAllAliens(GameObjectContainer container, InitialConfiguration conf) throws InitializationException {
		 for (String linea : conf.getShipDescription()) {
	            String[] parts = linea.split(" ");

	            String alienType = parts[0];
	            int x, y;

	            try {
	            	if (parts.length < 3) {
	                   throw new PostionCreationException(Messages.INITIAL_CONFIGURATION_ERROR + "\n" +
	                		   							String.format(Messages.INCORRECT_ENTRY, linea));
	                }
	            	 
	                x = Integer.parseInt(parts[1]);
	                y = Integer.parseInt(parts[2]);
	                          
	                Position posi = new Position(x, y);
	                if(posi.checkIfOutsideMap(Game.DIM_X, Game.DIM_Y)) {
	                	throw new PostionCreationException(Messages.INITIAL_CONFIGURATION_ERROR + "\n" +
	                					String.format(Messages.INVALID_POSITION, x, y));
	                }
	                
		            container.add(ShipFactory.spawnAlienShip(alienType, game, posi, this, cyclesToMoveAlliens));
		            this.remainingAliens++;
	                
	            } catch (NumberFormatException number) {
	                throw new InitializationException(Messages.INITIAL_CONFIGURATION_ERROR + "\n"+
	                    String.format(Messages.PARSE_INTEGER_ERROR, problematicValue(parts, number), linea),
	                    number
	                );
	            }catch (PostionCreationException e) {
	            	throw new InitializationException( e.getMessage());
	            } 
		 }		
	}
	   
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getRemainingAliens() {
		return remainingAliens;
	}

	public void setRemainingAliens(int remainingAliens) {
		this.remainingAliens = remainingAliens;
	}

	public boolean isSquadInFinalRow() {
		return squadInFinalRow;
	}
	
	public void increaseShipInBorder() {
		shipsOnBorder++;
	}

	public void setSquadInFinalRow(boolean squadInFinalRow) {
		this.squadInFinalRow = squadInFinalRow;
	}
	
	public void shipOnBorder() {
		if(!onBorder) {
			onBorder = true;
			shipsOnBorder = remainingAliens;
		}
	}
	
	public void decreaseOnBorder() {
		shipsOnBorder--;
		if(shipsOnBorder == 0) {
			onBorder = false;
		}
	}

	public int getShipsOnBorder() {
		return shipsOnBorder;
	}

	public void setShipsOnBorder(int shipsOnBorder) {
		this.shipsOnBorder = shipsOnBorder;
	}

	public boolean isOnBorder() {
		return onBorder;
	}

	public void setOnBorder(boolean onBorder) {
		this.onBorder = onBorder;
	}
}