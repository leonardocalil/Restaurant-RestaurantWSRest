package br.com.restaurant.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.restaurant.model.ProductModel;
import br.com.restaurant.model.ProductTypeModel;
import br.com.restaurant.util.DBConnection;

public class ProductDAO extends AbstractDAO<ProductModel> {
	
	public List<ProductModel> getAll() {
		return getAll("p.deleted = 0 AND tp.deleted = 0 ");
	}
	
	public List<ProductModel> getAll(String filter) {
		List<ProductModel> results = new ArrayList<ProductModel>();
		DBConnection db = new DBConnection();
		ResultSet rs = null;
		
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
		
		
		try {
			String sql = "SELECT p.id, p.name,p.description,p.cost_price, p.sale_price, "
					+ "tp.id tp_id,tp.description tp_description "
					+ "FROM restaurant.product p INNER JOIN restaurant.type_product tp ON  p.product_type_id = tp.id ";
			if(filter != null && filter.length() > 0) {
				sql += "WHERE "+filter; 
			}
			rs = db.ExecuteQuery(sql);
			while(rs.next()) {
				ProductTypeModel productType = new ProductTypeModel();
				productType.setId(rs.getInt("tp_id"));
				productType.setDescription(rs.getString("tp_description"));
				
				ProductModel model = new ProductModel();
				model.setId(rs.getInt("id"));
				model.setName(rs.getString("name"));
				model.setDescription(rs.getString("description"));
				try {
					if(rs.getString("cost_price") != null) {
						model.setCost_price(currencyFormatter.parse(rs.getString("cost_price")).floatValue());
					}
					if(rs.getString("sale_price") != null) {
						model.setSale_price(currencyFormatter.parse(rs.getString("sale_price")).floatValue());
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				model.setProductType(productType);
				
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
	public ProductModel get(String id) {
		List<ProductModel> results = getAll("p.id = "+id);
		if(results.size() > 0) {
			return results.get(0);
		}
		return null;
	}
	
	public int getNewId() {
		// TODO Auto-generated method stub
		String sql = "select nextval('product_seq')";
		
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
	public boolean save(ProductModel model) {
		
		
	    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
    
	    
		String sql = "insert into restaurant.product (id,product_type_id,name,description,cost_price,sale_price) "
				+ "values(nextval('product_seq'),"+model.getProductType().getId()+",'"+model.getName()+"','"+model.getDescription()+"','"+currencyFormatter.format(model.getCost_price())+"','"+currencyFormatter.format(model.getSale_price())+"')";
		
		
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
	public boolean update(ProductModel model) {
		
	    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());

		
		String sql = "update restaurant.product set product_type_id="+model.getProductType().getId()+",name='"+model.getName()+"',description='"+model.getDescription()+"',cost_price='"+currencyFormatter.format(model.getCost_price())+"',sale_price = '"+currencyFormatter.format(model.getSale_price())+"' "
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
		String sql = "update restaurant.product set deleted=1 "
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
