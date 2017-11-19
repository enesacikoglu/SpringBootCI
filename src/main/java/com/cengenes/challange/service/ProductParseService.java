package com.cengenes.challange.service;

import com.cengenes.challange.model.xml.ProductListModel;

public interface ProductParseService {

	ProductListModel parseProductFromUrl(String url);

}
