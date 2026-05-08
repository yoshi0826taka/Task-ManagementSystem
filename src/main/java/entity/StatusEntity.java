package entity;

import java.time.LocalDateTime;

public class StatusEntity {
    private int categoryId;
    private String categoryName;
    private LocalDateTime updateAt;
    
    // コンストラクタ
    public StatusEntity(int categoryId, String categoryName, LocalDateTime updateAt) {
        super();
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.updateAt = updateAt;
    }

    // デフォルトコンストラクタ
    public StatusEntity() { 
        super();
    }

    // Getter/Setter
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

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

}
