# TaskManagementSystem

このプロジェクトはJava Servlet/JSPベースのタスク管理システムです。
プログラミング研修の講師として受講者に教えるための一環として作成しました。

## 実行手順

1. MySQLを起動し、`src/main/java/db/ConMng.java` の接続情報を確認または変更します。
   - URL: `jdbc:mysql://localhost:3306/your_db?serverTimezone=UTC`
   - USER: `root`
   - PW: `mysqldb`

2. 必要なデータベースとテーブルを作成します。
   - `m_user` などのテーブルがこのアプリから参照されます。

3. プロジェクトルートでビルドします。

```bash
mvn clean package
```

4. Tomcatで実行する場合:

```bash
mvn tomcat7:run
```

ブラウザで `http://localhost:8080/` にアクセスしてください。

## 依存ライブラリ

- Servlet API
- JSTL
- MySQL Connector/J

## 補足

- 依存ライブラリは `pom.xml` で管理しています。
- `src/main/webapp/WEB-INF/lib` などに置いたローカルjarはGit管理対象外です。
- Maven でビルドと実行ができます。
