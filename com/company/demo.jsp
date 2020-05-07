<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>demo.jsp</title>
</head>
<body>
<c:choose>
    <%--注意：如果登陆成功，Session会存储一个登录成功的信息，名字为loginUser;--%>
    <c:when test="${loginUser!=null}">
        <%--已经登录--%>
        <h4>欢迎：${loginUser.userName}</h4>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
    </c:otherwise>
</c:choose>

<h2>商品列表</h2>
</body>
</html>
