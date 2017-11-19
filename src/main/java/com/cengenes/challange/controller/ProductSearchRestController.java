package com.cengenes.challange.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cengenes.challange.entity.ProductEntity;
import com.cengenes.challange.model.json.ProductAutoCompleteModel;
import com.cengenes.challange.service.ProductSearchAndSaveService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(basePath = "/api/product", produces = "application/json", value = "Product", description = "Operations with product find by certain criterias")
@RestController
@RequestMapping("/api/product")
public class ProductSearchRestController {

	@Autowired
	private ProductSearchAndSaveService productSearchAndSaveService;

	@ApiOperation(value = "find Categories by IgnoreCaseName", notes = "Fetch List Of Categories ignoring CaseSense")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Please check url"),
			@ApiResponse(code = 200, message = "List<ProductAutoCompleteModel>"),
			@ApiResponse(code = 500, message = "Error occurred while fetching Categories") })
	@RequestMapping(value = "/findDistinctByCategory", method = RequestMethod.GET)
	public List<ProductAutoCompleteModel> findDistinctByCategory(@RequestParam("category") String category) {

		List<ProductAutoCompleteModel> models = new ArrayList<>();

		productSearchAndSaveService.findDistinctByCategory(category).parallelStream().forEach(t -> {

			ProductAutoCompleteModel model = new ProductAutoCompleteModel();
			if (t != null) {
				model.setDescription(t);
			}

			models.add(model);

		});

		return models;
	}

	@ApiOperation(value = "find All products using criteria ignorecase with request params [category,brand,title] seperately or together", notes = "Fetch List Of Products ignoring CaseSense")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Please check url"),
			@ApiResponse(code = 200, message = "List<Products>"),
			@ApiResponse(code = 500, message = "Error occurred while fetching Products") })
	@RequestMapping(value = "/findAllWithIgnoreCase", method = RequestMethod.GET)
	public List<ProductEntity> findAllWithIgnoreCase(
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "title", required = false) String title) {
		return productSearchAndSaveService
				.findByCategoryContainingIgnoreCaseAndBrandContainingIgnoreCaseAndTitleContainingIgnoreCase(category,
						brand, title);

	}

	@ApiOperation(value = "find All Brands using certain name category and brand with IgnoreCase")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Please check url"),
			@ApiResponse(code = 200, message = "List<Brand>"),
			@ApiResponse(code = 500, message = "Error occurred while fetching Brands") })
	@RequestMapping(value = "/findDistinctByCategoryAndBrand", method = RequestMethod.GET)
	public List<ProductAutoCompleteModel> findByCategoryAndBrand(@RequestParam("category") String category,
			@RequestParam("brand") String brand) {

		List<ProductAutoCompleteModel> models = new ArrayList<>();

		productSearchAndSaveService.findDistinctByCategoryAndBrand(category, brand).parallelStream().forEach(t -> {
			ProductAutoCompleteModel jsonModel = new ProductAutoCompleteModel();

			if (t != null) {
				jsonModel.setDescription(t);
			}

			models.add(jsonModel);
		});

		return models;

	}

	@ApiOperation(value = "find All titles using certain name category , certain name brand and title with IgnoreCase")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Please check url"),
			@ApiResponse(code = 200, message = "List<Title>"),
			@ApiResponse(code = 500, message = "Error occurred while fetching Titles") })
	@RequestMapping(value = "/findFirst10ByCategoryAndBrandAndTitle", method = RequestMethod.GET)
	public List<ProductAutoCompleteModel> findByCategoryAndBrandAndTitle(@RequestParam("category") String category,
			@RequestParam("brand") String brand, @RequestParam("title") String title) {

		List<ProductAutoCompleteModel> models = new ArrayList<>();
		productSearchAndSaveService.findFirst10ByCategoryAndBrandAndTitleContainingIgnoreCase(category, brand, title)
				.parallelStream().forEach(t -> {
					ProductAutoCompleteModel jsonModel = new ProductAutoCompleteModel();

					jsonModel.setProductId(t.getProductId());
					jsonModel.setDescription(t.getTitle());

					models.add(jsonModel);
				});

		return models;
	}

}
