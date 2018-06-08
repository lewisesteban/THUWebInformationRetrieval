package searchengine.searchdata;

import searchengine.document.Document;

import java.io.IOException;

public interface SearchDataWriter extends AutoCloseable {

    void addDoc(Document doc) throws IOException;
}
