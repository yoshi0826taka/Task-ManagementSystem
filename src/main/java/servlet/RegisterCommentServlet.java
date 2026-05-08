package servlet;

import dao.CommentDao;
import dto.Comment;
import entity.UserEntity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RegisterCommentServlet")
public class RegisterCommentServlet extends HttpServlet {
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
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            String commentText = request.getParameter("commentText");
            
            // ログインユーザーIDを取得
            UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
            String userId = loginUser.getUserId();
            
            // Commentを作成
            Comment comment = new Comment();
            comment.setTaskId(taskId);
            comment.setUserId(userId);
            comment.setComment(commentText);
            
            // DB登録
            CommentDao commentDao = new CommentDao();
            int result = commentDao.insert(comment);
            
            if (result > 0) {
                request.setAttribute("successMessage", "コメントを投稿しました。");
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
