package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.ConMng;
import dto.Comment;
/**
 * コメントDaoクラス
 */
public class CommentDao {

    // 特定タスクのコメント一覧取得
    public List<Comment> findAllCommentsForTask(int taskId) {
        List<Comment> list = new ArrayList<>();
        String sql = "SELECT c.comment_id, c.task_id, c.user_id, u.user_name, c.comment " +
                     "FROM t_comment c INNER JOIN m_user u ON c.user_id = u.user_id " +
                     "WHERE c.task_id = ? ORDER BY c.update_datetime ASC";

        try (Connection conn = ConMng.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, taskId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Comment dto = new Comment();
                    dto.setCommentId(rs.getInt("comment_id"));
                    dto.setTaskId(rs.getInt("task_id"));
                    dto.setUserId(rs.getString("user_id"));
                    dto.setUserName(rs.getString("user_name"));
                    dto.setComment(rs.getString("comment"));
                    list.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DBエラー: findAllCommentsForTask", e);
        }
        return list;
    }

    // コメント投稿
    public int insert(Comment dto) {
        String sql = "INSERT INTO t_comment (task_id, user_id, comment) VALUES (?, ?, ?)";
        try (Connection conn = ConMng.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dto.getTaskId());
            pstmt.setString(2, dto.getUserId());
            pstmt.setString(3, dto.getComment());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DBエラー: insert comment", e);
        }
    }

    // コメント削除（投稿者本人のみ）
    public int delete(int commentId, String userId) {
        String sql = "DELETE FROM t_comment WHERE comment_id = ? AND user_id = ?";
        try (Connection conn = ConMng.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, commentId);
            pstmt.setString(2, userId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DBエラー: delete comment", e);
        }
    }

    // コメント更新（投稿者本人のみ）
    public int update(int commentId, String userId, String comment) {
        String sql = "UPDATE t_comment SET comment = ?, update_datetime = NOW() WHERE comment_id = ? AND user_id = ?";
        try (Connection conn = ConMng.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, comment);
            pstmt.setInt(2, commentId);
            pstmt.setString(3, userId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DBエラー: update comment", e);
        }
    }
}

