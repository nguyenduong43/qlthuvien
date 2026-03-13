<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

    <style>
        body{
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg,#74ebd5,#ACB6E5);
            height:100vh;
            display:flex;
            justify-content:center;
            align-items:center;
        }

        form{
            background:white;
            padding:30px 40px;
            border-radius:10px;
            box-shadow:0 10px 25px rgba(0,0,0,0.2);
        }

        table{
            border-collapse: collapse;
        }

        td{
            padding:10px;
            font-size:16px;
        }

        input[type="text"],input[type="password"]{
            width:200px;
            padding:8px;
            border:1px solid #ccc;
            border-radius:5px;
        }

        input[type="submit"]{
            background:#4CAF50;
            color:white;
            border:none;
            padding:8px 15px;
            border-radius:5px;
            cursor:pointer;
        }

        input[type="submit"]:hover{
            background:#45a049;
        }

        a{
            margin-left:10px;
            text-decoration:none;
            color:#2196F3;
            font-weight:bold;
        }

        a:hover{
            text-decoration:underline;
        }

        .message{
            color:red;
            margin-top:10px;
            text-align:center;
        }
    </style>
</head>

<body>

<form method="post" action="/login">
    <table>
        <tr>
            <td>Username</td>
            <td><input type="text" name="username"></td>
        </tr>

        <tr>
            <td>Password</td>
            <td><input type="password" name="password"></td>
        </tr>

        <tr>
            <td colspan="2" style="text-align:center;">
                <input type="submit" value="Login">
                <a href="/register">Register</a>
            </td>
        </tr>
    </table>

    <div class="message">
        <c:out value="${message}"></c:out>
    </div>
</form>

</body>
</html>