<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログイン - タスク管理システム</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow-sm border-dark">
                    <div class="card-body text-center p-5">
                        <h3 class="mb-4 fw-bold">タスク管理システム</h3>
                        
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger">${errorMessage}</div>
                        </c:if>
                        <c:if test="${not empty debugMessage}">
                            <div class="alert alert-warning small">${debugMessage}</div>
                        </c:if>
                        
                        <form action="LoginServlet" method="post">
                            <div class="mb-3 row justify-content-center align-items-center">
                                <label for="userId" class="col-sm-4 col-form-label text-end fw-bold">ユーザID：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control border-danger" id="userId" name="userId" required>
                                </div>
                            </div>
                            <div class="mb-4 row justify-content-center align-items-center">
                                <label for="password" class="col-sm-4 col-form-label text-end fw-bold">パスワード：</label>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control border-danger" id="password" name="password" required>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary px-5 text-white" style="background-color: #4169e1;">ログイン</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>