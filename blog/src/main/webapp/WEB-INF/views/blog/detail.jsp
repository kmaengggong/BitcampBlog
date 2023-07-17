<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
          crossorigin="anonymous">
</head>
<style>
    .text-center,
    .row,
    .col {
        border: 1px solid black;
    }

    .title {
        font-weight: bold;
        background-color: rgb(178, 198, 235);
    }

    .content {
        padding: 20px;
    }

    .button {
        display: inline;
        float: left;
    }

    .writer {
        float: left;
    }

    .date {
        float: right;
        font-size: 12px;
        margin-left: 10px;
    }

    .replyTextarea {
        width: 87%;
        height: 100px;
        resize: none;
        vertical-align: middle;
        float: left;
    }

    .replyInput {
        width: 10%;
        height: 100px;
        vertical-align: middle;
        float: right;
    }

    .reButton {
        float: right;
        color: gray;
        text-decoration: none;
        font-size: 12px;
    }

    .replyContent {
        float: left;
    }
</style>

<body>
<div class="container text-center">
    <div class="row first-row">
        <div class="col-1 title">
            글번호
        </div>
        <div class="col-1">
            ${blog.blogId}
        </div>
        <div class="col-2 title">
            글제목
        </div>
        <div class="col-6">
            ${blog.blogTitle}
        </div>
        <div class="col-1 title">
            글쓴이
        </div>
        <div class="col-1">
            ${blog.writer}
        </div>
    </div>
    <div class="row second-row">
        <div class="col-1 title">
            작성일
        </div>
        <div class="col-4">
            ${blog.publishedAt}
        </div>
        <div class="col-1 title">
            수정일
        </div>
        <div class="col-4">
            ${blog.updatedAt}
        </div>
        <div class="col-1 title">
            조회수
        </div>
        <div class="col-1">
            ${blog.blogCount}
        </div>
    </div>
    <div class="row" style="height: 500px;">
        <div class="col content">
            ${blog.blogContent}
        </div>
    </div>
</div>
<br>
<div class="container">
    <form name="deleteForm" style="margin-right: 5px;" class="button">
        <a href="/blog/list" class="btn btn-primary">목록</a>
        <input type="button" class="btn btn-primary" value="삭제" onclick="deleteBlog()">
        <input type="hidden" name="blogId" value="${blog.blogId}">
    </form>
    <form name="updateForm">
        <input type="button" class="btn btn-primary" value="수정" onclick="updateBlog()">
        <input type="hidden" name="blogId" value="${blog.blogId}">
    </form>
    <div style="height: 100px; margin-top: 10px; ">
        <input type="text" name="replyWriter" id="replyWriter" placeholder="작성자를 입력하세요">
        <textarea placeholder="댓글을 작성해주세요" name="replyContent" id="replyContent"
                  class="replyTextarea"></textarea>
        <input type="button" value="작성" id="replySubmit" class="btn btn-primary replyInput">
    </div>
    <div class="row" style="border: none; width: 100%;">
        <div id="replies"></div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="replyUpdateModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">댓글 수정</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p><b>작성자</b></p>
                <input type="text" class="updateReplyWriter">
                <p><b>댓글</b></p>
                <textarea class = 'replyTextarea updateReplyContent'></textarea>
                <input type="hidden" class="updateReplyId">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-primary" id="updateSubmitBtn" data-bs-dismiss="modal">수정</button>
            </div>
        </div>
    </div>
