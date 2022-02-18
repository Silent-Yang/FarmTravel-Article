package com.farmTravelCollection.controller;

import com.farmTravelCollection.model.FarmTravelCollectionService;
import com.mem.model.MemVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet("/farmTravelCollection.do")
public class FarmTravelCollectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/gif");
        HttpSession session = request.getSession();
        ServletOutputStream out = response.getOutputStream();
        String action = request.getParameter("action");
        boolean collection ;

        if ("collection".equals(action)){
            Integer mem_ID = ((MemVO)(session.getAttribute("mem"))).getMem_id();
            Integer farm_travel_ID = Integer.valueOf(request.getParameter("farm_travel_ID"));

            FarmTravelCollectionService farmTravelCollectionService = new FarmTravelCollectionService();
            collection = farmTravelCollectionService.getOneFarmTravelCollection(mem_ID, farm_travel_ID);

            InputStream inputStream;

            if (collection){
                inputStream = getServletContext().getResourceAsStream("/front-end/farmTravel/images/Hearted.png");
                byte[] image = new byte[inputStream.available()];
                inputStream.read(image);
                out.write(image);
            }else{
                inputStream = getServletContext().getResourceAsStream("/front-end/farmTravel/images/Heart.png");
                byte[] image = new byte[inputStream.available()];
                inputStream.read(image);
                out.write(image);
            }
            inputStream.close();
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        boolean collected ;

        if ("collection".equals(action)){

            Integer mem_ID = ((MemVO)(session.getAttribute("mem"))).getMem_id();
            Integer farm_travel_ID = Integer.valueOf(request.getParameter("farm_travel_ID"));

            FarmTravelCollectionService farmTravelCollectionService = new FarmTravelCollectionService();
            collected = farmTravelCollectionService.getOneFarmTravelCollection(mem_ID, farm_travel_ID);

            if (collected){
                farmTravelCollectionService.deleteFarmTravelCollection(mem_ID, farm_travel_ID);
                out.print("0");
            }else{
                farmTravelCollectionService.addFarmTravelCollection(mem_ID, farm_travel_ID);
                out.print("1");
            }
        }
    }
}
