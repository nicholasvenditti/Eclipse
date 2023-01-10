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

import model.BOOKS;
import model.Deployment;
import bean.AddressBean;

public class AddressDAO {

	DataSource ds;
	
	public AddressDAO() throws ClassNotFoundException {
		try {
			if (BOOKS.deployment == Deployment.LOCAL)
				ds= (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
			else
				ds= (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public AddressBean retrieve(int aid) throws SQLException {
		AddressBean ab= null;
		
		String query= "SELECT * FROM Address WHERE id=?";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ps.setInt(1, aid);
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			ab= new AddressBean(
					rs.getInt("id"), rs.getString("street"), rs.getString("province"), rs.getString("country"),
					rs.getString("zip"),rs.getString("phone"),rs.getInt("id"),rs.getString("lname"),rs.getString("fname"));
		}
		rs.close();
		ps.close();
		conn.close();
		
		return ab;
	}
	
	public List<AddressBean> retrieveUserAddresses(int uid) throws SQLException {
		List<AddressBean> alist= new ArrayList<AddressBean>();
		
		String query= "SELECT * FROM Address WHERE uid=?";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ps.setInt(1, uid);
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			AddressBean ab= new AddressBean(
					rs.getInt("id"), rs.getString("street"), rs.getString("province"), rs.getString("country"),
					rs.getString("zip"),rs.getString("phone"),rs.getInt("id"),rs.getString("lname"),rs.getString("fname"));
			alist.add(ab);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return alist;
	}

    public int insert(String street, String province, String country, String zip, String phone, int uid, String lname, String fname) 
    		throws SQLException 
    {
		String preparedStatement= "INSERT INTO Address (street, province, country, zip, phone, uid, lname, fname) VALUES (?,?,?,?,?,?,?,?)";
		Connection con= this.ds.getConnection();
		PreparedStatement stmt= con.prepareStatement(preparedStatement);
		stmt.setString(1,  street);
		stmt.setString(2, province);
		stmt.setString(3, country);
        stmt.setString(4, zip);
		stmt.setString(5, phone);
		stmt.setInt(6, uid);
		stmt.setString(7, lname);
		stmt.setString(8, fname);
		
		int stmtResult= stmt.executeUpdate();
		stmt.close();
		con.close();
		return stmtResult;
	}
    
    public int retrieveLastUserAddressId(int uid) throws SQLException {
		int r= 0;
		
		String query= "SELECT id FROM Address WHERE uid=? ORDER BY id DESC LIMIT 1";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ps.setInt(1, uid);
		ResultSet rs= ps.executeQuery();
		while (rs.next())
			r= rs.getInt("id");
		rs.close();
		ps.close();
		conn.close();
		
		return r;
    }
}
