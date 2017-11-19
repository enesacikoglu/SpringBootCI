package com.cengenes.challange.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cengenes.challange.entity.ProductEntity;
import com.cengenes.challange.repository.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testFindDistinctByCategory() {

		// setup data scenario
		ProductEntity aNewEntity = new ProductEntity();
		aNewEntity.setCategory("Telefon");

		// save test data
		entityManager.persist(aNewEntity);

		// Find an inserted record
		List<String> foundedCategories = productRepository.findDistinctByCategory("tel");

		assertEquals(1, foundedCategories.size());
		assertThat(foundedCategories.get(0), is(equalTo("Telefon")));
	}

}
