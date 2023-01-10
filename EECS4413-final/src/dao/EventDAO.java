package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.BOOKS;
import model.Deployment;
import model.SalesRecord;
import bean.EventBean;

public class EventDAO {
	
	DataSource ds;
	
	public EventDAO() throws ClassNotFoundException {
		try 
		{
			if (BOOKS.deployment == Deployment.LOCAL)
				ds= (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
			else
				ds= (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public int insert(String day, String bid, int eventtype) throws SQLException {
		String preparedStatement= "INSERT INTO VisitEvent (day, bid, eventtype) VALUES (?,?,?)";
		Connection con= this.ds.getConnection();
		PreparedStatement stmt= con.prepareStatement(preparedStatement);
		stmt.setString(1, day);
		stmt.setString(2, bid);
		stmt.setShort(3, (short)eventtype);

		int stmtResult= stmt.executeUpdate();
		stmt.close();
		con.close();
		return stmtResult;
	}
	
	public Map<String, List<EventBean>> retrieveMonthlyPurchases() throws SQLException {
		Map<String, List<EventBean>> resultMap= new HashMap<String, List<EventBean>>();
		
		String query= "SELECT * FROM VisitEvent WHERE eventtype=3";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			String day= rs.getString("day");
			// Key = MM + yyyy | Day= MMddyyyy
			String mapkey= day.substring(0, 2) + "-" + day.substring(4);
			EventBean eb= new EventBean(rs.getInt("eid"), rs.getString("day"), rs.getString("bid"), rs.getShort("eventtype"));
			if (resultMap.containsKey(mapkey))
				resultMap.get(mapkey).add(eb);
			else
			{
				List<EventBean> elist= new ArrayList<EventBean>();
				elist.add(eb);
				resultMap.put(mapkey, elist);
			}
		}
		rs.close();
		ps.close();
		conn.close();
		
		return resultMap;
	}
	
	public List<SalesRecord> retrieveTopPurchases() throws SQLException {
		List<SalesRecord> salesList= new ArrayList<SalesRecord>();
		
		String query= "SELECT bid, COUNT(bid) as bidCount FROM VisitEvent WHERE eventtype=3 GROUP BY bid ORDER BY bidCount DESC LIMIT 10";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			SalesRecord sr= new SalesRecord(salesList.size()+1, rs.getString("bid"), rs.getInt("bidCount"));
			salesList.add(sr);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return salesList;
	}
	
	public int insertPO(String lname, String fname, int status, int addressID) throws SQLException {
		String preparedStatement= "INSERT INTO PO (lname, fname, status, address) VALUES (?,?,?,?)";
		Connection con= this.ds.getConnection();
		PreparedStatement stmt= con.prepareStatement(preparedStatement);
		stmt.setString(1, lname);
		stmt.setString(2, fname);
		stmt.setInt(3, status);
		stmt.setInt(4, addressID);

		int stmtResult= stmt.executeUpdate();
		stmt.close();
		con.close();
		return stmtResult;
	}
	
	public int insertPOItem(int id, String bid, double price) throws SQLException {
		String preparedStatement= "INSERT INTO POItem (id, bid, price) VALUES (?,?,?)";
		Connection con= this.ds.getConnection();
		PreparedStatement stmt= con.prepareStatement(preparedStatement);
		stmt.setInt(1, id);
		stmt.setString(2, bid);
		stmt.setDouble(3, price);

		int stmtResult= stmt.executeUpdate();
		stmt.close();
		con.close();
		return stmtResult;		
	}
	
	public int retrieveLastPOId () throws SQLException {
		int r= 0;
		
		String query= "SELECT id FROM PO ORDER BY id DESC LIMIT 1";
		Connection conn= this.ds.getConnection();
		PreparedStatement ps= conn.prepareStatement(query);
		ResultSet rs= ps.executeQuery();
		while (rs.next())
			r= rs.getInt("id");
		rs.close();
		ps.close();
		conn.close();
		
		return r;
	}

}
