package com.company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取用户名与密码
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        //2. 校验用户名与密码
        if (userName.equals("Regino") && password.equals("123")) {
            //正确用户名与密码
            //3. 检查是否要七天免登陆
            String auto = request.getParameter("auto");//如果复选框被选中，那么该值就不为null
            if (auto == null) {
                //4. 用户不需要七天免登录, 清楚以前Cookie信息
                Cookie cookie = new Cookie("autoLogin", "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);

            } else {
                //5.用户需要七天免登录, 用户名与密码已经保存到Cookie中
                Cookie cookie = new Cookie("autoLogin", userName + "_" + password);
                cookie.setMaxAge(60 * 60 * 24 * 7); //七天
                response.addCookie(cookie);
            }

            //6.不管是否要七天免登录，那么登陆成功我们都需要在Session中做一个登陆成功标记
            request.getSession().setAttribute("loginUser", new User(userName, password));
            //跳转到首页
            response.sendRedirect(request.getContextPath() + "/demo.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
