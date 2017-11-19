package com.cengenes.challange.service;

import java.util.List;

import com.cengenes.challange.entity.ProductCassandraEntity;
import com.cengenes.challange.model.json.ProductChartModel;
import com.cengenes.challange.model.xml.ProductListModel;

public interface ProductCassandraService {

	public List<ProductChartModel> findByUrl(String url);

	public List<ProductCassandraEntity> saveProductDatesAndPrices(ProductListModel model);

}
