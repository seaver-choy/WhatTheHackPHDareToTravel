package com.wth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wth.model.Account;
import com.wth.model.AccountDAO;
import com.wth.model.MySQLAccountDAO;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usernameEntered = request.getParameter("username");
		String passwordEntered = request.getParameter("password");
		AccountDAO acctDAO = new MySQLAccountDAO();
		Account acct = acctDAO.getAccount(usernameEntered);
		if (acct != null && acct.getPassword().equals(passwordEntered)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("username", acct.getUsername());
			response.sendRedirect("index.jsp");
		} else {
			//TODO -proper responce when wrong username or password
			response.sendRedirect("index.jsp");
		}
	}

}
