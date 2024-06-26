package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class SLaserCommand extends NoParamsCommand {
private final static int COST = 5;
	

	public SLaserCommand() {}
	@Override
	protected String getName() {
		return Messages.COMMAND_SUPERLASER_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SUPERLASER_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_SUPERLASER_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_SUPERLASER_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException {
		game.superLaser(COST);
		game.update();
		return true;
	}

	
}


