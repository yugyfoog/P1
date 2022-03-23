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
import com.training.DAO.ReimbursementRequest;
import com.training.DAO.Status;
import com.training.DAO.User;

/**
 * Servlet implementation class SubmitController
 */
public class SubmitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitController() { }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String catagory = request.getParameter("catagory");
		String details = request.getParameter("details"); // ?????
		ReimbursementDAO db = new ReimbursementDAOImpl();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		db.submitRequest(new ReimbursementRequest(
					0, // just use a dummy value here
					user.getUserId(),
					amount,
					catagory,
					details,
					Status.SUBMITED));
		
		session.setAttribute("message", "Reimbursement Request Submitted");
		RequestDispatcher dispatcher = request.getRequestDispatcher("employeehome.jsp");
		dispatcher.include(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
