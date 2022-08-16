package Main;

import Main.Commands.Patchs.PatchRotation;
import Main.StreamWhen.IMissFauna;
import Main.StreamWhen.IMissIna;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.Normalizer;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class BotListener extends ListenerAdapter {
    public static final Pattern DIACRITICS_AND_FRIENDS = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(!event.getAuthor().isBot()) {
            //add actions
            takoTuesday(event);
            takoJueves(event);
            faunaWhen(event);
            inaWhen(event);
            honkaiPatch(event);
        }
    }

    private void honkaiPatch(MessageReceivedEvent event) {
        String messageSend = event.getMessage().getContentRaw().toLowerCase(Locale.ROOT);
        messageSend = stripDiacritics(messageSend);
        messageSend = messageSend.replaceAll("\\s", "");
        if(messageSend.contains("honkaipatch")){
            PatchRotation currentpatch = new PatchRotation();
            currentpatch.honkaiPatchListener(event);
        }
    }

    private void inaWhen(MessageReceivedEvent event){
        String messageSend = event.getMessage().getContentRaw().toLowerCase(Locale.ROOT);
        messageSend = stripDiacritics(messageSend);
        messageSend = messageSend.replaceAll("\\s", "");
        if(messageSend.contains("inawhen")){
            event.getTextChannel().sendMessage("Uno momento.. Finding Ina..").queue();
            IMissIna Inya = null;
            //Button inaRandomStream = Button.success("inaRandomStream", "Random Ina Stream");
            try {
                Inya = new IMissIna();
                String message = "";
                if (!Inya.getImageURL().equalsIgnoreCase("") && !Inya.getStream().equalsIgnoreCase("")) {  //htmlChangeCheck
                    if (Inya.isCurrentStream()) {
                        message = "She's live!! >:O" + "\n" + "Current Streamerino: " + Inya.getStream() + "\n" + Inya.getImageURL();
                    } else {
                        message = "Nexto Stream: " + Inya.getStream() + "\n" + "Countdown: " + Inya.getCountDown() + "\n" + Inya.getImageURL();
                    }
                }else{
                    Member cucharoth = event.getGuild().getOwner();
                    assert cucharoth != null;
                    message = "Por la cuchachucha " + cucharoth.getAsMention() + " \nThey changed the coderino aganeeee!!!11 REEEEE!!";
                }
                Message messageBuilder = new MessageBuilder()
                        .append(message)
                        .setActionRows(
                                //ActionRow.of(faunaRandomStream)
                        ).build();
                event.getTextChannel().sendMessage(messageBuilder).queue();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void faunaWhen(MessageReceivedEvent event){
        String messageSend = event.getMessage().getContentRaw().toLowerCase(Locale.ROOT);
        messageSend = stripDiacritics(messageSend);
        messageSend = messageSend.replaceAll("\\s", "");
        if(messageSend.contains("faunawhen")){
            event.getTextChannel().sendMessage("Uno momento.. Finding Fauna..").queue();
            IMissFauna fauwuna = null;
            Button faunaRandomStream = Button.success("faunaRandomStream", "Random Fauwuna Stream");
            try {
                fauwuna = new IMissFauna();
                String message = "";
                if (!fauwuna.getImageURL().equalsIgnoreCase("") && !fauwuna.getStream().equalsIgnoreCase("")) {  //htmlChangeCheck
                    if (fauwuna.isCurrentStream()) {
                        message = "She's live!! >:O" + "\n" + "Current Streamerino: " + fauwuna.getStream() + "\n" + fauwuna.getImageURL();
                    } else {
                        message = "Nexto Stream: " + fauwuna.getStream() + "\n" + "Countdown: " + fauwuna.getCountDown() + "\n" + fauwuna.getImageURL();
                    }
                }else{
                    Member cucharoth = event.getGuild().getOwner();
                    assert cucharoth != null;
                    message = "Por la cuchachucha " + cucharoth.getAsMention() + " \nThey fking change it aganeeee!!!11 REEEEE!!";
                }
                Message messageBuilder = new MessageBuilder()
                        .append(message)
                        .setActionRows(
                                ActionRow.of(faunaRandomStream)
                        ).build();
                event.getTextChannel().sendMessage(messageBuilder).queue();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void takoTuesday(MessageReceivedEvent event){
        String messageSend = event.getMessage().getContentRaw().toLowerCase(Locale.ROOT);
        messageSend = stripDiacritics(messageSend);
        messageSend = messageSend.replaceAll("\\s", "");
        if(messageSend.contains("martes") || messageSend.contains("tuesday")){
            event.getTextChannel().sendMessage("https://i.imgur.com/kf7WnWY.png").queue();
        }
    }

    private void takoJueves(MessageReceivedEvent event){
        String messageSend = event.getMessage().getContentRaw().toLowerCase(Locale.ROOT);
        messageSend = stripDiacritics(messageSend);
        messageSend = messageSend.replaceAll("\\s", "");
        if(messageSend.contains("jueves") || messageSend.contains("thursday")){
            Calendar cal = Calendar.getInstance();
            Date date = new Date();
            cal.setTime(date);
            if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
                event.getTextChannel().sendMessage("https://c.tenor.com/riA830QHvC8AAAAC/feliz-jueves-asuka.gif").queue();
            }else{
                event.getTextChannel().sendMessage("Si no es jueves no es un dia feliz").queue();
            }
        }
    }

    private static String stripDiacritics(String string) {
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        string = DIACRITICS_AND_FRIENDS.matcher(string).replaceAll("");
        return string;
    }
}
