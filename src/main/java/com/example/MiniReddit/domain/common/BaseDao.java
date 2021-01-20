package com.example.MiniReddit.domain.common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.example.MiniReddit.config.DataSourceProvider;

public class BaseDao {
	private final DataSource dataSource;

	public BaseDao() {
		try {
			this.dataSource = DataSourceProvider.getDataSource();
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
