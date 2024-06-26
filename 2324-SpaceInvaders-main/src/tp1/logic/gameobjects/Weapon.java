package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class  Weapon extends GameObject {

	protected Move dir;
	
    // Constructor vacío
    public Weapon() {
        super(null, null, 1);
    }

    public Weapon(GameWorld game, Position pos, int life, Move dir) {
        super(game, pos, life);
        this.dir = dir;
    }

    // Métodos comunes

    @Override
    public boolean isAlive() {
        return this.life > 0 && !isOutsideBorders();
     }

    private boolean isOutsideBorders() {
		return checkOutsideMap(this);
	};

	public boolean checkIfOutsideMap(int dimx, int dimy) {
		return this.pos.checkIfOutsideMap(dimx, dimy);
	}

    @Override
    protected int getLife() {
        return super.getLife();
    }

    @Override
    public boolean performAttack(GameItem other) {
    	if(!other.isAlive() || !this.isAlive()) return false;
    	else return weaponAttack(other) && (other.isOnPosition(pos)) ;
    }

    @Override
    public boolean receiveAttack(EnemyWeapon weapon) {
        return super.receiveAttack(weapon);
    }

    @Override
    public boolean receiveAttack(UCMWeapon weapon) {
        return false; // Implementar según necesidades específicas de cada objeto Weapon
    }

    @Override
    public boolean isOnPosition(Position pos) {
        return this.pos.equals(pos);
    }

    @Override
    protected String getSymbol() {
        return ""; // Implementar según necesidades específicas
    }

    @Override
    protected int getDamage() {
        return 0; // Implementar según necesidades específicas
    }

    @Override
    protected int getArmour() {
        return 0; // Implementar según necesidades específicas
    }

    @Override
    public void onDelete() {
        // Implementar según necesidades específicas
    }

    @Override
    public void automaticMove(int cycle) {
        // Implementar según necesidades específicas
    }


	@Override
	public void receiveDamage(int damage) {
		this.life-=damage;
		
	}

	public abstract boolean weaponAttack(GameItem other);

}
