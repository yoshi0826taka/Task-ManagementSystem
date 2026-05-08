package servlet;

import dao.TaskDao;
import entity.CategoryEntity;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ShowRegisterServlet")
public class ShowRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションチェック
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            request.setAttribute("errorMessage", "セッションが切れました。再度ログインしてください。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        TaskDao taskDao = new TaskDao();
        // プルダウン用のカテゴリを取得
        List<CategoryEntity> categoryList = taskDao.findAllCategory();
        
        // JSPに渡すデータをセット
        request.setAttribute("categoryList", categoryList);
        
        // 登録画面へフォワード
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
