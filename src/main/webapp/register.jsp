<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>タスク登録 - タスク管理システム</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-4" style="max-width: 800px;">
        <div class="text-center mb-4">
            <h3 class="fw-bold">タスク管理システム</h3>
        </div>

        <div class="card border-dark rounded-4">
            <div class="card-body p-5">
                <h4 class="fw-bold mb-4">タスク登録</h4>

                <form action="RegisterTaskServlet" method="post">
                    <div class="mb-3 row align-items-center">
                        <label for="taskName" class="col-sm-3 col-form-label fw-bold">・タスク名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control border-danger rounded-0" id="taskName" name="taskName" required>
                        </div>
                    </div>

                    <div class="mb-3 row align-items-center">
                        <label for="categoryId" class="col-sm-3 col-form-label fw-bold">・カテゴリ</label>
                        <div class="col-sm-9">
                            <select class="form-select border-danger rounded-0 w-50" id="categoryId" name="categoryId" required>
                                <option value="" disabled selected>カテゴリを選択</option>
                                <c:forEach var="category" items="${categoryList}">
                                    <option value="${category.categoryId}">${category.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="mb-3 row align-items-center">
                        <label for="limitDate" class="col-sm-3 col-form-label fw-bold">・期限</label>
                        <div class="col-sm-9">
                            <input type="date" class="form-control border-danger rounded-0 w-50" id="limitDate" name="limitDate">
                        </div>
                    </div>

                    <div class="mb-3 row align-items-center">
                        <label for="statusCode" class="col-sm-3 col-form-label fw-bold">・ステータス</label>
                        <div class="col-sm-9">
                            <select class="form-select border-danger rounded-0 w-50" id="statusCode" name="statusCode">
                                <option value="00" selected>未着手</option>
                                <option value="50">着手</option>
                                <option value="99">完了</option>
                            </select>
                        </div>
                    </div>

                    <div class="mb-4 row">
                        <label for="memo" class="col-sm-3 col-form-label fw-bold">・メモ</label>
                        <div class="col-sm-9">
                            <textarea class="form-control border-danger rounded-0" id="memo" name="memo" rows="3"></textarea>
                        </div>
                    </div>

                    <div class="d-flex justify-content-end gap-3">
                        <button type="submit" class="btn btn-outline-danger bg-white rounded-0 px-4 text-danger">登録</button>
                        <a href="MenuServlet" class="btn btn-outline-dark bg-white rounded-0 px-4">キャンセル</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>