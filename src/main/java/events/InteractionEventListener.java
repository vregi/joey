package events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;

public class InteractionEventListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);

        if (event.getName().equals("modmail"))
        {
            TextInput subject = TextInput.create("subject", "Subject", TextInputStyle.SHORT)
                    .setPlaceholder("Subject of this ticket")
                    .setMinLength(10)
                    .setMaxLength(100) // or setRequiredRange(10, 100)
                    .build();

            TextInput body = TextInput.create("body", "Body", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Your concerns go here")
                    .setMinLength(30)
                    .setMaxLength(1000)
                    .build();

            Modal modal = Modal.create("modmail", "Modmail")
                    .addActionRows(ActionRow.of(subject), ActionRow.of(body))
                    .build();

            event.replyModal(modal).queue();
        }
    }
}
