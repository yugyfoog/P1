package com.training.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.DAO.ReimbursementDAO;
import com.training.DAO.ReimbursementDAOImpl;
import com.training.DAO.User;

/**
 * Servlet implementation class PasswordChangeController
 */
public class PasswordChangeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordChangeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// update password in db
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String password = request.getParameter("password");
		user.setPassword(password);
		ReimbursementDAO db = new ReimbursementDAOImpl();
		db.updatePassword(user);
		session.setAttribute("message",  "Password Updated");
		RequestDispatcher dispatcher;
		if (user.isManager())
			dispatcher = request.getRequestDispatcher("managerhome.jsp");
		else
			dispatcher = request.getRequestDispatcher("employeehome.jsp");
		dispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
