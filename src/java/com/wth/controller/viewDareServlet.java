package com.wth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wth.model.Dare;
import com.wth.model.DareDAO;
import com.wth.model.MySQLDareDAO;

/**
 * Servlet implementation class viewDareServlet
 */
@WebServlet("/viewDareServlet")
public class viewDareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewDareServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		DareDAO dareDAO = new MySQLDareDAO();
		Dare d = dareDAO.getDare((String) session.getAttribute("dare_id"));
		
		dareDAO.completeDare(d);
		response.sendRedirect("viewDare.jsp");
	}

}
