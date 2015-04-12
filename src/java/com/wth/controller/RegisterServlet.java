package com.wth.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wth.exceptions.IncompleteFieldException;
import com.wth.exceptions.InvalidPasswordConfirmationException;
import com.wth.model.Account;
import com.wth.model.AccountDAO;
import com.wth.model.MySQLAccountDAO;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirm-password");
			String firstName = request.getParameter("first-name");
			String lastName = request.getParameter("last-name");
			String email = request.getParameter("email");
			
			if (username == null || password == null ||
					confirmPassword == null || firstName == null ||
					lastName == null || email == null) {
				throw new IncompleteFieldException();
			}
			
			if (!password.equals(confirmPassword)) {
				throw new InvalidPasswordConfirmationException();
			}
			Account acct = new Account();
			acct.setUsername(username);
			acct.setPassword(password);
			acct.setFirstName(firstName);
			acct.setLastName(lastName);
			acct.setEmail(email);
			AccountDAO acctDAO = new MySQLAccountDAO();
			acctDAO.createUserAccount(acct);
			response.sendRedirect("index.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IncompleteFieldException e) {
			// TODO - response if missing field
			e.printStackTrace();
		} catch (InvalidPasswordConfirmationException e) {
			// TODO - response if wrong password confirmation
			e.printStackTrace();
		}
		
		response.sendRedirect("index.jsp");
		
	}

}
