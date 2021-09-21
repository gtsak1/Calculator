package com.calc.calculator.Model;

public class Currency {
    public String FromCurrency;
    public String ToCurrency;
    public Double amount;

    public Currency(String fromCurrency, String toCurrency, Double amount) {
        this.FromCurrency = fromCurrency;
        this.ToCurrency = toCurrency;
        this.amount = amount;
    }

    public String getFromCurrency() {
        return FromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.FromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return ToCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.ToCurrency = toCurrency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
