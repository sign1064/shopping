<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>http://localhost:9092/chatting</title>

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<style type="text/css">
        *{
            margin:0;
            padding:0;
        }
        .container {
            width: 500px;
            margin: 0 auto;
            padding: 25px
        }
        .container h1{
            text-align: left;
            padding: 5px 5px 5px 15px;
            color: #FFBB00;
            border-left: 3px solid #FFBB00;
            margin-bottom: 20px;
        }
        .chatting_pannel {
            background-color: #000;
            width: 500px;
            height: 400px;
            overflow: auto;
        }
        .chatting_pannel p{
            color: #fff;
            text-align: left;
        }
        input {
            width: 330px;
            height: 25px;
        }
        #yourMsg {
            display: none;
        }
        
</style>

<script type="text/javascript">
var ws;

$(function() {
    // 키이벤트 처리
    $('#userName').on('keydown', function(key) {
      if (key.keyCode == 13) { // Enter
        // $('#rname').focus();
        chatName(); // 사용자명 입력 Layer를 닫고 메시지 Layer로 변경됨.
      }  
    });  
})

// alert(location.host); // localhost:9092
function wsOpen(){
    ws = new WebSocket("ws://" + location.host + "/chat"); // ws://localhost:9092/chat
    wsEvt();
}
    
function wsEvt() {
    ws.onopen = function(data){
        //소켓이 열리면 초기화 세팅하기
      $('#chatting').focus();
    }
    
    ws.onmessage = function(data) {
        var msg = data.data;
        if(msg != null && msg.trim() != ''){
            $("#chatting_pannel").append("<p>" + msg + "</p>");
        }
    }

    document.addEventListener("keypress", function(e){
        if(e.keyCode == 13){ //enter press
            send();
        }
    });
}

function chatName(){
    var userName = $("#userName").val();
    if(userName == null || userName.trim() == ""){
        alert("사용자 이름을 입력해주세요.");
        $("#userName").focus();
    }else{
        wsOpen();
        $("#yourName").hide();
        $("#yourMsg").show();
    }
}

function send() {
    var uN = $("#userName").val();
    var msg = $("#chatting").val();
    ws.send(uN+" : "+msg);
    $('#chatting').val("");
}
</script>

</head>
<body>
    <div id="container" class="container">
        <h1>실시간 문의</h1>
        <div id="chatting_pannel" class="chatting_pannel">
        </div>
        
        <div id="yourName">
            <table class="inputTable">
                <tr>
                    <th>사용자명</th>
                    <th><input type="text" name="userName" id="userName"></th>
                    <th><button onclick="chatName()" id="startBtn">이름 등록</button></th>
                </tr>
            </table>
        </div>
        
        <div id="yourMsg">
            <table class="inputTable">
                <tr>
                    <th>메시지</th>
                    <th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
                    <th><button onclick="send()" id="sendBtn">보내기</button></th>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>