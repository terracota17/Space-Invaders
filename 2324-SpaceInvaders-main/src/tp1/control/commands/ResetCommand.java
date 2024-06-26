package tp1.control.commands;

import java.io.FileNotFoundException;
import tp1.control.InitialConfiguration;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.InitializationException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ResetCommand extends Command{

	
	private InitialConfiguration conf = InitialConfiguration.NONE;

	public ResetCommand() {}

	protected ResetCommand(InitialConfiguration c) {
		this.conf = c;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException  {
		try {
			game.reset(this.conf);
		} catch (InitializationException e) {
			throw new CommandExecuteException(e.getMessage());
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {

		Command c = null;
		try {
			if(super.matchCommandName(commandWords[0]) ) {
		       if(commandWords.length < 3) {
		    	   if(commandWords.length > 1) {
		    		   conf = InitialConfiguration.readFromFile(commandWords[1]);
		    		   c = new ResetCommand(conf);
		         	}else {
		         		c = new ResetCommand(InitialConfiguration.NONE);
		         	}
		       }else
					throw new CommandParseException( Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		       
			}
		}catch(FileNotFoundException e) {
			throw new CommandParseException(e.getMessage());
		}
		return c;
	}


	
	

}
