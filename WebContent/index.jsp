<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href ="<%= request.getContextPath() %>/board/list.do">게시판</a>
<a href ="<%= request.getContextPath() %>/board/writeForm.do">새글등록</a>
<a href ="<%= request.getContextPath() %>/board/login.do">로그인</a>
<a href ="<%= request.getContextPath() %>/board/logout.do">로그아웃</a>
</body>
</html>