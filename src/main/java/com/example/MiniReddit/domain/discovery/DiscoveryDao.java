package com.example.MiniReddit.domain.discovery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.example.MiniReddit.config.DataSourceProvider;
import com.example.MiniReddit.domain.common.BaseDao;

public class DiscoveryDao extends BaseDao {

	public List<Discovery> findAll() {
		final String query = """
				SELECT
					id, title, url, description, date_added, category_id
				FROM
					discovery d
				""";
		try (Connection connection = getConnection();
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

	public List<Discovery> findByCategory(int categoryId) {
		final String query = """
				SELECT
					id, title, url, description, date_added, category_id
				FROM
					discovery
				WHERE
					category_id = ?
				""";

		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query)
		) {
			statement.setInt(1, categoryId);
			ResultSet resultSet = statement.executeQuery();

			List<Discovery> discoveries = new ArrayList<>();
			while(resultSet.next()) {
				Discovery discovery = mapRow(resultSet);
				discoveries.add(discovery);
			}
			return discoveries;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private static Discovery mapRow(ResultSet resultSett) throws SQLException {
		int discoveryId = resultSett.getInt("id");
		String title = resultSett.getString("title");
		String url = resultSett.getString("url");
		String description = resultSett.getString("description");
		LocalDateTime dateAdded = resultSett.getTimestamp("date_added").toLocalDateTime();
		int categoryId = resultSett.getInt("category_id");
		return new Discovery(discoveryId, title, url,description, dateAdded, categoryId);
	}
}
