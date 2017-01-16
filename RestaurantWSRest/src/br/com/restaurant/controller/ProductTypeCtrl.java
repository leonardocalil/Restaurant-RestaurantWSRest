package br.com.restaurant.controller;

import java.util.List;

import br.com.restaurant.dao.ProductTypeDAO;
import br.com.restaurant.model.ProductTypeModel;

public class ProductTypeCtrl {
	public static List<ProductTypeModel> getAll() {
		return new ProductTypeDAO().getAll();
	}
	
	
	
	public static ProductTypeModel get(String id) {
		return new ProductTypeDAO().get(id);
	}
	
	public static boolean save(ProductTypeModel model) {
		ProductTypeDAO dao = new ProductTypeDAO();
		if(model.getId() != 0) {
			return dao.update(model);
		} else {
			return dao.save(model);
		}
		
	}
	public static boolean delete(String id) {
		return new ProductTypeDAO().delete(id);
	}
	
}
