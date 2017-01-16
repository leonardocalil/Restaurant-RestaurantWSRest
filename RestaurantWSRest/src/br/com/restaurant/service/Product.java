package br.com.restaurant.service;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.restaurant.controller.ProductCtrl;
import br.com.restaurant.model.ProductModel;

@Path("/product")
public class Product {
	
		
	@GET
	@Path("alive")
	@Produces(MediaType.TEXT_PLAIN)
	public String alive() {
		return "Sim, estou vivo!!";
	}
	
	@GET
	@Path("get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductModel get(@PathParam("id") String id) {
		
		return ProductCtrl.get(id);
	}
	
	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductModel> getAll() {
		
		return ProductCtrl.getAll();
	}
	@GET
	@Path("getAllJson")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllJson() {
		
		return ProductCtrl.getAllJson();
	}
	/*@GET
	@Path("getAll/{productType}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductModel> getAll(@PathParam("productType") ProductTypeModel productType) {
		
		return ProductCtrl.getAll(productType);
	}*/
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean save(String json) {
		return ProductCtrl.save(json);
	}

	@GET
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") String id) {
		
		return ProductCtrl.delete(id);
	}
	
	
}
