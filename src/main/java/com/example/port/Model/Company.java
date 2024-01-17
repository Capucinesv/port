package com.example.port.Model;

public enum Company {
    AAPL("AAPL", "Apple"),
    GOOG("GOOG", "Google Inc"),
    GOOGL("GOOGL", "Google Inc"),
    AMZN("AMZN", "Amazon.com"),
    TSLA("TSLA", "Tesla Inc"),
    MSFT("MSFT", "Microsoft");

    private final String tickerSymbol;
    private final String companyName;

    Company(String tickerSymbol, String companyName) {
        this.tickerSymbol = tickerSymbol;
        this.companyName = companyName;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public String getCompanyName() {
        return companyName;
    }
}