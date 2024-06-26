package tp1.logic;

import java.util.Random;

import tp1.control.InitialConfiguration;
import tp1.exceptions.InitializationException;
import tp1.exceptions.LaserInFlightException;
import tp1.exceptions.NoShockWaveException;
import tp1.exceptions.NotAllowedMoveException;
import tp1.exceptions.NotEnoughtPointsException;
import tp1.exceptions.OffWorldException;
import tp1.logic.gameobjects.AlienShip;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.EnemyWeapon;
import tp1.logic.gameobjects.ExplosiveAlien;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.ShipFactory;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.Ufo;
import tp1.logic.gameobjects.Weapon;
import tp1.view.Messages;


public class Game implements GameStatus, GameWorld, GameModel {

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;
	
	private GameObjectContainer container;
	private UCMShip player;
	private AlienManager alienManager;
	private int currentCycle = 0;
	
	private long seed;
	private Random random;
	private Level level;
	
	
	private boolean isEnabledShock = false;
	private int points= 0;
	private boolean doExit = false;
	
	
	public Game (Level level, long seed) throws InitializationException {
		
		//alien manager
		this.alienManager = new AlienManager(this, level, level.getNumCyclesToMoveOneCell(), level.getNumDestroyerAliens() + level.getNumRegularAliens());
				
		this.level = level;
	    this.currentCycle = 0;
	    this.points = 0;
	    this.seed = seed;
	    this.setRandom(new Random(seed));

		initGame(InitialConfiguration.NONE);	
	}
	
	@Override
	public void deployBomb(DestroyerAlien alien){
		this.container.add(new EnemyWeapon(this, alien.createPosForBomb(), 1, DIM_X, DIM_Y,alien ));
	}
	
	@Override
	public double getDestroyerFreq() {
		return this.level.getShootFrequency();
	}
	
	@Override
	public int speed() {
		return this.level.getNumCyclesToMoveOneCell();
	}
	
	@Override
	public void enableLaser() {
		this.player.enableLaser();
	}
	
	public double getFreqDestroyer() {
		return this.level.getShootFrequency();
	}
	
	@Override
	public boolean checkIfOutsideMap(Weapon weapon) {
		return weapon.checkIfOutsideMap(DIM_X, DIM_Y);
	}
	
	@Override
	public boolean checkIfOutsideMap(Ufo u) {
		return u.checkIfOutsideMap(DIM_X, DIM_Y);
	}
	
	@Override
	public double getRandomNumber() {
	    return this.random.nextDouble();
	}
	
	@Override
	public void minusOneAllien() {
		this.alienManager.minusOneAllien();
	}
	
	@Override
	public void addPoints(int points) {
		this.points+=points;
	}
	
	@Override
	public void enableShock() {
		this.isEnabledShock = true;
	}
	
	@Override
	public void  shootLaser()  throws  LaserInFlightException{
		container.shoot();
	}
	
	@Override
	public void superLaser(int COST) throws LaserInFlightException, NotEnoughtPointsException {
		if(points - COST >= 0 ) {
			points -= COST;
			if(!container.superLaser()) {
				throw new LaserInFlightException(Messages.SUPERLASER_ERROR);
			}
		}else 
			throw new NotEnoughtPointsException( String.format(Messages.NOT_ENOUGH_POINTS_ERROR,this.points, 5));
	}
	
	@Override
	public void explote(ExplosiveAlien a, int damage, Position pos) {
		container.explote(a, damage, pos);
	}
	
	@Override
	public void reset(InitialConfiguration conf) throws InitializationException {
		//alien manager
		this.alienManager = new AlienManager(this, level, level.getNumCyclesToMoveOneCell(), level.getNumDestroyerAliens() + level.getNumRegularAliens());
		
		initGame(conf);
	
		this.isEnabledShock = false;
	    this.currentCycle = 0;
	    this.points = 0;
	    this.setRandom(new Random(seed));
	}

