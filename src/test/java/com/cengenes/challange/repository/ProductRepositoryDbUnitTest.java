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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.cengenes.challange.repository.ProductRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:test-datasets.xml")
public class ProductRepositoryDbUnitTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testFindDistinctByCategory() {

		// Find an inserted record
		List<String> foundedCategories = productRepository.findDistinctByCategory("tel");

		assertEquals(2, foundedCategories.size());
		assertThat(foundedCategories.get(0), is(equalTo("Telefon")));
	}

	// @Test
	// public void testFindSpecificContactByIdBypassReposClass() {
	//
	// // Find an inserted record
	// CustomerContact foundContact = entityManager.find(CustomerContact.class,
	// new Long("2"));
	//
	// assertThat(foundContact.getEmail(), is(equalTo("elaine@myemail.com")));
	// }
}
