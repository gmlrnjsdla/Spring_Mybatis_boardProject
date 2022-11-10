<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용 보기</title>
</head>
<body>
	<h2>글 내용 보기</h2>
	<hr>
	<table width = "500" border = "1" cellspacing="0" cellpadding ="5">
		<form action="modifyOk">
		<input type="hidden" name="fnum" value="${content.fnum}">
		<tr>
			<th width="100" bgcolor="skyblue">글번호</th>
			<td>${content.fnum}</td>
		</tr>
		<tr>
			<th bgcolor="skyblue">조회수</th>
			<td>${content.fhit}</td>
		</tr>
		<tr>
			<th bgcolor="skyblue">글쓴이</th>
			<td>${content.fname}</td>
		</tr>
		<tr>
			<th bgcolor="skyblue">아이디</th>
			<td>${content.fid}</td>
		</tr>
		<tr>
			<th bgcolor="skyblue">글제목</th>
			<td><input type="text" name="ftitle" value="${content.ftitle}"></td>
		</tr>
		<tr>
			<th valign ="top" bgcolor="skyblue">글내용</th>
			<td valign ="top" height ="100">
			<textarea rows="10" cols="40" name="fcontent">${content.fcontent}</textarea>
			</td>
		</tr>
		<tr>
			<th bgcolor="skyblue">등록일</th>
			<td>${content.fdate}</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="수정 완료" >
				<input type="button" value="삭제" onclick="jaavascript:window.location='delete?fnum=${content.fnum}'">
				<input type="button" value="목록" onclick="jaavascript:window.location='list'">
			</td>
		</tr>
		</form>
	</table>
</body>
</html>