package searchengine.searchdata;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import searchengine.document.Document;

import java.io.IOException;
import java.nio.file.Paths;

public class LuceneIndexWriter implements SearchDataWriter {

    private IndexWriter writer;

    public LuceneIndexWriter(String directory) throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = new SimpleFSDirectory(Paths.get(directory));
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        writer = new IndexWriter(index, config);
    }

    @Override
    public void addDoc(Document doc) throws IOException {
        org.apache.lucene.document.Document luceneDoc = new org.apache.lucene.document.Document();
        luceneDoc.add(new TextField("content", doc.getContent(), Field.Store.YES));
        luceneDoc.add(new StringField("url", doc.getUrl(), Field.Store.YES));
        luceneDoc.add(new StringField("title", doc.getTitle(), Field.Store.YES));
        writer.addDocument(luceneDoc);
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}
