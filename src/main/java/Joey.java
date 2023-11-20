import events.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Joey {
    private final Dotenv config;

    public Joey(Dotenv config) {
        this.config = config;
    }

    public static void main(String[] args) {
        Dotenv config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        JDABuilder jdaBuilder = JDABuilder.createDefault(token);

        JDA jda = jdaBuilder
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                    .addEventListeners(new ReadyEventListener(), new MessageEventListener(), new InteractionEventListener())
                    .build();

        jda.upsertCommand("cmd", "test command").setGuildOnly(false).queue();
        jda.upsertCommand("clear", "Clears entered amount of messages over command.").setGuildOnly(true).queue();
    }
    public Dotenv getConfig(){
        return config;
    }
}