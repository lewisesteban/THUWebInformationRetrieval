package searchengine.document;

public class LuceneDocument extends WebPageDoc {

    public LuceneDocument(org.apache.lucene.document.Document luceneDoc) {
        super(luceneDoc.get("url"), luceneDoc.get("content"), luceneDoc.get("title"), "");
    }
}
