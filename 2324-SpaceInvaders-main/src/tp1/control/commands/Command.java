package tp1.control.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.logic.GameModel;


/**
 * Represents a user action in the game.
 *
 */
public abstract class Command {

	  protected abstract String getName();
	  protected abstract String getShortcut();
	  protected abstract String getDetails();
	  protected abstract String getHelp();
	
	  public abstract boolean execute(GameModel game) throws CommandExecuteException;	  
	  
	  public abstract Command parse(String[] commandWords) throws CommandParseException, FileNotFoundException, IOException;
	  
	  protected boolean matchCommandName(String name) {
		    return getShortcut().equalsIgnoreCase(name) || 
		        getName().equalsIgnoreCase(name);
	  }
	  
	  public String helpText(){
	    return getDetails() + " : " + getHelp() + "\n";
	  }
}
