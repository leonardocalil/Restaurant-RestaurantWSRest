package br.com.restaurant.service;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.restaurant.controller.ClientCtrl;
import br.com.restaurant.model.ClientModel;

@Path("/client")
public class Client {
	
		
	@GET
	@Path("alive")
	@Produces(MediaType.TEXT_PLAIN)
	public String alive() {
		return "Sim, estou vivo!!";
	}
	
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean save(ClientModel model) {
		return ClientCtrl.save(model);
	}
	
	
	@GET
	@Path("get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ClientModel get(@PathParam("id") String id) {
		
		return ClientCtrl.get(id);
	}
	@GET
	@Path("validateUser/{login}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public ClientModel validateUser(@PathParam("login") String login,
									  @PathParam("password") String password) {
		
		return ClientCtrl.validateUser(login,password);
	}
	@GET
	@Path("getByLogin/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public ClientModel getByLogin(@PathParam("login") String login) {
		
		return ClientCtrl.getByLogin(login);
	}
	@GET
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") String id) {
		
		return ClientCtrl.delete(id);
	}

	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientModel> getAll() {
		
		return ClientCtrl.getAll();
	}
	
	
	
	
	
}
