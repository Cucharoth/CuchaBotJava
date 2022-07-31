package Main;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.Calendar;
import java.util.Date;

public class BotListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(!event.getAuthor().isBot()) {
            //add actions
            takoTuesday(event);
            takoJueves(event);
        }
    }

    private void takoTuesday(MessageReceivedEvent event){
        String messageSend = event.getMessage().getContentRaw();
        if(messageSend.contains("martes") || messageSend.contains("tuesday")){
            event.getTextChannel().sendMessage("https://i.imgur.com/kf7WnWY.png").queue();
        }
    }

    private void takoJueves(MessageReceivedEvent event){
        String messageSend = event.getMessage().getContentRaw();
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
}
