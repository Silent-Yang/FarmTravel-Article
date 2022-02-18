package com.farmTravelTag.controller;

import com.farmTravelTag.model.FarmTravelTagService;
import com.farmTravelTag.model.FarmTravelTagVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/farmTravelTag.do")
public class farmTravelTagServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String tag_name = request.getParameter("tag_name");

        if ("insert".equals(action)){
            FarmTravelTagService farmTravelTagService = new FarmTravelTagService();
            FarmTravelTagVO farmTravelTag = farmTravelTagService.getOneFarmTravelTag(tag_name);
            if (farmTravelTag == null){
                farmTravelTagService.addFarmTravelTag(tag_name);
            }else {
                farmTravelTagService.updateFarmTravelTag(farmTravelTag);
            }
        }
    }
}
