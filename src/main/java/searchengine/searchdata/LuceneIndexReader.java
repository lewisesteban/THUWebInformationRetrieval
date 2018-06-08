package searchengine.searchdata;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import searchengine.document.Document;
import searchengine.document.LuceneDocument;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LuceneIndexReader implements RankedSearcher {

    private StandardAnalyzer analyzer;
    private IndexReader reader;
    private IndexSearcher searcher;
    private ScoreDoc lastSearched = null;

    public LuceneIndexReader(String directory) throws IOException {
        analyzer = new StandardAnalyzer();
        Directory index = new SimpleFSDirectory(Paths.get(directory));
        reader = DirectoryReader.open(index);
        searcher = new IndexSearcher(reader);
    }

    @Override
    public Query newQuery(String queryStr) throws ParseException {
        return new LuceneQuery(queryStr);
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }

    private class LuceneQuery implements Query {

        org.apache.lucene.search.Query luceneQuery;

        LuceneQuery(String queryStr) throws ParseException {
            luceneQuery = new QueryParser("content", analyzer).parse(queryStr);
        }

        @Override
        public int getNbResults() throws IOException {
            return searcher.count(luceneQuery);
        }

        @Override
        public Iterable<Document> searchNext(int nbDocs) throws IOException {
            TopDocs docs = searcher.searchAfter(lastSearched, luceneQuery, nbDocs);
            ArrayList<Document> results = new ArrayList<>(nbDocs);
            for (int i = 0; i < docs.scoreDocs.length; ++i) {
                results.add(new LuceneDocument(searcher.doc(docs.scoreDocs[i].doc)));
            }
            lastSearched = docs.scoreDocs[docs.scoreDocs.length - 1];
            return results;
        }
    }
}
