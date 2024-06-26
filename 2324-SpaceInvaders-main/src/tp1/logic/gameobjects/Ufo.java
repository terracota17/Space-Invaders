package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Ufo extends GameObject{

	 // Atributos estáticos
	protected  static final int ARMOUR = 1;
    protected  static final int LIFE_STATIC = 1;
    protected  static final int DAMAGE = 0;
    protected  static final int POINTS = 25;

    // Atributos privados de la clase Ufo
    private boolean enabled = false;
    //private int dimesionX;
    private int speed;
    private Move dir;

    
	public Ufo(Game game, Position pos) {
		super(game, pos, 1);
		
		//this.dimesionX = dimx;
		this.dir = Move.LEFT;
	}

	//Ataque
	
	@Override
	public void receiveDamage(int damage) {
		this.life-=damage;
		
		if(life <= 0) {
			this.onDelete();
		}
		
	}
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		if(this.pos != null && pos.isOnPosition(weapon.pos)) {
			this.receiveDamage(weapon.DAMAGE);
			weapon.receiveDamage(weapon.life);	
			enableShockWave();
			enabled = false;
			return true;
		}
		return false;		
	}
	
	@Override
	public boolean isOnPosition(Position pos) {
		return super.isOnPosition(pos);
	}

	public void enableShockWave() {
		super.game.enableShock();
	}
	
	@Override
	protected String getSymbol() {
		return Messages.UFO_SYMBOL;
	}
	
	@Override
    public String toString() {
    	return " " + this.getSymbol() + "[0" + this.life + "]";
	}

	@Override
	protected int getDamage() {
		return DAMAGE;
	}

	@Override
	protected int getArmour() {
		return ARMOUR;
	}

	@Override
	public void onDelete() {
		this.enabled = false;
		if(this.life <= 0) {
			game.addPoints(POINTS);
			this.life = LIFE_STATIC;
		}
	}

	@Override
	public String checkPosition(Position posi) {
		return (this.pos != null && posi!=null  && this.enabled && this.pos.equals(posi)) ?  toString() : "";
	}

	@Override
	public boolean isAlive() {
		if(isOutsideBorders()) {
			this.onDelete();
			
			if(this.life <= 0) {
				this.game.addPoints(POINTS);
				this.life = LIFE_STATIC;
			}
		}
		return true;
    }
	
	public static String list_static() {
		return Messages.alienDescription(Messages.UFO_DESCRIPTION , POINTS, DAMAGE, LIFE_STATIC);
	}

	 private boolean isOutsideBorders() {
		return this.pos != null && this.checkOutsideMap(this);
	};

	public boolean checkIfOutsideMap(int dimx, int dimy) {
		return this.pos.checkIfOutsideMap(dimx, dimy);
	}

	@Override
	public boolean computerAction()  {
		if (!enabled && canGenerateRandomUfo()) {
            enable();
            return true;
        }		
		return false;
	}
	
	private boolean canGenerateRandomUfo() {
        return game.getRandom().nextDouble() < game.getLevel().getUfoFrequency();
    }

    private void enable()  {
    	this.pos = game.cretePositionForUfo(this);
        this.enabled = true;
    }
    
    public Position createPosUfo(int dimx) {
    	return new Position(dimx,0);
    }

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public GameWorld getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Move getDir() {
		return dir;
	}

	public void setDir(Move dir) {
		this.dir = dir;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public static int getStaticLife() {
		return LIFE_STATIC;
	}

	public static int getPoints() {
		return POINTS;
	}

	@Override
	public boolean hasAnyoneLanded(int dimensionY) {
		return false;
	}

	@Override
	public void automaticMove(int cycleFromGame) {
		if(this.pos != null)
			this.pos.movePosition(Move.LEFT);
	}

	@Override
	public boolean receiveAttack(SuperLaser slaser) {
		if(this.pos != null && this.pos.isOnPosition(slaser.pos)) {
			receiveDamage(slaser.DAMAGE);
			slaser.receiveDamage(slaser.life);
			enableShockWave();
			return true;
		}
		return false;
	}

}
