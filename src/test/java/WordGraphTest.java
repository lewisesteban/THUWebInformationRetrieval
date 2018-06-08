import org.junit.Test;
import searchengine.conf.WordGraph;

public class WordGraphTest {

    @Test
    public void testWordGraph() {
        WordGraph graph = new WordGraph(2);
        graph.feed("dog", 1);
        graph.feed("docker", 10);
        graph.feed("doc", 100);
        graph.feed("cat", 1000);
        graph.feed("cat", 0.1);
        graph.feed("at", 0.01);
        graph.feed("'", 0.001);
        graph.feed("hve", 1);
        assert graph.weigh("I have a dog who likes docker but doesn't like cats.") == 1111.111;
    }

    @Test
    public void testMacCount() {
        WordGraph graph = new WordGraph(2);
        graph.feed("test", 1);
        graph.feed("one", 10);
        assert graph.weigh("test test test one") == 12;
    }
}
