package ui.crawl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import searchengine.HteiCrawler;
import searchengine.io.CrawlInput;
import searchengine.io.CrawlOutput;

@Controller
@RequestMapping("/crawl")
public class CrawlController {

    private CrawlInput crawler = null;
    private int nbCrawled = 0;
    private int nbFound = 0;
    private Exception error = null;
    private boolean going = false;

    private synchronized CrawlInput getCrawler() throws Exception {
        Object controller = this;
        if (crawler == null) {
            crawler = new HteiCrawler(new CrawlOutput() {
                @Override
                public void crawlStarted() {
                    going = true;
                    controller.notifyAll();
                }

                @Override
                public void crawledPage(String url, boolean saved) {
                    nbCrawled++;
                    if (saved) {
                        nbFound++;
                        System.out.println("+++++ Found recipe: " + url);
                    } else {
                        System.out.println("- - - Not a recipe: " + url);
                    }
                }

                @Override
                public void crawlStopped() {
                    going = false;
                }

                @Override
                public void error(Exception e) {
                    error = e;
                }
            });
        }
        return crawler;
    }

    @GetMapping("")
    public synchronized String home(Model model) {
        try {
            if (going) {
                CrawlInput crawler = getCrawler();
                crawler.stop();
                crawler.close();
            }
            if (error != null) {
                model.addAttribute("error", error.getMessage());
                error = null;
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("message", "Crawled " + nbCrawled + " pages, found " + nbFound + " recipes.");
        return "crawl/home";
    }

    @GetMapping("/go")
    public synchronized String crawling(@RequestParam(name = "nbCrawlers", required = false, defaultValue = "4") int nbCrawlers, Model model) {
        Object controller = this;
        try {
            if (!going) {
                CrawlInput crawler = getCrawler();
                new Thread(() -> {
                    synchronized (controller) {
                        crawler.start(nbCrawlers);
                    }
                }).start();
                wait();
            }
            if (!going) {
                return home(model);
            }
            if (error != null) {
                model.addAttribute("error", error.getMessage());
                error = null;
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("message", "Crawled " + nbCrawled + " pages, found " + nbFound + " recipes.");
        return "crawl/crawling";
    }
}