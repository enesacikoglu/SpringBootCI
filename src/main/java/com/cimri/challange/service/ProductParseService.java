package com.cimri.challange.service;

import com.cimri.challange.model.xml.ProductListModel;

public interface ProductParseService {

	ProductListModel parseProductFromUrl(String url);

}
