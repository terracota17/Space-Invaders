package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class RegularAlien extends AlienShip {

    protected static int DAMAGE = 1;
    protected  static int LIFE_STATIC = 2;  
    protected  static int POINTS = 5;       

    public RegularAlien() {
        super(null, null, LIFE_STATIC,null, 0);   
    }

    public RegularAlien(GameWorld game, Position pos, AlienManager alienManager, int cyclesToMove) {
        super(game, pos, LIFE_STATIC, alienManager, cyclesToMove);
    }

    @Override
    protected String getSymbol() {
        return Messages.REGULAR_ALIEN_SYMBOL;
    }
    
    @Override
    public void onDelete() {
    	 super.game.addPoints(POINTS);
         super.game.minusOneAllien();
    }
    
	public  String list() {
		return Messages.alienDescription(Messages.REGULAR_ALIEN_DESCRIPTION , POINTS, DAMAGE, LIFE_STATIC);
	}
    
    @Override
    public String toString() {
    	return " " + this.getSymbol() + "[0" + this.life + "]";
    }
    
	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am, int cyclesToMove) {
		return new RegularAlien(game, pos, am, cyclesToMove);
	}

	@Override
	public boolean receiveAttack(SuperLaser slaser) {
		if(this.pos.isOnPosition(slaser.pos)) {
			this.receiveDamage(slaser.DAMAGE);
			slaser.receiveDamage(DAMAGE); //el laser tammbien tiene que reci9bir el daño y morir
			return true;
		}
		return false;
	}

}
