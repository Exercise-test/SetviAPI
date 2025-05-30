package controllers;

public class RfqUrlRequest {
    private String url;

    public RfqUrlRequest(String url) {
        this.url = url;
    }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}