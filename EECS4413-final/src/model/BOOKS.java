package model;

import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import bean.AddressBean;
import bean.BookBean;
import bean.EventBean;
import bean.OrderBean;
import bean.UserBean;
import bean.ReviewBean;
import dao.AddressDAO;
import dao.BookDAO;
import dao.EventDAO;
import dao.UserDAO;
import dao.OrderDAO;
import dao.ReviewDAO;

import org.json.JSONArray;
import org.json.JSONObject;

public class BOOKS {
	
	private static BOOKS instance;
	private BookDAO bd;
	private UserDAO ud;
	private OrderDAO od;
	private ReviewDAO rd;
	private EventDAO ed;
	private AddressDAO ad;
	public static final Deployment deployment= Deployment.ONLINE;

	private BOOKS () throws ClassNotFoundException {
		bd= new BookDAO();
		ud= new UserDAO();
		od=new OrderDAO();
		rd= new ReviewDAO();
		ed= new EventDAO();
		ad= new AddressDAO();
	}
	
	public static BOOKS getInstance() throws ClassNotFoundException {
		if (instance==null)
		{
			instance= new BOOKS();
		}
		return instance;
	}
	
	public boolean validString(String st) {
		Pattern valid= Pattern.compile("^[\\w-]+$", Pattern.CASE_INSENSITIVE);
		Matcher match= valid.matcher(st);
		return match.find();
	}
	
	public String getJsonBook (String bid) throws Exception {
		BookBean book= bd.retrieve(bid);
		if (book==null)
			return "ERROR: No book exists with bookID [" + bid + "]";
		
		return book.toJson().toString(4);
	}
	
