package com.training.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.DAO.ReimbursementDAO;
import com.training.DAO.ReimbursementDAOImpl;
import com.training.DAO.ReimbursementRequest;

/**
 * Servlet implementation class ByEmployeeController
 */
public class ByEmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ByEmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int userid;
		try {
			userid = Integer.parseInt(request.getParameter("userid"));
		} catch (NumberFormatException e) {
			userid = -1;
		}
		List<ReimbursementRequest> requests;
		if (userid < 0)
			requests = new ArrayList<ReimbursementRequest>();
		else {
			ReimbursementDAO db = new ReimbursementDAOImpl();
			requests = db.requestsByUserid(userid);
		}
		HttpSession session = request.getSession();
		session.setAttribute("requests", requests);
		RequestDispatcher dispatcher = request.getRequestDispatcher("requestlist.jsp");
		dispatcher.include(request,  response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
