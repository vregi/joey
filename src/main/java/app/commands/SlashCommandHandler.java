package app.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public interface SlashCommandHandler {
    CommandData getCommandData();
    String getName();
    void handle(SlashCommandInteractionEvent event);

}
