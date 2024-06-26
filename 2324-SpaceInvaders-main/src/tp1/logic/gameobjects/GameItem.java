package tp1.logic.gameobjects;

import tp1.logic.Position;

public interface GameItem {
	
	
	public boolean isAlive();
	public boolean isOnPosition(Position pos);
	public boolean checkOutsideMap(Weapon w);
	public boolean checkOutsideMap(Ufo u);
	
	//ataque
	public boolean performAttack(GameItem other);
	
	//recibir ataque
	public boolean receiveAttack(EnemyWeapon weapon);
	public boolean receiveAttack(UCMWeapon weapon);
	public boolean receiveAttack(SuperLaser slaser);
	public void explote(int damage, Position pos);
	public boolean receiveAttack(ShockWave shock);
	
}
