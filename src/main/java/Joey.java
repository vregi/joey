import events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Joey {
    public static void main(String[] args) {
        final String TOKEN = "OTY5NzQ0MDczMzkwOTc3MDk1.G1MUDl.yOfE5z_z_Xm1Z9eDEaH3FlQatllt3eq3_4H39o";
        JDABuilder jdaBuilder = JDABuilder.createDefault(TOKEN);

        JDA jda = jdaBuilder
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                    .addEventListeners(new ReadyEventListener(), new MessageEventListener(), new InteractionEventListener())
                    .build();

        jda.upsertCommand("slash-cmd", "This is a slash command!").setGuildOnly(true).queue();
    }
}