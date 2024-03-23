package com.example.testbot;


public class CurrencyService {
    private final CurrencyDAO currencyDAO = new CurrencyDAO();
    public void saveUser(CurrencyState currencyState) {
        currencyDAO.save(currencyState);
    }
}
