package tp1.logic.gameobjects;


import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends GameObject {

	protected static int DAMAGE = 1;
	protected static int LIFE_STATIC = 0;  
	protected static int POINTS = 0;      
	 
    protected Move dir = Move.LEFT;

    protected boolean movedDown=false;
    protected AlienManager alienManager;
  
    private int cyclesToMove;
    private int speed;
    
    public AlienShip() {
        super(null, null, 0);
    }

    // Constructor normal
    public AlienShip(GameWorld game, Position pos, int life, AlienManager am, int speed) {
        super(game, pos, life);
        this.cyclesToMove = speed;
        this.speed = speed;  
        this.alienManager = am;
    }
    
    
 

	protected abstract AlienShip copy(GameWorld game, Position pos, AlienManager am, int cyclesToMove);
    
    @Override
    public boolean isAlive() {
        return this.life > 0;
    }

    @Override
    protected int getLife() {
        return super.getLife();
    }

	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		return super.receiveAttack(weapon);
	}

    @Override
    public boolean receiveAttack(UCMWeapon weapon) {
        if (this.pos.isOnPosition(weapon.pos)) {
            receiveDamage(weapon.getDamage());
            weapon.receiveDamage(DAMAGE);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOnPosition(Position posi) {
        return this.pos.isOnPosition(posi);
    }

    @Override
    protected String getSymbol() {
         return "";
    }

    @Override
    protected int getDamage() {
        return DAMAGE;
    }

    @Override
    protected int getArmour() {
        return DAMAGE;
    }

    @Override
    public void onDelete() {
       
    }
    
    public boolean isAnyRightBorder(int dimx) {
        return pos.isAnyRightBorder(dimx);
    }
    
    public boolean isAnyLeftBorder(int dimy) {
    	return this.pos.isAnyLeftBorder(dimy);
    }
    
    //Movimiento de AlienShip
    public void automaticMove(int cycle) {
    	
    	if(cyclesToMove % cycle == 0) {
    		performMovement(dir);
			cyclesToMove = speed;
    		if(pos.isInBorder(Game.DIM_X, dir)) {
    			alienManager.shipOnBorder();
    		}
    	}else if(alienManager.isOnBorder()) {
    		descent();
    	}else
			cyclesToMove--;		
    }
    
  
    @Override
	public boolean receiveAttack(ShockWave shock) {
		receiveDamage(ShockWave.DAMAGE);
		shock.receiveDamage(DAMAGE);
    	return true;
	}
    
    public void descent() {
    	if(!isInFinalRowAnyOne()) {
    		performMovement(Move.DOWN);
    		
			dir = dir.flip(); 
    		
    		 this.alienManager.decreaseOnBorder();
    		 if(isInFinalRowAnyOne()) {
    			 this.alienManager.setSquadInFinalRow(true);
    		 }
    	} 
    	
    	//checkattacks
    	game.checkAttacksBy(this);
    }
    
    private boolean isInFinalRowAnyOne() {
    	return pos.isInFinalRowAnyOne(Game.DIM_Y);
    }

    public void performMovement(Move dir) {
    	pos.movePosition(dir);
    }
        
    @Override
	public boolean receiveAttack(SuperLaser slaser) {
		if(pos.isOnPosition(slaser.pos)) {
			this.receiveDamage(slaser.DAMAGE);
			slaser.receiveDamage(DAMAGE);
			return true;
		}
		return false;
	}
   
    public int speed() {
    	return game.speed();
    }
    
    public AlienManager getAlienManager() {
        return alienManager;
    }

    public void setAlienManager(AlienManager alienManager) {
        this.alienManager = alienManager;
    }

    public static int getPoints() {
        return POINTS;
    }

    @Override
    public boolean hasAnyoneLanded(int dimensionY) {
        return pos.landed(dimensionY);
    }

    @Override
    public void receiveDamage(int damage) {
        this.life -= damage;
    }

    @Override
    public boolean computerAction()  {
        return false;
    }

	public  String list() {
		return "";
	}

}