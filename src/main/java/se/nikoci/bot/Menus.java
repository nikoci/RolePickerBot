package se.nikoci.bot;

import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class Menus {
    
    public static StringSelectMenu getPickerSelectMenu(){
        StringSelectMenu.Builder selectMenu = StringSelectMenu.create("menu:rolepicker");

        selectMenu.addOptions(SelectOption.of("Click here to select a role", "default")
                .withDescription("Default option. Select this to remove other roles")
                .withDefault(true));

        for (PickerRole role : Main.getPickerRoles()) {
            SelectOption selectOption = SelectOption.of(role.getLabel(), role.getRoleId()+"");
            if (!role.getDescription().isBlank()) {
                selectOption = selectOption.withDescription(role.getDescription());
            }

            if (role.getEmoji() != null){
                selectOption = selectOption.withEmoji(role.getEmoji());
            }

            selectMenu.addOptions(selectOption);
        }

        return selectMenu.build();
    }

    public static StringSelectMenu getConfigSelectMenu(){
        StringSelectMenu.Builder selectMenu = StringSelectMenu.create("menu:rolepicker-config");

        selectMenu.addOptions(SelectOption.of("Select an option to configure", "default")
                .withDescription("You will be prompted with a menu")
                .withDefault(true));

        for (PickerRole role : Main.getPickerRoles()) {
            SelectOption selectOption = SelectOption.of(role.getLabel(), role.getRoleId());
            if (!role.getDescription().isBlank()) {
                selectOption = selectOption.withDescription(role.getDescription());
            }

            if (role.getEmoji() != null){
                selectOption = selectOption.withEmoji(role.getEmoji());
            }

            selectMenu.addOptions(selectOption);
        }

        return selectMenu.build();
    }

    public static Modal getConfigMenu(String roleId) {
        TextInput emojInput = TextInput.create("emojiInput", "Emoji", TextInputStyle.SHORT)
                .setPlaceholder("Emoji to use for option")
                .setRequiredRange(0, 50)
                .build();

        TextInput descriptionInput = TextInput.create("descriptionInput", "Description", TextInputStyle.SHORT)
                .setPlaceholder("Emoji to use for option")
                .setRequiredRange(0, 50)
                .build();

        return Modal.create("menu:rolepicker-config#" + roleId, "Edit: role#" + roleId)
                .addActionRows(ActionRow.of(emojInput), ActionRow.of(descriptionInput))
                .build();
    }

}
