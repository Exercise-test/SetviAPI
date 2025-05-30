package controllers;

public class RfqRequest {
    private String text;
    private int topK;
    private double threshold;
    private boolean enablePrivateLabelRanking;
    private boolean enableStockProductRanking;
    private boolean enableVendorRanking;

    public RfqRequest(String text, int topK, double threshold,
                      boolean enablePrivateLabelRanking,
                      boolean enableStockProductRanking,
                      boolean enableVendorRanking) {
        this.text = text;
        this.topK = topK;
        this.threshold = threshold;
        this.enablePrivateLabelRanking = enablePrivateLabelRanking;
        this.enableStockProductRanking = enableStockProductRanking;
        this.enableVendorRanking = enableVendorRanking;
    }

    // Getters and setters
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public int getTopK() { return topK; }
    public void setTopK(int topK) { this.topK = topK; }

    public double getThreshold() { return threshold; }
    public void setThreshold(double threshold) { this.threshold = threshold; }

    public boolean isEnablePrivateLabelRanking() { return enablePrivateLabelRanking; }
    public void setEnablePrivateLabelRanking(boolean enablePrivateLabelRanking) {
        this.enablePrivateLabelRanking = enablePrivateLabelRanking;
    }

    public boolean isEnableStockProductRanking() { return enableStockProductRanking; }
    public void setEnableStockProductRanking(boolean enableStockProductRanking) {
        this.enableStockProductRanking = enableStockProductRanking;
    }

    public boolean isEnableVendorRanking() { return enableVendorRanking; }
    public void setEnableVendorRanking(boolean enableVendorRanking) {
        this.enableVendorRanking = enableVendorRanking;
    }
}
