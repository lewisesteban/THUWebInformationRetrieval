package searchengine;

import org.apache.lucene.queryparser.classic.ParseException;
import searchengine.io.SEInput;
import searchengine.searchdata.LuceneIndexReader;
import searchengine.searchdata.Query;
import searchengine.searchdata.RankedSearcher;

import java.io.IOException;

public class HteiSearchEngine implements SEInput {

    private RankedSearcher searcher = new LuceneIndexReader("lucene");
    
    public HteiSearchEngine() throws IOException { }

    @Override
    public Query newQuery(String[] placeNames, String[] ingredients) throws ParseException {
        // example: "content:(+(\"China\" OR \"Chinese\") AND (cat dog garlic onion))"

        StringBuilder queryStr = new StringBuilder("content:(+(");
        boolean first = true;
        for (String place : placeNames) {
            if (first)
                first = false;
            else
                queryStr.append(" OR ");
            queryStr.append("\"").append(place).append("\"");
        }
        queryStr.append(") AND (");

        first = true;
        for (String ingredient : ingredients) {
            if (first)
                first = false;
            else
                queryStr.append(" ");
            queryStr.append(ingredient);
        }
        queryStr.append("))");

        return searcher.newQuery(queryStr.toString());
    }

    @Override
    public void close() throws Exception {
        searcher.close();
    }
}
