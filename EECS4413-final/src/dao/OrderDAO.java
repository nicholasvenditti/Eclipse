package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.OrderBean;
import model.BOOKS;
import model.Deployment;

import java.util.HashMap;
import java.util.Map;

public class OrderDAO
{
    DataSource ds;
    public OrderDAO() throws ClassNotFoundException
    {
        try {
			if (BOOKS.deployment == Deployment.LOCAL)
				ds= (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
			else
				ds= (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }

    public Map<String, OrderBean> retrieve(String bid) throws SQLException
    {
		Map<String, OrderBean> rv= new HashMap<String, OrderBean>();
		
		String query= "SELECT * FROM POItem WHERE bid=?";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ps.setString(1, bid);
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			OrderBean order= new OrderBean(rs.getInt("id"), rs.getString("bid"), rs.getDouble("price"));
			rv.put(""+rs.getInt("id"), order);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return rv;
    }
}