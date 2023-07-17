<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <title>Insert title here</title>
    <style>
        textarea{
            width: 100%;
            resize: none;
        }
        .container{
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="/blog/updateOk" method="POST">
        <div class="row">
            <div class="col">
                <div class="mb-3">
                    <label for="blogTitle">글제목</label>
                    <input type="text" class="form-control" id="blogTitle" name="blogTitle" value="${blog.blogTitle}">
                </div>
            </div>
            <div class="col mb-3">
                <label for="writer">글쓴이</label>
                <input type="text" class="form-control" id="writer" name="writer" value="${blog.writer}" readonly>
            </div>
        </div>

        <div class="row">
            <div class="mb-3">
                <textarea clsas="form-control" rows="10" name="blogContent">${blog.blogContent}</textarea>
            </div>
        </div>
        <input type="hidden" value="${blog.blogId}" name="blogId">
        <div class="row">
            <input type="submit" value="수정" class="btn btn-primary" onclick="alert('수정완료!');">
            <br><hr>
            <a class="btn btn-primary" href="/blog/detail/${blog.blogId}">취소</a>
        </div>
    </form>
</div>
</body>
</html>