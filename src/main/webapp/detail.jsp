<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>タスク詳細 - タスク管理システム</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-4" style="max-width: 800px;">
        
        <div class="card border-dark rounded-0 mb-4">
            <div class="card-body p-4">
                <div class="mb-2"><span class="fw-bold">・タスク名：</span><c:out value="${task.taskName}"/></div>
                <div class="mb-2"><span class="fw-bold">・カテゴリ：</span><c:out value="${task.categoryName}"/></div>
                <div class="mb-2"><span class="fw-bold">・期限：</span><c:out value="${task.limitDate}"/></div>
                <div class="mb-2"><span class="fw-bold">・ステータス：</span><c:out value="${task.statusName}"/></div>
                <div class="mb-4"><span class="fw-bold">・メモ：</span><c:out value="${task.memo}"/></div>
                
                <hr class="border-dark border-2 border-top">
                
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <span class="fw-bold fs-5">コメント：</span>
                    <form action="RegisterCommentServlet" method="post" class="d-flex w-75 gap-2">
                        <input type="hidden" name="taskId" value="${task.taskId}">
                        <input type="text" class="form-control border-dark rounded-0" name="commentText" placeholder="コメントを入力" required>
                        <button type="submit" class="btn btn-outline-dark bg-white rounded-0 px-3" style="white-space: nowrap;">コメントを投稿する</button>
                    </form>
                </div>

                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success py-1">${successMessage}</div>
                </c:if>

                <c:forEach var="comment" items="${commentList}">
                    <div class="ms-4 mb-3">
                        <div class="d-flex justify-content-between align-items-start">
                            <div class="border border-danger px-3 py-2 bg-white w-75 rounded-0">
                                <div><span class="fw-bold">○ <c:out value="${comment.userName}"/></span></div>
                                <div class="mt-1"><c:out value="${comment.comment}"/></div>
                            </div>
                            
                            <c:if test="${comment.userId == sessionScope.loginUser.userId}">
                                <div class="d-flex flex-column gap-2 ms-2">
                                        <form action="EditCommentServlet" method="post" class="m-0" 
                                            onsubmit="var newText = prompt('コメントを編集してください', '${comment.comment}'); if(newText){ this.elements['newComment'].value = newText; return true; } return false;">
                                            <input type="hidden" name="commentId" value="${comment.commentId}">
                                            <input type="hidden" name="taskId" value="${task.taskId}">
                                            <input type="hidden" name="newComment" value="">
                                            <button type="submit" class="btn btn-sm text-primary border border-primary bg-white rounded-0 w-100">編集</button>
                                        </form>                                    
                                        <form action="DeleteCommentServlet" method="post" class="m-0" onsubmit="return confirm('コメントを削除しますか？');">
                                        <input type="hidden" name="commentId" value="${comment.commentId}">
                                        <input type="hidden" name="taskId" value="${task.taskId}">
                                        <button type="submit" class="btn btn-sm text-danger border border-danger bg-white rounded-0 w-100">削除</button>
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>

                <div class="d-flex justify-content-end mt-5">
                    <a href="MenuServlet" class="btn btn-outline-danger bg-white rounded-0 px-5 text-danger">戻る</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>