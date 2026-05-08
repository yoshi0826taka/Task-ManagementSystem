package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.ConMng;
import dto.TaskDto;
import entity.CategoryEntity;

/**
 * タスクDaoクラス
 */
public class TaskDao {

    // 全タスク取得（一覧表示用）
    public List<TaskDto> findAll() {
        List<TaskDto> list = new ArrayList<>();
        String sql = "SELECT t.task_id, t.task_name, t.category_id, c.category_name, " +
                     "t.limit_date, t.user_id, u.user_name, t.status_code, s.status_name, t.memo " +
                     "FROM t_task t " +
                     "LEFT JOIN m_category c ON t.category_id = c.category_id " +
                     "LEFT JOIN m_user u ON t.user_id = u.user_id " +
                     "LEFT JOIN m_status s ON t.status_code = s.status_code " +
                     "ORDER BY t.limit_date ASC";

        // 作成したコネクションマネージャを呼び出す
        try (Connection conn = ConMng.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                TaskDto dto = new TaskDto();
                dto.setTaskId(rs.getInt("task_id"));
                dto.setTaskName(rs.getString("task_name"));
                dto.setCategoryId(rs.getInt("category_id"));
                dto.setCategoryName(rs.getString("category_name"));
                
                Date sqlDate = rs.getDate("limit_date");
                if (sqlDate != null) dto.setLimitDate(sqlDate.toLocalDate());
                
                dto.setUserId(rs.getString("user_id"));
                dto.setUserName(rs.getString("user_name"));
                dto.setStatusCode(rs.getString("status_code"));
                dto.setStatusName(rs.getString("status_name"));
                dto.setMemo(rs.getString("memo"));
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DBエラー: findAll", e);
        }
        return list;
    }

    // タスク登録
    public int insert(TaskDto dto) {
        String sql = "INSERT INTO t_task (task_name, category_id, limit_date, user_id, status_code, memo) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConMng.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, dto.getTaskName());
            pstmt.setInt(2, dto.getCategoryId());
            
            if (dto.getLimitDate() != null) pstmt.setDate(3, Date.valueOf(dto.getLimitDate()));
            else pstmt.setNull(3, Types.DATE);
            
            pstmt.setString(4, dto.getUserId());
            pstmt.setString(5, dto.getStatusCode() != null ? dto.getStatusCode() : "00");
            
            if (dto.getMemo() != null && !dto.getMemo().isEmpty()) pstmt.setString(6, dto.getMemo());
            else pstmt.setNull(6, Types.NVARCHAR);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DBエラー: insert", e);
        }
    }   

    // タスク更新
    public int update(TaskDto dto) {
        String sql = "UPDATE t_task SET task_name = ?, category_id = ?, limit_date = ?, " +
                     "status_code = ?, memo = ?, update_datetime = NOW() WHERE task_id = ?";
        try (Connection conn = ConMng.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, dto.getTaskName());
            pstmt.setInt(2, dto.getCategoryId());
            if (dto.getLimitDate() != null) pstmt.setDate(3, Date.valueOf(dto.getLimitDate()));
            else pstmt.setNull(3, Types.DATE);
            pstmt.setString(4, dto.getStatusCode());
            if (dto.getMemo() != null && !dto.getMemo().isEmpty()) pstmt.setString(5, dto.getMemo());
            else pstmt.setNull(5, Types.NVARCHAR);
            pstmt.setInt(6, dto.getTaskId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DBエラー: update", e);
        }
    }

    // タスク削除
    public int delete(int taskId) {
        String sqlDeleteComments = "DELETE FROM t_comment WHERE task_id = ?";
        String sqlDeleteTask = "DELETE FROM t_task WHERE task_id = ?";
        int result = 0;
        try (Connection conn = ConMng.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // コメント削除
                try (PreparedStatement pstmt1 = conn.prepareStatement(sqlDeleteComments)) {
                    pstmt1.setInt(1, taskId);
                    pstmt1.executeUpdate();
                }
                // タスク削除
                try (PreparedStatement pstmt2 = conn.prepareStatement(sqlDeleteTask)) {
                    pstmt2.setInt(1, taskId);
                    result = pstmt2.executeUpdate();
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DBエラー: delete", e);
        }
        return result;
    }

    // 単一タスク取得（編集画面表示用）
    public TaskDto findById(int taskId) {
        String sql = "SELECT t.task_id, t.task_name, t.category_id, c.category_name, " +
                     "t.limit_date, t.user_id, u.user_name, t.status_code, s.status_name, t.memo " +
                     "FROM t_task t " +
                     "LEFT JOIN m_category c ON t.category_id = c.category_id " +
                     "LEFT JOIN m_user u ON t.user_id = u.user_id " +
                     "LEFT JOIN m_status s ON t.status_code = s.status_code " +
                     "WHERE t.task_id = ?";
        try (Connection conn = ConMng.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, taskId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    TaskDto dto = new TaskDto();
                    dto.setTaskId(rs.getInt("task_id"));
                    dto.setTaskName(rs.getString("task_name"));
                    dto.setCategoryId(rs.getInt("category_id"));
                    dto.setCategoryName(rs.getString("category_name"));
                    Date sqlDate = rs.getDate("limit_date");
                    if (sqlDate != null) dto.setLimitDate(sqlDate.toLocalDate());
                    dto.setUserId(rs.getString("user_id"));
                    dto.setUserName(rs.getString("user_name"));
                    dto.setStatusCode(rs.getString("status_code"));
                    dto.setStatusName(rs.getString("status_name"));
                    dto.setMemo(rs.getString("memo"));
                    return dto;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DBエラー: findById", e);
        }
        return null;
    }
    
    // プルダウン用カテゴリ全取得
    public List<CategoryEntity> findAllCategory() {
        List<CategoryEntity> list = new ArrayList<>();
        String sql = "SELECT category_id, category_name FROM m_category ORDER BY category_id";
        try (Connection conn = ConMng.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                CategoryEntity entity = new CategoryEntity();
                entity.setCategoryId(rs.getInt("category_id"));
                entity.setCategoryName(rs.getString("category_name"));
                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DBエラー: findAllCategory", e);
        }
        return list;
    }
}