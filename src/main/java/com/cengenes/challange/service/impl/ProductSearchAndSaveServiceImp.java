package com.cengenes.challange.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cengenes.challange.entity.ProductEntity;
import com.cengenes.challange.model.xml.ProductListModel;
import com.cengenes.challange.repository.ProductRepository;
import com.cengenes.challange.service.ProductSearchAndSaveService;

/**
 * @author ENES.ACIKOGLU
 *
 */
@Service
public class ProductSearchAndSaveServiceImp implements ProductSearchAndSaveService {

	private Logger log = Logger.getLogger(ProductSearchAndSaveServiceImp.class);

	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<String> findDistinctByCategory(String category) {
		return productRepo.findDistinctByCategory(category);
	}

	@Override
	public List<String> findDistinctByCategoryAndBrand(String category, String brand) {
		return productRepo.findDistinctByCategoryAndBrand(category, brand);
	}

	@Override
	public List<ProductEntity> findFirst10ByCategoryAndBrandAndTitleContainingIgnoreCase(String category, String brand,
			String title) {
		return productRepo.findFirst10ByCategoryAndBrandAndTitleContainingIgnoreCase(category, brand, title);
	}

	@Override
	public List<ProductEntity> findByCategoryContainingIgnoreCaseAndBrandContainingIgnoreCaseAndTitleContainingIgnoreCase(
			String category, String brand, String title) {
		return productRepo.findByCategoryContainingIgnoreCaseAndBrandContainingIgnoreCaseAndTitleContainingIgnoreCase(
				category, brand, title);
	}

	@Override
	public List<ProductEntity> saveProductListModel(ProductListModel productList) {

		return productList.getRowsList().parallelStream().map(product -> {

			ProductEntity entity = new ProductEntity();

			entity.setProductId(product.getId());
			entity.setBrand(product.getBrand());
			entity.setCategory(product.getCategory());
			entity.setTitle(product.getTitle());
			entity.setUrl(product.getUrl());

			return productRepo.save(entity);

		}).collect(Collectors.toList());

	}

}
