package Main.StreamWhen;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class IMissFauna {
    private String stream;
    private String countDown;
    private String imageURL = "";
    private boolean currentStream = false;
    private boolean changeAlert = false;


    public IMissFauna() throws IOException {
        String url = "https://imissfauna.com";
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);//Enable the JS interpreter, the default is true
        webClient.getOptions().setCssEnabled(false);//disable css support
        webClient.getOptions().setThrowExceptionOnScriptError(false);//Whether to throw an exception when js runs incorrectly
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(5 * 1000);//Set connection timeout
        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(5 * 1000);//wait for js background execution for 30 seconds

        String pageAsXml = page.asXml();

        //Jsoup analysis processing
        Document fauwuna = Jsoup.parse(pageAsXml, "https://imissfauna.com");

        //old Jsoup
        /*Document fauwuna = Jsoup.connect("https://imissfauna.com")
                .userAgent("Mozilla")
                .get();*/

        Elements currentStreamTag = fauwuna.getElementsByTag("b");
        if (!currentStreamTag.text().equalsIgnoreCase("")){
            this.stream = currentStreamTag.text();
        }else {
            Element nextStreamRef = fauwuna.select("a[href]").first();
            assert nextStreamRef != null;
            this.stream = nextStreamRef.attr("href");
        }

        Elements countDownClass = fauwuna.getElementsByTag("a");
        if(!countDownClass.text().equalsIgnoreCase("")) {
            this.countDown = countDownClass.get(0).text();
            Element image = fauwuna.select("img").first();
            assert image != null;
            this.imageURL = image.attr("src");
        }else{
            Elements image = fauwuna.select("img");
            this.imageURL = image.get(1).attr("src");
            this.currentStream = true;

        }
    }

    public IMissFauna(int i) throws IOException {
        Document fauwuna = Jsoup.connect("https://imissfauna.com/reps")
                .userAgent("Mozilla")
                .get();
        Element randomStreamRef = fauwuna.select("a[href]").first();
        assert randomStreamRef != null;
        this.stream = randomStreamRef.attr("href");
    }

    public void faunaDoko(SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("faunadoko")){
            Button faunaRandomStream = Button.primary("faunaRandomStream", "Random Stream");
            String message = "";
            if (!this.imageURL.equalsIgnoreCase("") && !this.stream.equalsIgnoreCase("")) {
                if (this.currentStream) {
                    message = "She's live!! >:O" + "\n" + "Current Streamerino: " + this.stream + "\n" + this.imageURL;
                }else{
                    message = "Nexto Stream: " + this.stream + "\n" + "Countdown: " + this.countDown + "\n" + this.imageURL;
                }
            }else{
                Member cucharoth = event.getGuild().getOwner();
                assert cucharoth != null;
                message = "Por la cuchachucha " + cucharoth.getAsMention() + " \nThey changed the coderino aganeeee!!!11 REEEEE!!";
            }
            Message messageBuilder = new MessageBuilder()
                    .append(message)
                    .setActionRows(
                            ActionRow.of(faunaRandomStream)
                    ).build();
            event.getHook().sendMessage(messageBuilder).queue();
        }
    }

    public String getStream() {
        return stream;
    }

    public String getCountDown() {
        return countDown;
    }

    public String getImageURL() {
        return imageURL;
    }

    public boolean isCurrentStream() {
        return currentStream;
    }
}
