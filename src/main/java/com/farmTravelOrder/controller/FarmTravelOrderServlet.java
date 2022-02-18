package com.farmTravelOrder.controller;

import com.farmTravel.model.FarmTravelService;
import com.farmTravel.model.FarmTravelVO;
import com.farmTravelOrder.model.FarmTravelOrderService;
import com.farmTravelOrder.model.FarmTravelOrderVO;
import com.farmTravelPartner.model.FarmTravelPartnerService;
import com.mem.model.MemVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/farmTravelOrder.do")
public class FarmTravelOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if ("insert".equals(action)){
            List<String> errorMsgs = new LinkedList<String>();
            request.setAttribute("errorMsgs", errorMsgs);
            try{
                Integer mem_ID = ((MemVO)(session.getAttribute("mem"))).getMem_id();
                Integer farm_travel_ID = Integer.valueOf(request.getParameter("farm_travel_ID"));
                Integer f_mem_ID = Integer.valueOf(request.getParameter("f_mem_ID"));
                Integer people_num = Integer.valueOf(request.getParameter("people_num"));
                Integer order_fee = Integer.valueOf(request.getParameter("order_fee"));

                java.sql.Timestamp farm_travel_start = null;
                try {
                    farm_travel_start = java.sql.Timestamp.valueOf(request.getParameter("farm_travel_start"));
                }catch (Exception e) { errorMsgs.add("請確認行程起日"); }

                java.sql.Timestamp farm_travel_end = null;
                try {
                    farm_travel_end = java.sql.Timestamp.valueOf(request.getParameter("farm_travel_end"));
                }catch (Exception e) { errorMsgs.add("請確認行程迄日"); }

                String order_memo = request.getParameter("order_memo");

                // 以下為農遊小夥伴資料
                String[] partner_name_list = request.getParameterValues("partner_name");
                if (partner_name_list.length != people_num){
                    errorMsgs.add("請填寫參加人姓名");
                }
                String[] partner_phone_list = request.getParameterValues("partner_phone");
                if (partner_phone_list.length != people_num){
                    errorMsgs.add("請填寫參加人電話");
                }
                String[] guardian_name_list = request.getParameterValues("guardian_name");
                if (guardian_name_list.length != people_num){
                    errorMsgs.add("請填寫緊急連絡人姓名");
                }
                String[] guardian_phone_list = request.getParameterValues("guardian_phone");
                if (guardian_phone_list.length != people_num){
                    errorMsgs.add("請填寫緊急連絡人姓名");
                }

                FarmTravelOrderVO farmTravelOrder = new FarmTravelOrderVO();
                farmTravelOrder.setMem_ID(mem_ID);
                farmTravelOrder.setFarm_travel_ID(farm_travel_ID);
                farmTravelOrder.setF_mem_ID(f_mem_ID);
                farmTravelOrder.setPeople_num(people_num);
                farmTravelOrder.setOrder_fee(order_fee);
                farmTravelOrder.setFarm_travel_start(farm_travel_start);
                farmTravelOrder.setFarm_travel_end(farm_travel_end);
                farmTravelOrder.setOrder_memo(order_memo);

                request.setAttribute("partmer_name_list", partner_name_list);
                request.setAttribute("partner_phone_list", partner_phone_list);
                request.setAttribute("guardian_name_list", guardian_name_list);
                request.setAttribute("guardian_phone_list", guardian_phone_list);
                request.setAttribute("errorMsgs", errorMsgs);

                FarmTravelService farmTravelService = new FarmTravelService();
                FarmTravelVO farmTravel = farmTravelService.getOneFarmTravel(farm_travel_ID);
                request.setAttribute("farmTravel",farmTravel);

                if (!errorMsgs.isEmpty()) {  // 若以上格式有錯返回add頁面
                    request.setAttribute("farmTravelOrder", farmTravelOrder);
                    RequestDispatcher errView = request.getRequestDispatcher("/front-end/farmTravel/applyFarmTravel.jsp");
                    errView.forward(request, response);
                    return;
                }
                try{  // 嘗試新增資料，若發生錯誤返回add頁面
                    FarmTravelOrderService farmTravelOrderService = new FarmTravelOrderService();
                    farmTravelOrder =  farmTravelOrderService.addFarmTravelOrder(mem_ID, farm_travel_ID, f_mem_ID, people_num, order_fee, farm_travel_start, farm_travel_end, order_memo, partner_name_list, partner_phone_list, guardian_name_list, guardian_phone_list);
                    if (farmTravel.getFarm_travel_max() == farmTravel.getFarm_travel_now()+people_num){
                        farmTravel = farmTravelService.updateFarmTravel(farmTravel.getFarm_travel_title(),farmTravel.getFarm_travel_img(),farmTravel.getFarm_travel_info(),farmTravel.getFarm_travel_start(),farmTravel.getFarm_travel_end(),farmTravel.getFarm_travel_fee(),farmTravel.getTravel_apply_start(),farmTravel.getTravel_apply_end(),farmTravel.getFarm_travel_min(),farmTravel.getFarm_travel_max(),farmTravel.getFarm_travel_now()+people_num,3,farmTravel.getFarm_travel_ID());
                    }else{
                        farmTravel = farmTravelService.updateFarmTravel(farmTravel.getFarm_travel_title(),farmTravel.getFarm_travel_img(),farmTravel.getFarm_travel_info(),farmTravel.getFarm_travel_start(),farmTravel.getFarm_travel_end(),farmTravel.getFarm_travel_fee(),farmTravel.getTravel_apply_start(),farmTravel.getTravel_apply_end(),farmTravel.getFarm_travel_min(),farmTravel.getFarm_travel_max(),farmTravel.getFarm_travel_now()+people_num,farmTravel.getFarm_travel_state(),farmTravel.getFarm_travel_ID());
                    }
                }catch(Exception e){
                    e.printStackTrace(System.err);
                    errorMsgs.add("新增失敗");
                    request.setAttribute("farmTravelOrder", farmTravelOrder);
                    RequestDispatcher errView = request.getRequestDispatcher("/front-end/farmTravel/applyFarmTravel.jsp");
                    errView.forward(request, response);
                    return;
                }

                RequestDispatcher successView = request.getRequestDispatcher("/front-end/farmTravelOrder/listAllFarmTravelOrderByMem.jsp");
                successView.forward(request, response);
            }catch (Exception e){  // 發生其他Error時
                e.printStackTrace(System.err);
                RequestDispatcher errView = request.getRequestDispatcher("/front-end/farmTravel/applyFarmTravel.jsp");
                errView.forward(request, response);
            }
        }
    }
}
