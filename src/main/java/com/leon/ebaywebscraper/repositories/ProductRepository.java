package com.leon.ebaywebscraper.repositories;

import com.leon.ebaywebscraper.dto.Product;
import com.leon.ebaywebscraper.dto.ProductListing;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

  @Override
  List<Product> findAll();

  Product insert(Product product);

  Product update(Product product);

  @Query(value = "{'_id': ?0, 'productListings': { $exists: true, $ne: [] } }", sort = "{'productListings.listingDate': 1}", fields = "{'aList.$': 1}")
  ProductListing findProductListingWithMostRecentListingDateForProduct(String productId);
}
