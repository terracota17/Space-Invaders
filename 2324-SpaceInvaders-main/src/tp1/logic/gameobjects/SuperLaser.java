package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class SuperLaser  extends Weapon{

	protected final int DAMAGE = 2;
    protected final int STATIC_LIFE = 1;

    private int dimensionX, dimensionY;

    public SuperLaser(GameWorld game, Position pos, int DIM_X, int DIM_Y) {
        super(game, pos, 1, Move.UP);
        this.dimensionX = DIM_X;
        this.dimensionY = DIM_Y;
    }

    // Métodos específicos de UCMWeapon

    @Override
  	public boolean weaponAttack(GameItem other) {
      	return other.receiveAttack(this);
  	}
    
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		if(this.pos.isOnPosition(weapon.pos)) {
			this.receiveDamage(weapon.DAMAGE);
			weapon.receiveDamage(DAMAGE);
			return true;
		}	
		return false;	
	}

	@Override
    protected String getSymbol() {
        return Messages.SUPERLASER_SYMBOL;
    }
	
	@Override
    public String toString() {
    	return this.getSymbol();
	}
	
    @Override
    protected int getDamage() {
        return DAMAGE;
    }

    @Override
    public void onDelete() {
        this.game.enableLaser();
    }

    @Override
    public void automaticMove(int cyclesFromGame) {
        moveUp();
        if (isOutsideBorders()) {
            this.onDelete();
        }
    }

    private void moveUp() {
        pos.movePosition(Move.UP);
    }

    private boolean isOutsideBorders() {
        return (this.pos.checkIfOutsideMap(this.dimensionX, this.dimensionY));
    }

	public int getSTATIC_LIFE() {
		return STATIC_LIFE;
	}

	@Override
	public boolean receiveAttack(SuperLaser slaser) {
		return false;
	}
}
