package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.NotAllowedMoveException;
import tp1.exceptions.OffWorldException;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.view.Messages;

public class MoveCommand extends Command {

	private Move move;

	public MoveCommand() {}

	protected MoveCommand(Move move) {
		this.move = move;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_MOVE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_MOVE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_MOVE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_MOVE_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException{
		try {
			game.move(move);
			game.update();
			return true; 
		}catch(OffWorldException ofworld) {
			throw new CommandExecuteException(Messages.MOVEMENT_ERROR + "\n"
													+ ofworld.getMessage());
		}catch(NotAllowedMoveException notallowed) {
			throw new CommandExecuteException(Messages.DIRECTION_ERROR + move + "\n" 
												+ Messages.ALLOWED_MOVES_MESSAGE);
		}
	}


	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
       Command c = null;
       try {
    	   if(super.matchCommandName(commandWords[0])) {
        	   if(commandWords.length > 1 && commandWords.length < 3 ) {
        		   Move movement = Move.valueOfIgnoreCase(commandWords[1]);
            	   if(movement != null)
            		   c = new MoveCommand(movement);
        	   }else
     				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
        	   
    	   }
        	      
       }catch(IllegalArgumentException  illegal) {
    	   throw new CommandParseException(illegal.getMessage());
       }
      
       
       return c;
	}

}
