package servlet;

import dao.TaskDao;
import entity.*;
import dto.*;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションチェック（未ログイン、または10分放置でタイムアウトした場合は弾く）
        HttpSession session = request.getSession(false);
        
        // ★【Q&A No.20 要件回収】セッションの有効期限を10分(600秒)に設定★
        if (session == null || session.getAttribute("loginUser") == null) {
            request.setAttribute("errorMessage", "セッションが切れました。再度ログインしてください。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        TaskDao taskDao = new TaskDao();
        
        // 全タスクを取得
        List<TaskDto> taskList = taskDao.findAll();
        // プルダウン用のカテゴリを取得
        List<CategoryEntity> categoryList = taskDao.findAllCategory();
        
        // JSPに渡すデータをセット
        request.setAttribute("taskList", taskList);
        request.setAttribute("categoryList", categoryList);
        
        // メニュー画面へフォワード
        request.getRequestDispatcher("/menu.jsp").forward(request, response);
    }
}
