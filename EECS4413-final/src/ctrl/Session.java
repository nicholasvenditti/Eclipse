package ctrl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import bean.AddressBean;
import bean.BookBean;
import bean.ReviewBean;
import bean.UserBean;
import model.BOOKS;
import model.CartItem;
import model.Deployment;

/**
 * Servlet implementation class Books
 */
@WebServlet({"/Session","/Session/*"})
@SuppressWarnings("unchecked")
public class Session extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BOOKS model;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Session() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			model= BOOKS.getInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void clearTempSessions(HttpSession session) {
		String[] tempAttributes= { 
					"regError", "regSuccess", "logError", "logSuccess", "bookError",
					"cartUpdate", "reviewError", "purchaseSuccess", "purchaseError"
			};
		for (String atr : tempAttributes) {
			if (session.getAttribute(atr) != null)
				session.removeAttribute(atr);			
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		clearTempSessions(session);
		String reqPath= request.getPathInfo();
		String target= "/Home.jsp";
		boolean dispatch= true;
		Map<String, CartItem> cartMap = (Map<String, CartItem>) session.getAttribute("cartMap");
		if (cartMap==null)
		{
			cartMap= new HashMap<String, CartItem>();
			session.setAttribute("cartMap", cartMap);
		}
		double totalCost= 0;
		Object totalObject= session.getAttribute("totalCost");
		if (totalObject instanceof Double)
			totalCost= (double) totalObject;
		else
			session.setAttribute("totalCost", 0.0);
		UserBean currentUser= (UserBean) session.getAttribute("userAccount");

		String regSubmit= request.getParameter("regSubmit");
		String logSubmit= request.getParameter("loginB");
		String signout= request.getParameter("signout");
		String searchB= request.getParameter("searchB");
		String search= request.getParameter("search");
		String searchBy= request.getParameter("searchBy");
		String updateQty= request.getParameter("updateQty");
		String confirmPurchase= request.getParameter("confirmPurchase");
		List<BookBean> bookList = null;

		request.getSession().setAttribute("logSubmit", logSubmit);

		// Search by category
		if(searchB != null)
		{
			if(searchBy.equals("name"))
			{
				try {
					Map<String, BookBean> map= model.searchByNameResult(search);
					bookList= new ArrayList<BookBean>(map.values());
					session.setAttribute("searchResults", bookList);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			else 
			{
				try {
					Map<String, BookBean> map= model.searchByCategoryResult(search);
					bookList= new ArrayList<BookBean>(map.values());
					session.setAttribute("searchResults", bookList);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			target = "/SearchResult.jsp";
		}
		
		// Registration
		else if (regSubmit != null && regSubmit.equalsIgnoreCase("true"))
		{
			try {
				int regStatus= model.registerUser(request.getParameter("uname"), request.getParameter("pass1"), request.getParameter("pass2"));
				if (regStatus==0)
				{
					target= "/Home.jsp";
					session.setAttribute("regSuccess", true);
				} 
			} catch (Exception e) {
				target= "/Register.jsp";
				session.setAttribute("regError", e.getMessage());
			}
		}
		
		// Login Request
		else if (logSubmit != null && logSubmit.equalsIgnoreCase("true")) 
		{
			try {
				UserBean ub= model.login(request.getParameter("uname"), request.getParameter("passw"));
				session.setAttribute("loggedIn", true);
				session.setAttribute("uid", ub.getUid());
				session.setAttribute("uname", ub.getUname());
				session.setAttribute("utype", ub.getUtype());
				session.setAttribute("userAccount", ub);
				target= "/Home.jsp";
				session.setAttribute("logSuccess",  true);
			} catch (Exception e) {
				target= "/Register.jsp";
				session.setAttribute("logError", e.getMessage());
			}
		}
		// Sign off account
		else if (signout != null && signout.equalsIgnoreCase("1"))
		{
			session.removeAttribute("loggedIn");
			session.removeAttribute("uid");
			session.removeAttribute("uname");
			session.removeAttribute("utype");
			session.removeAttribute("userAccount");
			System.out.println("signed out!");
		}
		
		// Confirm Order
		else if (confirmPurchase != null && confirmPurchase.equalsIgnoreCase("true"))
		{
			Object purchaseAttemptsObject= session.getAttribute("purchaseAttempts");
			int purchaseAttempts= 0;
			if (purchaseAttemptsObject instanceof Integer)
				purchaseAttempts= (int) purchaseAttemptsObject;

			// First two attempts, go through.
			if (purchaseAttempts < 2)
			{
				String finalShipping= request.getParameter("shipAdd");
				try {
					AddressBean shippingAddress= model.getAddress(finalShipping);
					for (CartItem cartItem : cartMap.values())
					{
						BookBean book= cartItem.getBook();
						String bookId= book.getBid();
						System.out.println("Purchase: AddressID=" + shippingAddress.getAid() + "; BookID=" + bookId);
						for (int i=0; i<cartItem.getQuantity(); i++)
						{
							model.submitPurchaseOrder(shippingAddress, book);
							model.submitBookPurchase(bookId);						
						}
					}
					purchaseAttempts+= 1;
					session.setAttribute("purchaseSuccess", "Your item(s) were successfully purchased!");

					cartMap= new HashMap<String, CartItem>();
					session.setAttribute("cartMap", cartMap);
					totalCost= 0;
					session.setAttribute("totalCost", totalCost);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// Third purchase attempt, reject.
			else {
				session.setAttribute("purchaseError", "Your order could not be processed.");
				purchaseAttempts= 0;
			}

			session.setAttribute("purchaseAttempts", purchaseAttempts);
		}
		
		// Fresh Home Page
		else if (reqPath == null || reqPath.equals("/"))
			;
		// Registration Page
		else if (reqPath.equalsIgnoreCase("/Register") || reqPath.equalsIgnoreCase("/Register/"))
		{
			target= "/Register.jsp";
		}

		// When user updates Shopping Cart Quantities
		else if (updateQty != null && updateQty.equalsIgnoreCase("true"))
		{
			totalCost= 0;
			Iterator<Map.Entry<String, CartItem>> iterator = cartMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, CartItem> entry = iterator.next();

				int qty = Integer.parseInt(request.getParameter(entry.getValue().getBook().getBid() + "Qty"));

				if (qty<=0) {
					iterator.remove();
				} else {
					entry.getValue().setQuantity(qty);
					totalCost += qty * entry.getValue().getBook().getPrice();
				}
			}

			session.setAttribute("totalCost", totalCost);
			session.setAttribute("cartMap", cartMap);
			target= "/ShoppingCart.jsp";
		}
		
		// Book Page
		else if (reqPath.equalsIgnoreCase("/Book") || reqPath.equalsIgnoreCase("/Book/"))
		{			
			String bid= request.getParameter("bid");
			String addCart= request.getParameter("addCart");
			
			try {
				BookBean bb= model.getBook(bid);
				session.setAttribute("book", bb);
				int newQuantity= 0;
				
				// Add To Cart
				if (addCart != null && addCart.equals("true"))
				{
					String bbID= bb.getBid();
					if (cartMap.containsKey(bbID)) {
						cartMap.get(bbID).incrementQuantity();
						newQuantity= cartMap.get(bbID).getQuantity();
					}
					else {
						cartMap.put(bb.getBid(), new CartItem(bb,1));
						newQuantity= 1;
					}
					model.submitBookCart(bbID);
					session.setAttribute("cartUpdate", "BookID="+bb.getBid() + ", Qty="+newQuantity );
					totalCost+= newQuantity * bb.getPrice();
					session.setAttribute("totalCost", totalCost);
				}

			} catch (Exception e) {
				session.setAttribute("bookError", e.getMessage());				
			}
			
			// review submit
			String reviewSubmit= request.getParameter("reviewSubmit");
			if(reviewSubmit != null && reviewSubmit.equals("true"))
			{
				String rate= request.getParameter("rate");
				String reviewText= request.getParameter("comment");
				String reviewAuthor= request.getParameter("author");
				System.out.println(rate + ", " + reviewAuthor + ", " + bid + ", " + reviewText);
				
				try {
					model.addReview(bid, reviewAuthor, rate, reviewText);
				} catch (Exception e)
				{
					e.printStackTrace();
					session.setAttribute("reviewError", e.getMessage());		
				}
			}
			
			// Get Reviews
			try {
				List<ReviewBean> reviews= model.getReviews(bid);
				session.setAttribute("reviewList", reviews);
			} catch (Exception e) {
				session.setAttribute("reviewError", e.getMessage());	
			}

			target= "/BookInfo.jsp";
		}

		// Shopping Cart Page
		else if (reqPath.equalsIgnoreCase("/Cart") || reqPath.equalsIgnoreCase("/Cart/"))
		{
			target= "/ShoppingCart.jsp";
		}

		else if (reqPath.equalsIgnoreCase("/Ajax") || reqPath.equalsIgnoreCase("/Ajax/"))
		{
			String bid= request.getParameter("bid");
			String ajaxReq= request.getParameter("ajax");
			
			if (ajaxReq != null && ajaxReq.equals("viewer"))
			{
				try {
					model.submitBookView(bid);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			dispatch= false;
		}
		
		else if (reqPath.equalsIgnoreCase("/Admin") || reqPath.equalsIgnoreCase("/Admin/"))
		{
			target= "/Admin.jsp";
			if (currentUser != null && currentUser.getUtype() == (short) 1) 
			{
				try {
					JSONObject jsonMonthly= model.getMonthlyPurchases();
					JSONObject jsonTop= model.getTopPurchases();
					String monthlyFile;
					if (BOOKS.deployment == Deployment.LOCAL)
						monthlyFile= request.getServletContext().getRealPath("/")+"res\\monthly.json";
					else
						monthlyFile= request.getServletContext().getRealPath("/")+"/res/monthly.json";
					FileWriter fw= new FileWriter(monthlyFile);
					fw.write(jsonMonthly.toString(4));
					fw.close();
					String topFile;
					if (BOOKS.deployment == Deployment.LOCAL)
						topFile= request.getServletContext().getRealPath("/")+"res\\topsales.json";
					else
						topFile= request.getServletContext().getRealPath("/")+"/res/topsales.json";
					fw= new FileWriter(topFile);
					fw.write(jsonTop.toString(4));
					fw.close();
					System.out.println(jsonTop.toString(4));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// Payment Page
		else if (reqPath.equalsIgnoreCase("/Payment") || reqPath.equalsIgnoreCase("/Payment/"))
		{
			target= "/Payment.jsp";
			String paymentLogin= request.getParameter("paymentLogin");
			
			if (paymentLogin != null && paymentLogin.equals("true")) {
				try {
					UserBean ub= model.login(request.getParameter("uname"), request.getParameter("passw"));
					session.setAttribute("loggedIn", true);
					session.setAttribute("uid", ub.getUid());
					session.setAttribute("uname", ub.getUname());
					session.setAttribute("utype", ub.getUtype());
					session.setAttribute("logSuccess",  true);
					session.setAttribute("userAccount", ub);
					currentUser= ub;
					target= "/Payment.jsp";
				} catch (Exception e) {
					target= "/Register.jsp";
					session.setAttribute("logError", e.getMessage());
				}
			}
			
			if (currentUser != null) {
				try {
					List<AddressBean> addresses= model.getAddresses(currentUser.getUid());
					for (AddressBean ab : addresses) {
						System.out.println(ab.toString());
					}
					session.setAttribute("addresses", addresses);
				} catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("addressError", e.getMessage());
				}
			}
		}
		
		else if (reqPath.equalsIgnoreCase("/AddressBook") || reqPath.equalsIgnoreCase("/AddressBook/"))
		{
			target= "/AddressBook.jsp";
			String defaultButton= request.getParameter("defaultAddButton");
			String newDefaultAddress= request.getParameter("newDefault");
			String addedAddressButton= request.getParameter("submitAddress");
			
			if (currentUser != null) 
			{
				// Add Address
				if (addedAddressButton != null && addedAddressButton.equals("true")) {
					String fname= request.getParameter("fname");
					String lname= request.getParameter("lname");
					String street= request.getParameter("street");
					String province= request.getParameter("province");
					String country= request.getParameter("country");
					String zip= request.getParameter("zip");
					String phone= request.getParameter("phone");
					
					try {
						model.addAddress(street, province, country, zip, phone, currentUser.getUid(), lname, fname);
						if (currentUser.getDefaultAddress()==0)
						{
							int lastAdd= model.getLastUserAddress(currentUser.getUid());
							model.updateDefaultAddress(""+lastAdd, currentUser.getUid());
							currentUser.setDefaultAddress(lastAdd);
							session.setAttribute("userAccount", currentUser);
						}
					} catch (Exception e) {
						e.printStackTrace();
						session.setAttribute("addressError", e.getMessage());
					}
				}
				
				// Set Default Address
				else if (defaultButton != null && defaultButton.equals("true")) {
					try {
						model.updateDefaultAddress(newDefaultAddress, currentUser.getUid());
						currentUser.setDefaultAddress(Integer.parseInt(newDefaultAddress));
						session.setAttribute("userAccount", currentUser);
					} catch (Exception e) {
						e.printStackTrace();
						session.setAttribute("addressError", e.getMessage());
					}
				}
				
				// Get Address List
				try {
					List<AddressBean> addresses= model.getAddresses(currentUser.getUid());
					for (AddressBean ab : addresses) {
						System.out.println(ab.toString());
					}
					session.setAttribute("addresses", addresses);
				} catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("addressError", e.getMessage());
				}
			}
		}
		
		// Confirmation Page
		else if (reqPath.equalsIgnoreCase("/Confirmation") || reqPath.equalsIgnoreCase("/Confirmation/")) 
		{
			String shipAddress= request.getParameter("shipAddress");
			String billAddress= request.getParameter("billAddress");
			String sameAddress= request.getParameter("same");
			String ccardNumber= request.getParameter("ccardNumber");
			String ccardName= request.getParameter("ccardName");
			
			try {
				AddressBean shippingAddress= model.getAddress(shipAddress);
				AddressBean billingAddress;
				if (sameAddress != null && sameAddress.equals("on"))
					billingAddress= shippingAddress;
				else
					billingAddress= model.getAddress(billAddress);
				session.setAttribute("shippingAddress", shippingAddress);
				session.setAttribute("billingAddress", billingAddress);
				//String displayNumber= "****-****-****-" + ccardNumber.substring(12);
				String displayNumber = ccardNumber;
				session.setAttribute("ccNumber", displayNumber);
				session.setAttribute("ccName", ccardName);
				target= "/Confirmation.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		// Address Page 
		else if (reqPath.equalsIgnoreCase("/Address") || reqPath.equalsIgnoreCase("/Address/"))
		{
				target= "/AddressInfo.jsp";
		}
		
		if (dispatch)
			request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
