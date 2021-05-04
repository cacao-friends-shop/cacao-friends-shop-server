package com.cacaofriendsshop.scraping.scrapper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Scrapper {

    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "C:/Users/ebseu/Desktop/chromedriver.exe";

    private WebDriver driver;

    public Scrapper() {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();
    }

    public void get(String url) {
        this.driver.get(url);
    }

    public String getPageSource() {
        return this.driver.getPageSource();
    }

    public JavascriptExecutor createJavascriptExecutor() {
        return (JavascriptExecutor) this.driver;
    }

}
