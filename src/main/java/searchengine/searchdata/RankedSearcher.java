package searchengine.searchdata;

import org.apache.lucene.queryparser.classic.ParseException;

public interface RankedSearcher extends AutoCloseable {

    Query newQuery(String queryStr) throws ParseException;
}
