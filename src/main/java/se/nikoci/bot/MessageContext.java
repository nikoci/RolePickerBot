package se.nikoci.bot;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class MessageContext extends ListenerAdapter {

    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {
        if (event.getName().equals("Add RolePicker")) {
            Main.setMessageToEditID(event.getTarget().getId());

            event.reply(
                    "**RolePicker configuration**\nSelect an option below to configure it. Click the \"Save Configuration\" button when you are done.")
                    .addComponents(
                            ActionRow.of(Menus.getConfigSelectMenu()),
                            ActionRow.of(Button.success("rolepicker-config-button", "Save Configuration")))
                    .setEphemeral(true)
                    .queue(ih -> ih.retrieveOriginal().queue(message -> {
                        Main.setConfigMessageID(message.getId());
                    }));

            /*
             * int roleIndex = 0;
             * 
             * List<PickerRole> roles = Main.getPickerRoles();
             * List<List<ActionComponent>> components = new ArrayList<>();
             * 
             * for (int i = 0; i < 4; i++){
             * List<ActionComponent> buttons = new ArrayList<>();
             * for (int j = 0; j < 5; j++){
             * if (roleIndex == roles.size()) break;
             * PickerRole role = roles.get(roleIndex);
             * Button btn = Button.secondary("rolepicker-config-button#" + role.getRoleId(),
             * role.getLabel());
             * buttons.add(btn);
             * roleIndex++;
             * }
             * if (!buttons.isEmpty() || buttons != null){
             * components.add(buttons);
             * }
             * }
             * 
             * ReplyCallbackAction reply = event.reply("**RolePicker configuration**");
             * 
             * components.forEach(componentList -> {
             * if (componentList != null){
             * List<ActionComponent> parsedList = new ArrayList<>();
             * 
             * componentList.forEach(btn -> {
             * if (btn != null) parsedList.add(btn);
             * });
             * 
             * if (parsedList.size() > 0) reply.addActionRow(parsedList);
             * }
             * });
             * 
             * reply.addActionRow(Button.success("rolepicker-config-button#save-config",
             * "Save Configuration")).queue();
             */
        }
    }

}
