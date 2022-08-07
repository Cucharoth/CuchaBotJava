package Main.Commands.Patchs;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class PatchRotation {
    public PatchRotation(){

    }

    public void honkaiOldPatchRotation(SlashCommandInteractionEvent event) {
        Button button54 = Button.primary("5.4", "5.4 (Anee-Bronya)");
        Button button55 = Button.primary("5.5", "5.5 (PE)");
        Button button56 = Button.primary("5.6", "5.6 (Gato)");
        Button button57 = Button.primary("5.7", "5.7 (Eden)");
        Button button58 = Button.primary("5.8", "5.8 (Griseo)");

        if(event.getName().equalsIgnoreCase("honkai_old_patch_info")){
            Message message = new MessageBuilder()
                    .append("Old Patch info:")
                    .setActionRows(
                            ActionRow.of(button58),
                            ActionRow.of(button57),
                            ActionRow.of(button56),
                            ActionRow.of(button55),
                            ActionRow.of(button54)
                    )
                    .build();
            event.reply(message).setEphemeral(true).queue();
        }
    }
}
