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
 * Servlet implementation class EditControler
 */
public class EditControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// update firstname and lastname in db
		response.setContentType("Text/html");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		ReimbursementDAO db = new ReimbursementDAOImpl();
		db.updateUser(user);
		session.setAttribute("message",  "User Information Updated");
		RequestDispatcher dispatcher;
		if (user.isManager())
			dispatcher = request.getRequestDispatcher("managerhome.jsp");
		else
			dispatcher = request.getRequestDispatcher("employeehome.jsp");
		dispatcher.include(request,  response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
