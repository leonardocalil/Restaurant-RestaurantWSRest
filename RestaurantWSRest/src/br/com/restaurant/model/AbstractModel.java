package br.com.restaurant.model;

import java.io.Serializable;

@SuppressWarnings("serial")

public abstract class AbstractModel implements Serializable {
	private int id;
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	
	
	
}
