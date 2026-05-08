package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * タスクエンティティクラス
 */
public class TaskEntity {
    private int taskId;
    private String taskName;
    private int categoryId;
    private LocalDate limitDate; // NULL許可
    private String userId;
    private String statusCode;
    private String memo;
    private LocalDateTime createDatetime;
    private LocalDateTime updateDatetime;
    
    // コンストラクタ
    public TaskEntity(int taskId, String taskName, int categoryId, LocalDate limitDate, String userId, String statusCode, String memo, LocalDateTime createDatetime, LocalDateTime updateDatetime) {
        super();
        this.taskId = taskId;
        this.taskName = taskName;
        this.categoryId = categoryId;
        this.limitDate = limitDate;
        this.userId = userId;
        this.statusCode = statusCode;
        this.memo = memo;
        this.createDatetime = createDatetime;
        this.updateDatetime = updateDatetime;
    }

    // デフォルトコンストラクタ
    public TaskEntity() {
        super();
    }

    //Getter/Setter
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

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public LocalDateTime getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(LocalDateTime createDatetime) {
        this.createDatetime = createDatetime;
    }

    public LocalDateTime getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(LocalDateTime updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

}
