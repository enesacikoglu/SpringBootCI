package com.cimri.challange.conf;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;

import com.cimri.challange.model.xml.ProductListModel;

@Component
public class ProductUnmarshaller {

	private Logger log = Logger.getLogger(ProductUnmarshaller.class);

	public ProductListModel unmarshlall(String url) {
		ProductListModel model = new ProductListModel();
		try {
			JAXBContext unmarshalJaxbContext = JAXBContext.newInstance(model.getClass());
			Unmarshaller unmarshaller = unmarshalJaxbContext.createUnmarshaller();
			model = (ProductListModel) unmarshaller.unmarshal(new InputSource(new URL(url).openStream()));
		} catch (Exception e) {
			log.error("Error on unmarshlall XML " + e.getMessage(), e);
		}

		return model;
	}

}
