package app.commands;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

import java.io.File;

public class ApplicationCommand implements SlashCommandHandler {


    @Override
    public CommandData getCommandData() {
        return new CommandDataImpl(Command.Type.SLASH, "applications")
                .addOption(OptionType.CHANNEL, "channel", "Channel which will receive applications", true);
    }

    @Override
    public String getName() {
        return "applications";
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        MessageCreateBuilder messageBuilder = new MessageCreateBuilder();
        MessageChannel channel = event.getMessageChannel();
        Button button = Button.success("apply", "Заполнить форму");
        channel.sendTyping().queue();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        channel.sendMessage(messageBuilder.addFiles(FileUpload.fromData(new File("src/main/java/images/image.png"))).build())
                .setActionRow(button)
                .queue();
    }
}
