<%@ page contentType="text/html;charset=euc-kr" %>

<html>
	<head><title>Test Page</title></head>
	<body>
		
	<a href = "/Spring14/user/getUser/user01">/Spring14/user/getUser/user01</a><br/><br/>
		
	<!-- Client Form Data 를 Server Servlet 으로 Post방식 전송 -->
	<form method="Post" action='/Spring14/user/getUser/user01'>

		아이디 : <input type='text' name='userId'><br/>
		이 &nbsp; 름 : <input type='text' name='userName'><br/><p/>
		
		<input type='submit' value='전송' />
		<input type='reset' value='취소' />

	</form>
		
		아이디(user.userId) : ${user.userId}<br/>
		이   름(user.userName) : ${user.userName}<br/><br/>

	<br/><br/>
		
	</body>
</html>