package com.cengenes.challange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cengenes.challange.conf.ProductUnmarshaller;
import com.cengenes.challange.model.xml.ProductListModel;
import com.cengenes.challange.service.ProductParseService;

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
