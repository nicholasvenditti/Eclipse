package dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.StudentBean;

public class StudentDAO {

	DataSource ds;
	
	public StudentDAO() throws ClassNotFoundException {
		
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, StudentBean> retrieve(String namePrefix, int credit_taken) throws SQLException {
		
		String np = "%" + namePrefix + "%";
		String query = "select * from students where surname like ? and credit_taken >= ?";
		Map<String, StudentBean> rv = new HashMap<String, StudentBean>();
		Connection con = ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, np);
		p.setInt(2, credit_taken);
		
		ResultSet r = p.executeQuery();
		while (r.next()){
			String name = r.getString("GIVENNAME") + ", " + r.getString("SURNAME");
			int credit_taking = r.getInt("CREDIT_GRADUATE") - r.getInt("CREDIT_TAKEN");
			StudentBean bean = new StudentBean(r.getString("SID"), name, r.getInt("CREDIT_TAKEN"), r.getInt("CREDIT_GRADUATE"), credit_taking >= 0 ? credit_taking : 0);
			rv.put(name, bean);
		}
		
		r.close();
		p.close();
		con.close();
		
		return rv;
	}
	
//	public void update(String sid, int credit_graduate) throws SQLException {
//		
//		String update = "update students set credit_graduate = ? where sid = ?";
//		Connection con = ds.getConnection();
//		PreparedStatement p = con.prepareStatement(update);
//		p.setInt(1, credit_graduate);
//		p.setString(2, sid);
//		p.executeUpdate();
//		p.close();
//		con.close();
//	}
	
	public int insert(String sid, String givenname, String surname, int credittake, int creditgraduate) throws SQLException, NamingException {
		
		String preparedStatement ="insert into students values(?,?,?,?,?)";
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, sid);
		stmt.setString(2, givenname);
		stmt.setString(3, surname);
		stmt.setInt(4, credittake);
		stmt.setInt(5, creditgraduate);

		return stmt.executeUpdate();
	}
	
	public int delete(String sid) throws SQLException, NamingException {
		
		 String preparedStatement ="delete from students where sid=?";
		 Connection con = this.ds.getConnection();
		 PreparedStatement stmt = con.prepareStatement(preparedStatement);
		 stmt.setString(1, sid);
		 
		 return stmt.executeUpdate();
	}
}
