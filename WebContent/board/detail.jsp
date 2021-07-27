<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/layout.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/board.css" />
<script>
	function clickBtn(type) {
		switch(type) {
		case 'U' : 
			location.href = 'updateForm.jsp?no=${ param.no }'
			break
		case 'D' : 
			if(confirm('[${param.no}]번 게시글을 삭제할까요?'))
				location.href = 'delete.jsp?no=${ param.no }'
			break
		case 'L' :
			location.href = "list.jsp"
			break
		}
	}
</script>
</head>
<body>
	<header>
		<%-- <jsp:include page="/jsp/include/topMenu.jsp" /> --%>
	</header>
	<section>
		<div align="center">
		<hr width="80%">
		<h2>게시판 상세</h2>
		<hr width="80%">
		<br>
		<table border="1" style="width: 80%">
			<tr>
				<th width="25%">번호</th>
				<td><c:out value="${ board.no }"/></td>
			</tr>
			<tr>
				<th width="25%">제목</th>
				<td><c:out value="${ board.title }" /></td>
			</tr>
			<tr>
				<th width="25%">작성자</th>
				<td>${ board.writer }</td>
			</tr>
			<tr>
				<th width="25%">내용</th>
				<td>${ board.content }</td>
			</tr>
			<tr>
				<th width="25%">조회수</th>
				<td>${ board.viewCnt }</td>
			</tr>
			<tr>
				<th width="25%">등록일</th>
				<td>${ board.regDate }</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:forEach items="${ fileList }" var="file">
						<a href="/Mission-Web/upload/${ file.fileSaveName }">
							<c:out value="${ file.fileOriName }" />
						</a>
						(${ file.fileSize } bytes)
						<br>
					</c:forEach>
				</td>
			</tr>
		</table>
		<br>
		<button onclick="clickBtn('U')">수정</button>
		<button onclick="clickBtn('D')">삭제</button>
		<button onclick="clickBtn('L')">목록</button>
	</div>
	</section>
	<footer>
		<%-- <%@ include file="/jsp/include/bottom.jsp" %> --%>
	</footer>
</body>
</html>