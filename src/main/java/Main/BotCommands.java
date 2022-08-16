package Main;

import Main.StreamWhen.IMissFauna;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Main.Commands.Patchs.*;

public class BotCommands extends ListenerAdapter {
    private final PatchRotation patchRotation = new PatchRotation();

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        //add comands on interaction
        patchRotation.honkaiRotation(event);
        patchRotation.honkaiOldPatchRotation(event);
        faunaDoko(event);
    }

    private void faunaDoko(SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("faunadoko")){
            event.deferReply().setEphemeral(true).queue();
            IMissFauna fauwuna = null;
            try {
                fauwuna = new IMissFauna();
                fauwuna.faunaDoko(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //onGuild
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        //add commands to the bot
        commandData.add(Commands.slash("honkai_current_rotation", "Honkai current patch info"));
        commandData.add(Commands.slash("honkai_old_patch_info", "Honkai old patch info"));
        commandData.add(Commands.slash("faunadoko", "Fauna dokooo" ));

        //update them
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    //global
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        //add commands
        //commandData.add(Commands.slash("honkai_rotation_test", "Honkai current patch rotation"));

        //update them
        event.getJDA().updateCommands().addCommands(commandData).queue();
    }

    /*@Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        super.onGuildJoin(event);
        List<CommandData> commandsData = new ArrayList<>();
        commandsData.add(Commands.slash("Testo", "testo"));
        event.getGuild().updateCommands().addCommands(commandsData).queue();
    }*/


}
