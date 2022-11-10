<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
	<h2>회원 정보 수정</h2>
	<hr>
	<table width = "500" border = "1" cellspacing="0" cellpadding ="5">
		<form action="modifyMemberOk">
		<input type="hidden" name="mid" value="${minfo.mid}">
		<tr>
			<th width="100" bgcolor="skyblue">아이디</th>
			<td>${minfo.mid}</td>
		</tr>
		<tr>
			<th bgcolor="skyblue">비밀번호</th>
			<td><input type="password" name="mpw" value="${minfo.mpw}"></td>
		</tr>
		<tr>
			<th bgcolor="skyblue">이름</th>
			<td><input type="text" name="mname" value="${minfo.mname}"></td>
		</tr>
		<tr>
			<th bgcolor="skyblue">이메일</th>
			<td><input type="text" name="memail" value="${minfo.memail}"></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="수정완료">
				<input type="button" value="회원탈퇴" onclick="javascript:window.location='deleteMember?mid=${minfo.mid}'">
				<input type="button" value="목록" onclick="jaavascript:window.location='list'">
			</td>
		</tr>
		</form>
	</table>
</body>
</html>