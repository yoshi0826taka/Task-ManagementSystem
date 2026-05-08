package dto;
import java.time.LocalDate;

/**
 * タスクDTOクラス
 */
public class TaskDto {

    private int taskId;
    private String taskName;
    private int categoryId;
    private String categoryName;
    private LocalDate limitDate;
    private String userId;
    private String userName;
    private String statusCode;
    private String statusName;
    private String memo;
    
    // コンストラクタ
    public TaskDto(int taskId, String taskName, int categoryId, String categoryName, LocalDate limitDate, String userId, String userName, String statusCode, String statusName, String memo) {
        super();
        this.taskId = taskId;
        this.taskName = taskName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.limitDate = limitDate;
        this.userId = userId;
        this.userName = userName;
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.memo = memo;
    }

    // デフォルトコンストラクタ
    public TaskDto() {
        super();
    }

    // Getter/Setter
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getCategoryId() {

        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public LocalDate getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
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

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getMemo() {
        return memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }

    
}
