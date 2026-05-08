# Task-ManagementSystem

Java Servlet / JSP / MySQLで作成したタスク管理システムです。
プログラミング研修で受講者に説明できる題材として、ログイン、タスク管理、コメント管理、マスタ参照を一通り実装しています。

## 概要

業務系Webアプリの基本構成を学ぶため、Servlet、JSP、DAO、DTO、Entityを分けて実装しています。
フレームワークに頼りすぎず、リクエスト処理、DB接続、画面遷移、フォーム入力、CRUD処理の流れが見える構成にしています。

## 主な機能

- ログイン / ログアウト
- タスク一覧表示
- タスク登録
- タスク詳細表示
- タスク編集
- タスク削除
- コメント登録
- コメント編集
- コメント削除
- カテゴリ、ステータス、ユーザのマスタ参照

## 技術スタック

- Java 8
- Servlet / JSP
- JSTL
- MySQL
- Maven
- Tomcat

## ディレクトリ構成

```text
src/main/java
├── dao       # DBアクセス
├── db        # DB接続管理
├── dto       # 画面表示・入力用データ
├── entity    # DBレコード対応データ
└── servlet   # リクエスト処理

src/main/webapp
├── WEB-INF/web.xml
├── login.jsp
├── menu.jsp
├── register.jsp
├── detail.jsp
└── edit.jsp
```

## セットアップ

### 1. データベース作成

MySQLを起動し、以下のSQLを実行します。

```bash
mysql -u <DB_USER> -p < sql/create_task_db.sql
```

`sql/create_task_db.sql` には動作確認用のサンプルユーザーが含まれます。サンプルパスワードは教材用の平文データであり、本番利用を想定したものではありません。

DB接続情報は環境変数で指定します。公開リポジトリに個人ローカルのユーザー名やパスワードは記載していません。

```text
TASK_DB_URL
TASK_DB_USER
TASK_DB_PASSWORD
```

例:

```bash
export TASK_DB_URL="jdbc:mysql://<HOST>:<PORT>/<DATABASE_NAME>?serverTimezone=UTC"
export TASK_DB_USER="<DB_USER>"
export TASK_DB_PASSWORD="<DB_PASSWORD>"
```

ユーザー名、パスワード、ホスト名などは各自のローカル環境に合わせて設定してください。実際の接続情報はGit管理しません。

### 2. ビルド

```bash
mvn clean package
```

### 3. 実行

```bash
mvn tomcat7:run
```

ブラウザで `http://localhost:8080/` にアクセスします。

## 工夫した点

- Servlet、DAO、DTO、Entityを分け、責務を見ながら学習できる構成にした
- タスクとコメントを分け、業務アプリでよくある親子データの扱いを実装した
- カテゴリ、ステータス、ユーザをマスタとして扱い、参照関係をDBで表現した
- Mavenで依存関係を管理し、ローカルjarや生成物をGit管理から外した

## 今後の改善

- パスワードのハッシュ化
- 入力バリデーションの強化
- SQL例外時のエラーハンドリング改善
- JUnitによるDAO / Servlet周辺のテスト追加
- Bootstrapなどを使った画面デザイン改善

## ビルド確認

```bash
mvn -q -DskipTests package
```

上記コマンドでビルド確認済みです。
