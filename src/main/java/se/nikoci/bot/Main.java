package se.nikoci.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public final class Main {

    @Getter private static JDA jda;
    @Getter private static String guildID = "1008521796590506004";
    @Getter private static List<PickerRole> pickerRoles = new ArrayList<>();
    @Getter @Setter private static String messageToEditID;
    @Getter @Setter private static String configMessageID;

    public static void main(@NotNull String[] args) throws InterruptedException {
        String BOT_TOKEN = args[0];

        jda = JDABuilder
                .createDefault(BOT_TOKEN, Set.of(GatewayIntent.MESSAGE_CONTENT))
                .disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOJI, CacheFlag.STICKER, CacheFlag.SCHEDULED_EVENTS)
                .build();

        jda.awaitReady();
        jda.addEventListener(
            new MessageContext(),
            new SelectMenu(),
            new ConfigModal(),
            new SaveConfig()
            );

        jda.getGuildById(guildID).updateCommands().addCommands(Commands
                .message("Add RolePicker")
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED)).queue();

        jda.getGuildById(guildID).getRoles().forEach(role -> {
            if (role.getName().startsWith("color:")) {
                pickerRoles.add(new PickerRole(
                        role.getId(),
                        role.getName().replace("color: ", "")));
            }
        });

        jda.getGuildById(Main.getGuildID()).getTextChannelById("1035538503343292417").sendMessage("This is a test message").queue();
    }

}
