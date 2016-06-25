package lt.ekgame.storasbot.commands.eval;

import java.util.Arrays;
import java.util.List;

import lt.ekgame.storasbot.StorasBot;
import lt.ekgame.storasbot.commands.engine.BotCommandContext;
import lt.ekgame.storasbot.commands.engine.Command;
import lt.ekgame.storasbot.commands.engine.CommandIterator;
import lt.ekgame.storasbot.commands.engine.CommandResult;

public class CommandEval implements Command<BotCommandContext>  {

	@Override
	public List<String> getLabels() {
		return Arrays.asList("eval", "exec");
	}

	@Override
	public String getHelp() {
		return "Usage:\n"
			 + "$eval <javascript expression/code>\n"
			 + "or\n"
			 + "$eval ```\n"
			 + "<javascript expression/code>\n"
			 + "```\n"
			 + "\n"
			 + "Executes a JavaScript expression or code using Nashorn engine.\n"
			 + "To print something, use print.line(<object>) or print.format(<format>[, objects...]).\n"
			 + "print.format(...) works just like Java's/C++ printf(...).";
		
	}

	@Override
	public boolean isGuildCommand() {
		return true;
	}

	@Override
	public boolean isPrivateCommand() {
		return true;
	}
	
	@Override
	public CommandResult execute(CommandIterator command, BotCommandContext context) {
		StorasBot.client.addEventListener(new CodeExecutor(context.getMessage()));
		return CommandResult.OK; // Content changes are handled by the executor
	}
}