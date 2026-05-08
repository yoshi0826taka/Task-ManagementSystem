package servlet;

import dao.TaskDao;
import dto.TaskDto;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EditTaskServlet")
public class EditTaskServlet extends HttpServlet {
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
            // フォームから値を取得
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            String taskName = request.getParameter("taskName");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String limitDateStr = request.getParameter("limitDate");
            String statusCode = request.getParameter("statusCode");
            String memo = request.getParameter("memo");
            
            // TaskDtoを作成
            TaskDto taskDto = new TaskDto();
            taskDto.setTaskId(taskId);
            taskDto.setTaskName(taskName);
            taskDto.setCategoryId(categoryId);
            if (limitDateStr != null && !limitDateStr.isEmpty()) {
                taskDto.setLimitDate(LocalDate.parse(limitDateStr));
            }
            taskDto.setStatusCode(statusCode);
            taskDto.setMemo(memo);
            
            // DB更新
            TaskDao taskDao = new TaskDao();
            int result = taskDao.update(taskDto);
            
            if (result > 0) {
                request.setAttribute("successMessage", "タスクを更新しました。");
            } else {
                request.setAttribute("errorMessage", "タスク更新に失敗しました。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "エラーが発生しました: " + e.getMessage());
        }
        
        // メニュー画面へリダイレクト
        response.sendRedirect("MenuServlet");
    }
}
