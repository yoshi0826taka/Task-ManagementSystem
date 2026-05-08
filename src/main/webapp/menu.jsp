<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>メニュー - タスク管理システム</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-4" style="max-width: 800px;">
        
        <div class="d-flex justify-content-center align-items-center position-relative mb-4">
            <h3 class="fw-bold m-0">タスク管理システム</h3>
            <form action="LogoutServlet" method="post" class="position-absolute end-0">
                <button type="submit" class="btn btn-outline-dark bg-white rounded-0 px-4">ログアウト</button>
            </form>
        </div>

        <div class="d-flex justify-content-between align-items-center mb-3">
            <div class="d-flex align-items-center">
                <label class="me-2 fw-bold fs-5">Task:</label>
                <select class="form-select w-auto border-dark rounded-0">
                    <option value="">カテゴリ</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.categoryId}">${category.categoryName}</option>
                    </c:forEach>
                </select>
            </div>
            <form action="ShowRegisterServlet" method="get">
                <button type="submit" class="btn btn-outline-danger bg-white rounded-0 px-4 text-danger">登録</button>
            </form>
        </div>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>

        <c:forEach var="task" items="${taskList}">
            <div class="card mb-3 border-danger rounded-4">
                <div class="card-body p-4">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="card-title m-0 fw-bold">・<c:out value="${task.taskName}" /></h5>
                        
                        <div class="d-flex gap-2">
                            <form action="ShowDetailServlet" method="get" class="m-0">
                                <input type="hidden" name="taskId" value="${task.taskId}">
                                <button type="submit" class="btn btn-sm btn-outline-dark bg-white rounded-0">コメント</button>
                            </form>
                            
                            <form action="ShowEditServlet" method="get" class="m-0">
                                <input type="hidden" name="taskId" value="${task.taskId}">
                                <button type="submit" class="btn btn-sm btn-outline-dark bg-white rounded-0">編集</button>
                            </form>
                            
                            <div class="border border-dark bg-white rounded-0 px-3 py-1 text-center" style="min-width: 100px;">
                                <c:out value="${task.statusName}" />
                            </div>
                            
                            <form action="DeleteTaskServlet" method="post" class="m-0" onsubmit="return confirm('本当に削除しますか？');">
                                <input type="hidden" name="taskId" value="${task.taskId}">
                                <button type="submit" class="btn btn-sm btn-outline-danger bg-white rounded-0 text-danger">削除</button>
                            </form>
                        </div>
                    </div>
                    
                    <div class="border border-dark rounded-0 p-2 bg-white text-muted" style="min-height: 50px;">
                        メモ：<c:out value="${task.memo}" default="" />
                    </div>
                </div>
            </div>
        </c:forEach>

        <c:if test="${empty taskList}">
            <div class="alert alert-secondary text-center mt-4">表示するタスクがありません。</div>
        </c:if>

    </div>
</body>
</html>