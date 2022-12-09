package se.nikoci.bot;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;

public class SaveConfig extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().startsWith("rolepicker-config-button")) {
            event.getChannel().retrieveMessageById(Main.getMessageToEditID())
                    .queue(msg -> msg.editMessageComponents(ActionRow.of(Menus.getPickerSelectMenu())).queue());

            event.editMessage("Successfully added RolePicker to message")
                    .queue(ih -> ih.editOriginalComponents().queue());
        }
    }

}
