package searchengine.crawler;

public interface Crawler {

    /**
     * Starts crawling on a new thread.
     * @param nbCrawlers Number of crawlers to start.
     */
    void start(int nbCrawlers);

    /**
     * Stops crawling and releases the crawling thread.
     */
    void stop();

    /**
     * @return Whether the crawler is running.
     */
    boolean isRunning();
}
