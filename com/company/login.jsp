<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login.jsp</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/loginServlet" method="post">
    用户名：<input type="text" name="userName"/><br/>
    密码：<input type="password" name="password"/><br/>
    七天免登陆<input type="checkbox" name="auto" value="true"/>
    <input type="submit" value="登陆"/>
</form>
</body>
</html>
