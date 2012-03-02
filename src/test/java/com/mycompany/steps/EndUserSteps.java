package com.mycompany.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.comparesEqualTo;

import java.math.BigDecimal;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

import com.mycompany.pages.ExchangeCurrenciesPage;

public class EndUserSteps extends ScenarioSteps {

    public EndUserSteps(Pages pages) {
        super(pages);
    }

    @Step
    public void enters(String amount, String fromCurrency, String toCurrency) {
        onExchangeCurrenciesPage().enter_amount_and_currencies(amount, fromCurrency, toCurrency);
    }

    @Step
    public void requests_quote() {
        onExchangeCurrenciesPage().lookup_quote();
    }

    private ExchangeCurrenciesPage onExchangeCurrenciesPage() {
        return getPages().currentPageAt(ExchangeCurrenciesPage.class);
    }

    private ExchangeCurrenciesPage ExchangeCurrenciesPage() {
        return getPages().currentPageAt(ExchangeCurrenciesPage.class);
    }

    @Step
    public void should_obtain_quote(String expectedQuote) {
        BigDecimal actualQuote = new BigDecimal(ExchangeCurrenciesPage().obtainQuote());
		assertThat(actualQuote, comparesEqualTo(new BigDecimal(expectedQuote)));
    }

    @Step
    public void is_the_exchange_currencies_page() {
        onExchangeCurrenciesPage().open();
    }

    @Step
    public void requests_quote(String amount, String fromCurrency, String toCurrency) {
        enters(amount, fromCurrency, toCurrency);
        requests_quote();
    }
}
