package com.example.MiniReddit.client.authentication;

import java.io.IOException;

import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
@ServletSecurity(httpMethodConstraints = {
		@HttpMethodConstraint(value = "GET", rolesAllowed = "USER")
}
)
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath());
	}
}
