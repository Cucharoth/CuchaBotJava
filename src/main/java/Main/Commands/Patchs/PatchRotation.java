package Main.Commands.Patchs;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;

public class PatchRotation {
    private HashMap<String, String> patchs = new HashMap<>();
    public PatchRotation(){
        this.patchs.put("5.4", "https://imgur.com/u7kzegM" + "\n" + "https://imgur.com/jnPCsOA" + "\n" + "Anee-Bronya");
        this.patchs.put("5.5", "https://imgur.com/FuPIbbf" + "\n" + "https://imgur.com/mc0eX8w" + "\n" + "PE");
        this.patchs.put("5.6", "https://imgur.com/10xAOQK" + "\n" + "https://imgur.com/WAV1jAI" + "\n" + "Gato");
        this.patchs.put("5.7", "https://imgur.com/4Tjdvpq" + "\n" + "https://imgur.com/HSPj47q" + "\n" + "Eden");
        this.patchs.put("5.8", "https://imgur.com/QwqTkvq" + "\n" + "https://imgur.com/JInQmZo" + "\n" + "Painto Girl");
        this.patchs.put("5.9", "https://imgur.com/SG117Xd" + "\n" + "https://imgur.com/6oAt3IS" + "\n" + "Vill-V");
    }

    public void honkaiRotation(SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("honkai_current_rotation")){
            String maxKeyValueInMap = Collections.max(patchs.keySet());
            event.reply(this.patchs.get(maxKeyValueInMap)).setEphemeral(true).queue();
        }
    }

    public void honkaiOldPatch(ButtonInteractionEvent event) {
        boolean hasNumber = false;
        for(char c : event.getButton().getId().toCharArray()) {
            if(Character.isDigit(c)) {
                hasNumber = true;
            }
        }

        if(hasNumber){
            String response = "";
            if (event.getButton().getId() != null) {
                switch (event.getButton().getId()) {
                    case "5.4":
                        response = patchs.get("5.4");
                        break;
                    case "5.5":
                        response = patchs.get("5.5");
                        break;
                    case "5.6":
                        response = patchs.get("5.6");
                        break;
                    case "5.7":
                        response = patchs.get("5.7");
                        break;
                    case "5.8":
                        response = patchs.get("5.8");
                        break;
                    case "5.9":
                        response = patchs.get("5.9");
                }
            }
            event.reply(response).setEphemeral(true).queue();
        }
    }

    public void honkaiOldPatchRotation(SlashCommandInteractionEvent event) {
        Button button54 = Button.primary("5.4", "5.4 (Anee-Bronya)");
        Button button55 = Button.primary("5.5", "5.5 (PE)");
        Button button56 = Button.primary("5.6", "5.6 (Gato)");
        Button button57 = Button.primary("5.7", "5.7 (Eden)");
        Button button58 = Button.primary("5.8", "5.8 (Griseo)");
        Button button59 = Button.primary("5.9", "5.9 (Vill-V)");

        if(event.getName().equalsIgnoreCase("honkai_old_patch_info")){
            Message message = new MessageBuilder()
                    .append("Old Patch info:")
                    .setActionRows(
                            ActionRow.of(button59),
                            ActionRow.of(button58),
                            ActionRow.of(button57),
                            ActionRow.of(button56),
                            ActionRow.of(button55)
                            //ActionRow.of(button54)
                    )
                    .build();
            event.reply(message).setEphemeral(true).queue();
        }
    }
}
