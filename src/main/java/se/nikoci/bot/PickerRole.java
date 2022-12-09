package se.nikoci.bot;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.emoji.Emoji;

@Data
@RequiredArgsConstructor
public class PickerRole {

    @NonNull private String roleId;
    @NonNull private String label;
    private String description = "";
    private Emoji emoji = null;
    
}
