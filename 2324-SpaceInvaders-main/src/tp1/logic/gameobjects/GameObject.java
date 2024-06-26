package tp1.logic.gameobjects;


import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class GameObject implements GameItem {

	protected Position pos;
	protected int life;
	protected GameWorld game;
	
	public GameObject(GameWorld game, Position pos, int life) {	
		this.pos = pos;
		this.game = game;
		this.life = life;
	}
	
	@Override
	public boolean isAlive() {
		return this.life > 0;
	}
		
	@Override
	public boolean receiveAttack(ShockWave shock) {
		return false;
	}

	@Override
	public boolean isOnPosition(Position pos) {
		return  this.pos != null && this.pos.equals(pos);
	}

	protected int getLife() {
		return this.life;
	}
	
	public boolean hasAnyoneLanded(int dimensionY) {
		return false;
	}
	
	@Override
	public boolean checkOutsideMap(Weapon w) {
		return game.checkIfOutsideMap(w);
	}
	
	@Override
	public boolean checkOutsideMap(Ufo w) {
		return game.checkIfOutsideMap(w);
	}
	
	public boolean isAnyRightBorder(int dimx) {
		return false;
	}
		
	public void descent() {	}
	
	public GameObject createLaserShoot()  {return null;}
	public GameObject createSuperLaserShoot() {return null;}
	
	public String checkPosition(Position posi) {
		return (this.pos != null && this.pos.equals(posi)) ?  toString() : "";
	}
		
	@Override
	public void explote(int damage, Position pos) {
		if(this.pos != null && this.pos.isInExplosionRange(pos)) {
			this.receiveDamage(damage);
		}
	}
	
	public String list() {return "";}
	public static String list_static() {return "";}
	
	protected abstract String getSymbol();
	protected abstract int getDamage();
	protected abstract int getArmour();
	
			
	public abstract void onDelete();
	public abstract void automaticMove(int cycleFromGame);
	public boolean computerAction()  {return false;};
	public abstract void receiveDamage(int damage);
	
	
	@Override
	public boolean performAttack(GameItem other) {return false;}
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {return false;}

	@Override
	public boolean receiveAttack(UCMWeapon weapon) {return false;}

}