	@Override
	public Position cretePositionForUfo(Ufo u) {
		return u.createPosUfo(DIM_X);
	}

	private void initGame (InitialConfiguration conf) throws InitializationException{	
		
		container = alienManager.initialize(conf);
		player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		container.add(player);
	}
	
	@Override
	public void move(Move move) throws OffWorldException,NotAllowedMoveException {
		player.move(move, DIM_X, DIM_Y);			
	}
	
	private boolean checkEnd() {
	    return (playerWin() || aliensWin());
	}
	
	public Position getUCMShipPos() {
		return player.getPosition();
	}

	//CONTROL METHODS
	
	public boolean isFinished() {
		return (checkEnd() || doExit);
	}
	
	@Override
	public void exit() {
		doExit = true;
	}

	public void update()  {
	    this.currentCycle++;

	    this.container.computerActions();
	    this.container.automaticMoves(this.currentCycle);
	    	    
	    //erase dead bodies
	    this.container.eraseDeadObjects();
	}

	//CALLBACK METHODS
	
	public void addObject(GameObject object) {
	    this.container.add(object);
	}
	
	public String positionToString(int col, int row)  {
		return container.toString(new Position(col, row));
	}
		
	@Override
	public String infoToString() {
		return null;
	}

	@Override
	public String stateToString() {
	    return Messages.PLAYER_LIFE_POINTS.formatted(this.player.getVida()) + "\n" +
	           Messages.PLAYER_POINTS.formatted(points) + "\n" +
	           Messages.SHOCKWAVE_STATUS.formatted(isEnabledShock ? Messages.ON : Messages.OFF) + "\n";
	}

	@Override
	public boolean playerWin() {
		return this.alienManager.playerWins();
	}

	public boolean haveLanded() {
		return container.hasAnyoneLanded(DIM_Y - 1);
	}
	
	@Override
	public boolean aliensWin() {
		return (haveLanded() || !player.isAliveUCMShip());
	}

	@Override
	public int getCycle() {
		return currentCycle;
	}

	@Override
	public int getRemainingAliens() {
		return alienManager.getRemainingAliens();
	}

	@Override
	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public GameObjectContainer getContainer() {
		return container;
	}

	public void setContainer(GameObjectContainer container) {
		this.container = container;
	}

	public UCMShip getPlayer() {
		return player;
	}

	public void setPlayer(UCMShip player) {
		this.player = player;
	}

	public AlienManager getAlienManager() {
		return alienManager;
	}

	public void setAlienManager(AlienManager alienManager) {
		this.alienManager = alienManager;
	}

	public int getCurrentCycle() {
		return currentCycle;
	}

	public void setCurrentCycle(int currentCycle) {
		this.currentCycle = currentCycle;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	@Override
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public boolean isEnabledShock() {
		return isEnabledShock;
	}

	public void setEnabledShock(boolean isEnabledShock) {
		this.isEnabledShock = isEnabledShock;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public static int getDimX() {
		return DIM_X;
	}

	public static int getDimY() {
		return DIM_Y;
	}

	@Override
	public void shockWave() throws NoShockWaveException {
		if(isEnabledShock) {
			isEnabledShock = false;
			container.shockWave(this, player.createPosShockWave(), alienManager.getRemainingAliens());
		}else
			throw new NoShockWaveException(Messages.SHOCKWAVE_ERROR);

	}

	@Override
	public void list() {
		System.out.println(this.player.list());
		System.out.println(ShipFactory.list());
		System.out.println(Ufo.list_static());	
	    System.out.println();  
	}

	@Override
	public void checkAttacksBy(AlienShip a) {
		// TODO Auto-generated method stub
		this.container.checkAttacksBy(a);
	}

	
}

/*public void writeFile(String nFichero) throws IOException {

		BufferedWriter write = new BufferedWriter(new FileWriter(new File(nFichero)));
		write.write(stringifier());
		write.close();
	}*/