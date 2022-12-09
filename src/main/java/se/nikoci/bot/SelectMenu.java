package se.nikoci.bot;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SelectMenu extends ListenerAdapter {

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {

        if (event.getComponentId().equals("menu:rolepicker")) {

            String roleId = event.getValues().get(0).toString();
            Role selectedRole = null;

            if (!event.getValues().get(0).toString().equalsIgnoreCase("default")){

                selectedRole = Objects.requireNonNull(event.getGuild()).getRoleById(roleId);
            }

            event.getSelectMenu().getOptions().forEach(option -> {
                String value = option.getValue().equals("default") ? null : option.getValue();
                if (value != null) {
                    Role removeRole = Objects.requireNonNull(event.getGuild()).getRoleById(value);
                    if (removeRole != null && event.getMember().getRoles().contains(removeRole)) {
                        event.getGuild().removeRoleFromMember(event.getUser(), removeRole).queue();
                    }
                }
            });

            if (event.getValues().get(0).toString().equalsIgnoreCase("default")){

                event.reply("Reset roles successfully.").setEphemeral(true)
                        .queue(msg -> msg.deleteOriginal().queueAfter(5, TimeUnit.SECONDS));
            } else {
                if (selectedRole != null){
                    event.getGuild().addRoleToMember(event.getUser(), selectedRole).queue();
                    event.reply("You got the " + selectedRole.getAsMention() + " role").setEphemeral(true)
                            .queue(msg -> msg.deleteOriginal().queueAfter(5, TimeUnit.SECONDS));
                } else {

                    event.reply("Failed to set role. Please contact an Administrator").setEphemeral(true)
                            .queue(msg -> msg.deleteOriginal().queueAfter(5, TimeUnit.SECONDS));
                }
            }
        } else if (event.getComponentId().equals("menu:rolepicker-config")) {

            String roleId = event.getValues().get(0).toString();
            event.replyModal(Menus.getConfigMenu(roleId)).queue();

        }
    }
    
}