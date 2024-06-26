package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class DestroyerAlien extends AlienShip {

    private boolean alreadyDeployedBomb = false;
	protected static int DAMAGE = 1;
	protected static int LIFE_STATIC = 1;  
	protected static int POINTS = 10;      
    
    public DestroyerAlien() {
        super(null, null, 1,null,0);
    }

    public DestroyerAlien(GameWorld game, Position pos, AlienManager alienManager, int cyclesToMove) {
        super(game, pos, LIFE_STATIC,alienManager,cyclesToMove);
        
    }
    
    public Position createPosForBomb() {
    	return this.pos.createNewPosForBomb();
    }
    
    public void enableBombDeployment() {
        this.alreadyDeployedBomb = false;
    }
    
    @Override
    protected String getSymbol() {
        return Messages.DESTROYER_ALIEN_SYMBOL;
    }
    
    @Override
    public void onDelete() {
    	 super.game.addPoints(POINTS);
         super.game.minusOneAllien();
    }
    
    @Override
    public String toString() {
    	return " " + this.getSymbol() +"[0" + this.life + "]";
    }

    @Override
    public boolean computerAction()  {
        if (!alreadyDeployedBomb && this.game.getRandomNumber() < game.getDestroyerFreq()) {

        	game.deployBomb(this);
            alreadyDeployedBomb = true;
            return true;
        }
        return false;
    }
    
	public  String list() {
		return Messages.alienDescription(Messages.DESTROYER_ALIEN_DESCRIPTION , POINTS, DAMAGE, LIFE_STATIC);
	}

	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am, int cyclesToMove) {
		return new DestroyerAlien(game, pos, am, cyclesToMove);
	}

	@Override
	public boolean receiveAttack(SuperLaser slaser) {
		if(this.pos.isOnPosition(slaser.pos)) {
			this.receiveDamage(slaser.getDamage());
			slaser.receiveDamage(DAMAGE); //el laser tammbien tiene que reci9bir el daño y morir
			return true;
		}
		return false;
	}

}

