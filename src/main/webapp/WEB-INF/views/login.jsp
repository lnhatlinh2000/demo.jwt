<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h3>Login Form</h3>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="username">Username:</label>
    <input type="text" name="username" id="username">
    <br>
    <label for="password">Password:</label>
    <input type="text" name="password" id="password">
    <br>
    <input type="submit" value="Login">
</form>
</body>
</html>