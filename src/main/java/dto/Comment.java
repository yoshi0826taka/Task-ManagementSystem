package dto;

/**
 * コメントDTOクラス
 */
public class Comment {
    private int commentId;
    private int taskId;
    private String userId;
    private String userName;
    private String comment;

    // デフォルトコンストラクタ
    public Comment() {
    }

    // コンストラクタ（全項目）
    public Comment(int commentId, int taskId, String userId, String userName, String comment) {
        this.commentId = commentId;
        this.taskId = taskId;
        this.userId = userId;
        this.userName = userName;       
        this.comment = comment;     
    }   

    // Getter/Setter
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
