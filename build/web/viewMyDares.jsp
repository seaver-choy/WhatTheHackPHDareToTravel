<%-- 
    Document   : viewMyDares
    Created on : 04 12, 15, 1:15:14 PM
    Author     : CHOY
--%>

<%@page import="com.wth.model.Dare"%>
<%@page import="com.wth.model.MySQLAccountDAO"%>
<%@page import="com.wth.model.Account"%>
<%@page import="com.wth.model.AccountDAO"%>
<%@page import="com.wth.model.MySQLDareDAO"%>
<%@page import="com.wth.model.DareDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
		String filterString = (String)request.getAttribute("filterString");
		String filters[] = filterString.split("_");
		DareDAO dareDAO = new MySQLDareDAO();
                AccountDAO acctDAO = new MySQLAccountDAO();
                Account acct = acctDAO.getAccount((String) request.getAttribute("username"));
		Dare[] dares = dareDAO.searchByAccount(acct);
	%>
        <div class="container-fluid">    
            <div class="row">
			<div class="col-md-12">
				<ul class="list-group">
					<!--  <li class="list-group-item">
						<h3>The Seaman Challenge</h3>
						<p> Challenges for people who love the sea </p>
					</li>
					<li class="list-group-item">
						<h3>Adrenaline Rush Chllenge</h3>
						<p>For all the adrenaline lovers out there </p>
					</li>
					<li class="list-group-item">
						<h3>Restaurant Challenge</h3>
						<p>Taste them all</p>
					</li> -->
					<% for (Dare x : dares) { %>
						<li class="list-group-item"> 
							<a href="viewDare.jsp?id=<%=x.getDareID()%>">
								<h3><%= x.getDareName() %></h3>
								<p><%= x.getDescription() %></p>
							</a>
						</li>
					<% } %>
				</ul>
			</div>
		</div>
        </div>
    </body>
</html>
