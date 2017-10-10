package com.cimri.challange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cimri.challange.conf.ProductUnmarshaller;
import com.cimri.challange.model.xml.ProductListModel;
import com.cimri.challange.service.ProductParseService;

/**
 * @author ENES.ACIKOGLU
 *
 */
@Service
public class ProductParseServiceImp implements ProductParseService {

	@Autowired
	private ProductUnmarshaller productUnmarshaller;

	@Override
	public ProductListModel parseProductFromUrl(String url) {
		return productUnmarshaller.unmarshlall(url);
	}

}
