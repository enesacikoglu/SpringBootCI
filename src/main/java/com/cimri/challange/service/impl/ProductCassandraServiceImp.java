package com.cimri.challange.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cimri.challange.entity.ProductCassandraEntity;
import com.cimri.challange.model.json.ProductChartModel;
import com.cimri.challange.model.xml.ProductListModel;
import com.cimri.challange.repository.ProductCassandraRepository;
import com.cimri.challange.service.ProductCassandraService;
import com.datastax.driver.core.utils.UUIDs;

@Service
public class ProductCassandraServiceImp implements ProductCassandraService {

	private Logger log = Logger.getLogger(ProductCassandraServiceImp.class);

	@Autowired
	private ProductCassandraRepository productCassandraRepo;

	@Override
	public List<ProductChartModel> findByUrl(String url) {
		return productCassandraRepo.findByUrl(url).parallelStream()
				.filter(productEntity -> productEntity.getUrl().equals(url)).findFirst().get().getPriceOnDateMap()
				.entrySet().parallelStream().map(t -> {

					ProductChartModel chartModel = new ProductChartModel();

					chartModel.setDate(t.getKey());
					chartModel.setPrice(t.getValue());

					return chartModel;

				}).collect(Collectors.toList());

	}

	@Override
	public List<ProductCassandraEntity> saveProductDatesAndPrices(ProductListModel model) {

		return model.getRowsList().stream().map(product -> {

			ProductCassandraEntity cassandraEntity = new ProductCassandraEntity();
			Map<String, String> priceOnDateMap = new HashMap<>();

			String[] dates = product.getDates().get(0).split(",");

			String[] prices = product.getPrices().get(0).split(",");

			for (int i = 0; i < prices.length; i++) {
				priceOnDateMap.put(dates[i], prices[i]);
			}

			cassandraEntity.setId(UUIDs.timeBased().toString());
			cassandraEntity.setUrl(product.getUrl());
			cassandraEntity.setPriceOnDateMap(priceOnDateMap);

			log.info("Cassandra Product data :" + cassandraEntity);

			return productCassandraRepo.save(cassandraEntity);

		}).collect(Collectors.toList());

	}
}
