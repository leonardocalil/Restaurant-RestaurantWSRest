package br.com.restaurant.service;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.restaurant.controller.EmployeeCtrl;
import br.com.restaurant.model.EmployeeModel;

@Path("/employee")
public class Employee {
	
		
	@GET
	@Path("alive")
	@Produces(MediaType.TEXT_PLAIN)
	public String alive() {
		return "Sim, estou vivo!!";
	}
	
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean save(EmployeeModel model) {
		return EmployeeCtrl.save(model);
	}
	
	
	@GET
	@Path("get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeModel get(@PathParam("id") String id) {
		
		return EmployeeCtrl.get(id);
	}
	
	@POST
	@Path("validateUser")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeModel validateUser(String user_password) {
		
		
		return EmployeeCtrl.validateUser(user_password);
	}
	
	@GET
	@Path("getByLogin/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeModel getByLogin(@PathParam("login") String login) {
		
		return EmployeeCtrl.getByLogin(login);
	}
	@GET
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") String id) {
		
		return EmployeeCtrl.delete(id);
	}

	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EmployeeModel> getAll() {
		
		return EmployeeCtrl.getAll();
	}
	
	
	
	
	
}
