package com.leon.ebaywebscraper.dto;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@Builder
public class Product {

  @Id
  private String productId;
  private String productName;
  private String productUrl;
  private Date mostRecentListingDate;
  private List<String> requiredKeywords;
  private List<String> blackListedKeywords;
  private double maxViablePrice;
  private double maxrealisticPrice;
  private List<ProductListing> productListings;
}
