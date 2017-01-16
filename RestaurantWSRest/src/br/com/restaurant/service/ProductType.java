package br.com.restaurant.service;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.restaurant.controller.ProductTypeCtrl;
import br.com.restaurant.model.ProductTypeModel;

@Path("/producttype")
public class ProductType {
	
		
	@GET
	@Path("alive")
	@Produces(MediaType.TEXT_PLAIN)
	public String alive() {
		return "Sim, estou vivo!!";
	}
	
	@GET
	@Path("get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductTypeModel get(@PathParam("id") String id) {
		
		return ProductTypeCtrl.get(id);
	}
	
	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductTypeModel> getAll() {
		
		return ProductTypeCtrl.getAll();
	}
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean save(ProductTypeModel model) {
		return ProductTypeCtrl.save(model);
	}

	@GET
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") String id) {
		
		return ProductTypeCtrl.delete(id);
	}
	
	
}
