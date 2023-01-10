package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.BookBean;
import model.BOOKS;
import model.Deployment;

public class BookDAO {

	DataSource ds;
	
	public BookDAO() throws ClassNotFoundException {
		try {
			if (BOOKS.deployment == Deployment.LOCAL)
				ds= (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
			else
				ds= (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public BookBean retrieve(String bid) throws SQLException {
		BookBean book= null;
		
		String query= "SELECT * FROM Book WHERE bid=?";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ps.setString(1, bid);
		ResultSet rs= ps.executeQuery();
		while (rs.next())
			book= new BookBean(rs.getString("bid"), rs.getString("title"), rs.getDouble("price"), rs.getString("category"));
		rs.close();
		ps.close();
		conn.close();
		
		return book;
	}
	
	public Map<String, BookBean> retrieveCategory(String category) throws SQLException {
		Map<String, BookBean> rv= new HashMap<String, BookBean>();
		
		String query= "SELECT * FROM Book WHERE category=?";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ps.setString(1, category);
		ResultSet rs= ps.executeQuery();
		
		while (rs.next()) {
			BookBean bb= new BookBean(rs.getString("bid"), rs.getString("title"), rs.getDouble("price"), rs.getString("category"));
			rv.put(rs.getString("bid"), bb);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return rv;
	}

	public Map<String, BookBean> retrieveName(String name) throws SQLException {
		Map<String, BookBean> rv= new HashMap<String, BookBean>();
		
		String query= "SELECT * FROM Book WHERE title LIKE ?";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ps.setString(1, '%'+name+'%');
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			BookBean bb= new BookBean(rs.getString("bid"), rs.getString("title"), rs.getDouble("price"), rs.getString("category"));
			rv.put(rs.getString("bid"), bb);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return rv;
	}
}
