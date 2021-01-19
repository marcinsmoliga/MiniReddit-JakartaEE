package com.example.MiniReddit.domain.discovery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.example.MiniReddit.config.DataSourceProvider;

public class DiscoveryDao {
	private final DataSource dataSource;

	public DiscoveryDao() {
		try {
			this.dataSource = DataSourceProvider.getDataSource();
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Discovery> findAll() {
		final String query = """
				SELECT
					id, title, url description, date_added, category_id
				FROM
					discovery d
				""";
		try (Connection connection = dataSource.getConnection();
		     Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(query);
			List<Discovery> allDiscoveries = new ArrayList<>();
			while (resultSet.next()) {
				Discovery discovery = mapRow(resultSet);
				allDiscoveries.add(discovery);
			}
			return allDiscoveries;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static Discovery mapRow(ResultSet set) throws SQLException {
		int discoveryId = set.getInt("id");
		String title = set.getString("title");
		String url = set.getString("url");
		String description = set.getString("description");
		LocalDateTime dateAdded = set.getTimestamp("date_added").toLocalDateTime();
		int categoryId = set.getInt("category_id");
		return new Discovery(discoveryId, title, url,description, dateAdded, categoryId);
	}
}
