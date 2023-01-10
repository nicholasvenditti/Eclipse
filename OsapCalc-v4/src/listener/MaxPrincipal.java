package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class MaxPrincipal
 *
 */
@WebListener
public class MaxPrincipal implements HttpSessionAttributeListener {

	double maxPrincipal = 0;
	
    /**
     * Default constructor. 
     */
    public MaxPrincipal() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
    	handleEvent(arg0);
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
    	handleEvent(arg0);
    }
    
    void handleEvent(HttpSessionBindingEvent event) {
    	
    	if (event.getName().equals("principal")) {
    		
    		double tempPrincipal = Double.parseDouble(event.getSession().getAttribute("principal").toString());
    		
    		if (tempPrincipal > maxPrincipal) {
    			
    			maxPrincipal = tempPrincipal;
    			event.getSession().setAttribute("maxPrincipal", maxPrincipal);
    		}
    	}
    }
}
