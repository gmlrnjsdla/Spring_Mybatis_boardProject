<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
</head>
<body>
	<h2>글 목록</h2>
	<hr>
	
	<table width="1000" border="1" cellpadding="0" cellspacing="0">
		<tr height="30">
			<th bgcolor="skyblue">번호</th>
			<th bgcolor="skyblue">아이디</th>
			<th bgcolor="skyblue">글쓴이</th>
			<th width="600" bgcolor="skyblue">제 목</th>
			<th bgcolor="skyblue">조회수</th>
			<th bgcolor="skyblue">등록일</th>
		</tr>
		<c:forEach items="${list}" var="fbdto">
			<tr height="30">
				<td>${fbdto.fnum}</td>
				<td>${fbdto.fid}</td>
				<td>${fbdto.fname}</td>
				<td>${fbdto.ftitle}</td>
				<td>${fbdto.fhit}</td>
				<td>${fbdto.fdate}</td>
			</tr>
		</c:forEach>
		<tr height="30">
			<td colspan="6" align="right"><a href="writeForm">글쓰기</a></td>
		</tr>
	</table>
</body>
</html>