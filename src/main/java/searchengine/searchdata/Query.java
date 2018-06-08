package searchengine.searchdata;

import searchengine.document.Document;

import java.io.IOException;

public interface Query {
    int getNbResults() throws IOException;
    Iterable<Document> searchNext(int nbDocs) throws IOException;
}
