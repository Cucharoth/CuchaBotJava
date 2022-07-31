package Main.Commands;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import Main.Patchs.*;

public class BotCommands extends ListenerAdapter {
    private final PatchRotation patchRotation = new PatchRotation();

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        //add comands on interaction
        honkaiRotation(event);
        patchRotation.honkaiOldPatchRotation(event);
    }

    private void honkaiRotation(SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("honkai_current_rotation")){
            event.reply("https://i.imgur.com/hiT8kvW.jpg" + "\n" + "https://i.imgur.com/aI38iPy.png" + "\n" +
                    "Patch 5.8 (Painto girl)").setEphemeral(true).queue();
        }

    }

    //onGuild
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        //add commands to the bot
        commandData.add(Commands.slash("honkai_current_rotation", "Honkai current patch info"));
        commandData.add(Commands.slash("honkai_old_patch_info", "Honkai old patch info"));

        //update them
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    //global
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        //add commands
        //commandData.add(Commands.slash("honkai_rotation", "Honkai current patch rotation"));

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
