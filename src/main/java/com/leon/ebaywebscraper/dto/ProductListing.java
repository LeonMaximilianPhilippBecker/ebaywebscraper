package com.leon.ebaywebscraper.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductListing {

  @NotNull
  private String listingurl;
  @NotNull
  private LocalDateTime listingdate;
  @NotNull
  private double price;
  @NotNull
  @Min(value = 0)
  private boolean isbuyable;

  @Override
  public String toString() {
    return "ProductListing{" +
        "listingurl='" + listingurl + '\'' +
        ", listingdate=" + listingdate +
        ", price=" + price +
        ", isbuyable=" + isbuyable +
        '}';
  }
}
