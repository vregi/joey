package app.listeners;

import app.commands.SlashCommandHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InteractionEventListener extends ListenerAdapter {

    private final Map<String, SlashCommandHandler> commandsMap = new HashMap<>();

    @Autowired
    public InteractionEventListener(List<SlashCommandHandler> commands){
        for (SlashCommandHandler command : commands) {
            commandsMap.put(command.getName(), command);
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        String name = event.getName();
        SlashCommandHandler command = commandsMap.get(name);

        command.handle(event);

        User user = null;
        List<Role> userRole = null;
        Role mutedRole = null;

        if (event.getInteraction().getOption("user") != null) {
            user = event.getInteraction().getOption("user").getAsUser();
            Member member = event.getInteraction().getOption("user").getAsMember();
            if (member != null) {
                userRole = member.getRoles();
            }
        }

        if (event.getGuild() != null) {
            mutedRole = event.getGuild().getRoleById("1216997414712508436");
        }

        System.out.println("interaction");
        switch (event.getName()) {
            case "application":
                String applicationCommandChannelID = event.getChannelId();
                String applicationCommandGuildID = event.getGuild().getId();
                TextChannel textChannel = event.getJDA().getGuildById(applicationCommandGuildID).getTextChannelById(applicationCommandChannelID);
                if (textChannel != null) {
                    EmbedBuilder embed = new EmbedBuilder();
                    MessageCreateBuilder message = new MessageCreateBuilder();
                    embed.setTitle("Нажмите ниже чтобы заполнить форму")
                            //.setImage("https://cdn.discordapp.com/attachments/1174492669997744178/1175512009689813103/image.png?ex=656b7fdf&is=65590adf&hm=b0368254dd5ec8f7fda356e78f52332a787eafa917d83d7d551a6ae5661f942b&")
                            .setColor(new Color(0x000000));
                    FileUpload file = FileUpload.fromData(new File("src/main/java/images/image.png"));
                    message.addFiles(file);
                    Button button = Button.success("apply", "Заполнить форму");
                    textChannel.sendMessageEmbeds(embed.build()).queue();
                    textChannel.sendMessage(message.build()).setActionRow(button).queue();
                    event.reply("Application form was created!").setEphemeral(true).queue();
                }
                break;

            case "clear":
                if (event.getInteraction().getOption("amount") != null) {
                    int parameter = event.getInteraction().getOption("amount").getAsInt();
                    List<Message> messages = event.getChannel().getHistory().retrievePast(parameter).complete();
                    event.getChannel().purgeMessages(messages);
                    event.reply(String.valueOf(parameter)).setEphemeral(true).queue();
                }
                break;

            case "say":
                if (event.getInteraction().getOption("string") != null) {
                    String sayStringOption = event.getInteraction().getOption("string").getAsString();
                    if (event.getInteraction().getOption("channel") != null) {
                        TextChannel sayChannelOption = event.getInteraction().getOption("channel").getAsChannel().asTextChannel();
                        sayChannelOption.sendMessage(sayStringOption).queue();
                        event.reply("Done").setEphemeral(true).queue();
                    } else {
                        event.reply(sayStringOption).queue();
                        event.reply("Done").setEphemeral(true).queue();
                    }
                }
                break;

            case "mute":
                if (user != null && userRole != null && mutedRole != null) {
                    if (userRole.contains(mutedRole)) {
                        event.reply("User is already muted. Unmute it or try again if failure.").setEphemeral(true).queue();
                    } else {
                        event.getGuild().addRoleToMember(user, mutedRole).queue();
                        event.reply("<@" + user.getId() + "> is muted successfully!").setEphemeral(true).queue();
                    }
                }
                break;

            case "unmute":
                if (user != null && userRole != null && mutedRole != null) {
                    if (!userRole.contains(mutedRole)) {
                        event.reply("User is already unmuted. Mute it or try again if failure.").setEphemeral(true).queue();
                    } else {
                        event.getGuild().removeRoleFromMember(user, mutedRole).queue();
                        event.reply("<@" + user.getId() + "> is unmuted successfully!").setEphemeral(true).queue();
                    }
                }
                break;

            case "test-form":

            default:
                event.reply("Unknown command, try again!").setEphemeral(true).queue();
                break;
        }
    }
}
