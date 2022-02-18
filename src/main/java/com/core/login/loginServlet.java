package com.core.login;

import com.fMem.model.FMemVO;
import com.mem.model.MemVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login.do")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String farmerOrNot = request.getParameter("farmerOrNot");
        // 假會員
        if (farmerOrNot.equals("not")) {
            MemVO mem = new MemVO();
            mem.setMem_id(77000);
            mem.setMem_id_state(0);
            session.setAttribute("mem", mem);
            System.out.println("一般會員登入");
            request.getRequestDispatcher("/front-end/demo/memIndex.jsp").forward(request,response);
        }
        // 假小農
        if (farmerOrNot.equals("yes")) {
            FMemVO fMem = new FMemVO();
            fMem.setF_mem_id(70000);
            fMem.setMem_id(77002);
            session.setAttribute("FMem", fMem);
            System.out.println("小農會員登入");
            request.getRequestDispatcher("/front-end/demo/fMemIndex.jsp").forward(request,response);
        }
    }
}
