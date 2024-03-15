package app;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import java.util.Arrays;
public class Joey {

    public static void main(String[] args) throws InterruptedException{

        final JDABuilder builder = JDABuilder.create("MTE3NTI0MTM3ODgxNzY1ODkzMQ.G-LzC5.I79galLC5AArSUwUxss-sb2aQ9DjO4tKTXYFb0", Arrays.asList(GatewayIntentsManager.getGatewayIntents()));
        final JDA jda = builder
                .setActivity(Activity.listening("voices in his head"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .addEventListeners(new events.ReadyEventListener(),
                        new events.MessageEventListener(),
                        new events.InteractionEventListener(),
                        new events.ButtonEventListener(),
                        new events.ModalEventListener(),
                        new events.GuildJoinEventListener()
                )
                .build().awaitReady();

        Guild guild = jda.getGuildById("1078891456376360960");
        if (guild != null) {
            guild.updateCommands().addCommands(
                            Commands.slash("application", "Creates a new application form (pop-up)")
                                    .setDefaultPermissions(DefaultMemberPermissions.DISABLED),

                            Commands.slash("clear", "Clears entered amount of messages over command.")
                                    .addOption(OptionType.INTEGER, "amount", "Amount of messages parameter", true),

                            Commands.slash("say", "Say something through bot.")
                                    .addOption(OptionType.STRING, "string", "String", true)
                                    .addOption(OptionType.CHANNEL, "channel", "Channel to send.")
                                    .setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                            Commands.slash("mute", "Mute someone")
                                    .addOption(OptionType.USER, "user", "User to mute", true)
                                    .setDefaultPermissions(DefaultMemberPermissions.DISABLED),
                            Commands.slash("unmute", "Unmute someone")
                                    .addOption(OptionType.USER, "user", "User to unmute", true)
                                    .setDefaultPermissions(DefaultMemberPermissions.DISABLED))
                            .queue();
        }


    }
}
        /*if(guild != null) {
            guild.upsertCommand("application", "Creates a new application form (pop-up)").queue();
            guild.upsertCommand("clear", "Clears the specified amount of messages above").queue();
            guild.upsertCommand("cls", "Clears the specified amount of messages above").queue();
        }*/
