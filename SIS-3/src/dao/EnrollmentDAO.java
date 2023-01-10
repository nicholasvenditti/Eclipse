package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.EnrollmentBean;

public class EnrollmentDAO {

	DataSource ds;
	
	public EnrollmentDAO() throws ClassNotFoundException {
		
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, EnrollmentBean> retrieve() throws SQLException {
		
		String query = "select * from enrollment";
		Map<String, EnrollmentBean> rv = new HashMap<String, EnrollmentBean>();
		Connection con = ds.getConnection();
		PreparedStatement p = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String cid = r.getString("CID");
			EnrollmentBean bean = new EnrollmentBean(cid, null, r.getInt("CREDIT"));
			
			List<String> s = new ArrayList<String>();
			do {
				s.add(r.getString("SID"));
			} while (r.next() && r.getString("CID").equals(cid));
			String[] students = s.toArray(new String[0]);
			bean.setStudents(students);
			
			rv.put(cid, bean);
			r.previous();
		}
		
		r.close();
		p.close();
		con.close();
		
		return rv;
	}
}
