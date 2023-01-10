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

import bean.UserBean;
import model.BOOKS;
import model.Deployment;


public class UserDAO {

	DataSource ds;
	
	public UserDAO() throws ClassNotFoundException {
		try {
			if (BOOKS.deployment == Deployment.LOCAL)
				ds= (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
			else
				ds= (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public int create(String uname, int salt, String pass, short utype)
			throws SQLException, NamingException
	{
		String preparedStatement= "INSERT INTO Accounts (uname, salt, pass, utype) VALUES (?,?,?,?)";
		Connection con= this.ds.getConnection();
		PreparedStatement stmt= con.prepareStatement(preparedStatement);
		stmt.setString(1, uname);
		stmt.setInt(2,  salt);
		stmt.setString(3, pass);
		stmt.setShort(4, utype);

		int stmtResult= stmt.executeUpdate();
		stmt.close();
		con.close();
		return stmtResult;
	}
	
	public UserBean retrieve(String uname) throws SQLException {
		UserBean ub= null;
		
		String query= "SELECT * FROM Accounts WHERE uname=?";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ps.setString(1, uname);
		ResultSet rs= ps.executeQuery();
		while (rs.next())
			ub= new UserBean(rs.getInt("uid"), rs.getString("uname"), rs.getInt("salt"), rs.getString("pass"), rs.getShort("utype"), rs.getInt("defaultAddress"));
		rs.close();
		ps.close();
		conn.close();
		
		return ub;
	}
	
	public List<UserBean> getUserList() throws SQLException {
		List<UserBean> users= new ArrayList<UserBean>();

		String query= "SELECT * FROM Accounts";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			UserBean ub= new UserBean(rs.getInt("uid"), rs.getString("uname"), rs.getInt("salt"), rs.getString("pass"), rs.getShort("utype"), rs.getInt("defaultAddress"));
			users.add(ub);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return users;
	}
	
	public int updateUserAddress(int defaultAddress, int uid) throws SQLException {
		String query= "UPDATE Accounts SET defaultAddress=? WHERE uid=?";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ps.setInt(1, defaultAddress);
		ps.setInt(2, uid);

		int stmtResult= ps.executeUpdate();
		ps.close();
		conn.close();
		return stmtResult;
	}
}
