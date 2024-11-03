package com.mehmetolgun.controller;

import com.mehmetolgun.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

    public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate , String endDate);
}

