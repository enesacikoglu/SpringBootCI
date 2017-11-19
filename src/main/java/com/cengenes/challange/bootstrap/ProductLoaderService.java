package com.cengenes.challange.bootstrap;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.cengenes.challange.conf.ApplicationProperties;
import com.cengenes.challange.model.xml.ProductListModel;
import com.cengenes.challange.service.ProductCassandraService;
import com.cengenes.challange.service.ProductParseService;
import com.cengenes.challange.service.ProductSearchAndSaveService;

@Component
public class ProductLoaderService {

	private Logger log = Logger.getLogger(ProductLoaderService.class);

	@Autowired
	private ApplicationProperties properties;

	@Autowired
	private ProductParseService productParseService;

	@Autowired
	private ProductSearchAndSaveService productSearchAndSaveService;

	@Autowired
	private ProductCassandraService productCassandraService;

	@Async
	public void initAsyncProcess() {

		try {
			properties.getSiteUrls().stream().forEach(url -> {

				ProductListModel parsedProductFromUrl = productParseService.parseProductFromUrl(url);

				// Save product details to RDMS.
				saveProduct(parsedProductFromUrl);

				// Save product dates and prices to Cassandra.
				saveProductPricesAndDates(parsedProductFromUrl);
			});

		} catch (Exception e) {
			log.error("Error on parsing and saving urls " + e.getMessage(), e);
		}

	}

	@Transactional
	private void saveProduct(ProductListModel model) {
		try {

			productSearchAndSaveService.saveProductListModel(model);

		} catch (Exception e) {
			log.error("Erron on saved product to MYSQL with model:" + model, e);
		}

		log.info("Product has been saved succesfully to MYSQL :" + model);

	}

	private void saveProductPricesAndDates(ProductListModel model) {

		try {

			productCassandraService.saveProductDatesAndPrices(model);

		} catch (Exception e) {
			log.error("Erron on saved product to Cassandra with model:" + model, e);
		}

		log.info("Product has been saved succesfully to Cassandra :" + model);
	}

}
