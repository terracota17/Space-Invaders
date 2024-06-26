package tp1.control.commands;

import tp1.exceptions.CommandParseException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class NoneCommand extends NoParamsCommand {

	@Override
	protected String getName() {
		return Messages.COMMAND_NONE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_NONE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_NONE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_NONE_HELP;
	}


	@Override
	public boolean execute(GameModel game)  {
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		Command c = null;
		
		if(commandWords[0].equals("none") || commandWords[0].equals("n") || commandWords[0].equals("") ) {
				c = new NoneCommand();
		}
				
		return c;
	}
	

}
