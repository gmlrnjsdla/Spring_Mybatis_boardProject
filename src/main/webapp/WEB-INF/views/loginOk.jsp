<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공 여부</title>
</head>
<body>
	<%
		int checkId = Integer.parseInt(request.getAttribute("checkIdFlag").toString());
		int checkPw = Integer.parseInt(request.getAttribute("checkPwFlag").toString());
		
		if(checkId == 1){
			if(checkPw != 1){
				%>
				<script language="JavaScript">
					alert("비밀번호가 틀렸습니다. 다시 확인해주세요.")
					history.go(-1);
				</script>
				<%
			}
		}else{
			%>
			<script language="JavaScript">
				alert("없는 아이디입니다. 다시 확인해주세요.")
				history.go(-1);
			</script>
			<%
		}
				%>
			
		
</body>
</html>