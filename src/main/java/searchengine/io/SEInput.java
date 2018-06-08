package searchengine.io;

import org.apache.lucene.queryparser.classic.ParseException;
import searchengine.searchdata.Query;

public interface SEInput extends AutoCloseable {
    Query newQuery(String[] placeNames, String[] ingredients) throws ParseException;
}
