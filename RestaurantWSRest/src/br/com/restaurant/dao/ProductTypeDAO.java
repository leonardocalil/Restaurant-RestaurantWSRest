package br.com.restaurant.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.restaurant.model.ProductTypeModel;
import br.com.restaurant.util.DBConnection;

public class ProductTypeDAO extends AbstractDAO<ProductTypeModel> {
	
	public List<ProductTypeModel> getAll() {
		return getAll("tp.deleted = 0 ");
	}
	
	public List<ProductTypeModel> getAll(String filter) {
		List<ProductTypeModel> results = new ArrayList<ProductTypeModel>();
		DBConnection db = new DBConnection();
		ResultSet rs = null;
		
		try {
			String sql = "SELECT tp.id tp_id,tp.description tp_description "
					+ "FROM restaurant.type_product tp ";
			if(filter != null && filter.length() > 0) {
				sql += "WHERE "+filter; 
			}
			rs = db.ExecuteQuery(sql);
			while(rs.next()) {
				ProductTypeModel productType = new ProductTypeModel();
				productType.setId(rs.getInt("tp_id"));
				productType.setDescription(rs.getString("tp_description"));
				
				
				results.add(productType);
				
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
	public ProductTypeModel get(String id) {
		
		List<ProductTypeModel> result =  getAll("p.id = "+id);
		if(result.size() > 0) {
			return result.get(0);
		}
		return null;

	}
	
	public int getNewId() {
		// TODO Auto-generated method stub
		String sql = "select nextval('product_type_seq')";
		
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
	public boolean save(ProductTypeModel model) {
		
		String sql = "insert into restaurant.type_product (id,description) "
				+ "values(nextval('product_type_seq'),'"+model.getDescription()+"')";
		
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
	public boolean update(ProductTypeModel model) {
		String sql = "update restaurant.type_product set description='"+model.getDescription()+"' "
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
		String sql = "update restaurant.type_product set deleted=1 "
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
