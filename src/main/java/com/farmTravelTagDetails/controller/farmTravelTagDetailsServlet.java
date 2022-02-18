package com.farmTravelTagDetails.controller;

import com.farmTravelTag.model.FarmTravelTagService;
import com.farmTravelTag.model.FarmTravelTagVO;
import com.farmTravelTagDetails.model.FarmTravelTagDetailsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/farmTravelTagDetails.do")
public class farmTravelTagDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)){
            Integer farm_travel_ID = Integer.valueOf(request.getParameter("farm_travel_ID"));
            String tag_name = request.getParameter("tag_name");
            FarmTravelTagService farmTravelTagService = new FarmTravelTagService();
            FarmTravelTagVO farmTravelTag = farmTravelTagService.getOneFarmTravelTag(tag_name);
            FarmTravelTagDetailsService farmTravelTagDetailsService = new FarmTravelTagDetailsService();
            farmTravelTagDetailsService.addFarmTravelTagDetails(farm_travel_ID, farmTravelTag.getTag_ID());
        }
        if ("delete".equals(action)){
            Integer farm_travel_ID = Integer.valueOf(request.getParameter("farm_travel_ID"));
            String tag_name = request.getParameter("tag_name");
            FarmTravelTagService farmTravelTagService = new FarmTravelTagService();
            FarmTravelTagVO farmTravelTag = farmTravelTagService.getOneFarmTravelTag(tag_name);
            FarmTravelTagDetailsService farmTravelTagDetailsService = new FarmTravelTagDetailsService();
            farmTravelTagDetailsService.deleteFarmTravelTagDetails(farm_travel_ID, farmTravelTag.getTag_ID());
        }
    }
}
