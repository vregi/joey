package events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class ReadyEventListener implements EventListener {

    @Override
    public void onEvent(@NotNull GenericEvent event){
        if(event instanceof ReadyEvent){
            System.out.println("sudo$ ready...");
        }
    }

}
