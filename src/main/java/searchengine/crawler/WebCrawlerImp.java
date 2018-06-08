package searchengine.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import searchengine.document.WebPageDoc;

import java.util.regex.Pattern;

public class WebCrawlerImp extends WebCrawler {

    private final static Pattern FILTERS = Pattern
            .compile(".*(\\.(css|js|gif|jpe?g" + "|png|mp3|zip|gz))$");

    /**
     * This method receives two parameters. The first parameter is the page in
     * which we have discovered this new url and the second parameter is the new
     * url. You should implement this function to specify whether the given url
     * should be crawled or not (based on your crawling logic). In this example,
     * we are instructing the crawler to ignore urls that have css, js, git, ...
     * extensions and to only accept urls that start with
     * "http://www.ics.uci.edu/". In this case, we didn't need the referringPage
     * parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches() && !forbidden(href);
    }

    private boolean forbidden(String url) {
        return url.contains("google") ||
                url.contains("facebook") ||
                url.contains("twitter") ||
                url.contains("youtube") ||
                url.contains("pinterest");
    }

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    @Override
    public void visit(Page page) {
        String html = new String(page.getContentData());
        Document doc = Jsoup.parse(html);
        String title;
        Elements titles = doc.getElementsByTag("title");
        if (titles.isEmpty()) {
            if (html.length() < 100)
                title = html;
            else
                title = html.substring(0, 100);
        } else {
            title = titles.get(0).html();
        }
        PageReceiverSingleton.get().receive(new WebPageDoc(
                page.getWebURL().getURL(), combineWhitespaces(page.getParseData().toString()), title));
    }

    private String combineWhitespaces(String str) {
        char[] newStr = new char[str.length()];
        int newStrLen = 0;
        boolean prevIsWhitespace = false;
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == ' ' || c == '\t' || c == '\n' || c == '\r') {
                if (!prevIsWhitespace) {
                    prevIsWhitespace = true;
                    newStr[newStrLen] = ' ';
                    newStrLen++;
                }
            } else {
                prevIsWhitespace = false;
                newStr[newStrLen] = c;
                newStrLen++;
            }
        }
        return new String(newStr, 0, newStrLen);
    }
}