package com.cengenes.challange.service;

import java.util.List;

import com.cengenes.challange.entity.ProductEntity;
import com.cengenes.challange.model.xml.ProductListModel;

public interface ProductSearchAndSaveService {

	public List<String> findDistinctByCategory(String category);

	public List<String> findDistinctByCategoryAndBrand(String category, String brand);

	public List<ProductEntity> findFirst10ByCategoryAndBrandAndTitleContainingIgnoreCase(String category, String brand,
			String title);

	public List<ProductEntity> findByCategoryContainingIgnoreCaseAndBrandContainingIgnoreCaseAndTitleContainingIgnoreCase(
			String category, String brand, String title);

	public List<ProductEntity> saveProductListModel(ProductListModel productList);

}
