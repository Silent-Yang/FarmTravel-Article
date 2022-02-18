<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>List One Farm Travel</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        main{
            width: 100%;
            display: block;
        }
        h3{
            text-align: center;
            display: inline;
        }
        .back{
            margin: 20px 20px;
        }
    </style>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<main>
    <button type="button" class="back btn btn-outline-light">
        <a href="<%=request.getContextPath()%>/front-end/farmTravel/listAllFarmTravel.jsp"><img src="<%=request.getContextPath()%>/front-end/farmTravel/images/back.png"></a>
    </button>
    <h3>農遊行程詳情</h3>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">農遊行程編號</th>
            <th scope="col">一般會員編號</th>
            <th scope="col">小農編號</th>
            <th scope="col">農遊標題</th>
            <th scope="col">農遊封面圖</th>
            <th scope="col">農遊資訊</th>
            <th scope="col">農遊行程起</th>
            <th scope="col">農遊行程迄</th>
            <th scope="col">農遊費用</th>
            <th scope="col">農遊報名開始</th>
            <th scope="col">農遊報名截止</th>
            <th scope="col">最少人數</th>
            <th scope="col">最大人數</th>
            <th scope="col">目前報名人數</th>
            <th scope="col">農遊狀態</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row">${farmTravel.farm_travel_ID}</th>
                <td>${farmTravel.mem_ID}</td>
                <td>${farmTravel.f_mem_ID}</td>
                <td>${farmTravel.farm_travel_title}</td>
                <td>
                    <c:if test="${not empty farmTravel.farm_travel_img}">
                        <img src="<%=request.getContextPath()%>/farmTravel.do?farm_travel_ID=${farmTravel.farm_travel_ID}" width="200" height="150">
                    </c:if>
                    <c:if test="${empty farmTravel.farm_travel_img}" >
                        <img src="<%=request.getContextPath()%>/front-end/farmTravel/images/NoImage.png" width="200" height="150">
                    </c:if>
                </td>
                <td>${farmTravel.farm_travel_info}</td>
                <td>${farmTravel.farm_travel_start}</td>
                <td>${farmTravel.farm_travel_end}</td>
                <td>${farmTravel.farm_travel_fee}</td>
                <td>${farmTravel.travel_apply_start}</td>
                <td>${farmTravel.travel_apply_end}</td>
                <td>${farmTravel.farm_travel_min}</td>
                <td>${farmTravel.farm_travel_max}</td>
                <td>${farmTravel.farm_travel_now}</td>
                <td>${farmTravel.farm_travel_state}</td>
                <td>
                    <c:if test="${farmTravel.farm_travel_state eq 0 && (farmTravel.farm_travel_max - farmTravel.farm_travel_now) > 0}">
                        <form method="post" action="<%=request.getContextPath()%>/farmTravel.do">
                            <input type="hidden" name="action" value="getOneForApply">
                            <input type="hidden" name="farm_travel_ID" value="${farmTravel.farm_travel_ID}">
                            <button type="submit" class="btn btn-outline-primary">馬上報名</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </tbody>
    </table>
</main>
<script>
    $(".back").click( ()=> history.back() );
</script>
</body>
</html>