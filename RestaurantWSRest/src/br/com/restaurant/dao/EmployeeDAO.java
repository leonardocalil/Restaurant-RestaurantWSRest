package br.com.restaurant.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.restaurant.model.EmployeeModel;
import br.com.restaurant.model.RoleModel;
import br.com.restaurant.util.DBConnection;

public class EmployeeDAO extends AbstractDAO<EmployeeModel> {
	
	public String getNewId() {
		String sql = "select nextval('employee_seq')";
		
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
	public boolean update(EmployeeModel model) {
		boolean result = false;
		String sql = "UPDATE restaurant.employee SET name = '"+model.getName()+"',"
												+ "phone = '"+model.getPhone()+"',"
												+ "email = '"+model.getEmail()+","
												+ "document='"+model.getDocument()+"',"
												+ "address_name='"+model.getAddress_name()+"',"
												+ "address_number='"+model.getAddress_number()+"',"
												+ "address_complement='"+model.getAddress_complement()+"',"
												+ "zip_code='"+model.getZip_code()+"' "												
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
	public boolean save(EmployeeModel model) {
		boolean result = false;
		String sql = "";
		sql = "INSERT INTO restaurant.employee (id,name,phone,email,document,address_name,address_number,address_complement,"
				+ "zip_code) "
				+ "VALUES (nextval('client_seq'),'"+model.getName()+"','"+model.getPhone()+"','"+model.getEmail()+"','"
				+  model.getDocument()+"','"+model.getAddress_name()+"','"+model.getAddress_number()+"','"+model.getAddress_complement()+"','"
				+  model.getZip_code()+"')";
		
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
		String sql = "UPDATE restaurant.employee SET deleted = 1 WHERE id = "+id;
		
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
	
	public List<EmployeeModel> getAll(String filter) {
		// TODO Auto-generated method stub
				List<EmployeeModel> result = new ArrayList<EmployeeModel>();
				String sql = "SELECT e.id,e.name,e.phone,e.email,e.document,e.address_name,e.address_number,e.address_complement, e.zip_code,e.login,e.access_level, "
						+ "r.id r_id, r.name r_name, r.description r_description, "
						+ "b.id b_id,b.name b_name,b.phone b_phone,b.email b_email,b.document b_document,b.address_name b_address_name,b.address_number b_address_number,b.address_complement b_address_number, b.zip_code b_zip_code,b.login b_login,b.access_level b_access_level "
						+ "FROM restaurant.employee e "
						+ "INNER JOIN restaurant.role r ON (e.role_id = r.id  and r.deleted = 0) "
						+ "LEFT JOIN restaurant.employee b ON (e.boss_id = b.id and b.deleted = 0) ";
				if(filter != null && filter.length() > 0) {
					sql+= "WHERE "+filter;
				}
				
				DBConnection db = new DBConnection();
				ResultSet rs = null;
				try {
					
					rs = db.ExecuteQuery(sql);
					while(rs.next()) {
						EmployeeModel model = new EmployeeModel();
						RoleModel role = new RoleModel();
						
						EmployeeModel boss = null;
						
						role.setId(rs.getInt("r_id"));
						role.setName(rs.getString("r_name"));
						role.setDescription(rs.getString("r_description"));
						
						if(rs.getString("b_id") != null) {
							boss = new EmployeeModel();
							
							boss.setId(rs.getInt("b_id"));
							boss.setName(rs.getString("b_name"));
							boss.setPhone(rs.getString("b_phone"));
							boss.setEmail(rs.getString("b_email"));
							boss.setDocument(rs.getString("b_document"));
							boss.setAddress_name(rs.getString("b_address_name"));
							boss.setAddress_number(rs.getInt("b_address_number"));
							boss.setAddress_complement(rs.getString("b_address_complement"));
							boss.setZip_code(rs.getString("b_zip_code"));
							boss.setLogin(rs.getString("b_login"));
							boss.setAccess_level(rs.getInt("b_access_level"));
						}
						
						
						model.setId(rs.getInt("id"));
						model.setName(rs.getString("name"));
						model.setPhone(rs.getString("phone"));
						model.setEmail(rs.getString("email"));
						model.setDocument(rs.getString("document"));
						model.setAddress_name(rs.getString("address_name"));
						model.setAddress_number(rs.getInt("address_number"));
						model.setAddress_complement(rs.getString("address_complement"));
						model.setZip_code(rs.getString("zip_code"));
						model.setLogin(rs.getString("login"));
						model.setAccess_level(rs.getInt("access_level"));
						model.setRole(role);
						model.setBoss(boss);
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
	public List<EmployeeModel> getAll() {
		return getAll("e.deleted = 0");
	}

	@Override
	public EmployeeModel get(String id) {
		List<EmployeeModel> result = getAll("e.id = "+id);
		if(result.size() > 0) {
			return result.get(0);
		}
		return null;
			
	
	}
	public EmployeeModel getByLogin(String login) {
		// TODO Auto-generated method stub
		
		List<EmployeeModel> result = getAll("e.login = '"+login+"' ");
		if(result.size() > 0) {
			return result.get(0);
		}
		return null;
			
		
		
	
	}
	
}
