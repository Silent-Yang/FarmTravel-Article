<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.farmTravel.model.*"%>
<%@ page import="com.mem.model.MemVO" %>
<%
    FarmTravelService farmTravelService = new FarmTravelService();
    List<FarmTravelVO> list = farmTravelService.getAllFarmTravel();
    pageContext.setAttribute("list",list);
    MemVO mem = new MemVO();
    mem.setMem_id(77000);
    session.setAttribute("memVO", mem);
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <title>List All Farm Travel.jsp</title>
    </head>
    <body>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <div id="searchBar">
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Search">
                <button class="search btn btn-outline-secondary" type="button" id="search">
                    <img src="<%=request.getContextPath()%>/front-end/farmTravel/images/search.png" width="30px" height="30px">
                </button>
            </div>
        </div>
        <main>
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
                        <th scope="col"><a href="<%=request.getContextPath()%>/front-end/farmTravel/addFarmTravel.jsp"><button type="button" class="btn btn-outline-success">新增行程</button></a></th>
                    </tr>
                </thead>
                <tbody>
                    <%@ include file="page1.file" %>
                    <c:forEach var="farmTravel" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                        <tr>
                            <th scope="row">${farmTravel.farm_travel_ID}<br/>
                                <button type="button" class="btn btn-outline-light" value="${farmTravel.farm_travel_ID}">
                                    <img src="<%=request.getContextPath()%>/farmTravelCollection.do?action=collection&mem_ID=${mem.mem_id}&farm_travel_ID=${farmTravel.farm_travel_ID}" class="collection">
                                </button>
                            </th>
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
                                <c:if test="${farmTravel.farm_travel_state eq 0 && farmTravel.farm_travel_now eq 0}">
                                    <form method="post" action="<%=request.getContextPath()%>/farmTravel.do">
                                        <input type="hidden" name="action" value="getOneForUpdate">
                                        <input type="hidden" name="farm_travel_ID" value="${farmTravel.farm_travel_ID}">
                                        <button type="submit" class="btn btn-outline-secondary">修改</button>
                                    </form>
                                </c:if>
                                <c:if test="${farmTravel.farm_travel_state eq 0 && farmTravel.farm_travel_now eq 0}">
                                    <button type="button" class="delete btn btn-outline-danger" value="${farmTravel.farm_travel_ID}">刪除</button>
                                </c:if>
                            </td>
                            <td>
                                <form method="post" action="<%=request.getContextPath()%>/farmTravel.do">
                                    <input type="hidden" name="action" value="getOne">
                                    <input type="hidden" name="farm_travel_ID" value="${farmTravel.farm_travel_ID}">
                                    <button type="submit" class="btn btn-outline-primary">查看詳情</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <%@ include file="page2.file" %>
        </main>
        <script>
            $(".delete").click(function() {
                $.post(
                    "<%=request.getContextPath()%>/farmTravel.do",
                    { action:"delete", farm_travel_ID:$(this).val() },
                     ()=>{
                        alert("刪除成功");
                        window.location.href = "<%=request.getContextPath()%>/front-end/farmTravel/listAllFarmTravel.jsp";
                    }
                );
            });
            $(".collection").click(function() {
                $.post(
                    "<%=request.getContextPath()%>/farmTravelCollection.do",
                    { action:"collection", farm_travel_ID:$(this).parent().val() },
                    data=>{
                        if (data == 0){
                            alert("移除收藏");
                            $(this).attr("src", "<%=request.getContextPath()%>/front-end/farmTravel/images/Heart.png");
                        }else if (data == 1){
                            alert("收藏成功");
                            $(this).attr("src", "<%=request.getContextPath()%>/front-end/farmTravel/images/Hearted.png");
                        }
                    }
                );
            });
        </script>
    </body>
</html>
