package app.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.time.Instant;

@Component
public class ReadyEventListener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        String imageLink = "https://cdn.discordapp.com/attachments/1174492669997744178/1175512127633625212/image.png?ex=656b7ffb&is=65590afb&hm=554d56ae2bd6e0a27d55aeabb724c629a8ae5cceea1322f3001a64eb89dbacfa&";
        System.out.println("API is ready!");
        TextChannel textChannel = event.getJDA().getGuildById("1078891456376360960").getTextChannelById("1121424971990843455");
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Bot activity");
        embed.setAuthor("joey_", null, imageLink);
        embed.setColor(new Color(0xFFFFFF));
        embed.addField("Bot", "<@1175241378817658931>", false);
        embed.addField("Notification", "Bot is ready to use!", false);
        embed.setTimestamp(Instant.now());


        textChannel.sendMessageEmbeds(embed.build()).queue();
    }
}
