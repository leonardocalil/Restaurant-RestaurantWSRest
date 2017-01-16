package br.com.restaurant.controller;

import java.util.List;

import br.com.restaurant.dao.RoleDAO;
import br.com.restaurant.model.RoleModel;

public class RoleCtrl {
	public static List<RoleModel> getAll() {
		return new RoleDAO().getAll();
	}
	
	
	
	public static RoleModel get(String id) {
		return new RoleDAO().get(id);
	}
	
	public static boolean save(RoleModel model) {
		RoleDAO dao = new RoleDAO();
		if(model.getId() != 0) {
			return dao.update(model);
		} else {
			return dao.save(model);
		}
		
	}
	public static boolean delete(String id) {
		return new RoleDAO().delete(id);
	}
	
}
