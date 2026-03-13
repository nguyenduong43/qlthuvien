<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>

    <style>

        body{
            font-family: Arial;
            height:100vh;
            display:flex;
            justify-content:center;
            align-items:center;
            background: linear-gradient(to right,#6dd5ed,#8e9eab);
        }

        .box{
            background:#f3f3f3;
            padding:30px;
            border-radius:10px;
            box-shadow:0 0 10px rgba(0,0,0,0.2);
            width:350px;
        }

        h2{
            text-align:center;
            margin-bottom:20px;
        }

        .form-group{
            margin-bottom:12px;
        }

        label{
            display:block;
            margin-bottom:4px;
        }

        input,select{
            width:100%;
            padding:8px;
            border:1px solid #ccc;
            border-radius:5px;
        }

        button{
            width:100%;
            padding:8px;
            background:#4CAF50;
            color:white;
            border:none;
            border-radius:5px;
            cursor:pointer;
        }

        button:hover{
            background:#45a049;
        }

        .login-link{
            text-align:center;
            margin-top:10px;
        }

        .login-link a{
            text-decoration:none;
            color:#007bff;
        }

        .message{
            color:red;
            text-align:center;
            margin-bottom:10px;
            font-weight:bold;
        }

        .error{
            color:red;
            font-size:13px;
        }

    </style>

</head>

<body>

<div class="box">

    <h2>Register</h2>

    <div class="message">
        <c:out value="${sessionScope.message}" />
    </div>
    <c:remove var="message" scope="session"/>

    <form action="/register" method="post">

        <div class="form-group">
            <label>Name</label>
            <input type="text" name="name" id="name">
            <div class="error" id="nameError"></div>
        </div>

        <div class="form-group">
            <label>Birthday</label>
            <input type="date" name="date" id="date">
            <div class="error" id="dateError"></div>
        </div>

        <div class="form-group">
            <label>Email</label>
            <input type="text" name="email" id="email">
            <div class="error" id="emailError"></div>
        </div>

        <div class="form-group">
            <label>Phone</label>
            <input type="text" name="phone" id="phone">
            <div class="error" id="phoneError"></div>
        </div>

        <div class="form-group">
            <label>Image</label>
            <input type="text" name="image" id="image">
        </div>

        <div class="form-group">
            <label>Username</label>
            <input type="text" name="username" id="username">
            <div class="error" id="usernameError"></div>
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" id="password">
            <div class="error" id="passwordError"></div>
        </div>

        <div class="form-group">
            <label>Position</label>
            <select name="position">
                <option value="user">User</option>
                <option value="librarian">Librarian</option>
                <option value="admin">Admin</option>
            </select>
        </div>

        <button type="submit">Register</button>

    </form>

    <div class="login-link">
        <a href="/lib">Back to Home</a>
    </div>

</div>

<script>

    document.querySelector("form").addEventListener("submit", function(e){

        let valid=true;

        let name=document.getElementById("name").value.trim();
        let email=document.getElementById("email").value.trim();
        let phone=document.getElementById("phone").value.trim();
        let username=document.getElementById("username").value.trim();
        let password=document.getElementById("password").value.trim();
        let date=document.getElementById("date").value.trim();

        document.getElementById("nameError").innerHTML="";
        document.getElementById("emailError").innerHTML="";
        document.getElementById("phoneError").innerHTML="";
        document.getElementById("usernameError").innerHTML="";
        document.getElementById("passwordError").innerHTML="";
        document.getElementById("dateError").innerHTML="";

        if(name===""){
            document.getElementById("nameError").innerHTML="Name không được để trống";
            valid=false;
        }

        if(date===""){
            document.getElementById("dateError").innerHTML="Birthday không được để trống";
            valid=false;
        }

        let emailRegex=/^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if(!emailRegex.test(email)){
            document.getElementById("emailError").innerHTML="Email không đúng định dạng";
            valid=false;
        }

        let phoneRegex=/^[0-9]{10}$/;

        if(!phoneRegex.test(phone)){
            document.getElementById("phoneError").innerHTML="SĐT phải gồm 10 chữ số";
            valid=false;
        }

        if(username===""){
            document.getElementById("usernameError").innerHTML="Username không được để trống";
            valid=false;
        }

        if(password===""){
            document.getElementById("passwordError").innerHTML="Password không được để trống";
            valid=false;
        }

        if(!valid){
            e.preventDefault();
        }
    });
</script>

</body>
</html>