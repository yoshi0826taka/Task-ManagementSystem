package servlet;

import dao.TaskDao;
import dto.TaskDto;
import entity.CategoryEntity;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * タスク編集用サーブレット
 */
@WebServlet("/ShowEditServlet")
public class ShowEditServlet extends HttpServlet {
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
        
        // タスク情報取得
        TaskDto task = taskDao.findById(taskId);
        if (task == null) {
            request.setAttribute("errorMessage", "タスクが見つかりません。");
            request.getRequestDispatcher("/menu.jsp").forward(request, response);
            return;
        }
        
        // プルダウン用のカテゴリを取得
        List<CategoryEntity> categoryList = taskDao.findAllCategory();
        
        // JSPに渡すデータをセット
        request.setAttribute("task", task);
        request.setAttribute("categoryList", categoryList);
        
        // 編集画面へフォワード
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }
}
