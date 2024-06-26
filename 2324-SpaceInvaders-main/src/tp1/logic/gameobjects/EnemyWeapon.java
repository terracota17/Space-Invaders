package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class EnemyWeapon extends Weapon {
	
    private DestroyerAlien alien = null;

    protected final int ARMOUR = 1;
    protected final int DAMAGE = 1;

    private int dimension_x;
    private int dimension_y;
    
    public EnemyWeapon(Game game, Position pos, int life, int dimx, int dimy, DestroyerAlien alien) {
        super(game, pos, life, (Move.DOWN));
        this.setDimension_x(dimx);
        this.setDimension_y(dimy);
        this.alien = alien;
    }

   
    @Override
    protected String getSymbol() {
        return Messages.BOMB_SYMBOL;
    }
      
	@Override
    public String toString() {
    	return getSymbol();
    }

    @Override
	public boolean weaponAttack(GameItem other) {
    	if(other.isOnPosition(this.pos)) {
    		this.receiveDamage(DAMAGE);
    		other.receiveAttack(this);
    	}
    	return true;
	}
    

	@Override
	public boolean performAttack(GameItem other) {
		return this.weaponAttack(other);
	}

	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		if(this.pos.isOnPosition(weapon.pos)) {
			this.receiveDamage(weapon.DAMAGE);
			weapon.receiveDamage(DAMAGE); //el laser tammbien tiene que reci9bir el daño y morir
			return true;
		}
		
		return false;	
	}

	@Override
    protected int getDamage() {
        return DAMAGE;
    }
	
	@Override
    protected int getArmour() {
        return this.ARMOUR;
    }

    @Override
    public void onDelete() {
        this.alien.enableBombDeployment();  
    }
    
    @Override
    public void automaticMove(int cyclesFromGame) {
        descent();
    }

    @Override
    public void descent() {
        pos.movePosition(Move.DOWN);
    }

	public int getDimension_x() {
		return dimension_x;
	}

	public void setDimension_x(int dimension_x) {
		this.dimension_x = dimension_x;
	}

	public int getDimension_y() {
		return dimension_y;
	}

	public void setDimension_y(int dimension_y) {
		this.dimension_y = dimension_y;
	}

	@Override
	public boolean receiveAttack(SuperLaser slaser) {
		if(this.pos.isOnPosition(slaser.pos)) {
			this.receiveDamage(slaser.getDamage());
			slaser.receiveDamage(DAMAGE); //el laser tammbien tiene que recibir el daño y morir
			return true;
		}
		return false;
		
	}
}
