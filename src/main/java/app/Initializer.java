package app;

import app.commands.SlashCommandHandler;
import app.managers.GatewayIntentsManager;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import app.utils.SecretManager;

import java.util.List;

@Slf4j
@Component
public class Initializer {
    private final SecretManager secretManager;
    private final List<ListenerAdapter> listeners;
    private final List<SlashCommandHandler> commands;

    @Autowired
    public Initializer(SecretManager secretManager, List<ListenerAdapter> listeners, List<SlashCommandHandler> commands) {
        this.secretManager = secretManager;
        this.listeners = listeners;
        this.commands = commands;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() throws InterruptedException {
        final JDABuilder builder = JDABuilder.create(secretManager.getToken(), GatewayIntentsManager.getGatewayIntents());
        final JDA jda = builder
                .setActivity(Activity.customStatus("ease yourself"))
                .setStatus(OnlineStatus.ONLINE)
                .addEventListeners(listeners.toArray())
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

            log.info("Application initialized");
        }
    }
}
