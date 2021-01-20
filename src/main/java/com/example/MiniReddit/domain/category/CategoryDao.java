package com.example.MiniReddit.domain.category;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.example.MiniReddit.config.DataSourceProvider;

public class CategoryDao {
	private final DataSource dataSource;

	public CategoryDao() {
		try {
			this.dataSource = DataSourceProvider.getDataSource();
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Category> findAll() {
		final String query = """
				SELECT 
					id, name, description
				FROM
					category
				""";
		try (
				Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement()
		) {
			ResultSet resultSet = statement.executeQuery(query);
			List<Category> allCategories = new ArrayList<>();

			while (resultSet.next()) {
				Category category = mapRow(resultSet);
				allCategories.add(category);
			}
			return allCategories;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static Category mapRow(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String name = resultSet.getString("name");
		String description = resultSet.getString("description");
		return new Category(id, name, description);
	}
 }
