package events;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;


public class ButtonEventListener extends ListenerAdapter {
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) throws IllegalArgumentException {
        super.onButtonInteraction(event);
        switch (event.getButton().getId()){
            case "apply":
                TextInput userinfo = TextInput.create("userinfo", "Ваш ник | IRL имя | Возраст | LVL", TextInputStyle.SHORT)
                        .setPlaceholder("Ваш ник | IRL имя | Возраст | LVL")
                        .setMinLength(1)
                        .setRequired(true)
                        .build();
                TextInput record = TextInput.create("record", "Откаты стрельбы", TextInputStyle.PARAGRAPH)
                        .setPlaceholder("Откаты с поставок, трассы или взп")
                        .setMinLength(1)
                        .setRequired(true)
                        .build();
                TextInput averageonline = TextInput.create("averageonline", "Средний онлайн", TextInputStyle.SHORT)
                        .setPlaceholder("Ваш средний онлайн")
                        .setMinLength(1)
                        .setRequired(true)
                        .build();
                TextInput entrypurpose = TextInput.create("entrypurpose", "Цель вступления", TextInputStyle.SHORT)
                        .setPlaceholder("Кто позвал и/или откуда о нас узнали")
                        .setMinLength(1)
                        .setRequired(true)
                        .build();
                TextInput families = TextInput.create("families", "Прошлые семьи", TextInputStyle.SHORT)
                        .setPlaceholder("В каких семьях были до этого")
                        .setMinLength(1)
                        .setRequired(true)
                        .build();

                Modal modal = Modal.create("application", "Заявка в UNCONTROL")
                        .addActionRow(userinfo)
                        .addActionRow(record)
                        .addActionRow(averageonline)
                        .addActionRow(entrypurpose)
                        .addActionRow(families)
                        .build();
                event.replyModal(modal).queue();

                break;
            default:
                event.reply("Button not found.").setEphemeral(true).queue();
                break;
        }
    }

}
