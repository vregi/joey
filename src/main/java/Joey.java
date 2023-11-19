import events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Joey {
    public static void main(String[] args) {
        final String TOKEN = "MTE3NTI0MTM3ODgxNzY1ODkzMQ.GJUtiG.6cpCrZBVEkdhfncxxFEiCrGgSkaFksJxns1nF8";
        JDABuilder jdaBuilder = JDABuilder.createDefault(TOKEN);

        JDA jda = jdaBuilder
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                    .addEventListeners(new ReadyEventListener(), new MessageEventListener(), new InteractionEventListener())
                    .build();

        jda.upsertCommand("cmd", "test command").setGuildOnly(false).queue();
        jda.upsertCommand("clear", "Clears entered amount of messages over command.").setGuildOnly(true).queue();
    }
}