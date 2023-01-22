package ru.gb.market_spring.api;

public class MarketError {
    private int statusCode;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MarketError() {
    }

    public MarketError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
