package app;

import net.dv8tion.jda.api.requests.GatewayIntent;

public class GatewayIntentsManager {
    public static final GatewayIntent GUILD_MESSAGES = GatewayIntent.GUILD_MESSAGES;
    public static final GatewayIntent GUILD_MEMBERS = GatewayIntent.GUILD_MEMBERS;
    public static final GatewayIntent MESSAGE_CONTENT = GatewayIntent.MESSAGE_CONTENT;


    public static GatewayIntent[] getGatewayIntents() {
        return new GatewayIntent[]{
                GUILD_MESSAGES,
                GUILD_MEMBERS,
                MESSAGE_CONTENT
        };
    }

}
