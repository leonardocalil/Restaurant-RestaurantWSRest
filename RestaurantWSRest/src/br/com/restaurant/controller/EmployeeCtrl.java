package br.com.restaurant.controller;

import java.util.List;

import org.json.JSONObject;

import br.com.restaurant.dao.EmployeeDAO;
import br.com.restaurant.model.EmployeeModel;

public class EmployeeCtrl {
	
	public static List<EmployeeModel> getAll() {
		return new EmployeeDAO().getAll();
	}
	public static EmployeeModel get(String id) {
		return new EmployeeDAO().get(id);
	}
	
	public static EmployeeModel validateUser(String user_password) {
		JSONObject obj = new JSONObject(user_password);
		EmployeeDAO dao = new EmployeeDAO();
		List<EmployeeModel> result = dao.getAll("lower(e.login) = '"+obj.getString("user").toLowerCase()+"' AND e.password = '"+obj.getString("password")+"' ");
		if(result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
	public static EmployeeModel getByLogin(String login) {
		return new EmployeeDAO().getByLogin(login);
	}
	public static boolean delete(String id) {
		return new EmployeeDAO().delete(id);
	}
	public static boolean save(EmployeeModel model) {
		EmployeeDAO dao = new EmployeeDAO();
		if(model.getId() == 0) {
			return dao.save(model);
		} else {
			return dao.update(model);
		}
		
	}
	
	
}
