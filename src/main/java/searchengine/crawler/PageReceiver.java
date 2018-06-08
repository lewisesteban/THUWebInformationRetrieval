package searchengine.crawler;

import searchengine.document.Document;

public interface PageReceiver {
    void receive(Document doc);
}
