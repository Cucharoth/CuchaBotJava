package Main;

import Main.StreamWhen.IMissFauna;
import Main.Commands.Patchs.PatchRotation;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ButtonListener extends ListenerAdapter {
    private PatchRotation patchRotation = new PatchRotation();
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        patchRotation.honkaiOldPatch(event);
        faunaRandomStream(event);

    }

    private void faunaRandomStream(ButtonInteractionEvent event) {
        if(event.getButton().getId().equals("faunaRandomStream")){
            try {
                IMissFauna fauna = new IMissFauna(2);
                event.reply("Random Stream: " + fauna.getStream()).setEphemeral(true).queue();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
