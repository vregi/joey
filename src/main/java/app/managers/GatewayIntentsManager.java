package app.managers;

import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.ArrayList;
import java.util.List;

public class GatewayIntentsManager {

    public static List<GatewayIntent> getGatewayIntents() {
        return new ArrayList<GatewayIntent>() {{
            add(GatewayIntent.GUILD_MESSAGES);
            add(GatewayIntent.GUILD_MEMBERS);
            add(GatewayIntent.MESSAGE_CONTENT);
            add(GatewayIntent.DIRECT_MESSAGES);
        }};
    }

}
