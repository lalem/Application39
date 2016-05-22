package com.dnkilic.application38;

public class Currency {

    private CurrencyCode currencyCode;
    private Integer crossOrder;
    private String currencyName;
    private Integer currencyUnit;
    private Double banknoteBuying;
    private Double banknoteSelling;
    private Double forexBuying;
    private Double forexSelling;
    private Double crossRateUSD;
    private Double crossRateOther;

    public Currency() {
    }

    public void setCurrencyUnit(Integer currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setCrossOrder(Integer crossOrder) {
        this.crossOrder = crossOrder;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setBanknoteBuying(Double banknoteBuying) {
        this.banknoteBuying = banknoteBuying;
    }

    public void setBanknoteSelling(Double banknoteSelling) {
        this.banknoteSelling = banknoteSelling;
    }

    public void setForexBuying(Double forexBuying) {
        this.forexBuying = forexBuying;
    }

    public void setForexSelling(Double forexSelling) {
        this.forexSelling = forexSelling;
    }

    public void setCrossRateUSD(Double crossRateUSD) {
        this.crossRateUSD = crossRateUSD;
    }

    public void setCrossRateOther(Double crossRateOther) {
        this.crossRateOther = crossRateOther;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public Integer getCrossOrder() {
        return crossOrder;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public Integer getCurrencyUnit() {
        return currencyUnit;
    }

    public Double getBanknoteBuying() {
        return banknoteBuying;
    }

    public Double getBanknoteSelling() {
        return banknoteSelling;
    }

    public Double getForexBuying() {
        return forexBuying;
    }

    public Double getForexSelling() {
        return forexSelling;
    }

    public Double getCrossRateUSD() {
        return crossRateUSD;
    }

    public Double getCrossRateOther() {
        return crossRateOther;
    }
}
