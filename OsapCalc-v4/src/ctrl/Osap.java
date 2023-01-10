package ctrl;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Loan;

/**
 * Servlet implementation class Osap
 */
@WebServlet("/Osap/*")
public class Osap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Osap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (Boolean.parseBoolean(request.getParameter("calculate"))) {
			
			ServletContext context = this.getServletContext();
			HttpSession session = request.getSession();
			Loan loan = (Loan) context.getAttribute("mLoan");
			
			String applicationName = context.getInitParameter("applicationName");
			String applicantName = context.getInitParameter("applicantName");
			
			String A, r, n;
			
			if (request.getParameter("principal") != null) {
				A = request.getParameter("principal");
			} else {
				A = session.getAttribute("principal").toString();
			}
			
			if (request.getParameter("interest") != null) {
				r = request.getParameter("interest");
			} else {
				r = session.getAttribute("interest").toString();
			}
			
			if (request.getParameter("period") != null) {
				n = request.getParameter("period");
			} else {
				n = session.getAttribute("period").toString();
			}
			
			String fixedR = context.getInitParameter("fixedInterest");
			String grace = request.getParameter("grace");
			String graceN = context.getInitParameter("gracePeriod");
			
			if (grace != null) {
				
				if (grace.equals("true")) {
					grace = "on";
				} else if (grace.equals("false")) {
					grace = null;
				}
			}
			
			/* Computes the OSAP monthly payments */
			double monthlyPayment = 0;
			try {
				monthlyPayment = loan.computePayment(A, n, r, grace, graceN, fixedR);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double graceR = 0;
			
			if (grace != null) {
				try {
					graceR = loan.computeGraceInterest(A, graceN, r, fixedR);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				grace = "off";
			}
			
			/* Remembers data for next invocation and jsps */
			session.setAttribute("applicationName", applicationName);
			session.setAttribute("applicantName", applicantName);
			session.setAttribute("principal", A);
			session.setAttribute("period", n);
			session.setAttribute("interest", r);
			session.setAttribute("grace", grace);
			session.setAttribute("graceInterest", graceR);
			session.setAttribute("monthlyPayment", monthlyPayment);
			
			if (request.getPathInfo() != null && request.getPathInfo().contains("Ajax") && loan.error == 0) {
				
				response.setContentType("application/json");
				response.getWriter().printf("Grace Period Interest: $%.2f<br/>", graceR);
				response.getWriter().printf("Monthly payment: $%.2f", monthlyPayment);
				response.getWriter().flush();
			
			} else if (loan.error != 0) {
				
				request.setAttribute("error", loan.error);
				String target = "/UI.jsp";
				request.getRequestDispatcher(target).forward(request, response);
				
			} else {
			
				/* Switches data and control to Results.jsp */
				String resultPage = "/Results.jsp";
				request.getRequestDispatcher(resultPage).forward(request, response);
			}
			
		} else {
			
			/* Switches data and control to UI.jsp */
			String target = "/UI.jsp";
			request.getRequestDispatcher(target).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
@Override
	public void init(ServletConfig config) throws ServletException {
	
		super.init(config);
		ServletContext context = getServletContext();
		context.setAttribute("mLoan", new Loan()); // Instantiate Loan object
	}
}