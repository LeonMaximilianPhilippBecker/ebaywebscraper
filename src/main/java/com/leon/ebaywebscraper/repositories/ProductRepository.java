package com.leon.ebaywebscraper.repositories;

import com.leon.ebaywebscraper.dto.Product;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

  void deleteByProductname(String productName);

  Product findProductByProductname(String productName);

  @Override
  List<Product> findAll();

  Product insert(Product product);

  Product save(Product product);

}
