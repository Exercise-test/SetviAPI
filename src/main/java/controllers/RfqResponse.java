package controllers;

import java.util.List;

public class RfqResponse {
    private String status;
    private Result result;
    private boolean isSuccess;
    private boolean isCancelled;

    // Getters and setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Result getResult() { return result; }
    public void setResult(Result result) { this.result = result; }

    public boolean isSuccess() { return isSuccess; }
    public void setSuccess(boolean success) { isSuccess = success; }

    public boolean isCancelled() { return isCancelled; }
    public void setCancelled(boolean cancelled) { isCancelled = cancelled; }

    public static class Result {
        private List<MatchedItem> matchedItems;

        public List<MatchedItem> getMatchedItems() { return matchedItems; }
        public void setMatchedItems(List<MatchedItem> matchedItems) { this.matchedItems = matchedItems; }
    }

    public static class MatchedItem {
        private List<MatchedInternalProduct> matchedInternalProducts;

        public List<MatchedInternalProduct> getMatchedInternalProducts() { return matchedInternalProducts; }
        public void setMatchedInternalProducts(List<MatchedInternalProduct> matchedInternalProducts) {
            this.matchedInternalProducts = matchedInternalProducts;
        }
    }

    public static class MatchedInternalProduct {
        private String name;
        private String sku;
        private String reasoning;
        private int percentage;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getSku() { return sku; }
        public void setSku(String sku) { this.sku = sku; }

        public String getReasoning() { return reasoning; }
        public void setReasoning(String reasoning) { this.reasoning = reasoning; }

        public int getPercentage() { return percentage; }
        public void setPercentage(int percentage) { this.percentage = percentage; }
    }
}
