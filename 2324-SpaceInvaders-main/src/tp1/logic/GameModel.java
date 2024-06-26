package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exceptions.InitializationException;
import tp1.exceptions.LaserInFlightException;
import tp1.exceptions.NoShockWaveException;
import tp1.exceptions.NotAllowedMoveException;
import tp1.exceptions.NotEnoughtPointsException;
import tp1.exceptions.OffWorldException;
import tp1.exceptions.PostionCreationException;

public interface GameModel {
	
	public boolean isFinished();

	public void move(Move move) throws OffWorldException, NotAllowedMoveException ;
	public void shootLaser() throws LaserInFlightException, PostionCreationException;
	public void superLaser(int cost) throws LaserInFlightException, NotEnoughtPointsException;
	public void reset(InitialConfiguration conf) throws InitializationException, PostionCreationException;
	public void shockWave() throws NoShockWaveException;
	
	public void list();
	public void exit();
	public void update() ;
		   
}
