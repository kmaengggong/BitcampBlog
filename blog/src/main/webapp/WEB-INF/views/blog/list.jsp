<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- CSS only -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <style>
        a {
            color: black;
            text-decoration: none;
        }
        .container{
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center" style="display: inline; ">게시물 목록</h1>
    <form action="/blog/insert" method="GET" style="display: inline; float: right;">
        <input type="submit" value="글쓰기" class="btn btn-primary">
    </form> <br>
    <table class="table table-hover">
        <thead>
        <tr class="table-primary">
            <th scope="col">글번호</th>
            <th scope="col">글제목</th>
            <th scope="col">글쓴이</th>
            <th scope="col">쓴날짜</th>
            <th scope="col">수정날짜</th>
            <th scope="col">조회수</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var = "i" items = "${pageInfo.toList()}">
            <tr>
                <td scope="row">${i.blogId}</td>
                <td scope="row"><a href="/blog/detail/${i.blogId}">${i.blogTitle}</a></td>
                <td scope="row">${i.writer}</td>
                <td scope="row">${i.publishedAt}</td>
                <td scope="row">${i.updatedAt}</td>
                <td scope="row">${i.blogCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="row">
        <div class="col-11">
            <ul class="pagination justify-content-center">
                <c:if test="${startPageNum != 1}">
                    <li class="page-item">
                        <a class="page-link" href="/blog/list/${startPageNum-1}">Prev</a>
                    </li>
                </c:if>
                <c:forEach begin="${startPageNum}" end="${endPageNum}" var="btnNum">
                    <li class="page-item ${currentPageNum == btnNum ? 'active' : ''}">
                        <a class="page-link" href="/blog/list/${btnNum}">${btnNum}</a>
                    </li>
                </c:forEach>
                <c:if test="${endPageNum != pageInfo.getTotalPages()}">
                    <li class="page-item">
                        <a class="page-link" href="/blog/list/${endPageNum+1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
</body>
</html>