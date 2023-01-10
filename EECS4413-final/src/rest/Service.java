package rest;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.BOOKS;

@Path("log")
public class Service {

	@GET
	@Path("/book/")
	@Produces("text/plain")
	public String getProductInfo(@QueryParam("bookID") String bid) throws Exception {
		String errorString= "";
		try {
			return BOOKS.getInstance().getJsonBook(bid);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			errorString= e.getMessage();
			String sStackTrace = sw.toString(); // stack trace as a string
			errorString= sStackTrace;
		}
		return "ERROR: bookID contains special characters! Use alphanumeric characters only.\n"+errorString;
	}
	
	@GET
	@Path("/orderlist/")
	@Produces("text/plain")
	public String getOrdersByPartNumber(@QueryParam("bookID") String bid) throws Exception
	{
		String errorString= "";
		try {
			return BOOKS.getInstance().getOrders(bid);
		} catch (Exception e) {
			e.printStackTrace();
			errorString= e.getMessage();
			errorString= e.getStackTrace().toString();
		}
		return "ERROR: bookID contains special characters! Use alphanumeric characters only.\n"+errorString;
	}
}
