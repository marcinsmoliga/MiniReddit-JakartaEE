package com.example.MiniReddit.client.category;

import java.io.IOException;
import java.util.List;

import com.example.MiniReddit.domain.api.CategoryFullInfo;
import com.example.MiniReddit.domain.api.CategoryService;
import com.example.MiniReddit.domain.api.DiscoveryBasicInfo;
import com.example.MiniReddit.domain.api.DiscoveryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
	private final CategoryService categoryService = new CategoryService();
	private final DiscoveryService discoveryService = new DiscoveryService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));

		CategoryFullInfo category = categoryService.findById(categoryId).orElseThrow();
		request.setAttribute("category", category);

		List<DiscoveryBasicInfo> discoveries = discoveryService.findByCategory(categoryId);
		request.setAttribute("discoveries", discoveries);

		request.getRequestDispatcher("/WEB-INF/views/category.jsp").forward(request, response);
	}
}
