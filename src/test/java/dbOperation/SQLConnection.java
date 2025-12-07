package dbOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utility.ConfigUtil;

public class SQLConnection {
	private static ThreadLocal<Connection> connectionHolder = ThreadLocal.withInitial(() -> {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(ConfigUtil.getProperty("databaseConnectionString"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	});

	public static Connection getConnection() {
		return connectionHolder.get();
	}

	public static void setConnection(Connection connection) {
		connectionHolder.set(connection);
	}

	public static void removeConnection() {
		Connection conn = connectionHolder.get();
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		connectionHolder.remove();
	}
}
