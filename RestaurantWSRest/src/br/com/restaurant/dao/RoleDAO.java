package br.com.restaurant.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.restaurant.model.RoleModel;
import br.com.restaurant.util.DBConnection;

public class RoleDAO extends AbstractDAO<RoleModel> {
	
	public List<RoleModel> getAll() {
		return getAll("deleted = 0 ");
	}
	
	public List<RoleModel> getAll(String filter) {
		List<RoleModel> results = new ArrayList<RoleModel>();
		DBConnection db = new DBConnection();
		ResultSet rs = null;
		
		try {
			String sql = "SELECT id, name,description  "
					+ "FROM restaurant.role ";
			if(filter != null && filter.length() > 0) {
				sql += "WHERE "+filter; 
			}
			rs = db.ExecuteQuery(sql);
			while(rs.next()) {
				RoleModel model = new RoleModel();
				model.setId(rs.getInt("id"));
				model.setName(rs.getString("name"));
				model.setDescription(rs.getString("description"));
				
				results.add(model);
				
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.finalize();
		}
		return results;
	}
	

	@Override
	public RoleModel get(String id) {
		
		List<RoleModel> result =  getAll("id = "+id);
		if(result.size() > 0) {
			return result.get(0);
		}
		return null;

	}
	
	public int getNewId() {
		// TODO Auto-generated method stub
		String sql = "select nextval('role_seq')";
		
		DBConnection db = new DBConnection();
		ResultSet rs = null;
		
		try {
			rs = db.ExecuteQuery(sql);
			if(rs.next()) {
				return rs.getInt(1);								
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.finalize();
		}
		return 0;
	}
	public boolean save(RoleModel model) {
		
		String sql = "insert into restaurant.role (id,name,description) "
				+ "values(nextval('role_seq'),'"+model.getName()+"','"+model.getDescription()+"')";
		
		DBConnection db = new DBConnection();
		
		boolean result = true;
		
		try {
			result = db.ExecuteSql(sql);				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		} finally {
			db.finalize();
		}
		return result;
	}
	public boolean update(RoleModel model) {
		String sql = "update restaurant.role set name='"+model.getName()+"',description='"+model.getDescription()+"' "
				+ "where id = "+model.getId();
		
		DBConnection db = new DBConnection();
		
		boolean result = true;
		
		try {
			result = db.ExecuteSql(sql);				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		} finally {
			db.finalize();
		}
		return result;
	}
	public boolean delete(String id) {
		String sql = "update restaurant.role set deleted=1 "
				+ "where id = "+id;
		
		DBConnection db = new DBConnection();
		
		boolean result = true;
		
		try {
			result = db.ExecuteSql(sql);				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		} finally {
			db.finalize();
		}
		return result;
	}
}
