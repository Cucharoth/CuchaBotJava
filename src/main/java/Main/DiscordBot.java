package Main;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class DiscordBot extends ListenerAdapter {
    private final ShardManager shardManager;
    private final Dotenv config = Dotenv.configure().load();


    public DiscordBot() throws LoginException{
        //Un// for local testing
        String token = config.get("TOKEN");


        //String token = System.getenv("TOKEN");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setActivity(Activity.playing("Honkai"));
        shardManager = builder.build();
        shardManager.addEventListener(new BotListener());
        shardManager.addEventListener(new BotCommands());
        shardManager.addEventListener(new ButtonListener());
    }

    public ShardManager getShardManager(){
        return shardManager;
    }



    public static void main(String[] args) {
        System.out.println("Actually Working >:O");
        try {
            DiscordBot discordBot = new DiscordBot();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        //Another way of building
        /*JDA jda = JDABuilder.createDefault("OTk3ODcyMTA4MDkwNjk1ODIx.G06Sd-.H6pSxC-NTaqv2krdAY1rU1wLfF_j2Ri_R41tSY")
                .setActivity(Activity.playing("Honkai"))
                .addEventListeners(new BotCommands())
                .build();/*
        /*Guild guild = jda.getGuildById("286988491160551424");
        if(guild != null) {
            guild.upsertCommand("test1", "test").queue();*/
    }
}
