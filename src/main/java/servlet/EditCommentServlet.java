package servlet;

import dao.CommentDao;
import entity.UserEntity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * コメント編集用サーブレット
 */
@WebServlet("/EditCommentServlet")
public class EditCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // セッションチェック
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            request.setAttribute("errorMessage", "セッションが切れました。再度ログインしてください。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        try {
            int commentId = Integer.parseInt(request.getParameter("commentId"));
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            String newComment = request.getParameter("newComment");
            
            // ログインユーザーIDを取得
            UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
            String userId = loginUser.getUserId();
            
            // DB更新
            CommentDao commentDao = new CommentDao();
            int result = commentDao.update(commentId, userId, newComment);
            
            if (result > 0) {
                request.setAttribute("successMessage", "コメントを更新しました。");
            } else {
                request.setAttribute("errorMessage", "コメント更新に失敗しました。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "エラーが発生しました: " + e.getMessage());
        }
        
        // 詳細画面へリダイレクト
        String taskId = request.getParameter("taskId");
        response.sendRedirect("ShowDetailServlet?taskId=" + taskId);
    }
}
