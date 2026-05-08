package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * コネクションマネージャクラス
 */
public class ConMng {
		public static final String URL = "jdbc:mysql://localhost:3306/task_db?serverTimezone=UTC";
		public static final String USER = "root";
		public static final String PW = "mysqldb";
		
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
}
