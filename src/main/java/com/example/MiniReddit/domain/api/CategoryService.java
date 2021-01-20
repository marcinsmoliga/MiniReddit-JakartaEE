package com.example.MiniReddit.domain.api;

import java.util.List;
import java.util.Optional;
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

	public Optional<CategoryFullInfo> findById(int categoryId) {
		return categoryDao.findById(categoryId)
				.map(CategoryFullInfoMapper::map);
	}

	private static class CategoryNameMapper {
		static CategoryName map(Category category) {
			return new CategoryName(
					category.getId(),
					category.getName());
		}
	}

	private static class CategoryFullInfoMapper {
		static CategoryFullInfo map(Category category) {
			return new CategoryFullInfo(
					category.getId(),
					category.getName(),
					category.getDescription()
			);
		}
	}
}
