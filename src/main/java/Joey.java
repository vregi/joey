import events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Joey {

    public static void main(String[] args) {
        final String token = "MTE3NTI0MTM3ODgxNzY1ODkzMQ.GipGEK.2CufUGOZXwUw1vaDaRQMG4E6IebZ3-tyR7elAQ";
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);

        JDA jda = jdaBuilder
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                    .addEventListeners(new ReadyEventListener(), new MessageEventListener(), new InteractionEventListener())
                    .build();

        jda.upsertCommand("cmd", "test command").setGuildOnly(false).queue();
        jda.upsertCommand("clear", "Clears entered amount of messages over command.").setGuildOnly(true).queue();
    }
}