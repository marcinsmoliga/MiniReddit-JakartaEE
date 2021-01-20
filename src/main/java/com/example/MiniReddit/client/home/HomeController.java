package com.example.MiniReddit.client.home;

import java.io.IOException;
import java.util.List;

import com.example.MiniReddit.domain.api.CategoryName;
import com.example.MiniReddit.domain.api.CategoryService;
import com.example.MiniReddit.domain.api.DiscoveryBasicInfo;
import com.example.MiniReddit.domain.api.DiscoveryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("")
public class HomeController extends HttpServlet {
	private DiscoveryService discoveryService = new DiscoveryService();
	private CategoryService categoryService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DiscoveryBasicInfo> discoveries = discoveryService.findAll();
		request.setAttribute("discoveries", discoveries);

		List<CategoryName> categories = categoryService.findAllCategoryNames();
		request.setAttribute("categories", categories);

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
