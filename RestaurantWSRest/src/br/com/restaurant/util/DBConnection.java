package br.com.restaurant.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

@SuppressWarnings("serial")
public class DBConnection implements Serializable{
	
	
	InitialContext ctx = null;
	Context envCtx = null;
	DataSource ds = null;
	Connection conn = null;
	Statement stmt = null;	
	ResultSet rs = null;
	
	public DBConnection() {
		
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:/comp/env/jdbc/restaurant");
			conn = ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public void finalize() {
		
		try {rs.close();} catch(Exception e) {}
		try {stmt.close();} catch(Exception e) {}
		try {ctx.close();} catch(Exception e) {}
		try {conn.close();} catch(Exception e) {}
		
	}
	public boolean ExecuteSql(String sql) {
		try {
			stmt = conn.createStatement();
			stmt.execute(sql); 
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(sql);
			
			return false;
		}
	}

	public ResultSet ExecuteQuery(String sql) throws SQLException {
		rs = null;
		stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			
	    } catch (Exception e) {
	      e.printStackTrace();
		  
	      System.out.println(sql);
	    }
	    return rs;
	}
}
