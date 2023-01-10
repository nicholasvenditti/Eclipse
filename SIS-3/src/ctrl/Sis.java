package ctrl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.XML;

import bean.EnrollmentBean;
import bean.StudentBean;
import model.SIS;

/**
 * Servlet implementation class Sis
 */
@WebServlet("/Sis/*")
public class Sis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean report = Boolean.parseBoolean(request.getParameter("report"));
		boolean xml = Boolean.parseBoolean(request.getParameter("xml"));
		boolean json = Boolean.parseBoolean(request.getParameter("json"));
		boolean ajax = request.getPathInfo() != null && request.getPathInfo().contains("Ajax");
		String xmlString = "";
		
		if (report || xml || json) {
			
			ServletContext context = this.getServletContext();
			SIS sis = (SIS) context.getAttribute("mSIS");
			
			String namePrefix = request.getParameter("namePrefix");
			String credit_taken = request.getParameter("credit_taken");
			
			Map<String, StudentBean> studentMap = null;
			try {
				studentMap = sis.retrieveStudent(namePrefix, credit_taken);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Map<String, EnrollmentBean> enrollmentMap = null;
			try {
				enrollmentMap = sis.retrieveEnrollment();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (xml || json || ajax) {
				try {
					xmlString = sis.export(namePrefix, credit_taken);
//					sis.myImport();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (studentMap == null) {
				request.setAttribute("report", false);
			} else {
				
				request.setAttribute("studentMap", studentMap);
				request.setAttribute("enrollmentMap", enrollmentMap);
				request.setAttribute("numOfEntries", studentMap.size());
				request.setAttribute("report", true);
			}
			
		} else {
			request.setAttribute("report", false);
		}
		
		if (xml) {
			
//			String filename = "C:/Users/nicho/eclipse-workspace/SIS-2/SIS.xml";
			
			response.setContentType("text/xml");
			response.getWriter().print(xmlString);
			
//			response.setHeader("Content-disposition", "attachment; filename=" + filename);
//			ServletOutputStream out = response.getOutputStream();
//			out.println(xmlString);
			
		} else if (json) {
			
//			response.setContentType("application/json");
//			response.getWriter().print(xmlString);
			
			response.setContentType("application/json");
			JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
            String jsonString = xmlJSONObj.toString(4);
            response.getWriter().print(jsonString);
            
		} else if (ajax && xmlString != null) {
				
//			response.setContentType("application/json");
//			String temp = xmlString.replace("<", "&lt;");
//			temp = temp.replace(">", "&gt;");
//			response.getWriter().print(temp);
//			response.getWriter().flush();
			
			response.setContentType("application/json");
			JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
            String jsonString = xmlJSONObj.toString(4);
            response.getWriter().print(jsonString);
            response.getWriter().flush();
			
		} else {
		
			/* Switches data and control to UI.jsp */
			String target = "/Form.jsp";
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
		try {
			context.setAttribute("mSIS", SIS.getInstance());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Instantiate SIS object
	}
}