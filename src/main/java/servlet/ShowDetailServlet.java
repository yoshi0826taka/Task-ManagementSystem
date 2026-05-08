package servlet;

import dao.TaskDao;
import dao.CommentDao;
import dto.TaskDto;
import dto.Comment;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * タスク詳細表示用サーブレット
 */
@WebServlet("/ShowDetailServlet")
public class ShowDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションチェック
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            request.setAttribute("errorMessage", "セッションが切れました。再度ログインしてください。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        int taskId = Integer.parseInt(request.getParameter("taskId"));
        
        TaskDao taskDao = new TaskDao();
        CommentDao commentDao = new CommentDao();
        
        // タスク情報取得
        TaskDto task = taskDao.findById(taskId);
        if (task == null) {
            request.setAttribute("errorMessage", "タスクが見つかりません。");
            request.getRequestDispatcher("/menu.jsp").forward(request, response);
            return;
        }
        
        // コメント一覧取得
        List<Comment> commentList = commentDao.findAllCommentsForTask(taskId);
        
        // JSPに渡すデータをセット
        request.setAttribute("task", task);
        request.setAttribute("commentList", commentList);
        
        // 詳細画面へフォワード
        request.getRequestDispatcher("/detail.jsp").forward(request, response);
    }
}
