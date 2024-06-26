package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExplosiveAlien extends AlienShip{


	private static int DAMAGE = 1;
	private static int LIFE_STATIC = 2;  
	private static int POINTS = 12;       

    public ExplosiveAlien() {
        super(null, null, LIFE_STATIC, null, 0);   
    }

    public ExplosiveAlien(GameWorld game, Position pos, AlienManager alienManager, int cyclesToMove) {
        super(game, pos, LIFE_STATIC, alienManager, cyclesToMove);
    }

    @Override
    protected String getSymbol() {
        return Messages.EXPLOSIVE_ALIEN_SYMBOL;
    }
    
    @Override
    public void onDelete() {
    	 super.game.addPoints(POINTS);
         super.game.minusOneAllien();    
         //explote
         this.game.explote(this, DAMAGE, pos);
    }
 
	public  String list() {
		return Messages.alienDescription(Messages.EXPLOSIVE_ALIEN_DESCRIPTION , POINTS, DAMAGE, LIFE_STATIC);
	}
    
    @Override
    public String toString() {
    	return " " + this.getSymbol() + "[0" + this.life + "]";
    }
    
	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am, int cyclesToMove) {
		return new ExplosiveAlien(game, pos, am, cyclesToMove);
	}
}
