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
    <form action="/blog/insert" method="POST">
        <div class="row">
            <div class="col">
                <div class="mb-3">
                    <label for="blogTitle">글제목</label>
                    <input type="text" class="form-control" id="blogTitle" name="blogTitle">
                </div>
            </div>
            <div class="col mb-3">
                <label for="writer">글쓴이</label>
                <input type="text" class="form-control" id="writer" name="writer">
            </div>
        </div>

        <div class="row">
            <div class="mb-3">
                <textarea clsas="form-control" rows="10" name="blogContent"></textarea>
            </div>
        </div>
        <div class="row">
            <input type="submit" value="저장" class="btn btn-primary">
            <br><hr>
            <a class="btn btn-primary" href="/blog/list">취소</a>
        </div>
    </form>
</div>
</body>
</html>