package tp1.control.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import tp1.exceptions.CommandParseException;
import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
		new MoveCommand(),
		new ShootCommand(),
		new ShockWaveCommand(),
		new ListCommand(),
		new ResetCommand(),
		new HelpCommand(),
		new ExitCommand(),
		new NoneCommand(),
		new SLaserCommand()
		//Command other commands
	);
	

	public static Command parse(String[] commandWords) throws CommandParseException, FileNotFoundException, IOException{		
		
		for (Command c: availableCommands) {
			if((c.parse(commandWords) != null)) {
				return c.parse(commandWords);
			}
		}
		 throw new CommandParseException(Messages.UNKNOWN_COMMAND);
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();	
		for (Command c: availableCommands) {
			commands.append(c.helpText());
		}
		return commands.toString();
	}

}
