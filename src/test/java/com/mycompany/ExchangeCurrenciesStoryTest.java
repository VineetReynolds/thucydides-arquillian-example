package com.mycompany;

import java.io.File;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.mycompany.requirements.Application;
import com.mycompany.steps.EndUserSteps;
import com.mycompany.web.model.ExchangeCurrenciesModel;

@Story(Application.ExchangeCurrency.BuyForeignCurrency.class)
@RunWith(ThucydidesRunner.class)
public class ExchangeCurrenciesStoryTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "http://localhost:8080/test/exchangeCurrencies.xhtml")
    public Pages pages;

    @Steps
    public EndUserSteps endUser;
    
    @Deployment(testable=false)
	public static WebArchive createTestArchive() {
		WebArchive archive = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage("com.mycompany.domain")
				.addPackage("com.mycompany.web.model")
				.setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
				.addAsWebResource(new File("src/main/webapp/exchangeCurrencies.xhtml"))
				.addAsWebInfResource(new File("src/main/webapp/WEB-INF/faces-config.xml"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		return archive;
	}

    @Test
    public void asking_for_yen_in_exchange_of_dollars_should_return_a_quote() {
        endUser.is_the_exchange_currencies_page();
		endUser.requests_quote("5", "USD", "JPY");
        endUser.should_obtain_quote("375");

    }

    @Test
    public void asking_for_euros_in_exchange_of_dollars_should_return_a_quote() {
        endUser.is_the_exchange_currencies_page();
        endUser.requests_quote("130", "USD", "EUR");
        endUser.should_obtain_quote("100");
    }

    @Pending @Test
    public void asking_for_rupees_in_exchange_of_dollars_should_return_a_quote() {
    }
} 