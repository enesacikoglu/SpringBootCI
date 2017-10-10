package com.cimri.challange.service;

import java.util.List;

import com.cimri.challange.entity.ProductCassandraEntity;
import com.cimri.challange.model.json.ProductChartModel;
import com.cimri.challange.model.xml.ProductListModel;

public interface ProductCassandraService {

	public List<ProductChartModel> findByUrl(String url);

	public List<ProductCassandraEntity> saveProductDatesAndPrices(ProductListModel model);

}
