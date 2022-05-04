package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	public static Connection abrirConexao() {
		if (conn == null) {
			try {
				Properties prop = carregarConfiguracoes();
				String url = prop.getProperty("dburl");
				conn = DriverManager.getConnection(url, prop);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	public static void fecharConexao() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

	}

	public static void fecharStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

	}

	public static void fecharResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

	}

	private static Properties carregarConfiguracoes() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties prop = new Properties();
			prop.load(fs);
			return prop;

		} catch (IOException e) {
			throw new DbException(e.getMessage());

		}

	}

}