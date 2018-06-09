import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.store.SimpleFSDirectory;
import org.jsoup.Jsoup;
import searchengine.HteiCrawler;
import searchengine.conf.PageIdentifier;
import searchengine.io.CrawlOutput;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        //crawlerTest();
        //luceneTest(args);
        //syncTest();
        //luceneQueryTest();
        //seDataTest();
        recipeIdentifierTest();
    }

    private static void recipeIdentifierTest() throws IOException {
        byte[] buffer = new byte[2048];
        int size = 0;
        size = System.in.read(buffer);
        while (size > 0) {
            String url = new String(Arrays.copyOf(buffer, size));
            org.jsoup.nodes.Document target = Jsoup.connect(url).get();
            double weigh = PageIdentifier.weigh(target.html());
            System.out.println(weigh + "\t" + target.html().length() + "\t" + (weigh / target.html().length()));
            size = System.in.read(buffer);
        }
    }

    private static void seDataTest() throws Exception {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = new SimpleFSDirectory(Paths.get("lucene"));
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = new IndexWriter(index, config);
        w.deleteAll();
        Document doc = new Document();
        doc.add(new TextField("content", "china dog", Field.Store.YES));
        doc.add(new StringField("url", "https://cn.bing.com", Field.Store.YES));
        w.addDocument(doc);
        w.close();
    }

    private static void crawlerTest() throws Exception {
        CrawlOutput out = new CrawlOutput() {
            @Override
            public void crawlStarted() {
                System.out.println("started");
            }

            @Override
            public void crawledPage(String url, boolean saved) {
                System.out.println(url + "\t" + saved);
            }

            @Override
            public void crawlStopped() {
                System.out.println("stopped");
            }

            @Override
            public void error(Exception e) {
                System.out.println("error " + e.getMessage());
            }
        };
        HteiCrawler loader = new HteiCrawler(out);
        loader.start(6);
    }

    private static void syncTest() throws InterruptedException {
        Object o = new Object();
        synchronized (o) {
            new Thread(() -> {
                synchronized (o) {
                    o.notifyAll();
                }
            }).start();
            o.wait();
            System.out.println("success");
        }
    }

    private static void luceneQueryTest() throws Exception {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = new SimpleFSDirectory(Paths.get("luceneFSDirTest"));

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = new IndexWriter(index, config);
        w.deleteAll();
        addDoc(w, "bla bla chinese cat horse", "2");
        addDoc(w, "bla bla chinese cat", "3");
        addDoc(w, "bla bla chinese china china chinese cat", "3");
        addDoc(w, "bla bla chinese china china chinese cat dog", "2");
        addDoc(w, "bla bla chinese cat dog horse", "1");
        addDoc(w, "bla bla chinese", "4");
        addDoc(w, "bla bla cat dog mouse horse", "0");
        w.close();

        String querystr = "title:(+(\"China\" OR \"Chinese\")^0 AND (cat dog mouse horse))";
        Query q = new QueryParser("title", analyzer).parse(querystr);
        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        System.out.println("Found " + hits.length + " hits.");
        for(int i = 0; i < hits.length; ++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
        }
    }


    private static void luceneTest(String[] args) throws Exception {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = new SimpleFSDirectory(Paths.get("luceneFSDirTest"));

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = new IndexWriter(index, config);
        w.deleteAll();
        addDoc(w, "cat", "0");
        addDoc(w, "dog cat", "1");
        addDoc(w, "dog dog dog dog dog cat", "5");
        addDoc(w, "dog dog cat", "2");
        addDoc(w, "dog dog dog dog cat", "4");
        addDoc(w, "dog dog dog cat", "3");
        w.close();

        String querystr = args.length > 0 ? args[0] : "dog";
        Query q = new QueryParser("title", analyzer).parse(querystr);
        int hitsPerPage = 4;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        System.out.println("Found " + hits.length + " hits.");
        for(int i = 0; i < hits.length; ++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
        }

        docs = searcher.searchAfter(hits[hits.length - 1], q, hitsPerPage);
        hits = docs.scoreDocs;
        System.out.println("Found " + hits.length + " hits.");
        for(int i = 0; i < hits.length; ++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
        }

        querystr = args.length > 1 ? args[1] : "cat";
        q = new QueryParser("title", analyzer).parse(querystr);
        hitsPerPage = 10;
        reader = DirectoryReader.open(index);
        searcher = new IndexSearcher(reader);
        docs = searcher.search(q, hitsPerPage);
        hits = docs.scoreDocs;
        System.out.println("Found " + hits.length + " hits.");
        for(int i = 0; i < hits.length; ++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
        }
    }

    private static void addDoc(IndexWriter w, String title, String isbn) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new StringField("isbn", isbn, Field.Store.YES));
        w.addDocument(doc);
    }
}
