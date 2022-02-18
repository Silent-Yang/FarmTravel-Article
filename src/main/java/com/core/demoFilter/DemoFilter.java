package com.core.demoFilter;

import com.fMem.model.FMemVO;
import com.mem.model.MemVO;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")  // 過濾所有的請求資源路徑
public class DemoFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final HttpSession session = httpServletRequest.getSession();

        // 設定編碼
        request.setCharacterEncoding("UTF-8");

        // 放行
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
