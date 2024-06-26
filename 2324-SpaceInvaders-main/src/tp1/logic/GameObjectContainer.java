package tp1.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import tp1.exceptions.LaserInFlightException;
import tp1.logic.gameobjects.AlienShip;
import tp1.logic.gameobjects.ExplosiveAlien;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.ShockWave;
import tp1.view.Messages;

public class GameObjectContainer {

	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<>();
	}

	public void add(GameObject object) {
		objects.add(object);
	}
	
	
	public void createExplosiveAlien(AlienShip newer) {
		this.objects.add(newer);
	}
	public void automaticMoves(int cycleFromGame) {
		for (int i=0;i<objects.size();i++) {
			objects.get(i).automaticMove(cycleFromGame);
	
			checkAttack(objects.get(i));
		}
	}
	
	
	public void checkAttack(GameObject attacker) {
		 for (GameObject target : objects) {
		        if (target != attacker) {
		        	attacker.performAttack(target);           
		        }
		  }	
	}
	
	public void checkAttacksBy(GameObject attacker) {
		 for (GameObject target : objects) {
		        if (target != attacker) {
		        	target.performAttack(attacker);           
		        }
		  }	
	}
	
	public void explote(ExplosiveAlien a, int damage, Position pos) {
		for(GameObject target: this.objects) {
			if(a != target ) { 
				target.explote(damage, pos);
			}
		}
	}
	

	public void computerActions()  {
		
		List<GameObject> copia = new ArrayList<>();
		copia.addAll(this.objects);
		 
        for (GameObject o : copia) {
            o.computerAction();
        }
	}
	
	public void descentAllAlliens() {
		for(int i = 0 ; i < this.objects.size();i++) {
			objects.get(i).descent();
		}
	}

	
	public boolean shoot()  throws LaserInFlightException{
		//mientras recorres puedes anadir
		boolean exito = false;
		ListIterator<GameObject> iterator = this.objects.listIterator();

		while (iterator.hasNext()) {
		    GameObject o = iterator.next();
		    GameObject laser = o.createLaserShoot();    
		    if (laser != null) {
		        iterator.add(laser); 
		        exito = true;
		    }
		}
		
		if(!exito) {
			throw new LaserInFlightException(Messages.LASER_ALREADY_SHOT);
		}
		
		return exito;

	}
	
	public boolean superLaser() {
		boolean exito = false;
		ListIterator<GameObject> iterator = this.objects.listIterator();

		while (iterator.hasNext()) {
		    GameObject o = iterator.next();
		    GameObject slaser = o.createSuperLaserShoot();
		    if (slaser != null) {
		        iterator.add(slaser); 
		        exito = true;
		    }
		}
		return exito;
	}
	
	public void eraseDeadObjects() {
		 List<GameObject> toRemove = new ArrayList<>();
		 List<GameObject> copia = new ArrayList<>();
		 copia.addAll(this.objects);
		 
		    // Recolectar los objetos a eliminar
		    for (GameObject o : copia) {
		        if (!o.isAlive()) {
		            o.onDelete();
		            toRemove.add(o);
		        }
		    }

		    // Eliminar los objetos recolectados de la lista original
		    this.objects.removeAll(toRemove);
	}
	
	public void shockWave(GameWorld game, Position pos, int numAliens) {
		this.add(new ShockWave(game,pos,numAliens));
	}
	
	public boolean hasAnyoneLanded(int dimY) {
		boolean landed = false;
		for(int i = 0; i < this.objects.size();i++) {
			if(this.objects.get(i).hasAnyoneLanded(dimY)) {
				landed = true;
			}
		}
		return landed;
	}
	
	public String toString(Position pos) {
		String objectString = "";
		for(int i =0; i < this.objects.size();i++) {
			if(!this.objects.get(i).checkPosition(pos).equals("")) {
				objectString = this.objects.get(i).checkPosition(pos);
			}
		}
		return objectString;
	}
	
}
