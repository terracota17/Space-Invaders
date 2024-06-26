package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.exceptions.NotAllowedMoveException;
import tp1.exceptions.OffWorldException;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class UCMShip extends GameObject {

	 // Atributos estáticos
	protected  final static int ARMOUR = 3;
	protected  final static int DAMAGE = 1;
	protected  static final int LIFE_STATIC= 3;
	
	private static final List<String> allowedMoves = Arrays.asList("left", "lleft", "right", "rright");

    // Atributos privados  
    private boolean canShoot = true;
    private boolean enabled = true;
    
    // Constructor normal
    public UCMShip(Game game, Position pos) {
       super(game, pos, LIFE_STATIC);
       this.canShoot = true;
    }
    
    public static List<String> allowedMoves(String separator) {
        return Collections.singletonList(String.join(separator, allowedMoves));
    }

    public int getArmour() {
        return ARMOUR;
    }
    
    public boolean isAliveUCMShip() {
    	return enabled && (this.life > 0);
    }
    
    public Position createPosSuperLaser() {
    	return this.pos.createPosSuperLaser();
    }
    
    public Position createPosShockWave() {
    	return this.pos.createPosShockWave();
    }

    private boolean canMove(Move move, int dimX, int dimY) {
    	return pos.canPlayerMove(move, dimX, dimY);
    }
    
    public void enableLaser() {
    	this.canShoot = true;
    }
    
    public boolean permittedMovement(Move move) {
    	return allowedMoves.contains(move.name().toLowerCase());
    }
    
    public boolean move(Move move, int dimX, int dimY) throws OffWorldException,NotAllowedMoveException{
    	if( move != null) {
    		if(canMove(move, dimX, dimY)){
        		if(permittedMovement(move)) {
        			pos.movePosition(move);
        			return true;
        		}else 
        			throw new NotAllowedMoveException(Messages.ALLOWED_UCMSHIP_MOVES);
    		}else
    			throw new OffWorldException(String.format(Messages.OFF_WORLD_MESSAGE,move.toString(), this.pos.toString()));
    	}
    	return false;
    }
    
    @Override
    public GameObject createLaserShoot()  {
        return (canShoot) ? createAndDisableLaser() : null;
    }

    private UCMWeapon createAndDisableLaser() {
        canShoot = false;
        return new UCMWeapon(this.game, this.pos.shoot());
    }

	
    @Override
    public GameObject createSuperLaserShoot()  {
        return (canShoot) ? createAndDisableSuperLaser() : null;
    }

    private SuperLaser createAndDisableSuperLaser()  {
        canShoot = false;
        return new SuperLaser(this.game, this.pos.shoot(), Game.DIM_X, Game.DIM_Y);
    }
    
	public  String list() {
		return Messages.ucmShipDescription(Messages.UCMSHIP_DESCRIPTION, DAMAGE, LIFE_STATIC);
	}

    public boolean isHereUCMShip(int col, int row) {
        return this.pos.equals(new Position(col, row));
    }
    
    @Override
    public boolean isAlive() {
    	if(!super.isAlive()) {
			this.onDelete();
			
		}
		return true;
    }

    public Position getPosition() {
        return super.pos;
    }

    @Override
	protected int getLife() {
		return super.getLife();
	}

	@Override
	public boolean hasAnyoneLanded(int dimensionY) {
		return super.hasAnyoneLanded(dimensionY);
	}

	@Override
	public String checkPosition(Position pos) {
		return super.checkPosition(pos);
	}

	@Override
	public boolean computerAction() {
		return super.computerAction();
	}

	@Override
	public boolean performAttack(GameItem other) {
		return super.performAttack(other);
	}

	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		if(this.pos.isOnPosition(weapon.pos)) {
			this.receiveDamage(weapon.DAMAGE);
			weapon.receiveDamage(weapon.life);
			return true;
		}

		return false;
	}

    public void setPosition(Position position) {
        this.pos = position;
    }

    public int getVida() {
        return life;
    }

    public void setVida(int vida) {
        this.life = vida;
    }

    public String getSymbol() {
        return Messages.UCMSHIP_SYMBOL;
    }

    @Override
    public String toString() {
        return (enabled && life > 0) ? 
        		this.getSymbol() 
        		: Messages.UCMSHIP_DEAD_SYMBOL;
    }
    
    public boolean isCanShoot() {
        return canShoot;
    }
 
    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public static int getStaticLife() {
        return LIFE_STATIC;
    }

	public GameWorld getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public boolean isOnPosition(Position pos) {
		return this.pos.isOnPosition(pos);
	}

	@Override
	protected int getDamage() {
		return DAMAGE;
	}

	@Override
	public void onDelete() {
		this.enabled = false;
	}

	@Override
	public void receiveDamage(int damage) {
		this.life-=damage;
	}
	
	@Override
	public void automaticMove(int cycleFromGame) {}

	@Override
	public boolean receiveAttack(SuperLaser slaser) {
		return false;
	}
}
