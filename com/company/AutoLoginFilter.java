package com.company;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AutoLoginFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        /*
        HttpServletRequest是属于ServletRequest的子接口， 所以HttpServletRequest的方法肯定要比ServletRequest的要多。
        这里的强制类型转换的目的就是为了使用子接口的方法，之所以可以强制类型转换是因为Tomcat本身传递过来的就是HttpServletRequest。
        */
        //1. 检查用户是否在Session有登录成功标记
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            //2. 如果用户还没有登录，那么就读取Cookie的信息
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                //遍历所有的Cookie，找到AutoLogin的Cookie
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("autoLogin")) {
                        //获取用户名与密码
                        String[] arrayInfo = cookie.getValue().split("_"); //Regino_123
                        String userName = arrayInfo[0];
                        String pasword = arrayInfo[1];
                        if (userName.equals("Regino") && pasword.equals("123")) {
                            //登录成功
                            session.setAttribute("loginUser", new User(userName, pasword));
                        }
                    }
                }
            }
        }
        //不管用户是否已经登陆，都需要放行
        chain.doFilter(request, resp);
    }

    public void destroy() {

    }
}
