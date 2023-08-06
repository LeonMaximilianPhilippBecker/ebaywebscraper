package com.leon.ebaywebscraper.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Product {

  @Id
  public String productId;
  public String productName;
  public String productUrl;
  public LocalDateTime mostRecentListingDate;
  private List<String> requiredKeywords;
  private List<String> blackListedKeywords;
  private double maxViablePrice;
  private double maxrealisticPrice;
  private List<ProductListing> productListings;
}
