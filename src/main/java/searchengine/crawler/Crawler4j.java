package searchengine.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Crawler4j implements SyncCrawler {

    private CrawlController controller;
    private boolean started = false;

    public Crawler4j(PageReceiver pageReceiver, String storageDir, String[] seeds) throws Exception {
        PageReceiverSingleton.set(pageReceiver);
        CrawlConfig config = new CrawlConfig();
        config.setResumableCrawling(true);
        config.setCrawlStorageFolder(storageDir);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setUserAgentName("Tsinghua University web information retrieval course project");
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        controller = new CrawlController(config, pageFetcher, robotstxtServer);
        for (String seed : seeds) {
            controller.addSeed(seed);
        }
    }

    public void start(int nbCrawlers) {
        started = true;
        controller.start(WebCrawlerImp.class, nbCrawlers);
    }

    public void stop() {
        controller.shutdown();
        controller.waitUntilFinish();
    }

    public boolean isRunning() {
        return started && !controller.isFinished();
    }

}
