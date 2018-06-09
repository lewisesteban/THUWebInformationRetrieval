package searchengine.document;

public class WebPageDoc implements Document {

    private String url;
    private String content;
    private String title;
    private String html;

    public WebPageDoc(String url, String content, String title, String html) {
        this.url = url;
        this.content = content;
        this.title = title;
        this.html = html;
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

    @Override
    public String getHtml() {
        return html;
    }
}
