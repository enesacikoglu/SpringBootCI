package com.cimri.challange.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cimri.challange.bootstrap.ProductLoaderService;
import com.cimri.challange.conf.ProductUnmarshaller;
import com.cimri.challange.entity.ProductEntity;
import com.cimri.challange.model.xml.ProductListModel;
import com.cimri.challange.service.ProductSearchAndSaveService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProductSearchAndSaveServiceIntegrationTest {

	@MockBean
	private ProductLoaderService startupConfiguration;

	@Autowired
	private ProductSearchAndSaveService productSearchAndSaveService;

	@Autowired
	private ProductUnmarshaller productMarshaller;

	@Value("${TEST_SITE_URL}")
	private String testSiteUrl;

	@Test
	public void testAddProductHappyPath() {

		// Get test product
		ProductListModel testProduct = productMarshaller.unmarshlall(testSiteUrl);

		// Adding the testProduct to DB.
		productSearchAndSaveService.saveProductListModel(testProduct);

		// Verify the addition
		assertNotNull(testProduct);
		assertNotNull(testProduct.getRowsList());
		assertEquals("Zyxel", testProduct.getRowsList().get(0).getBrand());

	}

	@Test
	public void testFindDistinctByCategory() {

		// Get test product
		ProductListModel testProduct = productMarshaller.unmarshlall(testSiteUrl);

		// Adding the testProduct to DB.
		productSearchAndSaveService.saveProductListModel(testProduct);

		// FindDistinctByCategory for testProduct
		List<String> findDistinctByCategory = productSearchAndSaveService.findDistinctByCategory("mode");

		// Verify the findedCategory
		assertNotNull(findDistinctByCategory);
		assertEquals(2, findDistinctByCategory.size());
		assertEquals("Modem", findDistinctByCategory.get(0));
		assertEquals("Modemler", findDistinctByCategory.get(1));

	}

	@Test
	public void testFindDistinctByCategoryAndBrand() {

		// Get test product
		ProductListModel testProduct = productMarshaller.unmarshlall(testSiteUrl);

		// Adding the testProduct to DB.
		productSearchAndSaveService.saveProductListModel(testProduct);

		// FindDistinctByCategoryAndBrand for testProduct
		List<String> findDistinctByCategoryAndBrand = productSearchAndSaveService
				.findDistinctByCategoryAndBrand("Ekran Kartları", "as");

		// Verify the findedDistinctByCategoryAndBrand
		assertNotNull(findDistinctByCategoryAndBrand);
		assertEquals(1, findDistinctByCategoryAndBrand.size());
		assertThat(findDistinctByCategoryAndBrand.get(0), CoreMatchers.containsString("ASUS"));

	}

	@Test
	public void testFindFirst10ByCategoryAndBrandAndTitleContainingIgnoreCase() {

		// Get test product
		ProductListModel testProduct = productMarshaller.unmarshlall(testSiteUrl);

		// Adding the testProduct to DB.
		productSearchAndSaveService.saveProductListModel(testProduct);

		// firstTenProduct for
		// testProduct
		List<ProductEntity> firstTenProduct = productSearchAndSaveService
				.findFirst10ByCategoryAndBrandAndTitleContainingIgnoreCase("Sabit Diskler", "TOSHIBA", "disk");

		// Verify the
		// firstTenProduct
		assertNotNull(firstTenProduct);
		assertEquals(10, firstTenProduct.size());

		assertThat(firstTenProduct.get(3).getTitle(), CoreMatchers.containsString("Harddisk"));
		assertThat(firstTenProduct.get(9).getTitle(), CoreMatchers.containsString("disk"));

		assertThat(firstTenProduct.get(5).getBrand(), CoreMatchers.containsString("TOSHIBA"));
		assertThat(firstTenProduct.get(7).getCategory(), CoreMatchers.containsString("Sabit Diskler"));
	}

}
