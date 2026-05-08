CREATE DATABASE IF NOT EXISTS task_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE task_db;

DROP TABLE IF EXISTS t_comment;
DROP TABLE IF EXISTS t_task;
DROP TABLE IF EXISTS m_status;
DROP TABLE IF EXISTS m_category;
DROP TABLE IF EXISTS m_user;

CREATE TABLE m_user (
    user_id     VARCHAR(20) PRIMARY KEY,
    password    VARCHAR(40) NOT NULL,
    user_name   VARCHAR(20) NOT NULL UNIQUE,
    update_at   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE m_category (
    category_id     INT AUTO_INCREMENT PRIMARY KEY,
    category_name   VARCHAR(20) NOT NULL UNIQUE,
    update_at       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE m_status (
    status_code     CHAR(2) PRIMARY KEY,
    status_name     VARCHAR(20) NOT NULL UNIQUE,
    update_at       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE t_task (
    task_id         INT AUTO_INCREMENT PRIMARY KEY,
    task_name       VARCHAR(50) NOT NULL,
    category_id     INT NOT NULL,
    limit_date      DATE,
    user_id         VARCHAR(20) NOT NULL,
    status_code     CHAR(2) NOT NULL,
    memo            VARCHAR(100),
    create_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES m_category(category_id),
    FOREIGN KEY (user_id) REFERENCES m_user(user_id),
    FOREIGN KEY (status_code) REFERENCES m_status(status_code)
);

CREATE TABLE t_comment (
    comment_id      INT AUTO_INCREMENT PRIMARY KEY,
    task_id         INT NOT NULL,
    user_id         VARCHAR(20) NOT NULL,
    comment         VARCHAR(100) NOT NULL,
    update_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (task_id) REFERENCES t_task(task_id),
    FOREIGN KEY (user_id) REFERENCES m_user(user_id)
);

INSERT INTO m_category(category_name) VALUES('新商品A開発プロジェクト');
INSERT INTO m_category(category_name) VALUES('既存商品B改良プロジェクト');

INSERT INTO m_status(status_code, status_name) VALUES('00', '未着手');
INSERT INTO m_status(status_code, status_name) VALUES('50', '着手');
INSERT INTO m_status(status_code, status_name) VALUES('99', '完了');

INSERT INTO m_user(user_id, password, user_name) VALUES('AB123456', 'abc123', '山田 太郎');
INSERT INTO m_user(user_id, password, user_name) VALUES('CD987654', 'def456', '開発 花子');
INSERT INTO m_user(user_id, password, user_name) VALUES('XY112233', 'ghi789', '確認 次郎');

INSERT INTO t_task(task_name, category_id, limit_date, user_id, status_code, memo)
VALUES('Aプロジェクト要件定義書作成', 1, '2026-12-31', 'AB123456', '50', 'フォーマットは社内標準を使用すること');

INSERT INTO t_task(task_name, category_id, limit_date, user_id, status_code, memo)
VALUES('Bプロジェクトモックアップ作成', 2, '2026-11-30', 'CD987654', '00', 'UI設計を先行して進める');

INSERT INTO t_comment(task_id, user_id, comment) VALUES(1, 'CD987654', '要件定義書のレビューは水曜で大丈夫でしょうか？');
INSERT INTO t_comment(task_id, user_id, comment) VALUES(1, 'AB123456', 'はい、水曜の14時にお願いします！');
INSERT INTO t_comment(task_id, user_id, comment) VALUES(2, 'CD987654', '【ヘルプ】デザインの承認が下りず、手が止まっています。');
