package Main.Commands.Fauna;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class IMissFauna {
    private String stream;
    private String countDown;
    private String imageURL;
    private boolean currentStream = false;


    public IMissFauna() throws IOException {
        Document fauwuna = Jsoup.connect("https://imissfauna.com")
                .userAgent("Mozilla")
                .get();

        Elements currentStreamTag = fauwuna.getElementsByTag("b");
        if (!currentStreamTag.text().equalsIgnoreCase("")){
            this.stream = currentStreamTag.text();
        }else {
            Element nextStreamRef = fauwuna.select("a[href]").first();
            assert nextStreamRef != null;
            this.stream = nextStreamRef.attr("href");
        }

        Elements countDownClass = fauwuna.getElementsByClass("Home_countdown__UZM9_");
        this.countDown = countDownClass.text();

        Element image = fauwuna.select("img").first();
        assert image != null;
        this.imageURL = image.attr("src");
    }

    public IMissFauna(int i) throws IOException {
        Document fauwuna = Jsoup.connect("https://imissfauna.com/reps")
                .userAgent("Mozilla")
                .get();
        Element randomStreamRef = fauwuna.select("a[href]").first();
        assert randomStreamRef != null;
        this.stream = randomStreamRef.attr("href");
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
