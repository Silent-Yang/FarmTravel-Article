package com.farmTravel.controller;

import com.farmTravel.model.FarmTravelService;
import com.farmTravel.model.FarmTravelVO;
import com.farmTravelTag.model.FarmTravelTagService;
import com.farmTravelTag.model.FarmTravelTagVO;
import com.farmTravelTagDetails.model.FarmTravelTagDetailsService;
import com.farmTravelTagDetails.model.FarmTravelTagDetailsVO;
import com.mem.model.MemVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/farmTravel.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class FarmTravelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/gif");
        ServletOutputStream out = response.getOutputStream();

        Integer farm_travel_ID = Integer.valueOf(request.getParameter("farm_travel_ID"));
        try {
            FarmTravelService farmTravelService = new FarmTravelService();
            FarmTravelVO dao = farmTravelService.getOneFarmTravel(farm_travel_ID);
            byte[] farm_travel_img = dao.getFarm_travel_img();
            out.write(farm_travel_img);
        } catch (NullPointerException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if("insert".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            request.setAttribute("errorMsgs", errorMsgs);
            try {  // 接收參數並嘗試做錯誤判斷
                Integer mem_ID = ((MemVO)(session.getAttribute("mem"))).getMem_id();
                Integer f_mem_ID = Integer.valueOf(request.getParameter("f_mem_ID"));

                String farm_travel_title = request.getParameter("farm_travel_title");
                if(farm_travel_title==null || farm_travel_title.trim().length()==0) { errorMsgs.add("請輸入農遊標題"); }

                String farm_travel_info = request.getParameter("farm_travel_info");
                if(farm_travel_info==null || farm_travel_info.trim().length()==0) { errorMsgs.add("請輸入行程資訊"); }

                // 使用Part讀取圖片
                InputStream in = request.getPart("farm_travel_img").getInputStream();
                byte[] farm_travel_img = null;
                if (in.available() != 0){
                    farm_travel_img = new byte[in.available()];
                    in.read(farm_travel_img);
                    in.close();
                }
                // 串接並格式化日期
                java.sql.Timestamp farm_travel_start = null;
                try {
                    farm_travel_start = java.sql.Timestamp.valueOf(request.getParameter("farm_travel_start_date")+" "+request.getParameter("farm_travel_start_time"));
                }catch (Exception e) { errorMsgs.add("請確認行程起日"); }

                java.sql.Timestamp farm_travel_end = null;
                try {
                    farm_travel_end = java.sql.Timestamp.valueOf(request.getParameter("farm_travel_end_date")+" "+request.getParameter("farm_travel_end_time"));
                }catch (Exception e) { errorMsgs.add("請確認行程迄日"); }

                Integer farm_travel_fee = null;
                try {
                    farm_travel_fee = Integer.valueOf(request.getParameter("farm_travel_fee"));
                }catch (Exception e) { errorMsgs.add("請確認農遊報名費用"); }

                java.sql.Timestamp  travel_apply_start = null;
                try {
                    travel_apply_start = java.sql.Timestamp.valueOf(request.getParameter("travel_apply_start")+" 00:00:00");
                }catch (Exception e) { errorMsgs.add("請確認報名起日"); }

                java.sql.Timestamp  travel_apply_end = null;
                try {
                    travel_apply_end = java.sql.Timestamp.valueOf(request.getParameter("travel_apply_end")+" 23:59:59");
                }catch (Exception e) { errorMsgs.add("請確認報名迄日"); }

                Integer farm_travel_min = null;
                try {
                    farm_travel_min = Integer.valueOf(request.getParameter("farm_travel_min"));
                }catch (Exception e) { errorMsgs.add("請確認最少成團人數"); }

                Integer farm_travel_max = null;
                try {
                    farm_travel_max = Integer.valueOf(request.getParameter("farm_travel_max"));
                }catch (Exception e) { errorMsgs.add("請確認最大滿額人數"); }

                String[] tag_names = request.getParameterValues("tag_name");

                // 存入VO，避免新增失敗時需再次輸入重複資料
                FarmTravelVO farmTravel = new FarmTravelVO();
                farmTravel.setMem_ID(mem_ID);
                farmTravel.setF_mem_ID(f_mem_ID);
                farmTravel.setFarm_travel_title(farm_travel_title);
                farmTravel.setFarm_travel_img(farm_travel_img);
                farmTravel.setFarm_travel_info(farm_travel_info);
                farmTravel.setFarm_travel_start(farm_travel_start);
                farmTravel.setFarm_travel_end(farm_travel_end);
                farmTravel.setFarm_travel_fee(farm_travel_fee);
                farmTravel.setTravel_apply_start(travel_apply_start);
                farmTravel.setTravel_apply_end(travel_apply_end);
                farmTravel.setFarm_travel_min(farm_travel_min);
                farmTravel.setFarm_travel_max(farm_travel_max);

                if (!errorMsgs.isEmpty()) {  // 若以上格式有錯返回add頁面
                    request.setAttribute("farmTravel", farmTravel);
                    RequestDispatcher errView = request.getRequestDispatcher("/front-end/farmTravel/addFarmTravel.jsp");
                    errView.forward(request, response);
                    return;
                }
                try{  // 嘗試新增資料，若發生錯誤返回add頁面
                    FarmTravelService farmTravelService = new FarmTravelService();
                    farmTravel = farmTravelService.addFarmTravel(mem_ID, f_mem_ID, farm_travel_title, farm_travel_img, farm_travel_info, farm_travel_start, farm_travel_end, farm_travel_fee, travel_apply_start, travel_apply_end, farm_travel_min, farm_travel_max, tag_names);
                }catch(Exception e){
                    e.printStackTrace(System.err);
                    errorMsgs.add("新增失敗");
                    request.setAttribute("farmTravel", farmTravel);
                    RequestDispatcher errView = request.getRequestDispatcher("/front-end/farmTravel/addFarmTravel.jsp");
                    errView.forward(request, response);
                    return;
                }
                // 新增成功時跳轉至listALl頁面
                RequestDispatcher successView = request.getRequestDispatcher("/front-end/farmTravel/listAllFarmTravel.jsp");
                successView.forward(request, response);

            }catch(Exception e){  // 發生其他Error時
                e.printStackTrace(System.err);
                errorMsgs.add(e.getMessage());
                RequestDispatcher errView = request.getRequestDispatcher("/front-end/farmTravel/addFarmTravel.jsp");
                errView.forward(request, response);
            }
        }
        if("getOne".equals(action)) {
            try{
                // 接收請求參數，並做錯誤判斷
                Integer farm_travel_ID = Integer.valueOf(request.getParameter("farm_travel_ID"));

                // 開始查詢資料
                FarmTravelService farmTravelService = new FarmTravelService();
                FarmTravelVO farmTravel = farmTravelService.getOneFarmTravel(farm_travel_ID);
                FarmTravelTagService farmTravelTagService = new FarmTravelTagService();
                FarmTravelTagDetailsService farmTravelTagDetailsService = new FarmTravelTagDetailsService();
                List<FarmTravelTagVO> tagList = new ArrayList<>();
                List<FarmTravelTagDetailsVO> tagDetailsList = farmTravelTagDetailsService.getTagByFarmTravelID(farm_travel_ID);
                if (tagDetailsList != null) {
                    for (FarmTravelTagDetailsVO tagDetails : tagDetailsList) {
                        tagList.add(farmTravelTagService.getOneFarmTravelTag(tagDetails.getTag_ID()));
                    }
                }
                // 查詢完成，準備轉交
                request.setAttribute("farmTravel", farmTravel);
                request.setAttribute("tagList",tagList);

                RequestDispatcher successView = request.getRequestDispatcher("/front-end/farmTravel/listOneFarmTravel.jsp");
                successView.forward(request, response);

            }catch(Exception e){  // 發生其他Error時
                e.printStackTrace(System.err);
                RequestDispatcher errView = request.getRequestDispatcher("/front-end/farmTravel/listAllFarmTravel.jsp");
                errView.forward(request, response);
            }
        }
        if("getOneForUpdate".equals(action)) {
            try{
                // 接收請求參數，並做錯誤判斷
                Integer farm_travel_ID = Integer.valueOf(request.getParameter("farm_travel_ID"));

                // 開始查詢資料
                FarmTravelService farmTravelService = new FarmTravelService();
                FarmTravelVO farmTravel = farmTravelService.getOneFarmTravel(farm_travel_ID);
                FarmTravelTagService farmTravelTagService = new FarmTravelTagService();
                FarmTravelTagDetailsService farmTravelTagDetailsService = new FarmTravelTagDetailsService();
                List<FarmTravelTagVO> tagList = new ArrayList<>();
                List<FarmTravelTagDetailsVO> tagDetailsList = farmTravelTagDetailsService.getTagByFarmTravelID(farm_travel_ID);
                if (tagDetailsList != null) {
                    for (FarmTravelTagDetailsVO tagDetails : tagDetailsList) {
                        tagList.add(farmTravelTagService.getOneFarmTravelTag(tagDetails.getTag_ID()));
                    }
                }
                // 查詢完成，準備轉交
                request.setAttribute("farmTravel", farmTravel);
                request.setAttribute("tagList",tagList);

                RequestDispatcher successView = request.getRequestDispatcher("/front-end/farmTravel/updateFarmTravel.jsp");
                successView.forward(request, response);

            }catch(Exception e){  // 發生其他Error時
                e.printStackTrace(System.err);
                RequestDispatcher errView = request.getRequestDispatcher("/front-end/farmTravel/listAllFarmTravel.jsp");
                errView.forward(request, response);
            }
        }
        if("update".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            request.setAttribute("errorMsgs", errorMsgs);
            try {  // 接收參數並嘗試做錯誤判斷
                Integer farm_travel_ID = Integer.valueOf(request.getParameter("farm_travel_ID"));

                FarmTravelService farmTravelService = new FarmTravelService();  // 查DB取出此筆資料
                FarmTravelVO farmTravel = farmTravelService.getOneFarmTravel(farm_travel_ID);

                // 接收參數並嘗試做錯誤判斷
                String farm_travel_title = request.getParameter("farm_travel_title");
                if(farm_travel_title==null || farm_travel_title.trim().length()==0) { errorMsgs.add("請輸入農遊標題"); }
                else { farmTravel.setFarm_travel_title(farm_travel_title); }

                String farm_travel_info = request.getParameter("farm_travel_info");
                if(farm_travel_info==null || farm_travel_info.trim().length()==0) { errorMsgs.add("請輸入行程資訊"); }
                else{ farmTravel.setFarm_travel_info(farm_travel_info); }

                // 使用Part讀取圖片
                byte[] farm_travel_img = farmTravel.getFarm_travel_img();
                InputStream in = request.getPart("farm_travel_img").getInputStream();
                if (in.available() != 0){
                    farm_travel_img = new byte[in.available()];
                    in.read(farm_travel_img);
                    in.close();
                }
                // 串接並格式化日期
                java.sql.Timestamp farm_travel_start = null;
                try {
                    farm_travel_start = java.sql.Timestamp.valueOf(request.getParameter("farm_travel_start_date")+" "+request.getParameter("farm_travel_start_time"));
                    farmTravel.setFarm_travel_start(farm_travel_start);
                }catch (Exception e) { errorMsgs.add("請確認行程起日"); }

                java.sql.Timestamp farm_travel_end = null;
                try {
                    farm_travel_end = java.sql.Timestamp.valueOf(request.getParameter("farm_travel_end_date")+" "+request.getParameter("farm_travel_end_time"));
                    farmTravel.setFarm_travel_end(farm_travel_end);
                }catch (Exception e) { errorMsgs.add("請確認行程迄日"); }

                Integer farm_travel_fee = null;
                try {
                    farm_travel_fee = Integer.valueOf(request.getParameter("farm_travel_fee"));
                    farmTravel.setFarm_travel_fee(farm_travel_fee);
                }catch (Exception e) { errorMsgs.add("請確認農遊報名費用"); }

                java.sql.Timestamp  travel_apply_start = null;
                try {
                    travel_apply_start = java.sql.Timestamp.valueOf(request.getParameter("travel_apply_start")+" 00:00:00");
                    farmTravel.setTravel_apply_start(travel_apply_start);
                }catch (Exception e) { errorMsgs.add("請確認報名起日"); }

                java.sql.Timestamp  travel_apply_end = null;
                try {
                    travel_apply_end = java.sql.Timestamp.valueOf(request.getParameter("travel_apply_end")+" 23:59:59");
                    farmTravel.setTravel_apply_end(travel_apply_end);
                }catch (Exception e) { errorMsgs.add("請確認報名迄日"); }

                Integer farm_travel_min = null;
                try {
                    farm_travel_min = Integer.valueOf(request.getParameter("farm_travel_min"));
                    farmTravel.setFarm_travel_min(farm_travel_min);
                }catch (Exception e) { errorMsgs.add("請確認最少成團人數"); }

                Integer farm_travel_max = null;
                try {
                    farm_travel_max = Integer.valueOf(request.getParameter("farm_travel_max"));
                    farmTravel.setFarm_travel_max(farm_travel_max);
                }catch (Exception e) { errorMsgs.add("請確認最大滿額人數"); }

                if (!errorMsgs.isEmpty()) {  // 若以上格式有錯返回update頁面
                    request.setAttribute("farmTravel", farmTravel);
                    RequestDispatcher errView = request.getRequestDispatcher("/front-end/farmTravel/updateFarmTravel.jsp");
                    errView.forward(request, response);
                    return;
                }

                try{  // 嘗試更新資料，若發生錯誤返回update頁面
                    farmTravelService = new FarmTravelService();
                    farmTravel = farmTravelService.updateFarmTravel(
                            farm_travel_title, farm_travel_img, farm_travel_info, farm_travel_start, farm_travel_end, farm_travel_fee, travel_apply_start, travel_apply_end,
                            farm_travel_min, farm_travel_max, farmTravel.getFarm_travel_now(), farmTravel.getFarm_travel_state(), farmTravel.getFarm_travel_ID());
                }catch(Exception e){
                    e.printStackTrace(System.err);
                    errorMsgs.add("更新失敗");
                    request.setAttribute("farmTravel", farmTravel);
                    RequestDispatcher errView = request.getRequestDispatcher("/front-end/farmTravel/updateFarmTravel.jsp");
                    errView.forward(request, response);
                    return;
                }
                // 更新成功時跳轉至listALl頁面
                RequestDispatcher successView = request.getRequestDispatcher("/front-end/farmTravel/listAllFarmTravel.jsp");
                successView.forward(request, response);

            }catch(Exception e){  // 發生其他Error時
                e.printStackTrace(System.err);
                errorMsgs.add(e.getMessage());
                RequestDispatcher errView = request.getRequestDispatcher("/front-end/farmTravel/updateFarmTravel.jsp");
                errView.forward(request, response);
            }
        }
        if("getOneForApply".equals(action)) {
            try{
                // 接收請求參數，並做錯誤判斷
                Integer farm_travel_ID = Integer.valueOf(request.getParameter("farm_travel_ID"));

                // 開始查詢資料
                FarmTravelService farmTravelService = new FarmTravelService();
                FarmTravelVO farmTravel = farmTravelService.getOneFarmTravel(farm_travel_ID);

                // 查詢完成，準備轉交
                request.setAttribute("farmTravel", farmTravel);

                RequestDispatcher successView = request.getRequestDispatcher("/front-end/farmTravel/applyFarmTravel.jsp");
                successView.forward(request, response);

            }catch(Exception e){  // 發生其他Error時
                e.printStackTrace(System.err);
                RequestDispatcher errView = request.getRequestDispatcher("/front-end/farmTravel/listOneFarmTravel.jsp");
                errView.forward(request, response);
            }
        }
        if("delete".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            request.setAttribute("errorMsgs", errorMsgs);

            try {
                // 接收參數
                Integer farm_travel_ID = Integer.valueOf(request.getParameter("farm_travel_ID"));

                // 準備刪除
                FarmTravelService farmTravelService = new FarmTravelService();
                farmTravelService.deleteFarmTravel(farm_travel_ID);

                // 刪除完成，準備轉交
                RequestDispatcher sucessView = request.getRequestDispatcher("/front-end/farmTravel/listAllFarmTravel.jsp");
                sucessView.forward(request, response);

            }catch(Exception e){  // 發生其他Error時
                errorMsgs.add("刪除資料失敗:"+e.getMessage());
                RequestDispatcher errView = request.getRequestDispatcher("/front-end/farmTravel/listAllFarmTravel.jsp");
                errView.forward(request, response);
            }
        }
    }
}
