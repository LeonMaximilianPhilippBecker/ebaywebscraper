package com.leon.ebaywebscraper.dto;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(access = AccessLevel.PUBLIC)
public class Product {

  @Id
  private String productid;
  @NotNull
  private String productname;
  @NotNull
  private String url;
  @NotNull
  private LocalDateTime mostrecentlistingdate;
  @NotNull
  private List<String> requiredkeywords;
  @NotNull
  private List<String> blacklistedkeywords;
  @NotNull
  @Min(value = 0)
  private double maxviableprice;
  @NotNull
  @Min(value = 0)
  private double maxrealisticprice;
  @NotNull
  private List<ProductListing> productlistings;
}
