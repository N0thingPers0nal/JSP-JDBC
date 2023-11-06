<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<body>
<%
session.invalidate();
response.sendRedirect(request.getContextPath());
%>
</body>
</html>