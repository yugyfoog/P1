package com.training.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.DAO.ReimbursementDAO;
import com.training.DAO.ReimbursementDAOImpl;

/**
 * Servlet implementation class ApproveControloer
 */
public class ApproveControloer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveControloer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String values[] = request.getParameterValues("requestid");
		String action = request.getParameter("action");
		ReimbursementDAO db = new ReimbursementDAOImpl();
		if (action.equals("Approve"))
			db.approveRequests(values);
		else if (action.equals("Deny"))
			db.denyRequests(values);
		RequestDispatcher dispatcher = request.getRequestDispatcher("managerhome.jsp");
		dispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
