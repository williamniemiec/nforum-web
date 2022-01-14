package wniemiec.web.nforum.core;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Responsible for loading views.
 */
public class ViewLoader {

	//-------------------------------------------------------------------------
	//		Constructor
	//-------------------------------------------------------------------------
	private ViewLoader() {
	}

	
	//-------------------------------------------------------------------------
	//		Methods
	//-------------------------------------------------------------------------
	/**
	 * Loads a JSP page.
	 * 
	 * @param		request HTTP request
	 * @param		response HTTP response
	 * @param		viewName Page filename
	 * 
	 * @throws		ServletException if the target resource throws this exception
	 * @throws		IOException if the target resource throws this exception
	 */
	public static void loadView(HttpServletRequest request, 
			HttpServletResponse response, 
			String viewName)
	throws ServletException, IOException {
		loadView(request, response, viewName, viewName);
	}

	/**
	 * Loads a JSP page.
	 * 
	 * @param		request HTTP request
	 * @param		response HTTP response
	 * @param		viewName Page filename
	 * @param		title Page title
	 * 
	 * @throws		ServletException if the target resource throws this exception
	 * @throws		IOException if the target resource throws this exception
	 */
	public static void loadView(HttpServletRequest request, 
								HttpServletResponse response,
								String viewName,
								String title) 
	throws ServletException, IOException {
		request.setAttribute("title", title);
		request.setAttribute("viewName", viewName);
		request
			.getRequestDispatcher("/WEB-INF/template/index.jsp")
			.forward(request, response);
	}
}
