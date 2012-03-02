package com.mycompany.pages;

import java.math.BigDecimal;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://localhost:8080/test/exchangeCurrencies.xhtml")
public class ExchangeCurrenciesPage extends PageObject {

    @FindBy(id="inputForm:fromCurrency")
	private WebElement fromCurrencyField;
    
    @FindBy(id="inputForm:toCurrency")
	private WebElement toCurrencyField;
    
    @FindBy(id="inputForm:amount")
	private WebElement amountField;
	
	@FindBy(id="inputForm:obtainQuoteAction")
	private WebElement obtainQuoteButton;
	
	public ExchangeCurrenciesPage(WebDriver driver) {
		super(driver);
	}

	public void enter_amount_and_currencies(String amount, String fromCurrency, String toCurrency) {
        element(amountField).type(amount);
        element(fromCurrencyField).type(fromCurrency);
        element(toCurrencyField).type(toCurrency);
	}

    public void lookup_quote() {
        element(obtainQuoteButton).click();
    }

    public String obtainQuote() {
        WebElement quoteElement = getDriver().findElement(By.id("inputForm:quote"));
        return quoteElement.getText();
    }

}


