package com.example.MiniReddit.domain.api;

import java.util.List;
import java.util.stream.Collectors;

import com.example.MiniReddit.domain.category.Category;
import com.example.MiniReddit.domain.category.CategoryDao;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();

	public List<CategoryName> findAllCategoryNames() {
		return categoryDao.findAll()
				.stream()
				.map(CategoryNameMapper::map)
				.collect(Collectors.toList());
	}

	private static class CategoryNameMapper {
		static CategoryName map(Category category) {
			return new CategoryName(
					category.getId(),
					category.getName());
		}
	}
}
