package servlet;

import dao.TaskDao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * タスク削除用サーブレット
 */
@WebServlet("/DeleteTaskServlet")
public class DeleteTaskServlet extends HttpServlet {
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
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            
            // DB削除
            TaskDao taskDao = new TaskDao();
            int result = taskDao.delete(taskId);
            
            if (result > 0) {
                request.setAttribute("successMessage", "タスクを削除しました。");
            } else {
                request.setAttribute("errorMessage", "タスク削除に失敗しました。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "エラーが発生しました: " + e.getMessage());
        }
        
        // メニュー画面へリダイレクト
        response.sendRedirect("MenuServlet");
    }
}
