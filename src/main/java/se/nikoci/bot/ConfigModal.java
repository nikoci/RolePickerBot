package se.nikoci.bot;

import java.util.concurrent.TimeUnit;

import com.vdurmont.emoji.EmojiManager;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class ConfigModal extends ListenerAdapter {

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getModalId().startsWith("menu:rolepicker-config#")) {
            String roleId = event.getModalId().replace("menu:rolepicker-config#", "");

            if (roleId.equals("default")) {
                event.reply("Perhaps you should save the configuration?").setEphemeral(true).queue();
                return;
            }

            PickerRole role = null;

            for (PickerRole r : Main.getPickerRoles()) {
                if (r.getRoleId().equalsIgnoreCase(roleId)) {
                    role = r;
                }
            }

            if (role != null) {
                String emojiString = event.getValue("emojiInput").getAsString();
                String descriptionString = event.getValue("descriptionInput").getAsString();

                role.setDescription(descriptionString);

                if (EmojiManager.isEmoji(emojiString)) {
                    role.setEmoji(Emoji.fromFormatted(emojiString));
                } else {
                    role.setEmoji(null);
                }

                event.editComponents(
                        ActionRow.of(Menus.getConfigSelectMenu()),
                        ActionRow.of(Button.success("rolepicker-config-button", "Save Configuration")))
                        .queue();
                

                /*
                 * event.getChannel().retrieveMessageById(Main.getConfigMessageID()).queue(msg
                 * -> {
                 * msg.editMessageComponents(
                 * ActionRow.of(Menus.getConfigSelectMenu()),
                 * ActionRow.of(Button.success("rolepicker-config-button",
                 * "Save Configuration")))
                 * .queue();
                 * });
                 */

                // event.reply("Successfully edited " + role.getLabel() + " role")
                // .queue(q -> q.deleteOriginal().queueAfter(5, TimeUnit.SECONDS));

            } else {
                event.reply("Failed to get role with id: " + roleId)
                        .queue(q -> q.deleteOriginal().queueAfter(5, TimeUnit.SECONDS));
            }
        }
    }
}
