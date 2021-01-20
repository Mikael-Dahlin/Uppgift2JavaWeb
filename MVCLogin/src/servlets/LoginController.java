package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Check if there is a bean in session.
		if (request.getSession().getAttribute("user")!=null) {
			UserBean bean = (UserBean) request.getSession().getAttribute("user");
			Cookie cookies[] = request.getCookies();
			Boolean hasCookie = false;
			// Check if there is a cookie that matches the user.
			for(Cookie cookie: cookies) {
				if(cookie.getValue().equals(bean.getName())) {
					cookie.setMaxAge(150);
					response.addCookie(cookie);
					hasCookie = true;
				}
			}
			
			// Validate username and password
			if(bean.validate() && hasCookie) {
				if (request.getParameter("snack") != null && request.getParameter("snack") != "") {
					bean.setFavoriteSnack(request.getParameter("snack"));
				}
				response.sendRedirect("success.jsp");
			} else {
				response.sendRedirect("Logout");
			}
		} else {
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        
        // Set values of the user bean for this session.
        UserBean bean=new UserBean();
        bean.setName(username);
        bean.setPassword(password);
        session.setAttribute("user",bean);
        
        // Check if the username and passowrd is correct.
        if(bean.validate()){
        	Cookie cookie = new Cookie("user-token", bean.getName());
        	cookie.setMaxAge(150);
        	response.addCookie(cookie);
        	response.sendRedirect("success.jsp");
        }
        else{
        	response.sendRedirect("error.jsp");
        }
	}

}
