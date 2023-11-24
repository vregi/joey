package events;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageEventListener extends ListenerAdapter  {
    public void onMessageReceived(@NotNull MessageReceivedEvent event)  {
        super.onMessageReceived(event);
        String guild = event.getGuild().getName();
        String channel = event.getChannel().getId();
        String message = event.getMessage().getContentDisplay();
        String user = event.getAuthor().getId();

        TextChannel textChannel = event.getGuild().getTextChannelById("1121424971990843455");

        String pingUser = "<@" + user + ">";
        String pingChannel = "<#" + channel + ">";
        System.out.println("user sent: " + message + "\n channel: " + channel + "\n guild: " + guild);
        switch (user){
            case "1175241378817658931":
                break;
            default:
                textChannel.sendMessage(pingUser + " sent: " + message + "\n channel: " + pingChannel).queue();
                break;
        }

    }
}
