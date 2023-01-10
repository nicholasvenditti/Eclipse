package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.ReviewBean;
import model.BOOKS;
import model.Deployment;

public class ReviewDAO {

	DataSource ds;
	
	public ReviewDAO() throws ClassNotFoundException {
		try {
			if (BOOKS.deployment == Deployment.LOCAL)
				ds= (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
			else
				ds= (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<ReviewBean> retrieveBookReviews(String bid) throws SQLException {
		List<ReviewBean> reviews= new ArrayList<ReviewBean>();
		
		String query= "SELECT * FROM Reviews WHERE bid=?";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ps.setString(1, bid);
		ResultSet rs= ps.executeQuery();
		while (rs.next()) 
		{
			ReviewBean rb= new ReviewBean(rs.getInt("rid"), rs.getString("bid"), rs.getString("uname"), rs.getInt("rating"), rs.getString("review"));
			// System.out.println("Review Found: " + rb.toString());
			reviews.add(rb);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return reviews;
	}
	
	public int insert(String bid, String author, int rating, String review) throws SQLException {
		String query= "INSERT INTO Reviews (bid, uname, rating, review) VALUES (?,?,?,?)";
		Connection conn= this.ds.getConnection();
		PreparedStatement stmt= conn.prepareStatement(query);
		stmt.setString(1, bid);
		stmt.setString(2,  author);
		stmt.setInt(3, rating);
		stmt.setString(4, review);
		
		System.out.println("INSERT INTO Reviews (bid, uname, rating, review) VALUES ('"+bid+"','"+author+"','"+rating+"','"+review+"')");

		
		int stmtResult= stmt.executeUpdate();
		stmt.close();
		conn.close();
		return stmtResult;
	}
}
