package com.leon.ebaywebscraper.service;

import com.leon.ebaywebscraper.dto.ProductListing;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class WebScraperImpl implements WebScraper {

  private static final String PRODUCTLISTINGS_UL_ID = "srchrslt-adtable";
  private static final String PRODUCTLISTING_PRICE_P_CLASS = "aditem-main--middle--price-shipping--price";
  private static final String PRODUCTLISTING_LISTINGDATE_DIV_CLASS = "aditem-main--top--right";

  @Override
  public ProductListing scrape(String url) {
    final Document webPage = fetchWebPage(url);
    if (webPage == null) {
      return null;
    }
    final Elements productListings = webPage.select("ul#" + PRODUCTLISTINGS_UL_ID);
    if (productListings.isEmpty()) {
      return null;
    }
    for (Element innerHTML : productListings) {
      for (Node productListing : innerHTML.childNodes()) {
        if (productListing instanceof Element) {
          if (!((Element) productListing).className().contains("topad")) {
            return createProductListingFromScrapedHTML((Element) productListing);
          }
        }
      }
    }
    return null;
  }

  final Document fetchWebPage(String url) {
    try {
      Document webPage = Jsoup.connect(url).get();
      return webPage;
    } catch (IOException e) {
      return null;
    }
  }

  private final ProductListing createProductListingFromScrapedHTML(Element element) {
    final Double price = getProductListingPrice(element);
    final LocalDateTime date = getProductListingDate(element);

    final ProductListing productListing = new ProductListing("", date, price, true);
    return productListing;
  }

  private final Double getProductListingPrice(Element element) {
    String priceString = element.selectFirst("p." + PRODUCTLISTING_PRICE_P_CLASS).text();
    String cleanedPriceString = priceString.replaceAll("[^0-9.,]", "").replace(',', '.');
    try {
      NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
      Number number = numberFormat.parse(cleanedPriceString);
      return number.doubleValue();
    } catch (ParseException e) {
      throw new RuntimeException();
    }
  }

  private final LocalDateTime getProductListingDate(Element element) {
    final Element someElement = element.selectFirst("div." + PRODUCTLISTING_LISTINGDATE_DIV_CLASS);
    final Element iElement = element.selectFirst("div." + PRODUCTLISTING_LISTINGDATE_DIV_CLASS)
        .selectFirst("i");
    final String productListingDate = ((TextNode) iElement.nextSibling()).text();
    if (productListingDate.contains("Heute")) {
      final LocalDate currentDate = LocalDate.now();
      final int charIndex = productListingDate.indexOf(',');
      final String productListingDateSuffix = productListingDate.substring(charIndex + 2);
      final String productListingDateInfix = productListingDateSuffix.substring(0,
          productListingDateSuffix.length() - 1);
      final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
      final LocalTime proudctListingTime = LocalTime.parse(productListingDateInfix, formatter);
      return LocalDateTime.of(currentDate, proudctListingTime);
    } else {
      final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
      return LocalDateTime.parse(productListingDate, formatter);
    }
  }

}
