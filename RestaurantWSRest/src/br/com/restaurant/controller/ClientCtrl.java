package br.com.restaurant.controller;

import java.util.List;

import br.com.restaurant.dao.ClientDAO;
import br.com.restaurant.model.ClientModel;

public class ClientCtrl {
	
	public static List<ClientModel> getAll() {
		return new ClientDAO().getAll();
	}
	public static ClientModel get(String id) {
		return new ClientDAO().get(id);
	}
	public static ClientModel validateUser(String login,String password) {
		ClientDAO dao = new ClientDAO();
		List<ClientModel> result = dao.getAll("login = '"+login+"' AND password = '"+password+"' ");
		if(result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
	public static ClientModel getByLogin(String login) {
		return new ClientDAO().getByLogin(login);
	}
	public static boolean delete(String id) {
		return new ClientDAO().delete(id);
	}
	public static boolean save(ClientModel model) {
		ClientDAO dao = new ClientDAO();
		if(model.getId() == 0) {
			return dao.save(model);
		} else {
			return dao.update(model);
		}
		
	}
	
	
}
