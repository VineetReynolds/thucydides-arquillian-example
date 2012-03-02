package com.mycompany.domain;

import java.math.BigDecimal;
import java.util.Currency;

/**
* A currency exchange service.
*
* @author Vineet Reynolds
*
*/
public interface CurrencyExchangeService
{
   BigDecimal getQuote(Currency fromCurrency, BigDecimal amount, Currency toCurrency);
}