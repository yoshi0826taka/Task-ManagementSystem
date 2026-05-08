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

@WebServlet("/DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            
            // ログインユーザーIDを取得
            UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
            String userId = loginUser.getUserId();
            
            // DB削除
            CommentDao commentDao = new CommentDao();
            int result = commentDao.delete(commentId, userId);
            
            if (result > 0) {
                request.setAttribute("successMessage", "コメントを削除しました。");
            } else {
                request.setAttribute("errorMessage", "コメント削除に失敗しました。");
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
