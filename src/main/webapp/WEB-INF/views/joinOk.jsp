<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
</head>
<body>
	<%
		int checkId = Integer.parseInt(request.getAttribute("checkIdFlag").toString());
		if(checkId == 1){
			%>
			<script language="JavaScript">
				alert("입력하신 아이디는 이미 사용중입니다. 다른아이디를 입력해주세요!")
				history.go(-1);
			</script>
			<%
		}
			%>
		<h2>회원가입을 축하합니다!</h2>
		<h2>${mname}님 반갑습니다.</h2>
		<a href="list">메인화면으로 가기</a>
		
</body>
</html>