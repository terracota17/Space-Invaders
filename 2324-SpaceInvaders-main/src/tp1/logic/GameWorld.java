package tp1.logic;

import java.util.Random;

import tp1.logic.gameobjects.AlienShip;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.ExplosiveAlien;
import tp1.logic.gameobjects.Ufo;
import tp1.logic.gameobjects.Weapon;

public interface GameWorld {
	
	public void addPoints(int points);
    public void minusOneAllien();
   
    public void deployBomb(DestroyerAlien alien) ;
    public void enableLaser();
    public void enableShock();
    public void checkAttacksBy(AlienShip a);
    
    public Random getRandom();
    public Level getLevel();
	public double getRandomNumber();  
	
	public double getDestroyerFreq();
	public Position cretePositionForUfo(Ufo u) ;
	public boolean checkIfOutsideMap(Weapon weapon);
	public boolean checkIfOutsideMap(Ufo e);
	
	public int speed();
	public void explote(ExplosiveAlien e, int damage, Position pos);
}