	public void createUser(String uname, Password pass, short utype) {
		try {
			ud.create(uname, pass.getSalt(), pass.getHashpass(), utype);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public UserBean getUser(String uname) {
		UserBean ub= null;
		try {
			ub= ud.retrieve(uname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ub;
	}
	
	public BookBean getBook(String bid) throws Exception {
		BookBean bb= null;
		if (bid==null)
			throw new Exception("Null Field - bid must not be empty.");
		else if (bid.length()==0)
			throw new Exception("Empty BookID - must be of length>0.");
		else if (!validString(bid))
			throw new Exception("Invalid BookID - bid may only contain alphanumeric characters, hyphens, or underscores.");
		
		try {
			bb= bd.retrieve(bid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (bb==null)
			throw new Exception("Book Not Found - bid ["+bid+"] does not exist.");
		
		return bb;
	}
	
	public List<ReviewBean> getReviews(String bid) throws Exception {
		if (bid==null)
			throw new Exception("Null Field - bid must not be empty.");
		else if (bid.length()==0)
			throw new Exception("Empty BookID - must be of length>0.");
		else if (!validString(bid))
			throw new Exception("Invalid BookID - bid may only contain alphanumeric characters, hyphens, or underscores.");
		
		return rd.retrieveBookReviews(bid);
	}
	
	public List<UserBean> getUserList() throws Exception {
		return ud.getUserList();
	}
	
	public int registerUser(String uname, String pass1, String pass2) throws Exception {
		if (uname==null || pass1==null || pass2==null)
			throw new Exception("Null Field - User Name and Password fields must not be empty.");
			// return 1; // Null field.
		else if (uname.length()==0)
			throw new Exception("Empty User Name - must be of length>0.");
			// return 2; // User Name Too Short
		else if (!validString(uname))
			throw new Exception("Invalid User Name - may only contain alphanumeric characters, hyphens, or underscores.");
			// return 3; // User Name has special characters
		else if (!pass1.equals(pass2))
			throw new Exception("Password Mismatch - the two passwords submitted do not match.");
			// return 4; // Passwords Different
		else if (pass1.length() < 8)
			throw new Exception("Short Password - must be of minimum length 8.");
			// return 5; // Password too short.
		
		Password pass= new Password(pass1);
		int creations= 0;
		try {
			creations= ud.create(uname, pass.getSalt(), pass.getHashpass(), (short) 3);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (creations==0)
			throw new Exception("Duplicate Name - could not be registered, user name taken.");
		// return (creations>0)? 0 : 6; // 0 is success (row inserted). 6 is failure (row could not be inserted, duplicate uname)
		
		return 0;
	}
	
	public UserBean login(String uname, String pass) throws Exception {
		if (uname==null || pass==null)
			throw new Exception("Null Field - User Name and Password must not be empty.");
		else if (uname.length()==0)
			throw new Exception("Empty User Name - must be of length>0.");
		else if (pass.length()==0)
			throw new Exception("Empty Password - must be of length>0.");
		else if (!validString(uname))
			throw new Exception("Invalid User Name - may only contain alphanumeric characters, hyphens, or underscores.");
		
		UserBean ub= null;
		try {
			ub= ud.retrieve(uname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (ub==null)
			throw new Exception("User Not Found - the account does not exist.");

		String validPass= ub.getHashpass();
		String checkedPass= Password.getSHA512(pass, ub.getSalt());
		
		if ( !checkedPass.contentEquals(validPass) )
			throw new Exception("Password Mismatch - the password entered is invalid");
		
		return ub;
	}

	//orderDAO retrieve method 
	public String getOrders(String bid) throws Exception
	{
		Map<String, OrderBean> resultMap= od.retrieve(bid);
		
		JSONObject jsonObj= new JSONObject();
		jsonObj.put("bookID", bid);
		
		JSONArray jsonArr= new JSONArray();
		for (String key : resultMap.keySet()) {
			OrderBean ob= resultMap.get(key);
			jsonArr.put(ob.toJson());
		}
		
		jsonObj.put("orderList", jsonArr);
		return jsonObj.toString(4);
	}

	//bookdao search by category result
	public Map<String, BookBean> searchByCategoryResult(String category) throws Exception
	{
		 return bd.retrieveCategory(category);
	}
	

	//bookdao search by name result
	public Map<String, BookBean> searchByNameResult(String name) throws Exception
	{
		return bd.retrieveName(name);
	}

	//add review 
	public void addReview(String bid, String author, String rating, String review) throws Exception
	{
		System.out.println("addReview");
		if (bid==null || author==null || rating==null | review==null)
			throw new Exception("Null Field - bid/author/rating/review must not be empty.");
		else if (bid.length()==0 || author.length()==0 || review.length()==0)
			throw new Exception("Empty Field - bid/author/review must be of length>0.");
		else if (!validString(bid) || !validString(author) || !validString(review))
			throw new Exception("Invalid BookID - bid/author/review may only contain alphanumeric characters, hyphens, or underscores.");
		
		int intRating= -1;
		try {
			intRating= Integer.parseInt(rating);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (intRating < 0)
			throw new Exception("Invalid Rating - must be 1-5 stars");
		
		System.out.println("Review: " + bid);
		rd.insert(bid, author, intRating, review);
	}
	
	public void submitBookView(String bid) throws Exception {
		if (bid==null)
			throw new Exception("Null Field - bid must not be empty.");
		else if (bid.length()==0)
			throw new Exception("Empty BookID - must be of length>0.");
		else if (!validString(bid))
			throw new Exception("Invalid BookID - bid may only contain alphanumeric characters, hyphens, or underscores.");
		
		SimpleDateFormat sdfr= new SimpleDateFormat("MMddyyyy");
		Date date= new Date();

		String day= sdfr.format(date);
		ed.insert(day, bid, 1);
	}
	
	public void submitBookCart(String bid) throws Exception {
		if (bid==null)
			throw new Exception("Null Field - bid must not be empty.");
		else if (bid.length()==0)
			throw new Exception("Empty BookID - must be of length>0.");
		else if (!validString(bid))
			throw new Exception("Invalid BookID - bid may only contain alphanumeric characters, hyphens, or underscores.");
		
		SimpleDateFormat sdfr= new SimpleDateFormat("MMddyyyy");
		Date date= new Date();

		String day= sdfr.format(date);
		ed.insert(day, bid, 2);
	}
	
	public void submitBookPurchase(String bid) throws Exception {
		if (bid==null)
			throw new Exception("Null Field - bid must not be empty.");
		else if (bid.length()==0)
			throw new Exception("Empty BookID - must be of length>0.");
		else if (!validString(bid))
			throw new Exception("Invalid BookID - bid may only contain alphanumeric characters, hyphens, or underscores.");
		
		SimpleDateFormat sdfr= new SimpleDateFormat("MMddyyyy");
		Date date= new Date();
		
		String day= sdfr.format(date);
		
		ed.insert(day, bid, 3);
	}
	
	public JSONObject getMonthlyPurchases() throws Exception {
		Map<String, List<EventBean>> eventMap= ed.retrieveMonthlyPurchases();
		
		JSONObject jsonObj= new JSONObject();
		JSONArray monthArray= new JSONArray();
		Map<String, String> bookTitles= new HashMap<String, String>();
		for (String monthKey : eventMap.keySet()) {
			JSONObject monthObject= new JSONObject();
			monthObject.put("month", monthKey);
			JSONArray purchases= new JSONArray();
			for (EventBean eb : eventMap.get(monthKey))
			{
				String bid= eb.getBid();
				if (!bookTitles.containsKey(bid))
				{
					BookBean book= bd.retrieve(eb.getBid());
					bookTitles.put(bid, book.getTitle());
				}
				JSONObject eventJson= new JSONObject();
				eventJson.put("eid", eb.getEid());
				eventJson.put("day", eb.getDay());
				eventJson.put("bid", bid);
				eventJson.put("title", bookTitles.get(bid));
				purchases.put(eventJson);
			}
			monthObject.put("purchases", purchases);
			monthArray.put(monthObject);
		}
		jsonObj.put("months", monthArray);
		return jsonObj;
	}
	
	public JSONObject getTopPurchases() throws Exception {
		List<SalesRecord> salesList= ed.retrieveTopPurchases();
		JSONObject jsonObj= new JSONObject();
		JSONArray jsonArray= new JSONArray();
		
		for (SalesRecord sr : salesList)
		{
			BookBean book= bd.retrieve(sr.getBid());
			JSONObject eventJson= new JSONObject();
			eventJson.put("rank", sr.getRank() );
			eventJson.put("bid", sr.getBid());
			eventJson.put("title", book.getTitle());
			eventJson.put("sales", sr.getSales());
			jsonArray.put(eventJson);
		}
		
		jsonObj.put("top10", jsonArray);
		return jsonObj;
	}
	
	public List<AddressBean> getAddresses(int uid) throws Exception {
		return ad.retrieveUserAddresses(uid);
	}
	
	public AddressBean getAddress(String aid) throws Exception {
		//System.out.println(aid);
		int intAid= Integer.parseInt(aid);
		return ad.retrieve(intAid);
	}
	
	public void addAddress(String street, String province, String country, String zip, String phone, int uid, String lname, String fname) throws Exception
	{ 
		ad.insert(street, province, country, zip, phone, uid, lname, fname);
	}
	
	public void submitPurchaseOrder(AddressBean address, BookBean book) throws Exception {
		// Purchase Order
		ed.insertPO(address.getLname(), address.getFname(), 1, address.getAid());
		int poi= ed.retrieveLastPOId();
		
		// Purchase Order Item
		ed.insertPOItem(poi, book.getBid(), book.getPrice());
	}
	
	public void updateDefaultAddress (String defaultAddressInput, int uid) throws Exception 
	{
		int defaultAddress= Integer.parseInt(defaultAddressInput);
		
		ud.updateUserAddress(defaultAddress, uid);
	}
	
	public int getLastUserAddress (int uid) throws Exception {
		return ad.retrieveLastUserAddressId(uid);
	}
}