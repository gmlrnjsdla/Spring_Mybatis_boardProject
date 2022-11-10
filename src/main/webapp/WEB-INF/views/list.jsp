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
	<h3><${mname}></h3>
	
	<%
	//Session을 받을때는 값이 null로 올때를 생각해서 조건문을 사용한다.
	if (session.getAttribute("ValidMem") != null) {
	%>
		<input type="button" value="로그아웃" onclick="javascript:window.location='logout'">&nbsp;&nbsp;
		<input type="button" value="정보수정" onclick="javascript:window.location='modifyMember?mid=${mid}'">
		<br><br>
		
	<%
	}else{
	%>
		<input type="button" value="로그인" onclick="javascript:window.location='login'">&nbsp;&nbsp;	
		<input type="button" value="회원가입" onclick="javascript:window.location='joinMember'"><br><br>
	<%
	}
	%>
	
	총 게시글 개수 : ${count}개
	<table width="800" border="1" cellpadding="1" cellspacing="0">
		<tr height="30">
			<th bgcolor="skyblue">번호</th>
			<th bgcolor="skyblue">아이디</th>
			<th bgcolor="skyblue">글쓴이</th>
			<th width="400" bgcolor="skyblue">제 목</th>
			<th bgcolor="skyblue">조회수</th>
			<th bgcolor="skyblue">등록일</th>
		</tr>
		<c:forEach items="${list}" var="fbdto">
			<tr height="30"  align="center">
				<td>${fbdto.fnum}</td>
				<td>${fbdto.fid}</td>
				<td>${fbdto.fname}</td>
				<td align="left"><a href="contentView?fnum=${fbdto.fnum}">${fbdto.ftitle}</a></td>
				<td>${fbdto.fhit}</td>
				<td>${fbdto.fdate}</td>
			</tr>
		</c:forEach>
		<tr height="30">
			<td colspan="6" align="right"><input type="button" value="글쓰기" onclick="javascript:window.location='writeForm'"></td>
		</tr>
	</table>
</body>
</html>