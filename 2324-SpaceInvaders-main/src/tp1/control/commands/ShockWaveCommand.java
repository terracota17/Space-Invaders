package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ShockWaveCommand extends NoParamsCommand{

	@Override
	protected String getName() {
		return Messages.COMMAND_SHOCKWAVE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SHOCKWAVE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_SHOCKWAVE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_SHOCKWAVE_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException {
		game.shockWave();
		game.update();
		
		return true;
	}


}
