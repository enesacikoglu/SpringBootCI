package com.cengenes.challange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cengenes.challange.model.json.ProductChartModel;
import com.cengenes.challange.service.ProductCassandraService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/product/chart")
public class ProductTimeChartRestController {

	@Autowired
	private ProductCassandraService productCassandraService;

	@ApiOperation(value = "find date-price Map by given ProductURL", notes = "Fetch Map Of Date-Price of the Product")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Please check url"),
			@ApiResponse(code = 200, message = "Map<Date,Price>"),
			@ApiResponse(code = 500, message = "Error occurred while fetching Date-Price Map of the Product") })
	@RequestMapping("/findByProductUrl")
	public List<ProductChartModel> getAllProductsByProductId(@RequestParam("url") String url) {
		return productCassandraService.findByUrl(url);
	}

}