</div>
<script>
    function deleteBlog() {
        let deleteForm = document.deleteForm;
        let check = confirm("해당 게시물을 삭제하시겠습니까?");
        if (check) {
            alert("삭제완료!");
            deleteForm.method = "POST";
            deleteForm.action = "/blog/delete";
            deleteForm.submit();
        }

    }

    function updateBlog() {
        let updateForm = document.updateForm;
        let check = confirm("해당 게시물을 수정하시겠습니까?");
        if (check) {
            updateForm.method = "POST"
            updateForm.action = "/blog/update"
            updateForm.submit();
        }
    }

    let blogId = "${blog.blogId}";
    const $replies = document.getElementById('replies');
    function getAllReplies(blogId) {
        fetch(`/reply/${blogId}/all`, { method: 'get' })
            .then(res => res.json())
            .then(data => {
                console.log(data)
                $replies.innerHTML = "";
                // for (let i of data) {
                //     let date = new Date(`\${i.publishedAt}`);
                //     // jsp에서 리터럴을 사용하기 위해서는 역슬래시를 붙여줘야한다
                //     // js를 분리하면 안 붙여줘도 된다
                //     $replies.innerHTML += `<hr><p><b class = 'writer'>\${i.replyWriter}</b>
                //         <span class='date'>\${date.getFullYear()}년 \${date.getMonth()}월 \${date.getDate()}일</span>
                //         <br>\${i.replyContent} </p>`;
                // }

                data.map((reply, i) => { // 첫 파라미터 : 반복대상자료, 두번째 파라미터 순번
                    let date = "";
                    let check = false;
                    if(reply.publishedAt !== reply.updatedAt){
                        date = new Date(`\${reply.updatedAt}`);
                        check = true;
                    }else{
                        date = new Date(`\${reply.publishedAt}`);
                    }
                    $replies.innerHTML += `<hr>
                                    <p class = 'reply'>
                                        <b class = 'writer' >\${reply.replyWriter}</b>
                                        <span class='date' id='date\${reply.replyId}'>\${date.getFullYear()}년 \${date.getMonth()+1}월 \${date.getDate()}일 \${date.getHours()}시 \${date.getMinutes()}분</span>
                                        <br><span class ='replyContent' >\${reply.replyContent}</span>
                                        <a class = 'reButton updateReplyBtn'
                                            style='margin-left:5px' data-bs-toggle='modal' data-bs-target='#replyUpdateModal'
                                            data-modalReplyWriter='\${reply.replyWriter}' data-modalReplyContent='\${reply.replyContent}' data-modalReplyId='\${reply.replyId}'>수정</a>
                                        <a class = 'reButton deleteReplyBtn' data-replyId='\${reply.replyId}'>삭제</a>
                                    </p><br>`;

                    if(check){
                        document.getElementById(`date\${reply.replyId}`).innerHTML += "<span style='color:gray'>(수정됨)</span>";
                    }
                });
            });
    }
    getAllReplies(blogId);


    function insertReply() {
        let url = `/reply`;
        //내용이 없거나 띄어쓰기만 넣어놓고 작성하는것을 막는다.
        //금칙어는 cont ban =["금칙어내용"] 변수에 담아놓고 includes(ban)로 검사한다.
        if (document.getElementById("replyWriter").value.trim() === "") {
            alert("작성자를 입력해주세요");
            return;
        }
        if (document.getElementById("replyContent").value.trim() === "") {
            alert("댓글을 입력해주세요");
            return;
        }
        fetch(url, {
            method: 'post',
            headers: {// header에는 보내는 데이터의 자료형에 대해서 기술
                //json 데이터를 요청과 함께 전달, @RequestBody를 입력받는 로직에 추가
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ // 실질적으로 요청과 보낼 json정보를 기술
                replyWriter: document.getElementById("replyWriter").value,
                replyContent: document.getElementById("replyContent").value,
                blogId: "${blog.blogId}"
            }), //insert 로직이기 때문에 reponse에 실제화면에 사용할 데이터 전송 X
        }).then(() => {
            document.getElementById("replyWriter").value = "";
            document.getElementById("replyContent").value = "";
            alert("댓글 작성 완료");
            getAllReplies(blogId);
        })
    }
    // 9 10 11 12 13 14 / 18 19 20 21
    document.getElementById("replySubmit").addEventListener("click", insertReply);

    $replies.onclick = e =>{
        //#replies의 자손(공백/자식은 >) 객체 중 .deleteReplyBtn인지 확인
        if(!e.target.matches('#replies .deleteReplyBtn') && !e.target.matches('#replies .updateReplyBtn')){
            return;
        }

        if(e.target.matches('#replies .deleteReplyBtn')){
            //event 객체안에 target안에 dataset안에 replyId(카멜케이스 인정 X 소문자로 되어있음)가 있다
            const replyId = e.target.dataset.replyid;

            if(confirm("댓글을 삭제하시겠습니까?")){
                fetch(`/reply/\${replyId}`,{method: 'delete'})
                    .then(()=>{
                        alert("삭제완료");
                        getAllReplies(blogId);
                    });
            }
        }else if(e.target.matches('#replies .updateReplyBtn')){
            const replyWriter = e.target.dataset.modalreplywriter;
            const replyContent = e.target.dataset.modalreplycontent;
            const replyId = e.target.dataset.modalreplyid;

            document.querySelector('.updateReplyWriter').value = replyWriter;
            document.querySelector('.updateReplyContent').value = replyContent;
            document.querySelector('.updateReplyId').value = replyId;
        }
    }
    document.getElementById('updateSubmitBtn').addEventListener("click",updateReplyFunc);

    function updateReplyFunc(){
        const replyId = document.querySelector('.updateReplyId').value;
        const $replyWriter = document.querySelector(".updateReplyWriter");
        const $replyContent = document.querySelector(".updateReplyContent");

        if ($replyWriter.value.trim() === "") {
            alert("작성자를 입력해주세요");
            $replyWriter.focus();
            return;
        }
        if ($replyContent.value.trim() === "") {
            alert("댓글을 입력해주세요");
            $replyContent.focus();
            return;
        }

        fetch(`/reply/\${replyId}`, {
            method: 'PATCH',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                replyWriter: $replyWriter.value,
                replyContent: $replyContent.value
            })
        })
            .then(()=>{
                alert("수정완료");
                getAllReplies(blogId);
            });

    }
</script>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>

</html>