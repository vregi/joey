package app;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;

import java.util.Arrays;
public class Joey {

    public static void main(String[] args) throws InterruptedException{

        JDABuilder builder = JDABuilder.create("MTE3NTI0MTM3ODgxNzY1ODkzMQ.GETYqX.jzhNSpBNZTt5JVGzsyDsflB4CDTdhpTLs-1b24", Arrays.asList(GatewayIntentsManager.getGatewayIntents()));
        JDA jda = builder
                .setActivity(Activity.listening("voices in his head"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .addEventListeners(new events.ReadyEventListener(), new events.MessageEventListener(), new events.InteractionEventListener(), new events.ButtonEventListener(), new events.ModalEventListener())
                .build().awaitReady();

        Guild guild = jda.getGuildById("1078891456376360960");
        if(guild != null) {
            guild.upsertCommand("application", "Creates a new application form (pop-up)").queue();
            guild.upsertCommand("clear", "Clears the specified amount of messages above").queue();
            guild.upsertCommand("cmd", "Clears the specified amount of messages above").queue();
        }
    }
}