package searchengine.io;

public interface CrawlOutput {
    void crawlStarted();
    void crawledPage(String url, boolean saved);
    void crawlStopped();
    void error(Exception e);
}
