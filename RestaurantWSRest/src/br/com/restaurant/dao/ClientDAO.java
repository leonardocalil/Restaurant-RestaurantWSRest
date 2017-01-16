package br.com.restaurant.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.restaurant.model.ClientModel;
import br.com.restaurant.util.DBConnection;

public class ClientDAO extends AbstractDAO<ClientModel> {
	
	public String getNewId() {
		String sql = "select nextval('client_seq')";
		
		DBConnection db = new DBConnection();
		ResultSet rs = null;
		
		try {
			rs = db.ExecuteQuery(sql);
			if(rs.next()) {
				return rs.getString(1);								
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.finalize();
		}
		return "0";
	}
	public boolean update(ClientModel model) {
		boolean result = false;
		String sql = "UPDATE restaurant.client SET name = '"+model.getName()+"',"
												+ "phone = '"+model.getPhone()+"',"
												+ "email = '"+model.getEmail()+","
												+ "document='"+model.getDocument()+"',"
												+ "address_name='"+model.getAddress_name()+"',"
												+ "address_number='"+model.getAddress_number()+"',"
												+ "address_complement='"+model.getAddress_complement()+"',"
												+ "zip_code='"+model.getZip_code()+"',"
												+ "creditcard_number='"+model.getCreditcard_number()+"',"
												+ "creditcard_name='"+model.getCreditcard_name()+"',"
												+ "creditcard_flag='"+model.getCreditcard_flag()+"',"
												+ "creditcard_security_code='"+model.getCreditcard_security_code()+"' "
												+ "WHERE id = "+model.getId();
		
		DBConnection db = new DBConnection();
		
		try {
			result = db.ExecuteSql(sql);				
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.finalize();
		}
		return result;
	}
	public boolean save(ClientModel model) {
		boolean result = false;
		String sql = "";
		sql = "INSERT INTO restaurant.client (id,name,phone,email,document,address_name,address_number,address_complement,"
				+ "zip_code,creditcard_number,creditcard_name,creditcard_flag,creditcard_security_code) "
				+ "VALUES (nextval('client_seq'),'"+model.getName()+"','"+model.getPhone()+"','"+model.getEmail()+"','"
				+  model.getDocument()+"','"+model.getAddress_name()+"','"+model.getAddress_number()+"','"+model.getAddress_complement()+"','"
				+  model.getZip_code()+"','"+model.getCreditcard_number()+"','"+model.getCreditcard_name()+"','"+model.getCreditcard_flag()+"','"
				+  model.getCreditcard_security_code()+"')";
		
		DBConnection db = new DBConnection();
		
		try {
			result = db.ExecuteSql(sql);
				
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.finalize();
		}
		return result;
	}
	public boolean delete(String id) {
		boolean result = false;
		String sql = "UPDATE restaurant.client SET deleted = 1 WHERE id = "+id;
		
		DBConnection db = new DBConnection();
		
		try {
			result = db.ExecuteSql(sql);
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.finalize();
		}
		return result;
	}

	public List<ClientModel> getAll(String filter) {
		List<ClientModel> result = new ArrayList<ClientModel>();
		String sql = "SELECT id,name,phone,email,document,address_name,address_number,address_complement,"
				+ "zip_code,creditcard_number,creditcard_name,creditcard_flag,creditcard_security_code "
				+ "FROM restaurant.client c ";
		if(filter != null && filter.length() > 0) {
			sql+= "WHERE "+filter;
		}
		DBConnection db = new DBConnection();
		ResultSet rs = null;
		try {
			
			rs = db.ExecuteQuery(sql);
			while(rs.next()) {
				ClientModel model = new ClientModel();
				model.setId(rs.getInt("id"));
				model.setName(rs.getString("name"));
				model.setPhone(rs.getString("phone"));
				model.setEmail(rs.getString("email"));
				model.setDocument(rs.getString("document"));
				model.setAddress_name(rs.getString("address_name"));
				model.setAddress_number(rs.getInt("address_number"));
				model.setAddress_complement(rs.getString("address_complement"));
				model.setZip_code(rs.getString("zip_code"));
				model.setCreditcard_number(rs.getString("creditcard_number"));
				model.setCreditcard_name(rs.getString("creditcard_name"));
				model.setCreditcard_flag(rs.getString("creditcard_flag"));
				model.setCreditcard_security_code(rs.getString("creditcard_security_code"));
				result.add(model);				
			}
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.finalize();
		}
		return result;
	}
	
	@Override
	public List<ClientModel> getAll() {
		// TODO Auto-generated method stub
		
		return getAll("c.deleted = 0 ");
		
		
	}

	@Override
	public ClientModel get(String id) {
		// TODO Auto-generated method stub
		List<ClientModel> result = getAll("c.id = "+id);
		if(result.size() > 0) {
			return result.get(0);
		}
		return null;
		
	
	}
	public ClientModel getByLogin(String login) {
		// TODO Auto-generated method stub
		
		List<ClientModel> result = getAll("c.login = '"+login+"' ");
		if(result.size() > 0) {
			return result.get(0);
		}
		return null;
			
	}
	
}
