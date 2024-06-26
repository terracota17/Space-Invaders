package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public class ShockWave extends GameObject{

	
	protected static int DAMAGE = 1;
	protected static int LIFE_STATIC = 1;  
     	

	public ShockWave(GameWorld game, Position pos, int life) {
		super(game, pos, life);
	}
	
	@Override
	public boolean performAttack(GameItem other) {
		return other.receiveAttack(this);
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
		return 0;
	}

	@Override
	public void onDelete() {
	}

	@Override
	public void automaticMove(int cycleFromGame) {
		
	}

	@Override
	public void receiveDamage(int damage) {
		this.life-=damage;
	}

	@Override
	public boolean receiveAttack(SuperLaser slaser) {
		return false;
	}
}
