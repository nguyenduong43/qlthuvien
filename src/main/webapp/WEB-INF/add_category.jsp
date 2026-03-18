<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17/03/2026
  Time: 3:31 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add category</h1>
<p id="errorMessage" class="error">${message}</p>
<form method="post" >
    <tr>
        <td>Name category</td>
        <td><input type="text" name="name"></td>
        <td><input type="submit" value="Add"></td>
    </tr>
</form>
</body>
</html>
