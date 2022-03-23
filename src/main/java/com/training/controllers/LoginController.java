package com.training.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.DAO.Credentials;
import com.training.DAO.ReimbursementDAO;
import com.training.DAO.ReimbursementDAOImpl;
import com.training.DAO.User;

/**
 * Servlet implementation class LoginControler
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		ReimbursementDAO db = new ReimbursementDAOImpl();
		
		HttpSession session = request.getSession();
		User user = db.validate(new Credentials(username, password));
		if (user == null) {
			session.setAttribute("message",  "Username or password not reconised");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.include(request,  response);
		}
		else if (user.isManager()) {
			session.setAttribute("user",  user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("managerhome.jsp");
			dispatcher.include(request,  response);
		}
		else {
			session.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("employeehome.jsp");
			dispatcher.include(request,  response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
