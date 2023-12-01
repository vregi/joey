package events;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


public class GuildJoinEventListener extends ListenerAdapter {
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        super.onGuildMemberJoin(event);
        UserSnowflake memberID = event.getMember().getUser();
        Role role = event.getGuild().getRoleById("1122482726851846235");
        Guild guild = event.getGuild();
        guild.addRoleToMember(memberID, role).queue();
    }

}
