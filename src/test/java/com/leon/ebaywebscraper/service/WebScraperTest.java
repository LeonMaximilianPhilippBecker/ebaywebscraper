package com.leon.ebaywebscraper.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import com.leon.ebaywebscraper.dto.ProductListing;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WebScraperTest {

  private final Document exampleDocument = getExampleDocument();
  private final String url = "https://www.kleinanzeigen.de/s-wohnung-kaufen/altona/sortierung:preis/preis:1000:/wohnung/k0c196l9497r50";

  @Test
  public void test() {
    WebScraperImpl webScraper = new WebScraperImpl();
    Document document = webScraper.fetchWebPage(url);
    System.out.println(document);
  }

  @Test
  public void scrape_shouldReturnValidProductListingFromExampleHTMLPage() throws IOException {
    WebScraperImpl webScraper = new WebScraperImpl();
    WebScraperImpl webScraperMock = spy(webScraper);
    doReturn(exampleDocument).when(webScraperMock).fetchWebPage(anyString());
    ProductListing expectedResult = new ProductListing("", LocalDateTime.of(2023, 8, 5, 14, 40),
        235000.0, true);

    ProductListing actualResult = webScraperMock.scrape("");

    Assertions.assertEquals(expectedResult, actualResult);
  }

  @Test
  public void createProductListingFromScrapedHTML_shouldReturnValidProductListing_whenCalledWithValidUlElement() {

  }

  @Test
  public void getProductListingDate_shouldReturnExpectedDate_whenCalledWithValidDivElement() {

  }

  private Document getExampleDocument() {
    String pathName = "C:/Users/Leon/Desktop/ebaywebscraper/src/test/resources/ScrapedHtmlPageExample.html";
    File htmlFile = new File(pathName);
    try {
      return Jsoup.parse(htmlFile, "UTF-8", "http://example.com/");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}
