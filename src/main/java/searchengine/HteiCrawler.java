package searchengine;

import searchengine.conf.CrawlerStartingPoints;
import searchengine.crawler.Crawler4j;
import searchengine.crawler.SyncCrawler;
import searchengine.document.Document;
import searchengine.conf.RecipePageIdentifier;
import searchengine.io.CrawlInput;
import searchengine.io.CrawlOutput;
import searchengine.searchdata.LuceneIndexWriter;
import searchengine.searchdata.SearchDataWriter;

import java.io.IOException;

/**
 * Crawls, stores crawled data and performs static ranking on stored data.
 */
public class HteiCrawler implements CrawlInput {

    private SearchDataWriter writer = new LuceneIndexWriter("lucene");
    private SyncCrawler crawler = new Crawler4j(this::receivePage, "crawler", CrawlerStartingPoints.get());
    private CrawlOutput out;
    private Thread crawlingThread = null;

    public HteiCrawler(CrawlOutput out) throws Exception {
        this.out = out;
    }

    public void start(int nbCrawlers) {
        if (!isRunning()) {
            crawlingThread = new Thread(() -> go(nbCrawlers));
            crawlingThread.start();
            out.crawlStarted();
        }
    }

    private void go(int nbCrawlers) {
        try {
            crawler.start(nbCrawlers);
        } catch (Exception e) {
            error(e);
        }
    }

    public void stop() {
        crawler.stop();
        try {
            crawlingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        crawlingThread = null;
        out.crawlStopped();
    }

    public boolean isRunning() {
        return crawler.isRunning();
    }

    @Override
    public void close() {
        try {
            if (isRunning()) {
                stop();
            }
            writer.close();
        } catch (Exception e) {
            error(e);
        }
    }

    private void receivePage(Document doc) {
        try {
            if (mustSave(doc)) {
                writer.addDoc(doc);
                out.crawledPage(doc.getUrl(), true);
            } else {
                out.crawledPage(doc.getUrl(), false);
            }
        } catch (IOException e) {
            error(e);
        }
    }

    private boolean mustSave(Document doc) {
        return RecipePageIdentifier.isCookingPage(doc.getUrl(), doc.getContent());
    }

    private void error(Exception e) {
        out.error(e);
        if (isRunning()) {
            stop();
        }
    }
}
