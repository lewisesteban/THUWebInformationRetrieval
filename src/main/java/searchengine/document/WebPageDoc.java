package searchengine.document;

public class WebPageDoc implements Document {

    protected String url;
    protected String content;
    protected String title;

    public WebPageDoc(String url, String content, String title) {
        this.url = url;
        this.content = content;
        this.title = title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
