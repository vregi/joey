package app;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import java.util.Arrays;
public class Joey {

    public static void main(String[] args) throws InterruptedException{

        JDABuilder builder = JDABuilder.create("MTE3NTI0MTM3ODgxNzY1ODkzMQ.GGD_jL.kM76DHgaEoKCDEjtPuwu3V-gAe0Awqflq5u-tA", Arrays.asList(GatewayIntentsManager.getGatewayIntents()));
        JDA jda = builder
                .setActivity(Activity.listening("voices in his head"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .addEventListeners(new events.ReadyEventListener(), new events.MessageEventListener())
                .build().awaitReady();


        
    }
}