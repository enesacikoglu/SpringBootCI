package com.cengenes.challange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cengenes.challange.entity.ProductEntity;



public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

   @Query(value = "select distinct(u.category) from product_entity u where lower(u.category) like lower(concat('%', :category,'%'))",
          nativeQuery = true)
   public List<String> findDistinctByCategory(@Param("category") String category);

   @Query(value = "select distinct(u.brand) from product_entity u where u.category=:category and lower(u.brand) like lower(concat('%', :brand,'%'))",
          nativeQuery = true)
   public List<String> findDistinctByCategoryAndBrand(@Param("category") String category, @Param("brand") String brand);

   public List<ProductEntity> findFirst10ByCategoryAndBrandAndTitleContainingIgnoreCase(String category, String brand,
         String title);

   public List<ProductEntity> findByCategoryContainingIgnoreCaseAndBrandContainingIgnoreCaseAndTitleContainingIgnoreCase(
         String category, String brand, String title);

}
