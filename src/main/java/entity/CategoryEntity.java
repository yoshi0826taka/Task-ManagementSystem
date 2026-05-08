package entity;

import java.time.LocalDateTime;

/**
 * カテゴリーエンティティクラス
 */
public class CategoryEntity {
    private int categoryId;
    private String categoryName;
    private LocalDateTime updateAt;
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
