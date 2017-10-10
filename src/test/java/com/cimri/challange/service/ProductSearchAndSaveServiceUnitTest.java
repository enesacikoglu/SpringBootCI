package com.cimri.challange.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.cimri.challange.entity.ProductEntity;
import com.cimri.challange.model.xml.ProductListModel;
import com.cimri.challange.model.xml.ProductListModelType;
import com.cimri.challange.repository.ProductRepository;
import com.cimri.challange.service.impl.ProductSearchAndSaveServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProductSearchAndSaveServiceUnitTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductSearchAndSaveServiceImp productSearchAndSaveService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveProductListModel() {

		// Create a mock xml model product
		ProductListModel mockXmlProduct = new ProductListModel();

		List<ProductListModelType> mockListProduct = new ArrayList<>();

		ProductListModelType mockModelType = new ProductListModelType();

		mockModelType.setBrand("SAMSUNG");
		mockModelType.setCategory("Cep Telefonu");
		mockModelType.setId("123481");
		mockModelType.setTitle("SAMSUNG S8");
		mockModelType.setUrl("https://site1.example.com/?=123481");

		mockListProduct.add(mockModelType);

		mockXmlProduct.setRowsList(mockListProduct);

		// Create a mock entity product
		ProductEntity mockEntity = new ProductEntity();
		mockEntity.setBrand("SAMSUNG");
		mockEntity.setCategory("Cep Telefonu");
		mockEntity.setProductId("123481");
		mockEntity.setTitle("SAMSUNG S8");
		mockEntity.setUrl("https://site1.example.com/?=123481");

		when(productRepository.save(any(ProductEntity.class))).thenReturn(mockEntity);

		// Save the product
		List<ProductEntity> savedProductListModel = productSearchAndSaveService.saveProductListModel(mockXmlProduct);

		// Verify the save
		assertEquals("SAMSUNG", savedProductListModel.get(0).getBrand());
		assertEquals("Cep Telefonu", savedProductListModel.get(0).getCategory());
		assertEquals("123481", savedProductListModel.get(0).getProductId());
		assertEquals("https://site1.example.com/?=123481", savedProductListModel.get(0).getUrl());
		assertEquals("SAMSUNG S8", savedProductListModel.get(0).getTitle());
	}

}
