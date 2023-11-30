package events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import org.jetbrains.annotations.NotNull;



import java.awt.*;
import java.io.File;

public class InteractionEventListener extends ListenerAdapter {


    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);


        System.out.println("interaction");
        switch (event.getName()){

            case "application":
                String applicationCommandChannelID = event.getChannelId();
                String applicationCommandGuildID = event.getGuild().getId();
                TextChannel textChannel = event.getJDA().getGuildById(applicationCommandGuildID).getTextChannelById(applicationCommandChannelID);
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




            case "clear":
                int parameter = event.getInteraction().getOption("amount").getAsInt();
                String parameterString = String.valueOf(parameter);
                event.reply(parameterString).setEphemeral(true).queue();
                break;



            case "say":
                String sayStringOption = event.getInteraction().getOption("string").getAsString();
                if(event.getInteraction().getOption("channel") != null){
                    TextChannel sayChannelOption = event.getInteraction().getOption("channel").getAsChannel().asTextChannel();
                    sayChannelOption.sendMessage(sayStringOption).queue();
                    event.reply("Done").setEphemeral(true).queue();
                } else{
                    event.reply(sayStringOption).queue();
                }
                break;



            default:
                event.reply("Unknown command, try again!").setEphemeral(true).queue();
                break;
        }
    }
}
