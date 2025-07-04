package app.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModalEventListener extends ListenerAdapter {
    public void onModalInteraction(@NotNull ModalInteractionEvent event){
        switch (event.getModalId()){
            case "application":
                List<ModalMapping> values = event.getValues();
                EmbedBuilder embed = new EmbedBuilder();
                String userID = event.getUser().getId();
                String userPing = "<@" + userID + ">";
                String userURL = "https://discord.gg/users/" + userID;
                TextChannel textChannel = event.getGuild().getTextChannelById("1170956070081007646");
                String fieldThree;
                if (values.get(1).getAsString().length() < 2) {
                    fieldThree = "РП Состав";
                } else {
                    fieldThree = values.get(1).getAsString();
                }
                embed.setAuthor(event.getUser().getName(), userURL, event.getUser().getAvatarUrl());
                embed.addField("Пользователь: ", userPing, false);
                embed.addField("Ваш ник | IRL Возраст | LVL", values.get(0).getAsString(), false);
                embed.addField("Откаты стрельбы", fieldThree, false);
                embed.addField("Часовой пояс / Средний онлайн", values.get(2).getAsString(), false);
                embed.addField("Цель вступления", values.get(3).getAsString(), false);
                embed.addField("Прошлые семьи", values.get(4).getAsString(), false);
                embed.setFooter(event.getUser().getId(), "https://images-ext-1.discordapp.net/external/uTUhiDcRKw1xlc3n3jwcivy2O9WxpfYwxS8bzOgWF9c/https/cdn.discordapp.com/emojis/882601305871360040.png");
                MessageCreateBuilder message = new MessageCreateBuilder();
                message.addContent("||<@&1170406055327563856> <@&1084037594230292500> <@&1084035068894380064>||");

                textChannel.sendMessage(message.build()).queue();
                textChannel.sendMessageEmbeds(embed.build()).queue();
                event.reply("Заявка принята.").setEphemeral(true).queue();
                break;


            default:
                event.reply("Modal not found.").setEphemeral(true).queue();
                break;
        }

    }
}
