<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Apply Farm Travel</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>

    <style>
        h3 {
            text-align: center;
        }
        .farm_travel_partner {
            border: 2px solid #717d34;
            border-radius: 5px;
            background-color: #b9d4b3;
        }
        .panel {
            background-color: #F5F5F7;
            border: 1px solid #ddd;
            padding: 20px;
            display: block;
            width: 270px;
            border-radius: 6px;
            box-shadow: 0 2px 4px rgba(0,0,0,.1);
        }
        .payBtn {
            background: rgb(68,175,231); /* Old browsers */
            background: -moz-linear-gradient(top, rgba(68,175,231,1) 0%, rgba(49,152,223,1) 100%); /* FF3.6-15 */
            background: -webkit-linear-gradient(top, rgba(68,175,231,1) 0%,rgba(49,152,223,1) 100%); /* Chrome10-25,Safari5.1-6 */
            background: linear-gradient(to bottom, rgba(68,175,231,1) 0%,rgba(49,152,223,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#44afe7', endColorstr='#3198df',GradientType=0 );
            color: #fff;
            display: block;
            width: 100%;
            border: 1px solid rgba(46, 86, 153, 0.0980392);
            border-bottom-color: rgba(46, 86, 153, 0.4);
            border-top: 0;
            border-radius: 4px;
            font-size: 17px;
            text-shadow: rgba(46, 86, 153, 0.298039) 0px -1px 0px;
            line-height: 34px;
            -webkit-font-smoothing: antialiased;
            font-weight: bold;
            margin-top: 20px;
        }
        .payBtn:hover {
            cursor: pointer;
        }
    </style>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<form method="post" action="<%=request.getContextPath()%>/farmTravel.do">
    <input type="hidden" name="action" value="getOne">
    <input type="hidden" name="farm_travel_ID" value="${farmTravel.farm_travel_ID}">
    <button type="submit" class="btn btn-outline-light"><img src="<%=request.getContextPath()%>/front-end/farmTravel/images/back.png"></button>
</form>

<h3>報名農遊行程</h3>

<%--簡介--%>
<div class="card mb-3" style="max-width: 1920px;">
    <div class="row g-0">
        <div class="col-md-6">
            <c:if test="${not empty farmTravel.farm_travel_img}">
                <img src="<%=request.getContextPath()%>/farmTravel.do?farm_travel_ID=${farmTravel.farm_travel_ID}" class="img-fluid rounded-start">
            </c:if>
            <c:if test="${empty farmTravel.farm_travel_img}">
                <img src="<%=request.getContextPath()%>/front-end/farmTravel/images/NoImage.png" class="img-fluid rounded-start">
            </c:if>
        </div>
        <div class="col-md-6">
            <div class="card-body">
                <h5 class="card-title">${farmTravel.farm_travel_title}</h5>
                <p class="card-text">${farmTravel.farm_travel_info}</p>
                <p class="card-text"><small class="text-muted">標籤?</small><small class="text-muted">標籤?</small></p>
            </div>
        </div>
    </div>
</div>

<%--報名表單--%>
<form method="post" action="<%=request.getContextPath()%>/farmTravelOrder.do">
    <%--    報名人數--%>
    <div>
        <div>選擇報名人數</div>
        <input type="radio" class="people_num btn-check" name="people_num" id="people_num_1" autocomplete="off"
               value="1" checked>
        <label class="btn btn-outline-success" for="people_num_1">1</label>
        <c:forEach begin="2" end="${farmTravel.farm_travel_max - farmTravel.farm_travel_now}" var="people_num">
            <input type="radio" class="people_num btn-check" name="people_num" id="people_num_${people_num}"
                   autocomplete="off" value="${people_num}">
            <label class="btn btn-outline-success" for="people_num_${people_num}">${people_num}</label>
        </c:forEach>
    </div><br/>

    <%--    小夥伴清單--%>
    <div id="farm_travel_partner_list">
        <div class="farm_travel_partner input-group mb-3">
            <span class="partner_nums input-group-text" id="basic-addon1">1</span>
            <input type="text" class="partner_name form-control" name="partner_name" placeholder="參加人姓名" maxlength="20">
            <input type="text" class="partner_phone form-control" name="partner_phone" placeholder="參加人電話" maxlength="20">
            <input type="text" class="guardian_name form-control" name="guardian_name" placeholder="緊急連絡人姓名" maxlength="20">
            <input type="text" class="guardian_phone form-control" name="guardian_phone" placeholder="緊急連絡人電話" maxlength="20">
        </div>
    </div>
    <br/>

    <%-- 報名費用--%>
    <div class="input-group mb-3">
        <span class="input-group-text">報名費用：</span>
        <input type="text" class="form-control" id="total_fee" name="order_fee" value="${farmTravel.farm_travel_fee}" readonly>
        <span class="input-group-text">NTD</span>
    </div><br/>

    <%-- 訂單備註 --%>
    <div class="input-group mb-3">
        <span class="input-group-text">備註</span>
        <input type="text" class="form-control" name="order_memo">
    </div><br/>

    <%-- 信用卡 --%>
    <div style="margin-top: 50px">
        <div class="card-js" data-capture-name="true" data-icon-colour="#158CBA"></div>
    </div>

    <%-- 隱藏資訊 --%>
    <div>
        <input type="hidden" name="farm_travel_ID" value="${farmTravel.farm_travel_ID}">
        <input type="hidden" name="farm_travel_start" value="${farmTravel.farm_travel_start}">
        <input type="hidden" name="farm_travel_end" value="${farmTravel.farm_travel_end}">
        <input type="hidden" name="f_mem_ID" value="${farmTravel.f_mem_ID}">
        <input type="hidden" name="action" value="insert">
    </div>
    <br/><br/>

    <button type="submit" class="btn btn-outline-secondary">結帳</button>

</form>

<script>
    $(".people_num").click(function () {
        let people_num = this.value;
        let total_fee = people_num * ${farmTravel.farm_travel_fee};
        $("#total_fee").val(total_fee);
        $(".farm_travel_partner").remove();
        for (let nums = 1; nums <= people_num; nums++) {
            $("#farm_travel_partner_list").append(
                `<div class="farm_travel_partner input-group mb-3">
                    <span class="partner_nums input-group-text">` + nums + `</span>
                    <input type="text" class="partner_name form-control" name="partner_name" placeholder="參加人姓名" maxlength="20">
                    <input type="text" class="partner_phone form-control" name="partner_phone" placeholder="參加人電話" maxlength="20">
                    <input type="text" class="guardian_name form-control" name="guardian_name" placeholder="緊急連絡人姓名" maxlength="20">
                    <input type="text" class="guardian_phone form-control" name="guardian_phone" placeholder="緊急連絡人電話" maxlength="20">
                 </div>`
            )
        }
    });
</script>

</body>
</html>
