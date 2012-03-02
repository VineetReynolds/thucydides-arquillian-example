package com.mycompany.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
* An implementation of the {@link CurrencyExchangeService}
* that uses a static set of exchange rates.
*
* @author Vineet Reynolds
*
*/
@Named("exchangeService")
@Stateless
@Local(CurrencyExchangeService.class)
public class StaticCurrencyExchangeService implements CurrencyExchangeService
{
   
   static Map<String, BigDecimal> rates;
   
   static
   {
      rates = new HashMap<String, BigDecimal>();
      rates.put("EURUSD", new BigDecimal("1.3"));
      rates.put("GBPUSD", new BigDecimal("1.5"));
      rates.put("USDJPY", new BigDecimal("75"));
   }

   @Override
   public BigDecimal getQuote(Currency fromCurrency, BigDecimal amount, Currency toCurrency)
   {
      String exchangePair = fromCurrency.getCurrencyCode() + toCurrency.getCurrencyCode();
      BigDecimal exchangeRate = rates.get(exchangePair);
      if(exchangeRate == null)
      {
         String reverseExchangePair = toCurrency.getCurrencyCode() + fromCurrency.getCurrencyCode();
         exchangeRate = rates.get(reverseExchangePair);
         return amount.divide(exchangeRate, 5, RoundingMode.HALF_EVEN);
      }
      return exchangeRate.multiply(amount);
   }

}