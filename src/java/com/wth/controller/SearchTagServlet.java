package com.wth.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SearchTagServlet")
public class SearchTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchTagServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isStart = request.getParameter("isStart");
		String tagName = request.getParameter("tagName");
		String filterString = (String) request.getParameter("filterString");
		request.setAttribute("filterString", (isStart.equals("true")) ? tagName : filterString + "_" + tagName);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/searchResults.jsp");
		rd.include(request, response);
	}

}
