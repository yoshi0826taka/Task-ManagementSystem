package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.ConMng;
import entity.UserEntity;

/**
 * ログイン処理用サーブレット
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String pass = request.getParameter("password");
        
        // 簡易的なログイン処理（本来はUserDaoに書くべきですが、時間短縮のためここに記述）
        UserEntity loginUser = null;
        String sql = "SELECT user_id, user_name FROM m_user WHERE user_id = ? AND password = ?";
        
        // データベース接続とクエリ実行
        try (Connection conn = ConMng.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, pass); // ※実務ではハッシュ化必須ですが今回は平文
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    loginUser = new UserEntity();
                    loginUser.setUserId(rs.getString("user_id"));
                    loginUser.setUserName(rs.getString("user_name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "システムエラーが発生しました。" + e.getClass().getSimpleName() + ": " + e.getMessage());
            request.setAttribute("debugMessage", e.toString());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // ログイン成功か失敗かで処理を分岐
        if (loginUser != null) {
            // ログイン成功：セッションに保存
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
            
            // ★【Q&A No.20 要件回収】セッションの有効期限を10分(600秒)に設定★
            session.setMaxInactiveInterval(60 * 10); 
            
            // メニュー画面のServletへリダイレクト
            response.sendRedirect("MenuServlet");
        } else {
            // ログイン失敗
            request.setAttribute("errorMessage", "ユーザIDまたはパスワードが間違っています。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
