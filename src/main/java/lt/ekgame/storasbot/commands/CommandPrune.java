package lt.ekgame.storasbot.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lt.ekgame.storasbot.Utils;
import lt.ekgame.storasbot.commands.engine.BotCommandContext;
import lt.ekgame.storasbot.commands.engine.Command;
import lt.ekgame.storasbot.commands.engine.CommandIterator;
import lt.ekgame.storasbot.commands.engine.CommandResult;
import net.dv8tion.jda.Permission;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class CommandPrune implements Command<BotCommandContext> {
	
	@Override
	public List<String> getLabels() {
		return Arrays.asList("prune", "clear");
	}

	@Override
	public String getHelp() {
		return "Usage:\n"
			 + "$prune <number> [<user>]\n"
			 + "\n"
			 + "Removes last <number> of messages in the channel."
			 + " You can optionally provide a user who's messages should be deleted.";
	}

	@Override
	public boolean isGuildCommand() {
		return true;
	}

	@Override
	public boolean isPrivateCommand() {
		return false;
	}
	@Override
	public CommandResult execute(CommandIterator command, BotCommandContext context) {
		// TODO: refactor to check if enough messages will be deleted before performing the action.
		Guild guild = context.getGuild();
		TextChannel channel = context.getTextChannel();
		User sender = context.getSender();
		
		if (channel.checkPermission(sender, Permission.MESSAGE_MANAGE)) {
			Optional<Integer> oNumber = command.getInteger();
			Optional<String> oUserRaw = command.getEverything();
			if (oNumber.isPresent()) {
				int number = oNumber.get();
				if (number < 2 || number > 100) {
					context.reply("_The number has to be between 2 and 100 (inclusive)._");
					return CommandResult.FAIL;
				}
				
				List<Message> recent = channel.getHistory().retrieve();
				List<Message> remove = new ArrayList<>();
				
				if (oUserRaw.isPresent()) {
					Optional<User> user = Utils.getUser(guild, oUserRaw.get());
					if (user.isPresent()) {
						for (Message message : recent) {
							if (remove.size() >= number)
								break;
							if (message.getAuthor().equals(user.get()))
								remove.add(message);
						}
						channel.deleteMessages(remove);
						context.reply("_Deleted **" + number + "** messages by " + user.get().getAsMention() + "._");
						return CommandResult.OK;
					}
					else {
						context.reply("_Unknown user **" + Utils.escapeMarkdown(oUserRaw.get()) + "**._");
						return CommandResult.FAIL;
					}
				}
				else {
					for (Message message : recent) {
						if (remove.size() >= number)
							break;
						remove.add(message);
					}
					channel.deleteMessages(remove);
					context.reply("_Deleted **" + number + "** messages._");
					return CommandResult.OK;
				}
			}
			else {
				context.reply("_You don't know what you're doing. Try `$help prune`._");
				return CommandResult.FAIL;
			}
		}
		else {
			context.reply("_I'm sorry, " + sender.getAsMention() + ", I can't let you do that._");
			return CommandResult.FAIL;
		}
	}
}