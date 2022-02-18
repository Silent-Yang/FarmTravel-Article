<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登入測試</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<style>
    body{
        background-color: #434217;
    }
    #memBtn,#FMemBtn{
        margin: 20px;
        width: 200px;
        height: 100px;
        border-radius: 10px;
        background-color:  #dfdcb3;
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        font-weight: bold;
        font-size: 20px;
    }
    #memBtn:hover,#FMemBtn:hover{
        cursor:pointer;
        background-color: #aaba8b;
        border:5px solid gray;
    }
    .flex{
        display:flex;
        align-items:center;
        justify-content:center;
        width:100%;
        height:100%;
        background-color: #434217
    }
</style>
<body>
<div class="flex">
        <div>
            <input type="button" id="memBtn" value="一 般 會 員">

            <input type="button" id="FMemBtn" value="小 農 會 員">
        </div>
</div>

<script>
    $('#memBtn').click(function (){
        $.post(
            "<%=request.getContextPath()%>/login.do",
            { farmerOrNot:'not' },
            ()=>{
                window.location.href = '<%=request.getContextPath()%>/front-end/demo/memIndex.jsp';
            }
        )
    });
    $('#FMemBtn').click(function (){
        $.post(
            "<%=request.getContextPath()%>/login.do",
            { farmerOrNot:'yes' },
            ()=>{
                window.location.href = '<%=request.getContextPath()%>/front-end/demo/fMemIndex.jsp';
            }
        )
    });
</script>
</body>
</html>
