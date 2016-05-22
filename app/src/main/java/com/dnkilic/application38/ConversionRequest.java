package com.dnkilic.application38;

public class ConversionRequest {

    private CurrencyCode sourceCurrency;
    private CurrencyCode destinationCurrency;
    private Double amount;

    public ConversionRequest(CurrencyCode sourceCurrency, CurrencyCode destinationCurrency, Double amount) {
        this.sourceCurrency = sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.amount = amount;
    }

    public CurrencyCode getSourceCurrency() {
        return sourceCurrency;
    }

    public CurrencyCode getDestinationCurrency() {
        return destinationCurrency;
    }

    public Double getAmount() {
        return amount;
    }
}
