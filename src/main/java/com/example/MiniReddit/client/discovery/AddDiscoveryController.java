package com.example.MiniReddit.client.discovery;

import java.io.IOException;
import java.util.List;

import com.example.MiniReddit.domain.api.CategoryName;
import com.example.MiniReddit.domain.api.CategoryService;
import com.example.MiniReddit.domain.api.DiscoverySaveRequest;
import com.example.MiniReddit.domain.api.DiscoveryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/discovery/add")
@ServletSecurity(
		httpMethodConstraints = {
				@HttpMethodConstraint(value = "GET", rolesAllowed = "USER"),
				@HttpMethodConstraint(value = "POST", rolesAllowed = "USER")
		}
)
public class AddDiscoveryController extends HttpServlet {
	private CategoryService categoryService = new CategoryService();
	private DiscoveryService discoveryService = new DiscoveryService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CategoryName> categories = categoryService.findAllCategoryNames();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/WEB-INF/views/add-discovery.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiscoverySaveRequest saveRequest = createSaveRequest(request);
		discoveryService.add(saveRequest);
		response.sendRedirect(request.getContextPath());
	}

	private DiscoverySaveRequest createSaveRequest(HttpServletRequest request) {
		String loggedInUsername = request.getUserPrincipal().getName();
		return new DiscoverySaveRequest(
				request.getParameter("title"),
				request.getParameter("url"),
				request.getParameter("description"),
				Integer.valueOf(request.getParameter("categoryId")),
				loggedInUsername
		);
	}
}
