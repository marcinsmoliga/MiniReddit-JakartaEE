package com.example.MiniReddit.client.signup;

import java.io.IOException;

import com.example.MiniReddit.domain.api.UserRegistration;
import com.example.MiniReddit.domain.api.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignupController extends HttpServlet {
	private final UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserRegistration userRegistration = getUserData(request);
		userService.register(userRegistration);
		response.sendRedirect(request.getContextPath());
	}

	private UserRegistration getUserData(HttpServletRequest request) {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		return new UserRegistration(username, email, password);
	}
}
