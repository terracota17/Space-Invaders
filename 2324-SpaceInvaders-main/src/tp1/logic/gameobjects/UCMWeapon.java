package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class UCMWeapon extends Weapon {

	protected  final int DAMAGE = 1;
	protected  final int STATIC_LIFE = 1;



    public UCMWeapon(GameWorld game, Position pos) {
        super(game, pos, 1, Move.UP);
    }
    
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
        return Messages.LASER_SYMBOL;
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
        return (this.pos.checkIfOutsideMap(Game.DIM_X, Game.DIM_Y));
    }

	public int getSTATIC_LIFE() {
		return STATIC_LIFE;
	}

	@Override
	public boolean receiveAttack(SuperLaser slaser) {
		return false;
	}
}