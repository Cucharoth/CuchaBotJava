package Main;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.text.Normalizer;
import java.util.Calendar;
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
        }
    }

    private void takoTuesday(MessageReceivedEvent event){
        String messageSend = event.getMessage().getContentRaw().toLowerCase(Locale.ROOT);
        messageSend = stripDiacritics(messageSend);
        if(messageSend.contains("martes") || messageSend.contains("tuesday")){
            event.getTextChannel().sendMessage("https://i.imgur.com/kf7WnWY.png").queue();
        }
    }

    private void takoJueves(MessageReceivedEvent event){
        String messageSend = event.getMessage().getContentRaw().toLowerCase(Locale.ROOT);
        messageSend = stripDiacritics(messageSend);
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
