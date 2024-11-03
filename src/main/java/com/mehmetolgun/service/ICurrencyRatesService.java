package com.mehmetolgun.service;

import com.mehmetolgun.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

    public CurrencyRatesResponse getCurrencyRates(String startDate , String endDate);
}
