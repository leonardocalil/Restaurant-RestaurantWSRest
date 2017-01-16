package br.com.restaurant.dao;

import java.util.List;

import br.com.restaurant.model.AbstractModel;

public abstract class AbstractDAO <T extends AbstractModel>{

	public abstract List<T> getAll(); 
	public abstract T get(String id);
	public abstract boolean update(T model);
	public abstract boolean delete(String id);
}
