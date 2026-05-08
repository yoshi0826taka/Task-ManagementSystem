package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * コネクションマネージャクラス
 */
public class ConMng {
		public static final String URL = getRequiredEnv("TASK_DB_URL");
		public static final String USER = getRequiredEnv("TASK_DB_USER");
		public static final String PW = getRequiredEnv("TASK_DB_PASSWORD");
		
		// コネクションを取得するメソッド
		public static Connection getConnection() throws SQLException {
			// ドライバの読み込み
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.err.println("DBドライバの読み込みに失敗しました。確認してください。");
				e.printStackTrace();
			}
			return DriverManager.getConnection(URL, USER, PW);
		}

		private static String getRequiredEnv(String key) {
			String value = System.getenv(key);
			if (value == null || value.trim().isEmpty()) {
				throw new IllegalStateException(key + " is not set.");
			}
			return value;
		}
}
