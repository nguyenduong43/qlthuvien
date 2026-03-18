<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/03/2026
  Time: 10:58 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>

        body{
            font-family: Arial;
            height:100vh;
            display:flex;
            justify-content:center;
            align-items:center;
            background: linear-gradient(to right,#6dd5ed,#8e9eab);
        }

        form{
            background:#f3f3f3;
            padding:30px;
            border-radius:10px;
            box-shadow:0 0 10px rgba(0,0,0,0.2);
        }

        table{
            border-collapse: collapse;
        }

        td{
            padding:10px;
        }

        input[type="text"],
        input[type="date"]{
            width:200px;
            padding:6px;
            border:1px solid #ccc;
            border-radius:5px;
        }

        input[type="submit"]{
            padding:8px 20px;
            background:#4CAF50;
            color:white;
            border:none;
            border-radius:5px;
            cursor:pointer;
        }

        input[type="submit"]:hover{
            background:#45a049;
        }

    </style>
</head>
<body>
<form  method="post" onsubmit="return validateForm()">
    <table border="1">
        <tr>
            <td>Ho va ten</td>
            <td>
                <input type="text" name="name" value="${user.getName()}">
            </td>
        </tr>
        <tr>
            <td>Ngay sinh</td>
            <td>
                <input type="date" name="date" value="${user.getBirthday()}">
            </td>
        </tr>
        <tr>
            <td>Email</td>
            <td>
                <input type="text" name="email" value="${user.getEmail()}" readonly>
            </td>
        </tr>
        <tr>
            <td>Phone</td>
            <td>
                <input type="text" name="phone" id="phone" value="${user.getPhone()}">
                <div id="phoneError" style="color:red;font-size:13px;"></div>
            </td>
        </tr>
        <tr>
            <td>image</td>
            <td>
                <input type="text" name="image" value="${user.getImage()}">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Update">
            </td>
        </tr>
    </table>
</form>
<script>

    function validateForm(){

        let phone = document.getElementById("phone").value;
        let phoneError = document.getElementById("phoneError");

        let regex = /^0\d{9}$/;

        if(!regex.test(phone)){
            phoneError.innerHTML = "Số điện thoại phải gồm 10 số và bắt đầu bằng 0";
            return false;
        }

        phoneError.innerHTML = "";
        return true;
    }

</script>
</body>
</html>
