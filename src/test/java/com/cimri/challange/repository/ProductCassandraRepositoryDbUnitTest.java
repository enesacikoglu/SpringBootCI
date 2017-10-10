package com.cimri.challange.repository;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.cimri.challange.bootstrap.ProductLoaderService;
import com.cimri.challange.entity.ProductCassandraEntity;
import com.datastax.driver.core.Session;

@RunWith(SpringRunner.class)
@SpringBootTest
@CassandraDataSet(keyspace = "cimriKeyspace", value = "cassandra.cql")
@EmbeddedCassandra(timeout = 60000)
public class ProductCassandraRepositoryDbUnitTest {

	@MockBean
	private ProductLoaderService startupConfiguration;

	@Autowired
	Session session;
	@Autowired
	CassandraOperations template;

	@Autowired
	private ProductCassandraRepository productCassandraRepository;

	@Test
	public void testFindByUrl() {

		ProductCassandraEntity cassandraEntity = new ProductCassandraEntity();

		cassandraEntity.setId("1");
		cassandraEntity.setUrl("site1");
		Map<String, String> dateWitPriceMap = new HashMap<>();
		dateWitPriceMap.put("1433061049817", "399.01");
		cassandraEntity.setPriceOnDateMap(dateWitPriceMap);

		productCassandraRepository.save(cassandraEntity);

		List<ProductCassandraEntity> findByUrl = productCassandraRepository.findByUrl("site1");

		assertEquals(findByUrl.get(0).getId(), cassandraEntity.getId());

	}

}
